<template>
  <div class="app-container">
    <section class="am-inline-form">
      <el-form ref="infoForm" label-width="180px" :model="formData" :inline="true">
        <el-row type="flex" justify="space-between" align="bottom">
          <el-col :span="20" :xs="30">
            <el-form-item label="院系" prop="department">
              <el-input v-model="formData.department" disabled></el-input>
            </el-form-item>
            <el-form-item label="班级" prop="class">
              <el-input v-model="formData.class" disabled></el-input>
            </el-form-item>
            <el-form-item label="学号" prop="student_id">
              <el-input v-model="formData.number" disabled></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="formData.name" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10" :xs="30">
            <div class="am-flex-center am-mb-lg am-mr" style="width: 360px">
              <el-image class="stu-photo" style="width: 110px; height: 150px"></el-image>
            </div>
          </el-col>
        </el-row>
        <el-row type="flex" justify="space-between">
          <el-col :span="30" :xs="30">
            <el-form-item label="毕业意向调查" prop="graduate_intention">
              <el-select v-model="formData.graduateIntention" clearable>
                <el-option
                  v-for="graduateIntentionType in graduateIntentionTypes"
                  :key="graduateIntentionType.v"
                  :label="graduateIntentionType.l"
                  :value="graduateIntentionType.v"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="是否已缴费注册" prop="registration">
              <el-select v-model="formData.registration" clearable>
                <el-option
                  v-for="registrationState in registrationStates"
                  :key="registrationState.v"
                  :label="registrationState.l"
                  :value="registrationState.v"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="导师姓名" prop="teacher">
              <el-input v-model="formData.teacher" clearable></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone_number">
              <el-input v-model="formData.phoneNumber" clearable></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="formData.email" clearable></el-input>
            </el-form-item>
            <el-form-item label="QQ" prop="qq">
              <el-input v-model="formData.QQ" clearable></el-input>
            </el-form-item>
            <el-form-item label="微信" prop="we_chat">
              <el-input v-model="formData.weChat" clearable></el-input>
            </el-form-item>
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker v-model="formData.birthday" type="date" clearable></el-date-picker>
            </el-form-item>
            <el-form-item label="民族" prop="nation">
              <el-select v-model="formData.nation" clearable>
                <el-option
                  v-for="(nation, index) in nationNames"
                  :key="index"
                  :label="nation"
                  :value="String(index)"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="政治面貌" prop="politics_status">
              <el-select v-model="formData.politicsStatus" clearable>
                <el-option
                  v-for="politicsStatusOption in politicsStatusOptions"
                  :key="politicsStatusOption.v"
                  :label="politicsStatusOption.l"
                  :value="politicsStatusOption.v"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="加入时间" prop="join_time">
              <el-date-picker v-model="formData.joinTime" type="month" clearable></el-date-picker>
            </el-form-item>
            <el-form-item label="生源地区" prop="student_origin">
              <el-cascader v-model="formData.studentOrigin" :options="regionData" @change="handleChange" clearable>
              </el-cascader>
            </el-form-item>
            <el-form-item label="家庭地址" prop="family_address">
              <el-input v-model="formData.familyAddress" clearable></el-input>
            </el-form-item>
            <el-form-item label="家庭电话" prop="family_phone_number">
              <el-input v-model="formData.familyPhoneNumber" clearable></el-input>
            </el-form-item>
            <el-form-item label="家庭联系人" prop="family_contacts">
              <el-input v-model="formData.familyContacts" clearable></el-input>
            </el-form-item>
            <el-form-item label="家庭邮编" prop="family_postcode">
              <el-input v-model="formData.familyPostcode" clearable></el-input>
            </el-form-item>
            <el-form-item label="毕业中学" prop="graduate_school">
              <el-input v-model="formData.graduateSchool" clearable></el-input>
            </el-form-item>
            <el-form-item label="中学邮编" prop="graduate_school_postcode">
              <el-input v-model="formData.graduateSchoolPostcode" clearable></el-input>
            </el-form-item>
            <el-form-item label="曾用名" prop="used_name">
              <el-input v-model="formData.usedName" clearable></el-input>
            </el-form-item>
            <el-form-item label="籍贯" prop="native_place">
              <el-cascader
                v-model="formData.nativePlace"
                :options="regionData"
                :props="{ expandTrigger: 'hover' }"
                clearable
              >
              </el-cascader>
            </el-form-item>
            <el-form-item label="医保卡号" prop="health_card">
              <el-input v-model="formData.healthCard" clearable></el-input>
            </el-form-item>
            <el-form-item label="四级成绩" prop="cet4">
              <el-input v-model="formData.cet4" clearable></el-input>
            </el-form-item>
            <el-form-item label="六级成绩" prop="cet6">
              <el-input v-model="formData.cet6" clearable></el-input>
            </el-form-item>
            <el-form-item label="英文名" prop="english_name">
              <el-input v-model="formData.englishName" clearable></el-input>
            </el-form-item>
            <el-form-item label="信息上报项目" prop="report_item">
              <el-select v-model="formData.reportItem" v-bind="formData.reportItem" clearable>
                <el-option label="建档立卡学生" value="yes"></el-option>
                <el-option label="非建档立卡学生" value="no"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="space-between">
          <el-col :span="30" :xs="30">
            <el-form-item label=" ">
              <el-button disabsled @click="resetForm">重置</el-button>
              <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </section>
  </div>
</template>

<script>
import {
  nationOptions,
  politicsStatusOptions,
  graduateIntentionTypes,
  registrationStates
} from '../../../libs/personalInfo'
import { regionData } from 'element-china-area-data'
import { getStuInfo } from '@/api/info/stuInfo'

export default {
  name: 'StudentInfo',
  data () {
    return {
      nationNames: nationOptions,
      politicsStatusOptions: politicsStatusOptions,
      graduateIntentionTypes: graduateIntentionTypes,
      registrationStates: registrationStates,
      regionData: regionData,
      formData: {
        department: '',
        class: '',
        number: '',
        name: '',
        graduateIntention: '',
        registration: '',
        teacher: '',
        phoneNumber: '',
        email: '',
        QQ: '',
        weChat: '',
        birthday: '',
        nation: '0',
        politicsStatus: '',
        joinTime: '',
        studentOrigin: '',
        familyAddress: '',
        familyPhoneNumber: '',
        familyContacts: '',
        familyPostcode: '',
        graduateSchool: '',
        graduateSchoolPostcode: '',
        usedName: '',
        nativePlace: '',
        healthCard: '',
        cet4: '',
        cet6: '',
        englishName: '',
        reportItem: ''
      }
    }
  },
  created () {
    getStuInfo('20171344054').then(res => {
      this.formData = {}
      if(res && res.data){
        this.formData = res.data
      }
    })
  },
  methods: {
    resetForm () {
      getStuInfo('20171344054').then(res => {
      this.formData = {}
      if(res && res.data){
        this.formData = res.data
      }
    })
    },
    submitForm () {
    },
    handleChange (value) {
      // console.log(value)
    }
  }
}
</script>

<style>
.stu-photo{
  border-radius: 4px;
  border: 1px solid #dfe4ed;
}
</style>
