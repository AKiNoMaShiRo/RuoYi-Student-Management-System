<template>
  <div class="app-container">
    <section class="am-box">
      <div class="am-title am-p am-bd-b">学生账号列表</div>
      <div class="am-px-lg am-pt am-mb">
        <el-form
          :model="searchFormData"
          ref="searchForm"
          label-position="right"
          label-width="85px"
          inline
        >
          <el-form-item label="学生学号" prop="userName">
            <el-input v-model="searchFormData.userName" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="学生姓名" prop="nickName">
            <el-input v-model="searchFormData.nickName" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="profession">
            <el-input v-model="searchFormData.profession" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="年级" prop="grade">
            <el-input v-model="searchFormData.grade" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="辅导员工号" prop="instructorId">
            <el-input v-model="searchFormData.instructorId" size="small" clearable></el-input>
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
          <el-table-column label="学生学号" align="center" prop="userName" :show-overflow-tooltip="true" />
          <el-table-column label="学生姓名" align="center" prop="nickName" :show-overflow-tooltip="true" />
          <el-table-column label="专业" align="center" prop="profession" :show-overflow-tooltip="true" />
          <el-table-column
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
            classOptions: [],
            searchFormData: {
                nickName: '',
                classNum: ''
            }
        }
    },
    computed: {
    tableHeight () {
      return this.total > this.currentPage ? '289px' : 'calc(289px + 40px)'
    }
  },
    methods: {
        //    搜索按钮
        handleSearch () {},
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