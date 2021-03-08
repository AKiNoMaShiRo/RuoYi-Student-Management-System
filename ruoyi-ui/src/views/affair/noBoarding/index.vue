<template>
  <div class="app-container">
    <section class="am-box am-mb">
      <div class="am-p am-title am-bd-b">节假日去向报备</div>
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
              <el-option label="元旦" value="newYearDay"></el-option>
              <el-option label="清明节" value="qingMing "></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="外宿地址" prop="address">
            <el-input size="small" v-model="formData.address" clearable></el-input>
          </el-form-item>
          <el-form-item label="外宿原因" prop="reason">
            <el-input size="small" v-model="formData.reason" clearable></el-input>
          </el-form-item>
          <el-form-item label="家长联系方式" prop="connection">
            <el-input size="small" v-model="formData.connection" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
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
import Pagination from '../../components/Pagination.vue'

export default {
  components: { Pagination },
  data () {
    return {
      total: 0,
      currentPage: 1,
      pageSize: 10,
      formData: {
        term: '',
        address: '',
        reason: '',
        connection: ''
      },
      tableLoading: false,
      rules: {
        term: [ { required: true, message: '请选择外宿学期', trigger: 'blur' } ],
        address: [ { required: true, message: '请输入外宿地址', trigger: 'blur' } ],
        reason: [ { required: true, message: '请输入外宿原因', trigger: 'blur' } ],
        connection: [ { required: true, message: '请输入家长联系方式', trigger: 'blur' } ]
      },
      tableData: [],
      tableColumns: [
        { prop: 'term', label: '学期', minWidth: '80' },
        { prop: 'address', label: '外宿地址', minWidth: '80' },
        { prop: 'reason', label: '外宿原因', minWidth: '120' },
        { prop: 'connection', label: '家长联系方式', minWidth: '120' }
      ]
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '320px' : 'calc(320px + 40px)'
    }
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