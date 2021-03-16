<template>
  <div class="app-container">
    <section class="am-box">
      <div class="am-title am-p am-bd-b">辅导员账号列表</div>
      <div class="am-px-lg am-pt am-mb">
        <el-form
          :model="searchFormData"
          ref="searchForm"
          label-position="right"
          label-width="85px"
          inline
        >
          <el-form-item label="辅导员工号" prop="instructorId">
            <el-input v-model="searchFormData.instructorId" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="辅导员姓名" prop="instructorName">
            <el-input v-model="searchFormData.instructorName" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
          </el-form-item>
        </el-form>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </div>
      <div class="am-px-lg am-pb">
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          :height="tableHeight"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column
            v-for="column in tableColumns"
            v-bind="column"
            :key="column.prop"
            show-overflow-tooltip
          ></el-table-column><el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                v-if="scope.row.userId !== 1"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-key"
                @click="handleResetPwd(scope.row)"
              >重置</el-button>
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
import Pagination from '../../components/Pagination.vue'
import * as STUINFO from '@/api/info/stuInfo.js'

export default {
  components: {
      Pagination
  },
  data () {
    return {
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableData: [],
      tableColumns: [
        { label: '辅导员工号', prop: 'studentId', minWidth: '100' },
        { label: '辅导员姓名', prop: 'name', minWidth: '100' },
        { label: '管理班级', prop: 'manageClass', minWidth: '80' }
      ],
      classOptions: [],
      searchFormData: {
        instructorId: '',
        instructorName: ''
      }
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '289px' : 'calc(289px + 40px)'
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.tableLoading = true
      let param = {
        pageNum: this.currentPage,
        pageSize: this.pageSize
      }
      param = Object.assign(param, {...this.searchFormData})
      if (param.grade === '') {
        param.grade = 0
      }
      STUINFO.getAllStuUser(param).then( res => {
        if (res.rows && res.rows !== 0) {
          this.tableData = res.rows
          this.total = res.total
        } else {
          this.tableData = []
          this.total = 0
        }
      }).finally( () => {
        this.tableLoading = false
      })
    },
    //    搜索按钮
    handleSearch () {
      this.$refs.searchForm.validate(valid => {
        if (valid) {
          this.getInfo()
        }
      })
    },
    //    重置搜索表单
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    //    新增按钮
    handleAdd () {},
    handleUpdate () {},
    handleDelete () {},
    handleResetPwd () {},
    handleSelectionChange () {},
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
    }
  }
}
</script>