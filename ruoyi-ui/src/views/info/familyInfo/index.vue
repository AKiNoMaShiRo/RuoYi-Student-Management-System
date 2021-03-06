<template>
  <div class="app-container">
    <section class="am-box am-mb">
      <div class="am-title am-p am-bd-b">添加家庭成员信息</div>
      <div class="am-p">
        <el-form ref="infoForm" label-width="80px" :model="formData" :rules="rules" inline>
          <el-form-item label="称谓" prop="appellation">
            <el-input size="small" v-model="formData.appellation" clearable></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="memberName">
            <el-input size="small" v-model="formData.memberName" clearable></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="identityCard">
            <el-input size="small" v-model="formData.identityCard" clearable></el-input>
          </el-form-item>
          <el-form-item label="健康状况" prop="health">
            <el-select size="small" v-model="formData.health" clearable>
              <el-option
                v-for="healthState in healthStates"
                :key="healthState.v"
                :label="healthState.l"
                :value="healthState.v"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位名称" prop="company">
            <el-input size="small" v-model="formData.company" clearable></el-input>
          </el-form-item>
          <el-form-item label="职位" prop="duty">
            <el-input size="small" v-model="formData.duty" clearable></el-input>
          </el-form-item>
          <el-form-item label="邮编" prop="postCode">
            <el-input size="small" v-model="formData.postCode" clearable></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phoneNumber">
            <el-input size="small" v-model="formData.phoneNumber" clearable></el-input>
          </el-form-item>
          <el-form-item label="政治面貌" prop="politics_status">
            <el-select size="small" v-model="formData.politics_status" clearable>
              <el-option
                v-for="politicsStatusOption in politicsStatusOptions"
                :key="politicsStatusOption.v"
                :label="politicsStatusOption.l"
                  :value="politicsStatusOption.v"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input size="small" v-model="formData.remark" clearable></el-input>
          </el-form-item>
          <el-form-item label=" ">
            <el-button size="mini" disabsled @click="resetForm">重置</el-button>
            <el-button size="mini" type="primary" @click="submitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>
    <section class="am-box">
      <div class="am-title am-p am-bd-b">家庭成员信息</div>
      <div class="am-p">
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          height="320px"
        >
          <el-table-column
            v-for="column in tableColumns"
            v-bind="column"
            :key="column.prop"
            showOverflowTooltip
          >
            <template slot-scope="scope">
              {{ scope.row[column.prop] | dataFormatter(column.prop) }}
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="140" fixed="right">
            <template slot-scope="scope">
              <el-button
                type="text"
                icon="el-icon-edit"
                size="mini"
                style="margin-right: 6px;"
                @click="handleEdit(scope.row)"
              >
              编辑</el-button>
              <el-popconfirm
                confirm-button-text='删除'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="确定删除该成员信息？"
                @onConfirm="handleDelete(scope.row)"
              >
                <el-button
                    type="text"
                    icon="el-icon-delete"
                    size="mini"
                    slot="reference"
                  >
                  删除</el-button>
              </el-popconfirm>
              <!-- <el-popover
                placement="top"
                trigger="manual"
                style="display: inline-block; margin-left: 10px;"
                :value="deleteId === scope.row.memberId"
              >
                <p>确定删除该成员信息？</p>
                <div style="text-align: right; margin: 0">
                  <el-button size="mini" type="text" @click="deleteId = 0">取消</el-button>
                  <el-button type="primary" size="mini" @click="handleDelete(scope.row)">确定</el-button>
                </div>
                <el-button
                  type="text"
                  icon="el-icon-delete"
                  size="mini"
                  slot="reference"
                  @click="deleteId = scope.row.memberId"
                >
                删除</el-button>
              </el-popover> -->
            </template>
          </el-table-column>
        </el-table>
      </div>
    </section>
    <el-dialog title="家庭成员信息详情" :visible.sync="dgFromVisible">
      <el-form class="am-flex-center am-flex-wrap" :rules="rules" :model="dialogFormData" inline>
        <el-form-item label="称谓" prop="appellation" label-width="100px">
          <el-input size="small" v-model="dialogFormData.appellation"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="memberName" label-width="100px">
          <el-input size="small" v-model="dialogFormData.memberName"></el-input>
        </el-form-item>
        <el-form-item label="身份证" prop="identityCard" label-width="100px">
          <el-input size="small" v-model="dialogFormData.identityCard"></el-input>
        </el-form-item>
        <el-form-item label="健康状况" prop="health" label-width="100px">
          <el-select size="small" v-model="dialogFormData.health">
            <el-option
              v-for="healthState in healthStates"
              :key="healthState.v"
              :label="healthState.l"
              :value="healthState.v"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单位名称" prop="company" label-width="100px">
          <el-input size="small" v-model="dialogFormData.company"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="duty" label-width="100px">
          <el-input size="small" v-model="dialogFormData.duty"></el-input>
        </el-form-item>
        <el-form-item label="邮编" prop="postCode" label-width="100px">
          <el-input size="small" v-model="dialogFormData.postCode"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber" label-width="100px">
          <el-input size="small" v-model="dialogFormData.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="政治面貌" prop="politicsStatus" label-width="100px">
          <el-select size="small" v-model="dialogFormData.politicsStatus">
            <el-option
              v-for="politicsStatusOption in politicsStatusOptions"
              :key="politicsStatusOption.v"
              :label="politicsStatusOption.l"
              :value="politicsStatusOption.v"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark" label-width="100px">
          <el-input size="small" v-model="dialogFormData.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" label-width="100px">
        <el-button size="small" @click="handleDialogFormCancel">取消</el-button>
        <el-button size="small" type="primary" @click="handleDialogFormConform">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { politicsStatusOptions, healthStates } from '../../../libs/personalInfo'
