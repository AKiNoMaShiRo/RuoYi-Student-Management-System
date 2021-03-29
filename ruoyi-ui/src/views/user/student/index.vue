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
          <el-form-item label="学生学号" prop="studentId">
            <el-input v-model="searchFormData.studentId" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="学生姓名" prop="name">
            <el-input v-model="searchFormData.name" size="small" clearable></el-input>
          </el-form-item>
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
            <!-- <el-input v-model="searchFormData.profession" size="small" clearable></el-input> -->
          </el-form-item>
          <el-form-item label="年级" prop="grade">
            <el-input v-model="searchFormData.grade" size="small" clearable></el-input>
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
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column
            v-for="column in tableColumns"
            v-bind="column"
            :key="column.prop"
            show-overflow-tooltip
          ></el-table-column>
          <!-- <el-table-column label="学生学号" align="center" prop="userName" :show-overflow-tooltip="true" />
          <el-table-column label="学生姓名" align="center" prop="nickName" :show-overflow-tooltip="true" />
          <el-table-column label="专业" align="center" prop="profession" :show-overflow-tooltip="true" /> -->
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
                style="margin-right: 6px;"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
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
                  slot="reference"
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  style="margin-right: 6px; margin-left: 0px;"
                >删除</el-button>
              </el-popconfirm>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-key"
                style="margin-right: 6px; margin-left: 0px;"
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
    <el-dialog title="新增学生用户" :visible.sync="dgAddVisible" @close="handleAddDgClose">
      <el-form :model="addFormData" :rules="addRules" ref="addForm" label-width="95px" inline>
        <el-form-item label="学生学号" prop="userName">
          <el-input v-model="addFormData.userName" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" prop="nickName">
          <el-input v-model="addFormData.nickName" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addFormData.password" size="small" show-password></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-select size="small" v-model="addFormData.profession" clearable>
            <el-option
              v-for="pro in professions"
              :key="pro.label"
              :label="pro.label"
              :value="pro.label"
            >
            </el-option>
          </el-select>
          <!-- <el-input v-model="addFormData.profession" size="small" clearable></el-input> -->
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-input v-model.number="addFormData.grade" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="班级序号" prop="classNum">
          <el-input v-model.number="addFormData.classNum" size="small" clearable></el-input>
        </el-form-item>
        <!-- <el-form-item label="班级ID" prop="classId">
          <el-input v-model.number="addFormData.classId" size="small" clearable></el-input>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dgAddVisible = false">取消</el-button>
          <el-button size="small" type="primary" @click="handleAddDialog">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="编辑学生用户" :visible.sync="dgEditVisible">
      <el-form :model="editFormData" :rules="editRules" ref="editForm" label-width="95px" inline>
        <el-form-item label="学生学号" prop="studentId">
          <el-input v-model="editFormData.studentId" size="small" disabled></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" prop="name">
          <el-input v-model="editFormData.name" size="small" disabled></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-select size="small" v-model="editFormData.profession" clearable>
            <el-option
              v-for="pro in professions"
              :key="pro.label"
              :label="pro.label"
              :value="pro.label"
            >
            </el-option>
          </el-select>
          <!-- <el-input v-model="editFormData.profession" size="small" clearable></el-input> -->
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-input v-model.number="editFormData.grade" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="班级序号" prop="classNum">
          <el-input v-model.number="editFormData.classNum" size="small" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dgEditVisible = false">取消</el-button>
          <el-button size="small" type="primary" @click="handleEditDialog">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '../../components/Pagination.vue'
import * as STUINFO from '@/api/info/stuInfo.js'
import { getClassInfo } from '@/api/info/classInfo.js'
import { addUser, delUser } from '@/api/system/user'
import { resetUserPwd } from "@/api/system/user"
import { PROFESSION } from '@/libs/utils.js'

