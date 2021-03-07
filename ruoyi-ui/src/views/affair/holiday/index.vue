<template>
  <div class="app-container">
    <section class="am-box am-mb">
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
              <el-option label="元旦" value="newYearDay"></el-option>
              <el-option label="清明节" value="qingMing "></el-option>
              <el-option label="劳动节" value="laborDay"></el-option>
              <el-option label="国庆节" value="nationalDay"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="去向类别" prop="destination">
            <el-select size="small" v-model="formData.destination" clearable>
              <el-option label="回家" value="home"></el-option>
              <el-option label="外出" value="other"></el-option>
              <el-option label="留校" value="school"></el-option>
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
      <div class="am-p am-title am-bd-b">历史节假日去向报备记录</div>
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
          <el-table-column label="操作" min-width="140" fixed="right">
            <template slot-scope="scope">
              <el-button
                type="text"
                icon="el-icon-edit"
                size="mini"
                style="margin-right: 6px;"
                @click="handleEdit(scope.row)"
              >
                <!-- :disabled="scope.row.status !== 1" -->
              编辑</el-button>
              <el-button
                type="text"
                icon="el-icon-edit-outline"
                size="mini"
                style="margin-right: 6px; margin-left: 0px;"
                @click="handleDeal(scope.row)"
              >
                <!-- :disabled="scope.row.status !== 1" -->
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
  </div>
</template>

<script>
import moment from 'moment'
import Pagination from '../../components/Pagination.vue'

export default {
  components: { Pagination },
  data () {
    return {
      total: 0,
      currentPage: 1,
      pageSize: 10,
      formData: {
        holidayType: '',
        destination: '',
        address: '',
        timeRange: null,
        remark: ''
      },
      tableLoading: false,
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
      tableData: [],
      tableColumns: [
        { prop: 'holidayType', label: '节假日', minWidth: '80' },
        { prop: 'destination', label: '去向类别', minWidth: '80' },
        { prop: 'address', label: '外出地址', minWidth: '120' },
        { prop: 'holidayStartTime', label: '离校时间', minWidth: '120' },
        { prop: 'holidayEndTime', label: '拟回校时间', minWidth: '120' },
        { prop: 'remark', label: '备注', minWidth: '140' }
      ]
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '320px' : 'calc(320px + 40px)'
    }
  },
  watch: {
    'formData.destination': {
      handler(val) {
        if (val !== '') {
          this.$refs.holidayForm.clearValidate()
        }
      },
      immediate: true
    }
  },
  created () {
  },
  methods: {
    //    重置表单
    handleResetForm () {
      this.$refs.holidayForm.resetFields()
    },
    //    提交表单
    handleSubmitForm () {
      this.$refs.holidayForm.validate( valid => {
        if (valid) {
          console.log('验证成功')
          // TODO 提交申请
          this.$refs.holidayForm.resetFields()
        }
      })
    },
    //    表格编辑按钮
    handleEdit(row) {},
    //    表格审批按钮
    handleEdit(row) {},
    //    表格撤销按钮
    handleEdit(row) {},
    handlePaginationUpdate (param) {
      this.currentPage = param.pageNum
      this.pageSize = param.pageSize
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