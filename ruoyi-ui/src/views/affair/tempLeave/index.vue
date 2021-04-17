<template>
  <div class="app-container">
    <section class="am-box am-mb" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">临时请假申请</div>
      <div class="am-p">
        <el-form
          ref="tempForm"
          :rules="rules"
          :model="formData"
          label-position="right"
          label-width="80px"
          inline
        >
          <el-form-item label="请假原因" prop="reason">
            <el-input size="small" v-model="formData.reason" clearable></el-input>
          </el-form-item>
          <el-form-item label="请假时段" prop="timeRange">
            <el-date-picker
              v-model="formData.timeRange"
              type="datetimerange"
              size="small"
              value-format="yyyy-MM-dd HH:00:00"
              range-separator="至"
              start-placeholder="请假开始时间"
              end-placeholder="请假结束时间"
              clearable
            >
            </el-date-picker>
            <!-- <el-input size="small" v-model="formData.time_range" clearable></el-input> -->
          </el-form-item>
          <el-form-item label="任课老师" prop="teacher">
            <el-input size="small" v-model="formData.teacher" clearable></el-input>
          </el-form-item>
        </el-form>
        <div style="margin-top: 6px; margin-left: 80px;">
          <el-button el-button size="small" disabsled @click="handleResetForm">重置</el-button>
          <el-button size="small" type="primary" @click="handleSubmitForm">提交</el-button>
        </div>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b" v-if="roleName === '学生'">历史临时请假申请记录</div>
      <div class="am-p am-title am-bd-b" v-else>临时请假申请记录</div>
      <div class="am-px am-pt">
        <el-form
          ref="searchForm"
          :model="searchFormData"
          label-width="80px"
          inline
        >
          <el-form-item label="学号" prop="studentId" v-if="roleName !== '学生'">
            <el-input size="small" v-model="searchFormData.studentId" clearable></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="stuName" v-if="roleName !== '学生'">
            <el-input size="small" v-model="searchFormData.stuName" clearable></el-input>
          </el-form-item>
          <el-form-item label="审批状态" prop="status">
            <el-select size="small" v-model="searchFormData.status" clearable>
              <el-option label="待审批" :value="1"></el-option>
              <el-option label="已通过" :value="2"></el-option>
              <el-option label="未通过" :value="3"></el-option>
            </el-select>
          </el-form-item>
            <el-form-item label=" ">
              <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
            </el-form-item>
        </el-form>
      </div>
      <div class="am-p">
        <el-table v-loading="tableLoading" :data="tableData" :height="tableHeight" highlight-current-row>
          <!-- row-key=""
          :expand-row-keys="expandRowKeys" -->
          <el-table-column
            v-for="column in tableColumns"
            v-bind="column"
            :key="column.prop"
            showOverflowTooltip
          >
            <!-- <template slot-scope="scope">
              {{ scope.row[column.prop] | dataFormatter(column.prop) }}
            </template> -->
          </el-table-column>
          <el-table-column label="状态" min-width="80">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 1" class="status-pending">
                待审批
              </span>
              <span v-else-if="scope.row.status === 2" class="status-pass">
                已通过
              </span>
              <span v-else-if="scope.row.status === 3" class="status-reject">
                未通过
              </span>
              <span v-else>--</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="180" fixed="right">
            <template slot-scope="scope">
              <el-button
                type="text"
                icon="el-icon-edit"
                size="mini"
                style="margin-right: 6px;"
                :disabled="scope.row.status !== 1"
                @click="handleEdit(scope.row)"
              >
              编辑</el-button>
              <el-button
                type="text"
                icon="el-icon-edit-outline"
                size="mini"
                style="margin-right: 6px; margin-left: 0px;"
                :disabled="scope.row.status !== 1"
                v-hasPermi="['affair:templeave:approve']"
                @click="handleDeal(scope.row)"
              >
              审批</el-button>
              <el-popconfirm
                confirm-button-text="确定"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确定撤销该申请？"
                @onConfirm="handleCancel(scope.row)"
              >
                <el-button
                  type="text"
                  icon="el-icon-refresh-left"
                  size="mini"
                  style="margin-right: 6px; margin-left: 0px;"
                  slot="reference"
                  :disabled="scope.row.status !== 1"
                >
                撤销</el-button>
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
    <el-dialog title="临时请假申请信息修改" :visible.sync="dgEditVisible">
      <el-form
        ref="editForm"
        :rules="rules"
        :model="editData"
        label-position="right"
        label-width="80px"
        inline
      >
        <el-form-item label="请假原因" prop="reason">
          <el-input size="small" v-model="editData.reason" clearable></el-input>
        </el-form-item>
        <el-form-item label="请假时段" prop="timeRange">
          <el-date-picker
            v-model="editData.timeRange"
            type="datetimerange"
            size="small"
            value-format="yyyy-MM-dd HH:00"
            range-separator="至"
            start-placeholder="请假开始时间"
            end-placeholder="请假结束时间"
            clearable
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="任课老师" prop="teacher">
          <el-input size="small" v-model="editData.teacher" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dgEditVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="handleEditDialog">修改</el-button>
      </div>
    </el-dialog>
    <el-dialog title="临时请假审批" width="30%" :visible.sync="dgDealVisible">
      <el-form :model="dealResult" label-width="80">
        <el-form-item label="审批意见" prop="dealResult">
          <el-radio-group size="small" v-model="dealResult">
            <el-radio :label="2">同意</el-radio>
            <el-radio :label="3">不同意</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dgDealVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="handleDealDialog">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import * as TEMPLEAVE from '@/api/affair/tempLeave.js'
