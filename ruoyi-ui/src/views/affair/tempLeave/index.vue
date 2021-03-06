<template>
  <div class="app-container">
    <section class="am-box am-mb">
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
          <!-- <el-form-item label="姓名" prop="name">
            <el-input size="small" v-model="formData.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="学号" prop="student_id">
            <el-input size="small" v-model="formData.student_id" clearable></el-input>
          </el-form-item>
          <el-form-item label="学院" prop="department">
            <el-select size="small" v-model="formData.department" clearable>
              <el-option
                v-for="(department, index) in departments"
                :key="index"
                :label="department"
                :value="department"
                ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年级" prop="grade">
            <el-input size="small" v-model="formData.grade" clearable></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="profession">
            <el-input size="small" v-model="formData.profession" clearable></el-input>
          </el-form-item> -->
          <el-form-item label="请假原因" prop="reason">
            <el-input size="small" v-model="formData.reason" clearable></el-input>
          </el-form-item>
          <el-form-item label="请假时段" prop="timeRange">
            <el-date-picker
              v-model="formData.timeRange"
              type="datetimerange"
              size="small"
              range-separator="至"
              start-placeholder="请假开始时间"
              end-placeholder="请假结束时间"
              clearable
            >
              <!-- value-format="yyyy-MM-dd HH:00:00" -->
            </el-date-picker>
            <!-- <el-input size="small" v-model="formData.time_range" clearable></el-input> -->
          </el-form-item>
          <el-form-item label="任课老师" prop="teacher">
            <el-input size="small" v-model="formData.teacher" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
            <el-button el-button size="small" disabsled @click="handleResetForm">重置</el-button>
            <el-button size="small" type="primary" @click="handleSubmitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b">历史请假记录</div>
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
import { getTempLeave, addTempLeave } from '@/api/info/tempLeave.js'
import { DEPARTMENTS } from '@/libs/teachUnit.js'
import Pagination from '../../components/Pagination.vue'

export default {
  components: { Pagination },
  data () {
    return {
      expandRowKeys: [],    //展开行的数组
      departments: DEPARTMENTS,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      formData: {
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
        timeRange: [ { required: true, message: '请输入请假时间', trigger: 'blur' } ],
        teacher: [ { required: true, message: '请输入任课教师姓名', trigger: 'blur' } ]
      },
      tableLoading: false,
      tableColumns: [
        { prop: 'leaveStartTime', label: '开始时间', minWidth: '' },
        { prop: 'leaveEndTime', label: '结束时间', minWidth: '' },
        { prop: 'reason', label: '请假原因', minWidth: '' },
        { prop: 'teacher', label: '任课老师', minWidth: '' },
        { prop: 'status', label: '审批状态', minWidth: '' }
      ],
      tableData: []
    }
  },
  computed: {
    tableHeight () {
      return this.total > this.currentPage ? '320px' : 'calc(320px + 40px)'
    }
  },
  methods: {
    handleResetForm () {
      this.$refs.tempForm.resetFields()
    },
    handleSubmitForm () {
      this.$refs.tempForm.validate(valid => {
        if (valid) {
          let param = {
            studentId: '20171344054',
            ...this.formData,
            leaveStartTime: this.formData.timeRange[0],
            leaveEndTime: this.formData.timeRange[1]
          }
          // console.log(param)
          addTempLeave(param).then( res => {
            if ( res.msg === '操作成功' ) {
              this.$message.success('申请提交成功')
            } else {
              this.$message.error('申请提交失败')
            }
          })
        }
      })
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.currentPage
      this.pageSize = param.pageSize
    }
  }
}
</script>