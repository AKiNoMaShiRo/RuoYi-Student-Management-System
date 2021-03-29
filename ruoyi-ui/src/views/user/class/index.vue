<template>
    <div class="app-container">
      <section class="am-box">
        <div class="am-title am-p am-bd-b">班级信息列表</div>
        <div class="am-px-lg am-pt am-mb">
          <el-form
            :model="searchFormData"
            ref="searchForm"
            label-position="right"
            label-width="60px"
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
              <!-- <el-input
                v-model="searchFormData.profession"
                placeholder="请输入专业"
                size="small" 
                clearable
              ></el-input> -->
            </el-form-item>
            <el-form-item label="年级" prop="grade">
              <el-input
                v-model="searchFormData.grade"
                placeholder="请输入年级"
                size="small"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="辅导员" prop="instructorName">
              <el-input
                v-model="searchFormData.instructorName"
                placeholder="请输入辅导员姓名"
                size="small" 
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="班主任" prop="headmasterName">
              <el-input
                v-model="searchFormData.headmasterName"
                placeholder="请输入班主任姓名"
                size="small" 
                clearable
              ></el-input>
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
            highlight-current-row=""
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              v-for="column in tableColumns"
              v-bind="column"
              :key="column.prop"
              show-overflow-tooltip
            ></el-table-column>
            <el-table-column
              label="操作"
              align="center"
              width="160"
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
                  title="确定删除该班级？"
                  @onConfirm="handleDelete(scope.row)"
                >
                  <el-button
                    type="text"
                    icon="el-icon-delete"
                    size="mini"
                    style="margin-right: 6px; margin-left: 0px;"
                    slot="reference"
                  >删除</el-button>
                </el-popconfirm>
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
      <el-dialog title="新增班级" :visible.sync="dgAddVisible">
        <div class="am-mb" style="color: #ff4949; font-size: 12px;">请注意：同一专业，同一年级不应出现重复的班级序号</div>
        <el-form :model="addFormData" :rules="rules" ref="addForm" label-width="95px" inline>
            <el-form-item label="学院" prop="department">
              <el-input v-model="addFormData.department" size="small" clearable disabled></el-input>
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
            <el-form-item label="辅导员工号" prop="instructorId">
              <el-input v-model="addFormData.instructorId" size="small" clearable></el-input>
            </el-form-item>
            <el-form-item label="班主任工号" prop="headmasterId">
              <el-input v-model="addFormData.headmasterId" size="small" clearable></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dgAddVisible = false">取消</el-button>
          <el-button size="small" type="primary" @click="handleAddDialog">确定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改班级信息" :visible.sync="dgEditVisible">
        <el-form :model="editFormData" :rules="rules" ref="editForm" label-width="95px" inline>
          <el-form-item label="学院" prop="department">
            <el-input v-model="editFormData.department" size="small" clearable disabled></el-input>
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
          <el-form-item label="辅导员工号" prop="instructorId">
            <el-input v-model="editFormData.instructorId" size="small" clearable></el-input>
          </el-form-item>
          <el-form-item label="班主任工号" prop="headmasterId">
            <el-input v-model="editFormData.headmasterId" size="small" clearable></el-input>
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
import * as CLASSINFO from '@/api/info/classInfo.js'
import Pagination from '../../components/Pagination.vue'
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
      tableColumns: [
        // { label: '班级ID', prop: 'classId' },
        { label: '年级', prop: 'grade' },
        { label: '专业', prop: 'profession' },
        { label: '班级序号', prop: 'classNum' },
        { label: '辅导员', prop: 'instructorName' },
        { label: '班主任', prop: 'headmasterName' }
      ],
      tableData: [],
      searchFormData: {
        profession: '',
        grade: null,
        instructorName: '',
        headmasterName: ''
      },
      addFormData: {
        department: '计算机与软件学院',
        profession: '',
        grade: null,
        classNum: null,
        instructorId: '',
        headmasterId: ''
      },
      editFormData: {
        department: '计算机与软件学院',
        profession: '',
        grade: null,
        classNum: null,
        instructorId: '',
        headmasterId: ''
      },
      rules: {
        department: [ { required: true, message: '请输入学院', trigger: 'blur' } ],
        profession: [ { required: true, message: '请输入专业名称', trigger: 'blur' } ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' },
          { type: 'number', message: '年级必须为数字值'}
        ],
        classNum: [
          { required: true, message: '请输入班级序号', trigger: 'blur' },
          { type: 'number', message: '班级序号必须为数字值'}
        ],
        instructorId: [ { required: true, message: '请输入辅导员工号', trigger: 'blur' } ],
        headmasterId: [ { required: true, message: '请输入班主任工号', trigger: 'blur' } ]
      }
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '289px' : 'calc(289px + 40px)'
    },
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
      CLASSINFO.getClassInfo(param).then( res => {
        if (res.rows && res.rows.length != 0) {
          this.total = res.total
          this.tableData = res.rows
        } else {
          this.total = 0
          this.tableData = []
        }
      }).finally( () => {
        this.tableLoading = false
      })
    },
    //    新增按钮
    handleAdd () {
      this.dgAddVisible = true
    },
    handleUpdate (row) {
      this.dgEditVisible = true
      for (let key in row) {
        this.editFormData[key] = row[key]
      }
    },
    handleDelete (row) {
      let param = row.classId
      CLASSINFO.deleteClassInfo(param).then( res => {
        if (res.msg === '操作成功') {
          this.$message.success('删除成功')
          this.getInfo()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
        handleSelectionChange () {},
        //    搜索按钮
        handleSearch () {
            this.getInfo()
        },
        //    重置搜索表单
        resetSearchForm () {
            this.$refs.searchForm.resetFields()
        },
        handlePaginationUpdate (param) {
            this.currentPage = param.currentPage
            this.pageSize = param.pageSize
            this.getInfo()
        },
        handleAddDialog () {
            CLASSINFO.addClassInfo(this.addFormData).then( res => {
                if (res.msg === '操作成功') {
                    this.$message.success('添加成功')
                    this.getInfo()
                } else {
                    this.$message.error('添加失败')
                }
            }).finally( () => {
                this.dgAddVisible = false
            })
        },
        handleEditDialog () {
          this.$refs.editForm.validate( valid => {
            if (valid) {
              CLASSINFO.editClassInfo(this.editFormData).then( res => {
                if (res.msg === '操作成功') {
                  this.$message.success('修改成功')
                  this.getInfo()
                } else {
                  this.$message.error('修改失败')
                }
              }).finally( () => {
                this.dgEditVisible = false
              })
            }
          })
        }
    }
}
</script>