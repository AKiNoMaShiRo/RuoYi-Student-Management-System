<template>
  <div class="am-p">
    <el-form
      ref="searchForm"
      label-width="80px"
      :model="searchFormData"
      :rules="searchRules"
      inline
    >
      <el-form-item label="学年" prop="learnYear">
        <el-select v-model="searchFormData.learnYear">
          <el-option
            v-for="opt in termOpts"
            :key="opt.label"
            :value="opt.value"
            :label="opt.label"
            >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学期" prop="learnTerm">
        <el-select v-model="searchFormData.learnTerm">
          <el-option label="上" :value="1"></el-option>
          <el-option label="下" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程类别" prop="courseType">
        <el-select v-model="searchFormData.courseType" clearable>
          <el-option
            v-for="opt in courseTypeOpts"
            :key="opt.label"
            :value="opt.value"
            :label="opt.label"
            >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label=" ">
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="tableLoading"
      :data="tableData"
      :height="tableHeight"
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
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '@/views/components/Pagination';
import { learnYearOptions, COURSETYPE } from '@/libs/utils.js'
import * as COURSEGRADE from '@/api/grade/courseGrade.js';

export default {
  components: { Pagination },
  data () {
    return {
      courseTypeOpts: COURSETYPE,
      searchFormData: {
        learnYear: '',
        learnTerm: '',
        courseType: '',
      },
      searchRules: {
        learnYear: [ {required: true, message: '请选择学年', trigger: 'blur'} ],
        learnTerm: [ {required: true, message: '请选择学期', trigger: 'blur'} ]
      },
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableData: [],
      tableColumns: [
        // { prop: 'studentId', label: '学号',  minWidth: '100'},
        // { prop: 'stuName', label: '姓名',  minWidth: '100'},
        { prop: 'learnYear', label: '学年',  minWidth: '80'},
        { prop: 'learnTerm', label: '学期',  minWidth: '60'},
        { prop: 'courseType', label: '课程类别',  minWidth: '80'},
        { prop: 'courseName', label: '课程名称',  minWidth: '120'},
        { prop: 'courseGrade', label: '课程成绩',  minWidth: '80'},
        { prop: 'resitGrade', label: '补考成绩',  minWidth: '80'},
        { prop: 'retestGrade', label: '重新学习成绩',  minWidth: '100'},
        { prop: 'coursePoint', label: '学分',  minWidth: '80'}
      ]
    }
  },
  computed: {
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    }),
    tableHeight () {
      return this.total > this.pageSize ? '308px' : 'calc(308px + 40px)'
    },
    termOpts () {
      return learnYearOptions(this.userName)
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      let param = { studentId: this.userName }
      param = Object.assign(param, {...this.searchFormData})
      COURSEGRADE.getAllGrade(param).then( res => {
        if (res.rows && res.rows.length != 0) {
          this.tableData = res.rows
          this.total = res.total
        } else {
          this.tableData = []
          this.total = 0
        }
      })
    },
    handleSearch () {},
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
    }
  },
  filters: {
    tableFormatter (val, prop) {
      if (val === null || val === undefined || val === '' || val === 0) {
        return '--'
      } else {
        return val
      }
    }
  }
}
</script>