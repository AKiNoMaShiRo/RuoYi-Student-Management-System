<template>
  <div class="app-container">
    <InfoCollapse class="am-mb" :refresh="refreshCollapse"></InfoCollapse>
    <!-- <section class="am-box">
      <div class="am-p am-title am-bd-b">国家励志奖学金申请信息</div>
    </section> -->
    <section class="am-box" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">国家励志奖学金申请</div>
      <div class="am-p">
        <div class="am-bd-b am-pb am-mb">
          <el-button
            size="small"
            icon="el-icon-refresh"
            disabsled
            @click="handleResetAddForm"
          >重置</el-button>
          <el-button
            size="small"
            icon="el-icon-check"
            type="primary"
            :disabled="isDisabled"
            @click="handleSubmitAddForm"
          >提交</el-button>
          <div class="disable-reason">{{ disabledReason }}</div>
        </div>
        <el-form
          ref="addForm"
          :rules="addRules"
          :model="addData"
          label-position="right"
          label-width="110px"
          inline
        >
          <el-form-item label="是否破格" prop="isFit">
            <el-radio-group size="small" v-model="addData.isFit">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="2">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="困难认定等级" prop="diffLevel">
            <el-select size="small" v-model="addData.diffLevel">
                <el-option label="一般困难" value="一般困难"></el-option>
                <el-option label="比较困难" value="比较困难"></el-option>
                <el-option label="特别困难" value="特别困难"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="专业人数" prop="profeSum">
            <el-input size="small" v-model.number="addData.profeSum" clearable></el-input>
          </el-form-item>
          <section class="am-my">
            <div class="form-title am-bd-t am-py">体质测试：</div>
            <el-form-item label="体测成绩" prop="sportGrade">
              <el-input size="small" v-model="addData.sportGrade" clearable></el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
            <div class="form-title am-bd-t am-py">综合测评在专业内排名：</div>
            <el-form-item label="排名" prop="multipRank">
              <el-input size="small" v-model.number="addData.multipRank" clearable></el-input>
            </el-form-item>
            <el-form-item label="排名百分比" prop="multipRankRate">
              <el-input size="small" v-model="addData.multipRankRate" clearable>
                <template slot="append">%</template>
              </el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
            <div class="form-title am-bd-t am-py">表彰或成果：</div>
            <div>
              <el-form-item label="省级及以上" prop="porvincePrize">
                <el-input size="small" type="textarea" v-model.number="addData.porvincePrize" :rows="5" clearable></el-input>
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
import * as DIFFSTU from '@/api/scholarship/difficlutStu.js'
import * as EDV from '@/api/scholarship/edvScholarship.js'

export default {
  components: { InfoCollapse },
  data () {
    return {
      refreshCollapse: false,
      addData: {
        isFit: null,
        diffLevel: '',
        profeSum: '',
        sportGrade: '',
        // gradeRank: '',
        // gradeRange: '',
        multipRank: '',
        multipRankRate: '',
        porvincePrize: '',
        schoolPrize: ''
      },
      addRules: {
        isFit: [ {required: true, message: '请选择是否符合评选条件', trigger: 'blur'} ],
        diffLevel: [ {required: true, message: '请选择困难认定等级', trigger: 'blur'} ],
        profeSum: [
          {required: true, message: '请输入专业人数', trigger: 'blur'},
          { type: 'number', message: '专业人数必须为整数值'}
        ],
        sportGrade: [
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
        // gradeRank: [
        //   {required: true, message: '请输入排名', trigger: 'blur'},
        //   { type: 'number', message: '年级必须为数字值'}
        // ],
        // gradeRange: [
        //   {required: true, message: '请输入排名百分比', trigger: 'blur'},
        //   { type: 'number', message: '年级必须为数字值'}
        // ],
        multipRank: [
          {required: true, message: '请输入排名', trigger: 'blur'},
          { type: 'number', message: '排名必须为整数值'}
        ],
        multipRankRate: [
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
        // porvincePrize: [ {required: true, message: '请输入省级及以上表彰或成果', trigger: 'blur'} ],
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
      DIFFSTU.getIsDifficult({ studentId: this.userName }).then( res => {
        if (res.data && res.data.length != 0) {
          this.getMultipGrade()
        } else {
          this.isDisabled = true
          this.disabledReason = '*您不是困难生，无法申请'
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
    getMultipGrade () {
      return MULTIP.getAllGrade({ studentId: this.userName }).then( res => {
        if (res.rows && res.rows.length !== 0) {
          let temp = res.rows[0].multipRank / res.rows[0].profeSum
          this.isDisabled = temp >= 0.5 ? true : false
          if (this.isDisabled) {
            this.disabledReason = '*您的综测排名不足前50%，无法申请'
          }
        }
      })
    },
    handleResetAddForm () {
      this.$refs.addForm.resetFields()
    },
    handleSubmitAddForm () {
      this.$refs.addForm.validate( valid => {
        if (valid) {
          let param = {
            studentId: this.userName,
            learnYear: this.learnYear,
            ...this.addData
          }
          EDV.addEdv(param).then( res => {
            if (res.msg === '操作成功') {
              this.$message.success('申请提交成功')
              this.refreshCollapse = !this.refreshCollapse
              this.handleResetAddForm()
            } else {
              this.$message.error('申请提交失败')
            }
          })
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