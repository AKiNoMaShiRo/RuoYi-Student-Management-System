<template>
  <div class="app-container">
    <InfoCollapse class="am-mb" :refresh="refreshCollapse"></InfoCollapse>
    <!-- <section class="am-box am-mb">
      <div class="am-p am-title am-bd-b">国家奖学金申请记录</div>
    </section> -->
    <section class="am-box" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">国家奖学金申请</div>
      <div class="am-p">
        <div class="am-bd-b am-pb am-mb">
          <el-button size="small" icon="el-icon-refresh" disabsled @click="handleResetAddForm">重置</el-button>
          <el-button size="small" icon="el-icon-check" type="primary" :disabled="isDisabled" @click="handleSubmitAddForm">提交</el-button>
          <div class="disable-reason">{{ disabledReason }}</div>
        </div>
        <el-form
          ref="addForm"
          :rules="addRules"
          :model="addData"
          label-position="right"
          label-width="100px"
          inline
        >
          <el-form-item label="是否破格" prop="isFit">
            <el-radio-group size="small" v-model="addData.isFit">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="2">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="专业人数" prop="profeSum">
            <el-input size="small" v-model.number="addData.profeSum" clearable></el-input>
          </el-form-item>
          <section class="am-my">
            <div class="form-title am-bd-t am-py">体质测试：</div>
            <el-form-item label="体测成绩" prop="sport">
              <el-input size="small" v-model="addData.sport" clearable></el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
            <div class="form-title am-bd-t am-py">必修与选修课折合成绩在专业内排名：</div>
            <el-form-item label="排名" prop="gradeRank">
              <el-input size="small" v-model.number="addData.gradeRank" clearable></el-input>
            </el-form-item>
            <el-form-item label="排名百分比" prop="gradeRange">
              <el-input size="small" v-model="addData.gradeRange" clearable></el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
          <div class="form-title am-bd-t am-py">综合测评在专业内排名：</div>
            <el-form-item label="排名" prop="multipRank">
              <el-input size="small" v-model.number="addData.multipRank" clearable></el-input>
            </el-form-item>
            <el-form-item label="排名百分比" prop="multipRange">
              <el-input size="small" v-model="addData.multipRange" clearable></el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
          <div class="form-title am-bd-t am-py">表彰或成果：</div>
            <div>
              <el-form-item label="省级及以上" prop="provincePrize">
                <el-input size="small" type="textarea" v-model.number="addData.provincePrize" :rows="5" clearable></el-input>
              </el-form-item>
            </div>
            <el-form-item label="校级" prop="schoolPrize">
              <el-input size="small" type="textarea" v-model.number="addData.schoolPrize" :rows="5" clearable></el-input>
            </el-form-item>
          </section>
        </el-form>
      </div>
    </section>
  </div>
</template>

<script>
import moment from 'moment'
import InfoCollapse from './components/infoCollapse'
import { mapState } from 'vuex'
import * as MULTIP from '@/api/grade/multipGrade.js'

export default {
  components: {
    InfoCollapse
  },
  data () {
    return {
      refreshCollapse: false,
      addData: {
        isFit: null,
        profeSum: '',
        sport: '',
        gradeRank: '',
        gradeRange: '',
        multipRank: '',
        multipRange: '',
        provincePrize: '',
        schoolPrize: ''
      },
      addRules: {
        isFit: [ {required: true, message: '请选择是否符合评选条件', trigger: 'blur'} ],
        profeSum: [
          {required: true, message: '请输入专业人数', trigger: 'blur'},
          { type: 'number', message: '专业人数必须为数字值'}
        ],
        sport: [
          {required: true, message: '请输入体质测试成绩', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              // if (!value) {
              //   callback(new Error('请输入体质测试成绩'))
              // } else {
                if (!this.isNumber(value)) {
                  callback(new Error('体质测试成绩必须为数字值'))
                } else {
                  callback()
                }
              // }
            },
            trigger: 'blur'
          },
          // { type: 'number', message: '成绩必须为数字值'}
        ],
        gradeRank: [
          {required: true, message: '请输入排名', trigger: 'blur'},
          { type: 'number', message: '排名必须为数字值'}
        ],
        gradeRange: [
          {required: true, message: '请输入排名百分比', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              // if (!value) {
              //   callback(new Error('请输入体质测试成绩'))
              // } else {
                if (!this.isNumber(value)) {
                  callback(new Error('排名百分比必须为数字值'))
                } else {
                  callback()
                }
              // }
            },
            trigger: 'blur'
          },
          // { type: 'number', message: '排名百分比必须为数字值'}
        ],
        multipRank: [
          {required: true, message: '请输入排名', trigger: 'blur'},
          { type: 'number', message: '排名必须为数字值'}
        ],
        multipRange: [
          {required: true, message: '请输入排名百分比', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              // if (!value) {
              //   callback(new Error('请输入体质测试成绩'))
              // } else {
                if (!this.isNumber(value)) {
                  callback(new Error('排名百分比必须为数字值'))
                } else {
                  callback()
                }
              // }
            },
            trigger: 'blur'
          },
          // { type: 'number', message: '排名百分比必须为数字值'}
        ],
        // provincePrize: [ {required: true, message: '请输入省级及以上表彰或成果', trigger: 'blur'} ],
        // schoolPrize: [ {required: true, message: '请输入校级表彰或成果', trigger: 'blur'} ]
      },
      isDisabled: false,
      disabledReason: ''
    }
  },
  computed: {
    ...mapState({
      roleName: state => state.user.roleName,
      userName: state => state.user.name
    }),
    learnYear () {
      let currentYear = parseInt(moment().format('yyyy'))
      let currentMonth = parseInt(moment().format('MM'))
      return currentMonth > 7 ? currentYear + '-' + (currentYear + 1) : (currentYear - 1) + '-' + currentYear
    }
  },
  created () {
    if (this.roleName === '学生') {
      MULTIP.getAllGrade({ studentId: this.userName }).then( res => {
        if (res.rows && res.rows.length !== 0) {
          let temp = res.rows[0].multipRank / res.rows[0].profeSum
          this.isDisabled = temp >= 0.3 ? true : false
          if (this.isDisabled) {
            this.disabledReason = '*您的综测排名不足前30%，无法申请'
          }
        }
      })
    }
  },
  methods: {
    isNumber (val) {
      var regPos = /^\d+(\.\d+)?$/
      var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/
      if (regPos.test(val) || regNeg.test(val)) {
        return true
      } else {
        return false
      }
    },
    handleResetAddForm () {
      this.$refs.addForm.resetFields()
    },
    handleSubmitAddForm () {
      this.$refs.addForm.validate( valid => {
        if (valid) {
          let param = {
            studentId: this.userName,
            ...this.addData
          }
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.scholarship-detail {
  color: #606266;
  font-size: 12px;
  font-weight: 400;
  line-height: 22px;
  .detail-title {
    font-size: 14px;
    font-weight: 500;
    line-height: 28px;
  }
}
.form-title {
  color: #606266;
  font-size: 16px;
}
::v-deep .el-textarea__inner {
  width: 505px;
}
.disable-reason {
  color: #ff4949;
  font-size: 12px;
  margin-top: 8px;
  margin-left: 16px;
  // margin-left: 135px;
  display: inline-block;
}
</style>