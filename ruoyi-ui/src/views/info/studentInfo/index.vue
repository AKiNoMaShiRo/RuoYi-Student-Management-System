<template>
  <div class="app-container">
    <section class="am-box am-mb">
      <div class="am-p am-title am-bd-b">基本信息</div>
      <div class="am-p base-info am-flex">
        <section class="left am-flex-center">
          <el-image class="stu-photo" style="width: 110px; height: 150px"></el-image>
        </section>
        <section class="right am-flex-align-center">
          <div>
            <div class="info-content">
              <span>院系:</span>
              <span class="am-ml-lg">{{baseInfo.department}}</span>
            </div>
            <div class="info-content">
              <span>专业:</span>
              <span class="am-ml-lg">{{baseInfo.profession}}</span>
            </div>
            <div class="info-content">
              <span>班级:</span>
              <span class="am-ml-lg">{{baseInfo.classNum}}</span>
            </div>
            <div class="info-content">
              <span>学号:</span>
              <span class="am-ml-lg">{{baseInfo.studentId}}</span>
            </div>
            <div class="info-content" style="margin-bottom: 0px;">
              <span>姓名:</span>
              <span class="am-ml-lg">{{baseInfo.name}}</span>
            </div>
          </div>
        </section>
      </div>
    </section>
        
    <section class="am-box">
      <div class="am-p am-bd-b">
        <el-button size="small" icon="el-icon-close" disabsled @click="clearForm"> 清空</el-button>
        <el-button size="small" icon="el-icon-refresh" disabsled @click="resetForm"> 重置</el-button>
        <el-button size="small" icon="el-icon-check" type="primary" @click="submitForm"> 提交</el-button>
      </div>
      <div class="am-p">
        <el-form ref="infoForm" label-width="120px" :model="formData" inline>
          <el-form-item label="毕业意向调查" prop="graduateIntention">
            <el-select size="small" v-model="formData.graduateIntention" clearable>
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
            <el-select size="small" v-model="formData.registration" clearable>
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
            <el-input size="small" v-model="formData.teacher" clearable></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phoneNumber">
            <el-input size="small" v-model="formData.phoneNumber" clearable></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="email">
            <el-input size="small" v-model="formData.email" clearable></el-input>
          </el-form-item>
          <el-form-item label="QQ" prop="qq">
            <el-input size="small" v-model="formData.qq" clearable></el-input>
          </el-form-item>
          <el-form-item label="微信" prop="weChat">
            <el-input size="small" v-model="formData.weChat" clearable></el-input>
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker size="small" v-model="formData.birthday" type="date" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="民族" prop="nation">
            <el-select size="small" v-model="formData.nation" clearable>
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
            <el-select size="small" v-model="formData.politicsStatus" clearable>
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
            <el-date-picker size="small" v-model="formData.joinTime" type="month" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="生源地区" prop="studentOrigin">
            <el-cascader
              size="small"
              v-model="formData.studentOrigin"
              :options="regionData"
              :show-all-levels="false"
              clearable
            >
            </el-cascader>
          </el-form-item>
          <el-form-item label="家庭地址" prop="familyAddress">
            <el-input size="small" v-model="formData.familyAddress" clearable></el-input>
          </el-form-item>
          <el-form-item label="家庭电话" prop="familyPhoneNumber">
            <el-input size="small" v-model="formData.familyPhoneNumber" clearable></el-input>
          </el-form-item>
          <el-form-item label="家庭联系人" prop="familyContacts">
            <el-input size="small" v-model="formData.familyContacts" clearable></el-input>
          </el-form-item>
          <el-form-item label="家庭邮编" prop="familyPostcode">
            <el-input size="small" v-model="formData.familyPostcode" clearable></el-input>
          </el-form-item>
          <el-form-item label="毕业中学" prop="graduateSchool">
            <el-input size="small" v-model="formData.graduateSchool" clearable></el-input>
          </el-form-item>
          <el-form-item label="中学邮编" prop="graduateSchoolPostcode">
            <el-input size="small" v-model="formData.graduateSchoolPostcode" clearable></el-input>
          </el-form-item>
          <el-form-item label="曾用名" prop="usedName">
            <el-input size="small" v-model="formData.usedName" clearable></el-input>
          </el-form-item>
          <el-form-item label="籍贯" prop="nativePlace">
            <el-cascader
              size="small"
              v-model="formData.nativePlace"
              :options="regionData"
              :show-all-levels="false"
              clearable
            >
            </el-cascader>
          </el-form-item>
          <el-form-item label="医保卡号" prop="healthCard">
            <el-input size="small" v-model="formData.healthCard" clearable></el-input>
          </el-form-item>
          <el-form-item label="四级成绩" prop="cet4">
            <el-input size="small" v-model="formData.cet4" clearable></el-input>
          </el-form-item>
          <el-form-item label="六级成绩" prop="cet6">
            <el-input size="small" v-model="formData.cet6" clearable></el-input>
          </el-form-item>
          <el-form-item label="英文名" prop="englishName">
            <el-input size="small" v-model="formData.englishName" clearable></el-input>
          </el-form-item>
          <el-form-item label="信息上报项目" prop="reportItem">
            <el-select size="small" v-model="formData.reportItem" clearable>
              <el-option label="建档立卡学生" value="yes"></el-option>
              <el-option label="非建档立卡学生" value="no"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
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
import { getStuInfo, editStuInfo } from '@/api/info/stuInfo'

