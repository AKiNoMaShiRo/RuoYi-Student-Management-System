<template>
  <section>
    <!-- <div class="am-bd-b am-px" style="margin-top: 6px;">
      <el-form
        ref="typeForm"
        label-width="80px"
        :model="typeFormData"
        :rules="typeRules"
        inline
      >
        <el-form-item label="添加考试类别" label-width="100px"></el-form-item>
        <el-form-item label="考试类别" prop="testType">
          <el-select v-model="typeFormData.testType" size="small" label-width="60px" clearable multiple collapse-tags>
            <el-option label="大学英语CET4" value="大学英语CET4"></el-option>
            <el-option label="大学英语CET6" value="大学英语CET6"></el-option>
            <el-option label="普通话等级考试" value="普通话等级考试"></el-option>
            <el-option label="计算机等级考试二级" value="计算机等级考试二级"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label=" ">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddType">添加</el-button>
        </el-form-item>
      </el-form>
    </div> -->
    <div v-if="roleName !== '辅导员'" class="am-bd-b am-px" style="margin-top: 6px;">
      <el-form
        ref="classForm"
        label-width="50px"
        :model="classFormData"
        :rules="classRules"
        inline
      >
        <el-form-item label="添加班级" label-width="80px"></el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="classFormData.profession" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-input v-model.number="classFormData.grade" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classNum">
          <el-input v-model.number="classFormData.classNum" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label=" ">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddClass">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="chart am-p am-flex" style="height: 300px;">
      <!-- 图表区域 -->
      <section class="left am-flex am-flex-justify-center">
        <!-- 考试类别 -->
        <el-checkbox-group v-model="testType" size="medium">
          <el-checkbox label="大学英语CET4" border></el-checkbox>
          <el-checkbox label="大学英语CET6" border></el-checkbox>
          <el-checkbox label="普通话等级考试" border></el-checkbox>
          <el-checkbox label="计算机等级考试二级" border></el-checkbox>
        </el-checkbox-group>
      </section>
      <section class="right">
        <test-bar-chart :xAxis="chartOptions.xAxis" :series="chartOptions.series" />
      </section>
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex'
import { getClassInfo } from '@/api/info/classInfo.js'
import TestBarChart from './TestBarChart'

const animationDuration = 6000

export default {
  components: {
    TestBarChart
  },
  data () {
    return {
      testType: [], //    选中的复选框的值，一个数组
      classFormData: {
        profession: '',
        grade: '',
        classNum: ''
      },
      classRules: {
        profession: [ { required: true, message: '请输入专业名称', trigger: 'blur' } ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' },
          { type: 'number', message: '年级必须为数字值'}
        ],
        classNum: [
          { required: true, message: '请输入班级序号', trigger: 'blur' },
          { type: 'number', message: '班级序号必须为数字值'}
        ],
      },
      authClass: [],
      chartOptions: {
        xAxis: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        series: [{
          name: 'pageA',
          type: 'bar',
          // stack: 'vistors',
          barWidth: '18',
          data: [79, 52, 200, 334, 390, 330, 220],
          animationDuration
        },{
          name: 'pageB',
          type: 'bar',
          // stack: 'vistors',
          barWidth: '18',
          data: [79, 52, 200, 334, 390, 330, 220],
          animationDuration
        },]
      }
    }
  },
  computed: {
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    })
  },
  watch: {
    testType (value) {
      // console.log(value)
    }
  },
  created () {
    let param = {}
    if (this.roleName === '辅导员') {
      param.instructorId = this.userName
    }
    getClassInfo(param).then( res => {
      if (res.rows && res.rows.length !== 0) {
        this.authClass = res.rows
        console.log(this.authClass)
      }
    })
  },
  methods: {
    // handleAddType () {
    //   this.$refs.typeForm.validate ( valid => {
    //     if (valid) {
    //       console.log(this.typeFormData)
    //     }
    //   })
    // },
    handleAddClass () {
      this.$refs.classForm.validate ( valid => {
        if (valid) {
          console.log(this.classFormData)
        }
      })
    },
    handleSearch () {}
  }
}
</script>

<style lang="scss" scoped>
.chart {
  flex: 1;
  .left {
    width: 20%;
    label {
      margin-left: 10px;
      margin-right: 10px;
      margin-bottom: 12px;
      width: 190px;
      display: flex;
      justify-content: center;
      ::v-deep .el-checkbox__input {
        display: none;
      }
      ::v-deep .el-checkbox__label {
        display: block;
      }
    }
  }
  .right {
    width: 80%;
  }
}
</style>