<template>
  <div class="app-container">
    <section class="am-box am-mb" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">节假日去向报备</div>
      <div class="am-p">
        <el-form
          ref="holidayForm"
          :rules="formData.destination === 'school' ? stayRules : leaveRules"
          :model="formData"
          label-position="right"
          label-width="80px"
          inline
        >
          <el-form-item label="节假日" prop="holidayType">
            <el-select size="small" v-model="formData.holidayType" clearable>
              <el-option
                v-for="option in holidayTypeOpts"
                :key="option.value"
                :label="option.label"
                :value="option.value"
              >
              </el-option>
              <!-- <el-option label="元旦" value="newYearDay"></el-option>
              <el-option label="清明节" value="qingMing "></el-option>
              <el-option label="劳动节" value="laborDay"></el-option>
              <el-option label="国庆节" value="nationalDay"></el-option>
              <el-option label="寒假" value="winter"></el-option>
              <el-option label="暑假" value="summer"></el-option> -->
            </el-select>
          </el-form-item>
          <el-form-item label="去向类别" prop="destination">
            <el-select size="small" v-model="formData.destination" clearable>
              <el-option
                v-for="option in destinationOpts"
                :key="option.value"
                :label="option.label"
                :value="option.value"
              >
              </el-option>
              <!-- <el-option label="回家" value="home"></el-option>
              <el-option label="外出" value="other"></el-option>
              <el-option label="留校" value="school"></el-option> -->
            </el-select>
          </el-form-item>
          <el-form-item label="离校时段" prop="timeRange">
            <el-date-picker
              v-model="formData.timeRange"
              :disabled="formData.destination === 'school'"
              type="daterange"
              size="small"
              value-format="yyyy-MM-dd"
              range-separator="-"
              start-placeholder="离校日期"
              end-placeholder="拟回校日期"
              clearable
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="外出地址" prop="address">
            <el-input
              size="small"
              v-model="formData.address"
              :disabled="formData.destination === 'school'"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input size="small" v-model="formData.remark" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
           <!-- style="margin-top: 6px; margin-left: 80px;"> -->
            <el-button el-button size="small" disabsled @click="handleResetForm">重置</el-button>
            <el-button size="small" type="primary" @click="handleSubmitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b" v-if="roleName === '学生'">历史节假日去向报备记录</div>
      <div class="am-p am-title am-bd-b" v-else>节假日去向报备记录</div>
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
            <template slot-scope="scope">
              {{ scope.row[column.prop] | dataFormatter(column.prop) }}
            </template>
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
                v-hasPermi="['affair:holiday:approve']"
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
    <el-dialog title="节假日去向报备审批" width="30%" :visible.sync="dgDealVisible">
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
    <el-dialog title="节假日去向报备信息修改" :visible.sync="dgEditVisible">
      <el-form
          ref="dialogForm"
          :rules="editData.destination === 'school' ? stayRules : leaveRules"
          :model="editData"
          label-position="right"
          label-width="80px"
          inline
        >
          <el-form-item label="节假日" prop="holidayType">
            <el-select size="small" v-model="editData.holidayType" clearable>
              <el-option
                v-for="option in holidayTypeOpts"
                :key="option.value"
                :label="option.label"
                :value="option.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="去向类别" prop="destination">
            <el-select size="small" v-model="editData.destination" clearable>
              <el-option
                v-for="option in destinationOpts"
                :key="option.value"
                :label="option.label"
                :value="option.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="离校时段" prop="timeRange">
            <el-date-picker
              v-model="editData.timeRange"
              :disabled="editData.destination === 'school'"
              type="daterange"
              size="small"
              value-format="yyyy-MM-dd"
              range-separator="-"
              start-placeholder="离校日期"
              end-placeholder="拟回校日期"
              clearable
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="外出地址" prop="address">
            <el-input
              size="small"
              v-model="editData.address"
              :disabled="editData.destination === 'school'"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input size="small" v-model="editData.remark" clearable></el-input>
          </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dgEditVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="handleEditDialog">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { HOLIDAYTYPEOPTS, DESTINATIONOPTS } from '@/libs/utils.js'
import * as HOLIDAY from '@/api/affair/holiday.js'
import Pagination from '../../components/Pagination.vue'
import { mapState } from 'vuex'

