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
            <el-form-item label="姓名" prop="name">
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
            </el-form-item>
            <el-form-item label="请假原因" prop="reason">
              <el-input size="small" v-model="formData.reason" clearable></el-input>
            </el-form-item>
            <el-form-item label="请假时段" prop="time_range">
              <el-input size="small" v-model="formData.time_range" clearable></el-input>
            </el-form-item>
            <el-form-item label="任课老师" prop="teacher">
              <el-input size="small" v-model="formData.teacher" clearable></el-input>
            </el-form-item>
          <el-form-item label=" ">
            <el-button size="small" disabsled @click="handleResetForm">重置</el-button>
            <el-button size="small" type="primary" @click="handleSubmitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b">历史请假记录</div>
    </section>
  </div>
</template>

<script>
import { DEPARTMENTS } from '@/libs/teachUnit.js'

export default {
  data () {
    return {
      departments: DEPARTMENTS,
      formData: {
        name: '',
        student_id: '',
        department: '',
        grade: '',
        profession: '',
        reason: '',
        time_range: '',
        teacher: ''
      },
      rules: {
        name: [ { required: true, message: '请输入姓名', trigger: 'blur' } ],
        student_id: [ { required: true, message: '请输入学号', trigger: 'blur' } ],
        department: [ { required: true, message: '请选择院系', trigger: 'change' } ],
        grade: [ { required: true, message: '请输入年级', trigger: 'blur' } ],
        profession: [ { required: true, message: '请输入专业', trigger: 'blur' } ],
        reason: [ { required: true, message: '请输入请假原因', trigger: 'blur' } ],
        time_range: [ { required: true, message: '请输入请假时间', trigger: 'blur' } ],
        teacher: [ { required: true, message: '请输入任课教师姓名', trigger: 'blur' } ]
      }
    }
  },
  methods: {
    handleResetForm () {
      this.$refs.tempForm.resetFields()
    },
    handleSubmitForm () {}
  }
}
</script>