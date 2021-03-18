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
          @click="dgAddVisible = true"
        >新增</el-button>
      </div>
      <div class="am-px-lg am-pb">
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          :height="tableHeight"
        >
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
              <!-- <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button> -->
              <el-popconfirm
                confirm-button-text="确定"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确定删除该学生账号？"
                @onConfirm="handleDelete(scope.row)"
              >
                <el-button
                  v-if="scope.row.userId !== 1"
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  slot="reference"
                >删除</el-button>
              </el-popconfirm>
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
    <el-dialog title="新增辅导员用户" :visible.sync="dgAddVisible" @close="handleAddDgClose">
        <el-form :model="addFormData" :rules="addRules" ref="addForm" label-width="95px" inline>
          <el-form-item label="辅导员工号" prop="userName">
            <el-input v-model="addFormData.userName" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="辅导员姓名" prop="nickName">
            <el-input v-model="addFormData.nickName" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addFormData.password" size="small" clearable show-password></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="dgAddVisible = false">取消</el-button>
            <el-button size="small" type="primary" @click="handleAddDialog">确定</el-button>
        </div>
      </el-dialog>
  </div>
</template>

<script>
import Pagination from '../../components/Pagination.vue'
import * as INSINFO from '@/api/info/instructorInfo.js'
import { addUser, delUser } from '@/api/system/user'

export default {
  components: {
      Pagination
  },
  data () {
    return {
      total: 0,
      currentPage: 1,
      pageSize: 10,
      dgAddVisible: false,
      addFormData: {
        userName: '',
        nickName: '',
        password: ''
      },
      addRules: {
        userName: [ {required: true, message: '请输入辅导员工号', trigger: 'blur'} ],
        nickName: [ {required: true, message: '请输入辅导员姓名', trigger: 'blur'} ],
        password: [ {required: true, message: '请输入账号密码', trigger: 'blur'} ]
      },
      tableLoading: false,
      tableData: [],
      tableColumns: [
        { label: '辅导员工号', prop: 'instructorId', minWidth: '100' },
        { label: '辅导员姓名', prop: 'instructorName', minWidth: '100' },
        { label: '管理班级', prop: 'manageClass' }
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
      INSINFO.getAllInstructorInfo(param).then( res => {
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
      this.getInfo()
    },
    //    重置搜索表单
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handleUpdate () {},
    delInsUser (insId) {
      return INSINFO.deleteInsUserInfo(insId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('删除辅导员用户失败')
        }
      })
    },
    delInsInfo (stuId) {
      return INSINFO.deleteInsInfo(stuId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('删除辅导员基本信息失败')
        }
      })
    },
    delInsRole (stuId) {
      return INSINFO.deleteInsRoleInfo(stuId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.success('删除辅导员角色信息失败')
        }
      })
    },
    handleDelete (row) {
      Promise.all([
        this.delInsInfo(row.instructorId), this.delInsUser(row.userId), this.delInsRole(row.userId)
      ]).then( () => {
        this.$message.success('删除成功')
        this.getInfo()
      }).catch( () => {
        this.$message.error('删除失败')
      })
    },
    handleResetPwd (row) {
      this.$prompt('请输入"' + row.instructorName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
        resetUserPwd(row.userId, value).then(response => {
          this.msgSuccess("修改成功，新密码是：" + value);
        });
      })
    },
    addInsUser () {
      return addUser(this.addFormData).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('添加辅导员用户失败')
        }
      })
    },
    addInsInfo () {
      let param = {
        instructorId: this.addFormData.userName,
        instructorName: this.addFormData.nickName
      }
      return INSINFO.addInstructorInfo(param).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('添加辅导员信息失败')
        }
      })
    },
    handleAddDialog () {
      this.$refs.addForm.validate( valid => {
        if (valid) {
          Promise.all([ this.addInsUser(), this.addInsInfo() ]).then( () => {
            INSINFO.addInstructorRole({instructorId: this.addFormData.userName}).then( res => {
              if (res.msg && res.msg === '操作成功') {
                this.$message.success('添加成功')
                this.getInfo()
                this.dgAddVisible = false
              } else {
                this.$message.error('添加失败')
              }
            })
          })
        }
      })
    },
    handleAddDgClose () {
      this.$refs.addForm.resetFields()
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
      this.getInfo()
    }
  }
}
</script>