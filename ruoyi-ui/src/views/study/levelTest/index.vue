<template>
  <div class="app-container">
    <section class="am-box">
      <div class="am-p am-title am-bd-b">等级考试成绩</div>
      <div class="am-p">
        <el-form
          v-if="roleName !== '学生'"
          ref="searchForm"
          label-width="80px"
          :model="searchFormData"
          :rules="searchRules"
          inline
        >
          <el-form-item label="学号" prop="studentId">
            <el-input v-model="searchFormData.studentId" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="考试类别" prop="testType">
            <el-input v-model="searchFormData.testType" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="考试成绩" prop="testGrade">
            <el-input v-model="searchFormData.testGrade" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="profession">
            <el-input v-model="searchFormData.profession" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="年级" prop="grade">
            <el-input v-model.number="searchFormData.grade" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="班级" prop="classNum">
            <el-input v-model.number="searchFormData.classNum" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
          </el-form-item>
        </el-form>
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          height="382px"
          highlight-current-row
        >
          <el-table-column v-for="col in tableColumns" v-bind="col" :key="col.prop" show-overflow-tooltip>
            <template slot-scope="scope">
              {{ scope.row[col.prop] | tableFormatter(col.prop) }}
            </template>
          </el-table-column>
        </el-table>
        <Pagination
          :total="total"
          :page-range="[10, 15, 20]"
          :current-page="currentPage"
          :page-size="pageSize"
          @onPaginationUpdate="handlePaginationUpdate"
        >
        </Pagination>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import * as LEVELTEST from '@/api/grade/levelTest.js'
import Pagination from '../../components/Pagination.vue'
import moment from 'moment'

export default {
  components: { Pagination },
  data () {
    return {
      searchFormData: {
        studentId: '',
        testType: '',
        testGrade: '',
        profession: '',
        grade: '',
        classNum: ''
      },
      searchRules: {
        grade: [ {type: 'number', message: '年级必须为数字值'} ],
        classNum: [ {type: 'number', message: '班级序号必须为数字值'} ],
      },
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableData: [],
      tableColumns: [
        { label: '学号', prop: 'studentId', minWidth: '110' },
        { label: '考试类别', prop: 'testType', minWidth: '110' },
        { label: '考试成绩', prop: 'testGrade', minWidth: '90' },
        { label: '获得成绩时间', prop: 'passTime', minWidth: '100' },
        { label: '备注', prop: 'remark', minWidth: '120' }
      ]
    }
  },
  computed: {
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    })
  },
  created () {
      this.getInfo()
  },
  methods: {
    getInfo () {
      this.tableLoading = true
      if (this.roleName === '学生') {
        LEVELTEST.getByStuId({ studentId: this.userName }).then( res => {
          if (res.rows && res.rows.length !== 0) {
            this.tableData = res.rows
            this.total = res.total
          } else {
            this.tableData = []
            this.total = res.total
          }
        }).finally( () => {
          this.tableLoading = false
        })
      } else {
        let param = {}
        for (let key in this.searchFormData) {
          param[key] = this.searchFormData[key]
        }
        if (param.grade === '') {
          param.grade = 0
        }
        if (param.classNum === '') {
          param.classNum = 0
        }
        LEVELTEST.getByLT(param).then( res => {
          if (res.rows && res.rows.length !== 0) {
            this.tableData = res.rows
            this.total = res.total
          } else {
            this.tableData = []
            this.total = res.total
          }
        }).finally( () => {
          this.tableLoading = false
        })
      }
    },
    //    搜索按钮
    handleSearch () {
      this.tableLoading = true
      let param = {}
      for (let key in this.searchFormData) {
        param[key] = this.searchFormData[key]
      }
      if (param.grade === '') {
        param.grade = 0
      }
      if (param.classNum === '') {
        param.classNum = 0
      }
      LEVELTEST.getByLT(param).then( res => {
        if (res.rows && res.rows.length !== 0) {
          this.tableData = res.rows
          this.total = res.total
        } else {
          this.tableData = []
          this.total = res.total
        }
      }).finally( () => {
        this.tableLoading = false
      })
    },
    //    搜索表单重置按钮
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
      this.getInfo()
    }
  },
  filters: {
    tableFormatter (val, prop) {
      if (val === '' || val === null || val === undefined) {
        return '--'
      } else if (prop === 'passTime') {
        return moment(val).format('yyyy-MM')
      } else {
        return val
      }
    }
  }
}
</script>