import { getFamilyInfo, editFamilyInfo, deleteFamilyInfo, addFamilyInfo } from '@/api/info/familyInfo'
// import { mapGetters, mapState } from "vuex";
// import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data'

export default {
  name: 'FamilyInfo',
  data () {
    return {
      politicsStatusOptions: politicsStatusOptions,
      healthStates: healthStates,
      dgFromVisible: false,
      deleteId: 0,
      formData: {
        appellation: '',
        memberName: '',
        identityCard: '',
        health: '',
        company: '',
        duty: '',
        postCode: '',
        phoneNumber: '',
        politicsStatus: '',
        remark: ''
      },
      dialogFormData: {
        memberId: '',
        appellation: '',
        memberName: '',
        identityCard: '',
        health: '',
        company: '',
        duty: '',
        postCode: '',
        phoneNumber: '',
        politicsStatus: '',
        remark: ''
      },
      tableLoading: false,
      tableColumns: Object.freeze([
        { label: '姓名', prop: 'memberName', minWidth: '80', fixed: "left" },
        { label: '称谓', prop: 'appellation', minWidth: '80' },
        { label: '身份证', prop: 'identityCard', minWidth: '120' },
        { label: '健康状况', prop: 'health', minWidth: '80' },
        { label: '单位名称', prop: 'company', minWidth: '110' },
        { label: '职务', prop: 'duty', minWidth: '80' },
        { label: '邮编', prop: 'postCode', minWidth: '70' },
        { label: '电话号码', prop: 'phoneNumber', minWidth: '120' },
        { label: '政治面貌', prop: 'politicsStatus', minWidth: '120' },
        { label: '备注', prop: 'remark', minWidth: '140' },
      ]),
      tableData: [
        // {
        //   appellation: '母亲',
        //   memberName: '黄玉珍',
        //   identity_card: '330823196409273645',
        //   health: '1',
        //   company: '',
        //   duty: '',
        //   post_code: '324100',
        //   phone_number: '18457052062',
        //   politics_status: '12',
        //   remark: ''
        // }
      ],
      rules: {
        memberName: [ { required: true, message: '请输入成员姓名', trigger: 'blur' } ]
      }
    }
  },
  computed: {
    // tableHeight () {
    //   // 面包屑50 tagsView34 app-container内边距20*2 标题42
    //   return this.$store.state.settings.tagsView ? `calc(100vh - 166px)` : `calc(100vh - 136px)`
    // },
    // formHeight () {
    //   // 面包屑50 tagsView34 app-container内边距20*2 标题42 按钮36+24
    //   return this.$store.state.settings.tagsView ? `calc(100vh - 226px)` : `calc(100vh - 196px)`
    // }
    // ...mapGetters(['sidebar']),
    // isSideBar () {
    //   return this.sidebar.open
    // }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.tableLoading = true
      getFamilyInfo('20171344054').then(res => {
        this.tableData = []
        if (res.data && res.data.length !== 0) {
          this.tableData = res.data
        }
      }).finally( () => {
        this.tableLoading = false
      } )
    },
    resetForm () {
      this.$refs.infoForm.resetFields()
    },
    submitForm () {
      this.$refs.infoForm.validate( ( valid ) => {
        if (valid) {
          let param = this.formData
          param = Object.assign(param, { relativeStu: '20171344054' })
          addFamilyInfo(param).then(res => {
            if (res.msg === '操作成功') {
              this.$message.success('添加成功')
              this.resetForm()
              this.getInfo('20171344054')
            } else {
              this.$message.error('添加失败')
            }
          }).catch( () =>{
            this.$message.error('添加失败')
          } )
        }
      } )
    },
    // 表格编辑按钮
    handleEdit (row) {
      this.dgFromVisible = true
      for (let key in this.dialogFormData){
        this.dialogFormData[key] = row[key]
      }
    },
    // 表格删除按钮
    handleDelete (row) {
      console.log(row)
      deleteFamilyInfo(row.memberId).then(res => {
        if (res.msg === '操作成功') {
          this.$message.success('删除成功')
          this.getInfo()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
    // 对话框取消按钮
    handleDialogFormCancel () {
      this.dgFromVisible = false
    },
    // 对话框确认按钮
    handleDialogFormConform () {
      editFamilyInfo(this.dialogFormData).then(res => {
        if (res.msg === '操作成功') {
          this.$message.success('修改成功')
          this.getInfo()
        } else {
          this.$message.error('修改失败');
        }
        this.dgFromVisible = false
      }).catch( () => {
        this.dgFromVisible = false
      })
    }
  },
  filters: {
    dataFormatter ( val, prop ) {
      if( val === '' || val === null ){
        return '--'
      } else if ( prop === 'health' ) {
        let res = ''
        healthStates.forEach(item => {
          if (item.v === val) {
            res = item.l
          }
        })
        return res
      } else if ( prop === 'politicsStatus' ){
        let res = ''
        politicsStatusOptions.forEach(item => {
          if (item.v === val) {
            res = item.l
          }
        })
        return res
      } else {
        return val
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>