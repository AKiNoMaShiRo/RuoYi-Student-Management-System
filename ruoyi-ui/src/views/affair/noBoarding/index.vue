<template>
  <div class="app-container">
    <section class="am-box am-mb" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">外宿申请</div>
      <div class="am-p">
        <el-form
          ref="boardForm"
          :rules="rules"
          :model="formData"
          label-position="right"
          label-width="110px"
          inline
        >
          <el-form-item label="学期" prop="term">
            <el-select size="small" v-model="formData.term" clearable>
              <el-option
                v-for="opt in termOpts"
                :key="opt.label"
                :value="opt.value"
                :label="opt.label"
              >
              </el-option>
              <!-- <el-option label="元旦" value="newYearDay"></el-option>
              <el-option label="清明节" value="qingMing "></el-option> -->
            </el-select>
          </el-form-item>
          <el-form-item label="外宿地址" prop="address">
            <el-input size="small" v-model="formData.address" clearable></el-input>
          </el-form-item>
          <el-form-item label="外宿原因" prop="reason">
            <el-input size="small" v-model="formData.reason" clearable></el-input>
          </el-form-item>
          <el-form-item label="家长联系方式" prop="connectMethod">
            <el-input size="small" v-model="formData.connectMethod" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
            <el-button el-button size="small" disabsled @click="handleResetForm">重置</el-button>
            <el-button size="small" type="primary" @click="handleSubmitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b" v-if="roleName === '学生'">历史外宿申请记录</div>
      <div class="am-p am-title am-bd-b" v-else>外宿申请记录</div>
      <div class="am-p">
        <el-table v-loading="tableLoading" :data="tableData" height="382px" highlight-current-row>
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
          <el-table-column label="操作" min-width="140" fixed="right">
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
                v-hasPermi="['affair:noboard:approve']"
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
                  :disabled="scope.row.status !== 1"
                  slot="reference"
                >
                  <!-- :disabled="scope.row.status !== 1" -->
                撤销</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!-- <Pagination
          :total="total"
          :page-range="[10, 15, 20]"
          :current-page="currentPage"
          :page-size="pageSize"
          @onPaginationUpdate="handlePaginationUpdate"
        >
        </Pagination> -->
      </div>
    </section>
    <el-dialog title="外宿申请审批" width="30%" :visible.sync="dgDealVisible">
      <el-form :model="dealResult" label-width="80px">
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
    <el-dialog title="外宿申请审批" :visible.sync="dgEditVisible">
      <el-form
          ref="dialogForm"
          :rules="rules"
          :model="editData"
          label-position="right"
          label-width="110px"
          inline
        >
          <el-form-item label="学期" prop="term">
            <el-select size="small" v-model="editData.term" clearable>
              <el-option
                v-for="opt in termOpts"
                :key="opt.label"
                :value="opt.value"
                :label="opt.label"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="外宿地址" prop="address">
            <el-input size="small" v-model="editData.address" clearable></el-input>
          </el-form-item>
          <el-form-item label="外宿原因" prop="reason">
            <el-input size="small" v-model="editData.reason" clearable></el-input>
          </el-form-item>
          <el-form-item label="家长联系方式" prop="connectMethod">
            <el-input size="small" v-model="editData.connectMethod" clearable></el-input>
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
import * as BOARD from '@/api/affair/board.js'
import { termOptions } from '@/libs/utils.js'
import Pagination from '../../components/Pagination.vue'
import { mapState } from 'vuex'