export default {
  name: 'StudentInfo',
  data () {
    return {
      nationNames: nationOptions,
      politicsStatusOptions: politicsStatusOptions,
      graduateIntentionTypes: graduateIntentionTypes,
      registrationStates: registrationStates,
      regionData: regionData,
      baseInfo: {
        department: '',
        profession: '',
        classNum: '',
        studentId: '',
        name: ''
      },
      formData: {
        // department: '',
        // classNum: '',
        // studentId: '',
        // name: '',
        graduateIntention: '',
        registration: '',
        teacher: '',
        phoneNumber: '',
        email: '',
        qq: '',
        weChat: '',
        birthday: '',
        nation: '',
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
        for (let key in this.baseInfo) {
          this.baseInfo[key] = res.data[key]
        }
        this.formData = res.data
        this.formData.studentOrigin = [res.data.studentOriginP, res.data.studentOriginC, res.data.studentOriginA]
        this.formData.nativePlace = [res.data.nativePlaceP, res.data.nativePlaceC, res.data.nativePlaceA]
      }
    })
  },
  methods: {
    clearForm () {
      this.$refs.infoForm.resetFields()
    },
    resetForm () {
      // this.$refs.infoForm.resetFields()
      getStuInfo('20171344054').then(res => {
        this.formData = {}
        if(res && res.data){
          this.formData = res.data
          this.formData.studentOrigin = [res.data.studentOriginP, res.data.studentOriginC, res.data.studentOriginA]
        this.formData.nativePlace = [res.data.nativePlaceP, res.data.nativePlaceC, res.data.nativePlaceA]
          this.$message.success('已重置')
        }
      })
    },
    submitForm () {
      let param = {}
      param = this.formData
      param.studentOriginP = this.formData.studentOrigin[0]
      param.studentOriginC = this.formData.studentOrigin[1]
      param.studentOriginA = this.formData.studentOrigin[2]
      param.nativePlaceP = this.formData.nativePlace[0]
      param.nativePlaceC = this.formData.nativePlace[1]
      param.nativePlaceA = this.formData.nativePlace[2]
      editStuInfo(this.formData).then(res => {
        if(res && res.msg === '操作成功'){
          this.$message.success('修改成功')
        } else {
          this.$$message.error('修改失败')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.base-info{
  flex: 1;
  .left{
    width: 30%;
  }
  .right{
    width: 70%;
    .info-content{
      margin-bottom: 12px;
      span {
        color: #606266;
        font-size: 14px;
      }
    }
  }
}
.stu-photo{
  border-radius: 4px;
  border: 1px solid #dfe4ed;
}
</style>
