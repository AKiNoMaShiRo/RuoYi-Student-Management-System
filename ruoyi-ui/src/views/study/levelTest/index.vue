<template>
  <div class="app-container">
    <div class="am-box">
      <section v-if="roleName !== '学生'" class="am-px am-bd-b">
        <ol class="am-py am-tabs-inline am-no-shrink">
          <li :class="{ active: activeIndex === 'table' }" @click="handleTabClick('table')">
            <span>成绩列表</span>
          </li>
          <li :class="{ active: activeIndex === 'chart' }" @click="handleTabClick('chart')">
            <span>通过率图表</span>
          </li>
        </ol>
      </section>
      <!-- 表格展示 -->
      <section v-show="activeIndex === 'table'">
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
              <el-select v-model="searchFormData.testType" size="small" clearable>
                <el-option label="大学英语CET4" value="大学英语CET4"></el-option>
                <el-option label="大学英语CET6" value="大学英语CET6"></el-option>
                <el-option label="普通话等级考试" value="普通话等级考试"></el-option>
                <el-option label="计算机等级考试二级" value="计算机等级考试二级"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="是否通过" prop="isPass">
              <el-select v-model="searchFormData.isPass" size="small" clearable>
                <el-option label="通过" value="yes"></el-option>
                <el-option label="未通过" value="no"></el-option>
              </el-select>
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
            class="am-mt"
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
      <!-- 图表展示 -->
    </div>
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
      activeIndex: 'table',
      searchFormData: {
        studentId: '',
        testType: '',
        isPass: '',
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
        { label: '姓名', prop: 'stuName', minWidth: '90' },
        { label: '专业', prop: 'profession', minWidth: '110' },
        { label: '年级', prop: 'grade', minWidth: '60' },
        { label: '班级序号', prop: 'classNum', minWidth: '80' },
        { label: '考试类别', prop: 'testType', minWidth: '110' },
        { label: '考试成绩', prop: 'testGrade', minWidth: '90' },
        { label: '是否通过', prop: 'isPass', minWidth: '100' },
        { label: '获得成绩时间', prop: 'testTime', minWidth: '100' },
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
    LEVELTEST.getPassRateByLT({testType: '大学英语CET6'}).then( res => {
      if (res && res.data) {
        console.log(res.data)
      }
    })
  },
  methods: {
    getInfo () {
      this.tableLoading = true
      let param = {}
      this.roleName === '学生' ? param.studentId = this.userName : this.roleName === '辅导员' ? param.instructorId = this.userName : param.instructorId = ''
      for (let key in this.searchFormData) {
        if (this.searchFormData[key] !== ''){
          param[key] = this.searchFormData[key]
        }
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
    handleTabClick (str) {
      this.activeIndex = str
    },
    //    搜索按钮
    handleSearch () {
      this.getInfo()
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
      } else if (prop === 'testTime') {
        return moment(val).format('yyyy-MM')
      } else if (prop === 'isPass') {
        return val === 'yes' ? '通过' : '未通过'
      } else {
        return val
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/assets/styles/variables.scss';
.am-tabs-inline {
  display: flex;
  overflow: hidden;
  padding-inline-start: 12px;
  margin: 0px;
  li {
    margin-right: $gap-l;
    font-size: 14px;
    font-weight: 500;
    color: #455a74;
    list-style: none;
    span {
      display: inline-block;
      border-bottom: 2px solid transparent;
      cursor: pointer;
      white-space: nowrap;
    }
    &.active {
      span {
        color: $menuActiveText;
        border-color: $menuActiveText;
        pointer-events: none;
      }
    }
    &:hover {
      span {
        color: $menuActiveText;
      }
    }
  }
}
</style>