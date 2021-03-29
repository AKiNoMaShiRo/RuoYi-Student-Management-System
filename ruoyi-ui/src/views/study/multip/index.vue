<template>
  <div class="app-container">
    <section v-if="roleName === '学生'" class="am-box am-mb">
      <div class="am-p am-title am-bd-b">我的成绩</div>
      <div class="am-p am-flex">
        <section class="info-content am-bd am-p">
          <div>专业人数</div>
          <span>{{ stuGrade.profeSum }}</span>
        </section>
        <section class="info-content am-bd am-p">
          <div>必修与选修折算成绩之和</div>
          <span>{{ stuGrade.gradeSum }}</span>
        </section>
        <section class="info-content am-bd am-p">
          <div>专业排名</div>
          <span>{{ stuGrade.profeRank }}</span>
        </section>
        <section class="info-content am-bd am-p">
          <div>综测总分</div>
          <span>{{ stuGrade.multipSum }}</span>
        </section>
        <section class="info-content am-bd am-p">
          <div>综测排名</div>
          <span>{{ stuGrade.multipRank }}</span>
        </section>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b">综合测评成绩公示</div>
      <div class="am-px am-pt" v-if="roleName !== '学生'">
        <el-form
          ref="searchForm"
          label-width="60px"
          :model="searchFormData"
          :rules="searchRules"
          inline
        >
          <el-form-item label="专业" prop="profession">
            <el-select size="small" v-model="searchFormData.profession" clearable>
              <el-option
                v-for="pro in professions"
                :key="pro.label"
                :label="pro.label"
                :value="pro.label"
              >
              </el-option>
            </el-select>
            <!-- <el-input size="small" v-model="searchFormData.profession" clearable></el-input> -->
          </el-form-item>
          <el-form-item label="年级" prop="grade">
            <el-input size="small" v-model.number="searchFormData.grade" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="am-p">
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          :height="tableHeight"
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
          :page-range="[20, 25, 30]"
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
import { PROFESSION } from '@/libs/utils.js'
import Pagination from '../../components/Pagination.vue'
import * as MULTIP from '@/api/grade/multipGrade.js'
import { getStuBaseInfo } from '@/api/info/stuInfo'
import { mapState } from 'vuex'

export default {
  components: {
    Pagination
  },
  data () {
    return {
      professions: PROFESSION,
      total: 0,
      currentPage: 1,
      pageSize: 20,
      stuGrade: {
        profeSum: '',
        gradeSum: '',
        profeRank: '',
        multipSum: '',
        multipRank: ''
      },
      tableLoading: false,
      tableData: [],
      tableColumns: [
        { prop: 'studentId', label: '学号', minWidth: '100' },
        { prop: 'stuName', label: '姓名', minWidth: '100' },
        { prop: 'profeSum', label: '专业人数', minWidth: '80' },
        { prop: 'gradeSum', label: '必修与选修折算成绩之和', minWidth: '140' },
        { prop: 'profeRank', label: '专业排名', minWidth: '80' },
        { prop: 'multipSum', label: '综测总分', minWidth: '80' },
        { prop: 'multipRank', label: '综测排名', minWidth: '80' }
      ],
      searchFormData: {
        profession: '',
        grade: null
      },
      searchRules: {
        profession: [
          { required: true, message: '请输入专业', trigger: 'blur' }
        ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' },
          { type: 'number', message: '年级必须为数字值' }
        ]
      }
    }
  },
  computed: {
    ...mapState({
      roleName: state => state.user.roleName,
      userName: state => state.user.name
    }),
    tableHeight () {
      return this.total > this.currentPage ? '484px' : 'calc(484px + 40px)'
    }
  },
  created () {
    let param = {
      pageNum: this.currentPage,
      pageSize: this.pageSize
    }
    if (this.roleName === '学生') {
      MULTIP.getAllGrade({studentId: this.userName}).then( res => {
        //页面上方显示本人成绩
        if (res.rows && res.rows.length !== 0) {
          this.stuGrade = res.rows[0]
          param.profession = res.rows[0].profession
          param.grade = res.rows[0].grade
        }
      }).finally( () => {
        //对学生公示本年级本专业成绩
        this.getInfo(param)
      })
    }
    // } else {
    //   //其他角色可查询所有成绩
    //   this.getInfo(param)
    // }
  },
  methods: {
    getInfo (param) {
      this.tableLoading = true
      MULTIP.getAllGrade(param).then( res => {
        if (res.rows && res.rows.length !== 0) {
          this.tableData = res.rows
          this.total = res.total
        } else {
          this.tableData = []
          this.total = 0
        }
      }).finally ( () => {
        this.tableLoading = false
      })
    },
    handleSearch () {
      let param = {
        pageNum: this.currentPage,
        pageSize: this.pageSize,
        ...this.searchFormData
      }
      this.getInfo(param)
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
      if (val === '' || val === null || val === undefined){
        return '--'
      } else if (prop === 'gradeSum' || prop === 'multipSum') {
        return parseFloat(val).toFixed(2)
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
  width: 190px;
  div {
    color: #606266;
    font-size: 14px;
    margin-bottom: 8px;
  }
  span {
    color: #606266;
  }
}
</style>