export default {
  components: {
      Pagination
  },
  data () {
    return {
      professions: PROFESSION,
      dgAddVisible: false,
      dgEditVisible: false,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableData: [],
      tableColumns: [
        { label: '学生学号', prop: 'studentId', minWidth: '100' },
        { label: '学生姓名', prop: 'name', minWidth: '100' },
        { label: '学院', prop: 'department', minWidth: '150' },
        { label: '专业', prop: 'profession', minWidth: '150' },
        { label: '年级', prop: 'grade', minWidth: '60' },
        { label: '班级序号', prop: 'classNum', minWidth: '80' }
      ],
      classInfos: [],
      searchFormData: {
        studentId: '',
        name: '',
        profession: '',
        grade: null
      },
      searchRules: {
        grade: [ { type: 'number', message: '年级必须为数字值'} ]
      },
      addFormData: {
        userName: '',
        nickName: '',
        password: '',
        profession: '',
        grade: '',
        classNum: '',
        classId: null
      },
      addRules: {
        userName: [ { required: true, message: '请输入学生学号', trigger: 'blur' } ],
        nickName: [ { required: true, message: '请输入学生姓名', trigger: 'blur' } ],
        password: [ { required: true, message: '请输入密码', trigger: 'blur' } ],
        profession: [ { required: true, message: '请输入专业', trigger: 'blur' } ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' },
          { type: 'number', message: '年级必须为数字' }
        ],
        classNum: [
          { required: true, message: '请输入班级序号', trigger: 'blur' },
          { type: 'number', message: '班级序号必须为数字' }
        ]
      },
      editFormData: {
        studentId: '',
        name: '',
        profession: '',
        grade: '',
        classNum: '',
        classId: null
      },
      editRules: {
        // userName: [ { required: true, message: '请输入学生学号', trigger: 'blur' } ],
        // nickName: [ { required: true, message: '请输入学生姓名', trigger: 'blur' } ],
        profession: [ { required: true, message: '请输入专业', trigger: 'blur' } ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' },
          { type: 'number', message: '年级必须为数字' }
        ],
        classNum: [
          { required: true, message: '请输入班级序号', trigger: 'blur' },
          { type: 'number', message: '班级序号必须为数字' }
        ]
      },
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '289px' : 'calc(289px + 40px)'
    }
  },
  created () {
    this.getInfo()
    getClassInfo({}).then( res => {
      if (res.rows && res.rows.length !== 0) {
        this.classInfos = res.rows
      }
    })
  },
  methods: {
    getInfo () {
      parseInt
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
    getClassId (param) {
      let res = 0
      this.classInfos.forEach( item => {
        if (param.profession === item.profession && param.grade === item.grade && param.classNum === item.classNum) {
          res = item.classId
          return
        }
      })
      return res
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
    //    TODO 修改学生信息
    handleUpdate (row) {
      this.editFormData.studentId = row.studentId
      this.editFormData.name = row.name
      this.editFormData.profession = row.profession
      this.editFormData.grade = row.grade
      this.editFormData.classNum = row.classNum
      this.dgEditVisible = true
    },
    deleteStuUser (stuId) {
      return STUINFO.deleteStuUserInfo(stuId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('删除学生用户失败')
        }
      })
    },
    deleteStuInfo (stuId) {
      return STUINFO.deleteStuInfo(stuId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('删除学生基本信息失败')
        }
      })
    },
    deleteStuRole (stuId) {
      return STUINFO.deleteStuRoleInfo(stuId).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.success('删除学生角色信息失败')
        }
      })
    },
    handleDelete (row) {
      Promise.all([
        this.deleteStuInfo(row.studentId), this.deleteStuUser(row.userId), this.deleteStuRole(row.userId)
      ]).then( () => {
        this.$message.success('删除成功')
        this.getInfo()
      }).catch( () => {
        this.$message.error('删除失败')
      })
    },
    handleResetPwd (row) {
      this.$prompt('请输入"' + row.name + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
        resetUserPwd(row.userId, value).then(response => {
          this.msgSuccess("修改成功，新密码是：" + value);
        });
      })
    },
    handleSelectionChange () {},
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
      this.getInfo()
    },
    addStuUser () {
      return addUser(this.addFormData).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('添加学生用户失败')
        }
      })
    },
    addStuInfo () {
      // //    获取班级ID
      // let cId = this.getClassId(this.addFormData)
      // console.log(cId)
      let param = {
        classId: this.addFormData.classId,    //    this.getClassId(this.addFormData),
        studentId: this.addFormData.userName,
        name: this.addFormData.nickName
      }
      return STUINFO.addStuInfo(param).then( res => {
        if ( !(res.msg && res.msg === '操作成功') ) {
          this.$message.error('添加学生信息失败')
        }
      })
    },
    handleAddDialog () {
      this.addFormData.classId = this.getClassId(this.addFormData)
      console.log(this.addFormData.classId)
      if (this.addFormData.classId === null || this.addFormData.classId === 0) {
        this.$message.error('班级不存在')
        return
      }
      this.$refs.addForm.validate( valid => {
        if (valid) {
          Promise.all([ this.addStuUser(), this.addStuInfo() ]).then( () => {
            STUINFO.addStuRoleInfo({studentId: this.addFormData.userName}).then( res => {
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
    handleEditDialog () {
      this.editFormData.classId = this.getClassId(this.editFormData)
      console.log(this.editFormData.classId)
      if (this.editFormData.classId === null || this.editFormData.classId === 0) {
        this.$message.error('班级不存在')
        return
      }
      this.$refs.editForm.validate( valid => {
        if (valid) {
          STUINFO.editStuInfo(this.editFormData).then( res => {
            if (res.msg && res.msg === '操作成功') {
              this.$message.success('修改成功')
              this.getInfo()
              this.dgEditVisible = false
            } else {
              this.$message.error('修改失败')
            }
          })
        }
      })
    }
  }
}
</script>