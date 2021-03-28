<template>
  <div class="am-p">
    <section>
      <div class="am-flex-wrap">
        <section v-for="status in pointGainStatus" :key="status.type" class="info-content am-bd am-p">
          <div>{{ status.type }}</div>
          <span>毕业共需学分：{{ status.needed }}</span>
          <span>已获学分：{{ status.gained }}</span>
        </section>
      </div>
    </section>
    <section>
      <el-form
        ref="searchForm"
        label-width="80px"
        :model="searchFormData"
        :rules="searchRules"
        inline
      >
        <el-form-item label="学年" prop="learnYear">
          <el-select size="small" v-model="searchFormData.learnYear">
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
          <el-select size="small" v-model="searchFormData.learnTerm">
            <el-option label="1" :value="1"></el-option>
            <el-option label="2" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程类别" prop="courseType">
          <el-select size="small" v-model="searchFormData.courseType" clearable>
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
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '@/views/components/Pagination'
import { learnYearOptions, COURSETYPE } from '@/libs/utils.js'
import * as COURSEGRADE from '@/api/grade/courseGrade.js'

export default {
  components: { Pagination },
  data () {
    return {
      courseTypeOpts: COURSETYPE,
      pointGainStatus: [
        { type: '学科(必)', needed: '24', gained: '' },
        { type: '专业(必)', needed: '18', gained: '' },
        { type: '专业(选)', needed: '19', gained: '' },
        { type: '实践(必)', needed: '35.5', gained: '' },
        { type: '实践(选)', needed: '3', gained: '' },
        { type: '公共(必)', needed: '63.5', gained: '' },
        { type: '方向(选)', needed: '11', gained: '' },
        { type: '通修课', needed: '6', gained: '' },
      ],
      searchFormData: {
        learnYear: '',
        learnTerm: '',
        courseType: '',
      },
      searchRules: {
        // learnYear: [ {required: true, message: '请选择学年', trigger: 'blur'} ],
        // learnTerm: [ {required: true, message: '请选择学期', trigger: 'blur'} ]
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
    // this.getPointInfo()
    this.getInfo()
  },
  methods: {
    // getPointInfo () {
    //   COURSEGRADE.getStuPoint({studentId: this.userName}).then( res => {
    //     if (res) {}
    //   })
    // },
    getInfo () {
      this.tableLoading = true
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
      }).finally( () => {
        this.tableLoading = false
        //  计算已获得学分
        if (this.tableData.length !== 0) {
          let pointMap = new Map();
          this.tableData.forEach( item => {
            if (pointMap.get(item.courseType)) {
              pointMap.set(item.courseType, pointMap.get(item.courseType) + parseFloat(item.coursePoint))
            } else {
              pointMap.set(item.courseType, parseFloat(item.coursePoint))
            }
          })
          console.log(pointMap)
          this.pointGainStatus.forEach( item => {
            if (pointMap.get(item.type)) {
              item.gained = pointMap.get(item.type)
            } else {
              item.gained = 0
            }
          })
        }
      })
    },
    handleSearch () {
      this.getInfo()
    },
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

<style lang="scss" scoped>
.info-content{
  margin-bottom: 12px;
  margin-right: 12px;
  border-radius: 4px;
  width: 160px;
  div {
    color: #606266;
    margin-bottom: 8px;
  }
  span {
    display: inline-block;
    color: #606266;
    font-size: 14px;
  }
}
</style>