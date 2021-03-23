<template>
  <div class="app-container">
    <section class="am-box" v-if="roleName === '学生'">
      <div class="am-p am-title am-bd-b">国家励志奖学金申请信息</div>
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
          label-width="135px"
          inline
        >
          <el-form-item label="是否符合评选条件" prop="isFit">
            <el-radio-group size="small" v-model="addData.isFit">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="困难认定等级" prop="diffRank">
            <el-select size="small" v-model="addData.diffRank">
                <el-option label="一般困难" value="一般困难"></el-option>
                <el-option label="比较困难" value="比较困难"></el-option>
                <el-option label="特殊困难" value="特殊困难"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="专业人数" prop="profeSum">
            <el-input size="small" v-model.number="addData.profeSum" clearable></el-input>
          </el-form-item>
          <section class="am-my">
            <div class="form-title am-bd-t am-py">体质测试：</div>
            <el-form-item label="体测成绩" prop="sport">
              <el-input size="small" v-model.number="addData.sport" clearable></el-input>
            </el-form-item>
          </section>
          <section class="am-mb">
            <div class="form-title am-bd-t am-py">综合测评在专业内排名：</div>
            <el-form-item label="排名" prop="multipRank">
              <el-input size="small" v-model.number="addData.multipRank" clearable></el-input>
            </el-form-item>
            <el-form-item label="排名百分比" prop="multipRange">
              <el-input size="small" v-model.number="addData.multipRange" clearable></el-input>
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
    <section class="am-box">
      <div class="am-p am-title am-bd-b">国家励志奖学金申请信息</div>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import * as MULTIP from '@/api/grade/multipGrade.js'

export default {
  data () {
    return {
      addData: {
        isFit: 1,
        diffRank: '',
        profeSum: '',
        sport: '',
        // gradeRank: '',
        // gradeRange: '',
        multipRank: '',
        multipRange: '',
        provincePrize: '',
        schoolPrize: ''
      },
      addRules: {
        isFit: [ {required: true, message: '请选择是否符合评选条件', trigger: 'blur'} ],
        diffRank: [ {required: true, message: '请选择困难认定等级', trigger: 'blur'} ],
        profeSum: [
          {required: true, message: '请输入专业人数', trigger: 'blur'},
          { type: 'number', message: '专业人数必须为数字值'}
        ],
        sport: [
          {required: true, message: '请输入体质测试成绩', trigger: 'blur'},
          { type: 'number', message: '成绩必须为数字值'}
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
          { type: 'number', message: '排名必须为数字值'}
        ],
        multipRange: [
          {required: true, message: '请输入排名百分比', trigger: 'blur'},
          { type: 'number', message: '排名百分比必须为数字值'}
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
    })
  },
  created () {
    if (this.roleName === '学生') {
      MULTIP.getAllGrade({ studentId: this.userName }).then( res => {
        if (res.rows && res.rows.length !== 0) {
          let temp = res.rows[0].multipRank / res.rows[0].profeSum
          this.isDisabled = temp >= 0.5 ? true : false
          if (this.isDisabled) {
            this.disabledReason = '*您的综测排名不足前30%，无法申请'
          }
        }
      })
    }
  },
  methods: {
    handleResetAddForm () {
      this.$refs.addForm.resetFields()
    },
    handleSubmitAddForm () {
      this.$refs.addForm.validate( valid => {
        if (valid) {}
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
</style>