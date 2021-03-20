<template>
  <section>
    <div class="am-p am-title am-bd-b">等级考试通过率查询</div>
    <div class="am-p">
      <el-form
        ref="searchForm"
        label-width="80px"
        :model="searchFormData"
        :rules="searchRules"
        inline
      >
        <el-form-item label="考试类别" prop="testType">
          <el-select v-model="searchFormData.testType" size="small" clearable>
            <el-option label="国家英语四级" value="国家英语四级"></el-option>
            <el-option label="国家英语六级" value="国家英语六级"></el-option>
            <el-option label="计算机等级考试二级" value="计算机等级考试二级"></el-option>
            <el-option label="普通话等级考试" value="普通话等级考试"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="searchFormData.profession" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-input v-model.number="searchFormData.grade" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classNum">
          <el-input v-model.number="searchFormData.classNum" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label=" ">
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="am-p am-title am-bd-b">查询结果：{{ queryResult }}</div>
  </section>
</template>

<script>
import * as LEVELTEST from '@/api/grade/levelTest.js'

export default {
  data () {
    return {
      searchFormData: {
        testType: '',
        profession: '',
        grade: null,
        classNum: null
      },
      searchRules: {
        testType: [ {required: true, message: '请选择考试类别', trigger: 'blur'} ],
        // profession: [ {required: true, message: '请输入专业', trigger: 'blur'} ],
        grade: [ {type: 'number', message: '年级必须为数字值'} ],
        classNum: [ {type: 'number', message: '班级序号必须为数字值'} ],
      },
      queryResult: ''
    }
  },
  methods: {
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handleSearch () {
      this.$refs.searchForm.validate( valid => {
        if (valid) {
          console.log('searchFormData')
          console.log(this.searchFormData)
          let param = {
            testType: '',
            profession: '',
            grade: null,
            classNum: null
          }
          for (let key in this.searchFormData) {
            if (this.searchFormData[key] !== '') {
              param[key] = this.searchFormData[key]
            }
          }
          console.log('param')
          console.log(param)
          LEVELTEST.getPassRateByLT([param]).then( res => {
            if (res.data && res.data.length !== 0) {
              this.queryResult = res.data[0] + '%'
            }
          })
        }
      })
    }
  }
}
</script>