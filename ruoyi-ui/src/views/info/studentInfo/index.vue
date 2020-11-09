<template>
  <div class="app-container">
    <el-form ref="infoForm" :model="formData" :inline="true">
      <el-form-item label="院系" prop="department">
        <div></div>
        <el-input v-model="formData.department" disabled></el-input>
      </el-form-item>
      <el-form-item label="班级" prop="class">
        <div></div>
        <el-input v-model="formData.class" disabled></el-input>
      </el-form-item>
      <el-form-item label="学号" prop="number">
        <div></div>
        <el-input v-model="formData.number" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <div></div>
        <el-input v-model="formData.name" disabled></el-input>
      </el-form-item>
      <el-form-item label="毕业意向调查" prop="graduateIntention">
        <div></div>
        <el-select v-model="formData.graduateIntention">
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
        <div></div>
        <el-select v-model="formData.registration">
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
        <div></div>
        <el-input v-model="formData.teacher"></el-input>
      </el-form-item>
      <el-form-item label="手机" prop="phoneNumber">
        <div></div>
        <el-input v-model="formData.phoneNumber"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email">
        <div></div>
        <el-input v-model="formData.email"></el-input>
      </el-form-item>
      <el-form-item label="QQ" prop="QQ">
        <div></div>
        <el-input v-model="formData.QQ"></el-input>
      </el-form-item>
      <el-form-item label="微信" prop="weChat">
        <div></div>
        <el-input v-model="formData.weChat"></el-input>
      </el-form-item>
      <el-form-item label="出生日期" prop="birthday">
        <div></div>
        <el-date-picker v-model="formData.birthday" type="date"></el-date-picker>
      </el-form-item>
      <el-form-item label="民族" prop="nation">
        <div></div>
        <el-select v-model="formData.nation">
          <el-option
            v-for="(nation, index) in nationNames"
            :key="index"
            :label="nation"
            :value="String(index)"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="政治面貌" prop="politicsStatus">
        <div></div>
        <el-select v-model="formData.politicsStatus">
          <el-option
            v-for="politicsStatusOption in politicsStatusOptions"
            :key="politicsStatusOption.v"
            :label="politicsStatusOption.l"
            :value="politicsStatusOption.v"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="加入时间" prop="joinTime">
        <div></div>
        <el-date-picker v-model="formData.joinTime" type="month"></el-date-picker>
      </el-form-item>
      <el-form-item label="生源地区" prop="studentOrigin">
        <div></div>
        <el-cascader v-model="formData.studentOrigin" :options="regionData" @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="家庭地址" prop="familyAddress">
        <div></div>
        <el-input v-model="formData.familyAddress"></el-input>
      </el-form-item>
      <el-form-item label="家庭电话" prop="familyPhoneNumber">
        <div></div>
        <el-input v-model="formData.familyPhoneNumber"></el-input>
      </el-form-item>
      <el-form-item label="家庭联系人" prop="familyContacts">
        <div></div>
        <el-input v-model="formData.familyContacts"></el-input>
      </el-form-item>
      <el-form-item label="家庭邮编" prop="familyPostcode">
        <div></div>
        <el-input v-model="formData.familyPostcode"></el-input>
      </el-form-item>
      <el-form-item label="毕业中学" prop="graduateSchool">
        <div></div>
        <el-input v-model="formData.graduateSchool"></el-input>
      </el-form-item>
      <el-form-item label="中学邮编" prop="graduateSchoolPostcode">
        <div></div>
        <el-input v-model="formData.graduateSchoolPostcode"></el-input>
      </el-form-item>
      <el-form-item label="曾用名" prop="usedName">
        <div></div>
        <el-input v-model="formData.usedName"></el-input>
      </el-form-item>
      <el-form-item label="籍贯" prop="nativePlace">
        <div></div>
        <el-input v-model="formData.nativePlace"></el-input>
      </el-form-item>
      <el-form-item label="医保卡号" prop="healthCard">
        <div></div>
        <el-input v-model="formData.healthCard"></el-input>
      </el-form-item>
      <el-form-item label="四级成绩" prop="cet4">
        <div></div>
        <el-input v-model="formData.cet4"></el-input>
      </el-form-item>
      <el-form-item label="六级成绩" prop="cet6">
        <div></div>
        <el-input v-model="formData.cet6"></el-input>
      </el-form-item>
      <el-form-item label="英文名" prop="englishName">
        <div></div>
        <el-input v-model="formData.englishName"></el-input>
      </el-form-item>
      <el-form-item label="信息上报项目" prop="reportItem">
        <div></div>
        <el-select v-model="formData.reportItem">
          <el-option label="建档立卡学生" value="1"></el-option>
          <el-option label="非建档立卡学生" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button disabsled @click="resetForm">重置</el-button>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { nationOptions, politicsStatusOptions, graduateIntentionTypes, registrationStates } from '../formOption'
import { regionData, CodeToText, TextToCode } from 'element-china-area-data'

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
        department: '计算机与软件学院',
        class: '17软工2班',
        number: '20171344054',
        name: '王圣滋',
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
  methods: {
    resetForm () {
      this.$refs.infoForm.resetFields()
    },
    submitForm () {
    },
    handleChange (value) {
      // console.log(value)
    }
  }
}
</script>