export default {
  components: { Pagination },
  data () {
    return {
      total: 0,
      currentPage: 1,
      pageSize: 10,
      dgDealVisible: false,
      dealResult: 2,
      dealRequest: {},
      dgEditVisible: false,
      formData: {
        goId: null,
        holidayType: '',
        destination: '',
        address: '',
        timeRange: ['', ''],
        remark: ''
      },
      searchFormData: {
        studentId: '',
        stuName: '',
        status: null
      },
      editData: {
        goId: null,
        holidayType: '',
        destination: '',
        address: '',
        timeRange: ['', ''],
        remark: ''
      },
      holidayTypeOpts: HOLIDAYTYPEOPTS,
      destinationOpts: DESTINATIONOPTS,
      leaveRules: {
        holidayType: [ { required: true, message: '请选择节假日类型', trigger: 'blur' } ],
        destination: [ { required: true, message: '请选择节假日去向', trigger: 'blur' } ],
        address: [ { required: true, message: '请输入外出地址', trigger: 'blur' } ],
        timeRange: [ { required: true, message: '请选择离校时段', trigger: 'blur' } ]
      },
      stayRules: {
        holidayType: [ { required: true, message: '请选择节假日类型', trigger: 'blur' } ],
        destination: [ { required: true, message: '请选择节假日去向', trigger: 'blur' } ]
      },
      tableLoading: false,
      tableData: [],
      tableColumnsStu: [
        { prop: 'holidayType', label: '节假日', minWidth: '80' },
        { prop: 'destination', label: '去向类别', minWidth: '80' },
        { prop: 'address', label: '外出地址', minWidth: '120' },
        { prop: 'holidayStartTime', label: '离校时间', minWidth: '120' },
        { prop: 'holidayEndTime', label: '拟回校时间', minWidth: '120' },
        { prop: 'remark', label: '备注', minWidth: '140' }
      ],
      tableColumnsIns: [
        { prop: 'studentId', label: '学号', minWidth: '110' },
        { prop: 'stuName', label: '姓名', minWidth: '80' },
        { prop: 'holidayType', label: '节假日', minWidth: '80' },
        { prop: 'destination', label: '去向类别', minWidth: '80' },
        { prop: 'address', label: '外出地址', minWidth: '120' },
        { prop: 'holidayStartTime', label: '离校时间', minWidth: '100' },
        { prop: 'holidayEndTime', label: '拟回校时间', minWidth: '100' },
        { prop: 'remark', label: '备注', minWidth: '140' }
      ]
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
  watch: {
    'formData.destination': {
      handler(val) {
        if (val !== '') {
          this.$refs.holidayForm.clearValidate()
          if (this.formData.destination === 'school') {
            this.formData.address = ''
            this.formData.timeRange = null
          }
        }
      },
      immediate: true
    },
    'editData.destination': {
      handler(newValue, oldValue) {
        if (oldValue !== newValue) {
          this.$refs[dialogForm].clearValidate()
          if (newValue === 'school') {
            this.editData.address = ''
            this.editData.timeRange = null
          }
        }
      }
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    //    获取表格数据
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
        HOLIDAY.getInsHoliday(param).then( res => {
          if (res.rows && res.rows.length !== 0) {
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
          ...this.searchFormData,
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          studentId: this.roleName === '超级管理员' ? '' : this.userName,
        }
        if (param.status === '') {
          param.status = 0
        }
        HOLIDAY.getHoliday(param).then( res => {
          if (res.rows && res.rows.length !== 0) {
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
    //    重置表单
    handleResetForm () {
      this.$refs.holidayForm.resetFields()
    },
    //    提交表单
    handleSubmitForm () {
      this.$refs.holidayForm.validate( valid => {
        if (valid) {
          // TODO 提交申请
          let param = this.formData
          param = Object.assign(param, {
            term: '2020-2021-2',
            holidayStartTime: this.formData.timeRange ? this.formData.timeRange[0] : null,
            holidayEndTime: this.formData.timeRange ? this.formData.timeRange[1] : null,
            studentId: this.userName
          })
          HOLIDAY.addHoliday(param).then( res => {
            if (res && res.msg === '操作成功') {
              this.$message.success('提交成功')
              this.getInfo()
            }
          }).finally(() => {
            this.$refs.holidayForm.resetFields()
          })
        }
      })
    },
    //    表格编辑按钮
    handleEdit (row) {
      this.editData = Object.assign(this.editData, row)
      if (row.holidayStartTime !== null && row.holidayEndTime !== null) {
        this.editData = Object.assign(this.editData, {
          timeRange: [ row.holidayStartTime, row.holidayEndTime ]
        })
      }
      this.dgEditVisible = true
    },
    //    编辑对话框按钮
    handleEditDialog () {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          HOLIDAY.editHoliday(this.editData).then( res => {
            if (res.msg === '操作成功') {
              this.$message.success('报备信息修改成功')
              this.getInfo()
              this.dgEditVisible = false
            } else {
              this.$message.error('报备信息修改失败')
            }
          })
        }
      })
    },
    //    表格审批按钮
    handleDeal (row) {
      this.dealRequest = row
      this.dgDealVisible = true
    },
    handleDealDialog () {
      let param = {
        status: this.dealResult,
        goId: this.dealRequest.goId
      }
      HOLIDAY.setStatus(param).then( res => {
        if (res && res.msg === '操作成功') {
          this.$message.success('审批完成')
          this.getInfo()
        } else {
          this.$message.error('审批失败')
        }
      }).finally( () => {
        this.dgDealVisible = false
      })
    },
    //    表格撤销按钮
    handleCancel(row) {
      HOLIDAY.deleteHoliday(row.goId).then( res => {
        if (res && res.msg === '操作成功') {
          this.$message.success('撤销成功')
          this.getInfo()
        } else {
          this.$message.error('撤销失败')
        }
      })
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.pageNum
      this.pageSize = param.pageSize
    },
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handleSearch () {
      this.getInfo()
    }
  },
  filters: {
    dataFormatter (val, prop) {
      if (val === '' || val === null || val === undefined) {
        return '--'
      } else if (prop === 'holidayType') {
        let res = ''
        HOLIDAYTYPEOPTS.forEach(item => {
          if (item.value === val) {
            res = item.label
          }
        })
        return res
      } else if (prop === 'destination') {
        let res = ''
        DESTINATIONOPTS.forEach(item => {
          if (item.value === val) {
            res = item.label
          }
        })
        return res
      } else {
        return val
      }
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