// import { getTempLeave, addTempLeave } from '@/api/info/tempLeave.js'
import Pagination from '../../components/Pagination.vue'
import { mapState } from 'vuex'

export default {
  components: { Pagination },
  data () {
    return {
      dgDealVisible: false,
      dgEditVisible: false,
      dealResult: 2,
      dealRequest: {},
      total: 0,
      currentPage: 1,
      pageSize: 10,
      formData: {
        reason: '',
        timeRange: '',
        teacher: ''
      },
      searchFormData: {
        studentId: '',
        stuName: '',
        status: null
      },
      editData: {
        reason: '',
        timeRange: '',
        teacher: ''
      },
      rules: {
        // name: [ { required: true, message: '请输入姓名', trigger: 'blur' } ],
        // student_id: [ { required: true, message: '请输入学号', trigger: 'blur' } ],
        // department: [ { required: true, message: '请选择院系', trigger: 'change' } ],
        // grade: [ { required: true, message: '请输入年级', trigger: 'blur' } ],
        // profession: [ { required: true, message: '请输入专业', trigger: 'blur' } ],
        reason: [ { required: true, message: '请输入请假原因', trigger: 'blur' } ],
        timeRange: [ { required: true, message: '请输入请假时间', trigger: 'blur' } ]
        // teacher: [ { required: true, message: '请输入任课教师姓名', trigger: 'blur' } ]
      },
      tableLoading: false,
      tableColumnsStu: [
        { prop: 'leaveId', label: '申请编号', minWidth: '80' },
        { prop: 'leaveStartTime', label: '开始时间', minWidth: '140' },
        { prop: 'leaveEndTime', label: '结束时间', minWidth: '140' },
        { prop: 'reason', label: '请假原因', minWidth: '180' },
        { prop: 'teacher', label: '任课老师', minWidth: '90' }
        // { prop: 'status', label: '审批状态', minWidth: '90' }
      ],
      tableColumnsIns: [
        { prop: 'studentId', label: '学号', minWidth: '110' },
        { prop: 'stuName', label: '姓名', minWidth: '80' },
        { prop: 'leaveId', label: '申请编号', minWidth: '80' },
        { prop: 'leaveStartTime', label: '开始时间', minWidth: '140' },
        { prop: 'leaveEndTime', label: '结束时间', minWidth: '140' },
        { prop: 'reason', label: '请假原因', minWidth: '140' },
        { prop: 'teacher', label: '任课老师', minWidth: '90' }
        // { prop: 'status', label: '审批状态', minWidth: '90' }
      ],
      tableData: []
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '338px' : 'calc(338px + 40px)'
    },
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    }),
    tableColumns () {
      return this.roleName === '学生' ? this.tableColumnsStu : this.tableColumnsIns
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.tableLoading = true
      if (this.roleName === '辅导员') {
        //    辅导员账号
        let param = {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          instructorId: this.userName,
          ...this.searchFormData
        }
        if (param.status === '') {
          param.status = 0
        }
        TEMPLEAVE.getInsTempLeave(param).then( res => {
          if (res.rows && res.rows.length !== 0){
            this.tableData = res.rows
            this.total = res.total
          } else {
            this.tableData = []
            this.total = 0
          }
        }).finally( () => {
          this.tableLoading = false
        })
      } else {
        //    超管账号、学生账号
        let param = {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          studentId: this.roleName === '超级管理员' ? '' : this.userName,
          ...this.searchFormData
        }
        if (param.status === '') {
          param.status = 0
        }
        TEMPLEAVE.getTempLeave(param).then( res => {
          if (res.rows && res.rows.length !== 0){
            this.tableData = res.rows
            this.total = res.total
          } else {
            this.tableData = []
            this.total = 0
          }
        }).finally( () => {
          this.tableLoading = false
        })
      }
    },
    //    表格编辑请假申请
    handleEdit (row) {
      this.dgEditVisible = true
      for (let key in row) {
        this.editData[key] = row[key]
      }
      this.editData.timeRange = [ row.leaveStartTime, row.leaveEndTime ]
      // console.log(this.editData)
    },
    //    表格辅导员审批请假申请
    handleDeal (row) {
      this.dealRequest = row
      this.dgDealVisible = true
    },
    //    表格撤销请假申请
    handleCancel (row) {
      TEMPLEAVE.deleteTempLeave(row.leaveId).then( res => {
        if (res.msg === '操作成功') {
          this.$message.success('申请撤销成功')
          this.getInfo()
        } else {
          this.$message.success('申请撤销失败')
        }
      })
    },
    //    编辑请假申请对话框确认按钮
    handleEditDialog () {
      this.$refs.editForm.validate( valid => {
        if (valid) {
          TEMPLEAVE.editTempLeave(this.editData).then( res => {
            if (res.msg === '操作成功') {
              this.$message.success('修改成功')
              this.dgEditVisible = false
              this.getInfo()
            } else {
              this.$message.error('修改失败')
            }
          })
        }
      })
    },
    //    审批对话框确认按钮
    handleDealDialog () {
      let param = this.dealRequest
      param.status = this.dealResult
      // this.dealResult === 'agree' ? param.status = 2 : param.status = 3
      TEMPLEAVE.changeStatus(param).then(res => {
        if (res.msg === '操作成功') {
          this.$message.success('审批完成')
          this.getInfo()
        } else {
          this.$message.error('审批失败')
        }
      }).finally( () => {
        this.dgDealVisible = false
      })
    },
    //    重置请假申请表单
    handleResetForm () {
      this.$refs.tempForm.resetFields()
    },
    //    提交请假申请表单
    handleSubmitForm () {
      this.$refs.tempForm.validate(valid => {
        if (valid) {
          let param = {
            studentId: this.userName,
            ...this.formData,
            leaveStartTime: this.formData.timeRange[0],
            leaveEndTime: this.formData.timeRange[1]
          }
          TEMPLEAVE.addTempLeave(param).then( res => {
            if ( res.msg === '操作成功' ) {
              this.$message.success('申请提交成功')
              this.getInfo()
            } else {
              this.$message.error('申请提交失败')
            }
          })
        }
      })
      this.handleResetForm()
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
      this.getInfo()
    },
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handleSearch () {
      this.getInfo()
    }
  }
}
</script>

<style lang="scss" scoped>
.status-pending {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #8e9cab, $alpha: 0.09);
  color: #8e9cab;
  border-radius: 4px;
}
.status-pass {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #31cf9a, $alpha: 0.09);
  color: #31cf9a;
  border-radius: 4px;
}
.status-reject {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #f56c6c, $alpha: 0.09);
  color: #f56c6c;
  border-radius: 4px;
}
</style>