export default {
  components: { Pagination },
  data () {
    return {
      // total: 0,
      // currentPage: 1,
      // pageSize: 10,
      dgDealVisible: false,
      dealResult: null,
      dealRuquest: {},
      dgEditVisible: false,
      editData: {
        term: '',
        address: '',
        reason: '',
        connectMethod: ''
      },
      formData: {
        term: '',
        address: '',
        reason: '',
        connectMethod: ''
      },
      tableLoading: false,
      rules: {
        term: [ { required: true, message: '请选择外宿学期', trigger: 'blur' } ],
        address: [ { required: true, message: '请输入外宿地址', trigger: 'blur' } ],
        reason: [ { required: true, message: '请输入外宿原因', trigger: 'blur' } ],
        connectMethod: [ { required: true, message: '请输入家长联系方式', trigger: 'blur' } ]
      },
      tableData: [],
      tableColumns: [
        { prop: 'term', label: '学期', minWidth: '80' },
        { prop: 'address', label: '外宿地址', minWidth: '80' },
        { prop: 'reason', label: '外宿原因', minWidth: '120' },
        { prop: 'connectMethod', label: '家长联系方式', minWidth: '120' }
      ]
    }
  },
  computed: {
    // tableHeight () {
    //   return this.total > this.currentPage ? '320px' : 'calc(320px + 40px)'
    // },
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    }),
    termOpts () {
      return termOptions(this.userName)
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
          instructorId: this.roleName === '超级管理员' ? '' : this.userName
        }
        BOARD.getInsBoard(param).then( res => {
          if (res.data && res.data.length !== 0){
            this.tableData = res.data
          } else {
            this.tableData = []
          }
        }).finally( () => {
          this.tableLoading = false
        })
      } else{
        //    超管账号、学生账号
        let param = {
          studentId: this.roleName === '超级管理员' ? '' : this.userName
        }
        BOARD.getBoard(param).then( res => {
          if (res.data && res.data.length !== 0){
            this.tableData = res.data
          } else {
            this.tableData = []
          }
        }).finally( () => {
          this.tableLoading = false
        })
      }
    },
    //    重置表单
    handleResetForm () {
      this.$refs.boardForm.resetFields()
    },
    //    提交表单
    handleSubmitForm () {
      this.$refs.boardForm.validate( valid => {
        if (valid) {
          // TODO 提交申请
          let param = this.formData
          param = Object.assign(param, {
            studentId: this.userName
          })
          BOARD.addBoard(param).then( res => {
            if (res.msg === '操作成功') {
              this.$message.success('提交成功')
              this.getInfo()
            } else {
              this.$message.error('提交失败')
            }
          }).finally( () => {
            this.$refs.boardForm.resetFields()
          })
        }
      })
    },
    handleDealDialog () {
      let param = {
        boardId: this.dealRuquest.boardId,
        status: this.dealResult
      }
      BOARD.setStatus(param).then( res => {
        if (res.msg === '操作成功'){
          this.$message.success('审批成功')
          this.dgDealVisible = false
          this.getInfo()
        } else {
          this.$message.error('审批失败')
        }
      })
    },
    handleEditDialog () {
      this.$refs.dialogForm.validate( valid => {
        if (valid) {
          let param = this.editData
          param = Object.assign(param, {
            studentId: this.userName
          })
          BOARD.editBoard(param).then( res => {
            if (res.msg === '操作成功') {
              this.$message.success('申请修改成功')
              this.getInfo()
              this.dgEditVisible = false
            } else {
              this.$message.error('申请修改失败')
            }
          })
        }
      })
      
    },
    //    表格编辑按钮
    handleEdit(row) {
      this.editData = row
      this.dgEditVisible = true
    },
    //    表格审批按钮
    handleDeal (row) {
      this.dgDealVisible = true
      this.dealRuquest = row
      console.log(this.dealRuquest)
    },
    //    表格撤销按钮
    handleCancel(row) {
      BOARD.deleteBoard(row.boardId).then( res => {
        if (res.msg === '操作成功'){
          this.$message.success('撤销成功')
          this.getInfo()
        } else {
          this.$message.error('撤销失败')
        }
      })
    },
    // handlePaginationUpdate (param) {
    //   this.currentPage = param.pageNum
    //   this.pageSize = param.pageSize
    // }
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