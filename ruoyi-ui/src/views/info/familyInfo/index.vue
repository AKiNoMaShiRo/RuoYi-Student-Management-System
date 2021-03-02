<template>
  <div class="app-container">
    <section class="am-box am-mb">
      <div class="am-title am-p am-bd-b">添加家庭成员信息</div>
      <div class="am-p">
        <el-form ref="infoForm" label-width="80px" :model="formData" :rules="rules" inline>
          <el-form-item label="称谓" prop="appellation">
            <el-input size="small" v-model="formData.appellation"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input size="small" v-model="formData.name"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="identity_card">
            <el-input size="small" v-model="formData.identity_card"></el-input>
          </el-form-item>
          <el-form-item label="健康状况" prop="health">
            <el-select size="small" v-model="formData.health">
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
            <el-input size="small" v-model="formData.company"></el-input>
          </el-form-item>
          <el-form-item label="职位" prop="duty">
            <el-input size="small" v-model="formData.duty"></el-input>
          </el-form-item>
          <el-form-item label="邮编" prop="post_code">
            <el-input size="small" v-model="formData.post_code"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone_number">
            <el-input size="small" v-model="formData.phone_number"></el-input>
          </el-form-item>
          <el-form-item label="政治面貌" prop="politics_status">
            <el-select size="small" v-model="formData.politics_status">
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
            <el-input size="small" v-model="formData.remark"></el-input>
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
                @click="handleEdit(scope.row)"
              >
              编辑</el-button>
              <el-button
                type="text"
                icon="el-icon-delete"
                size="mini"
                @click="handleDelete(scope.row)"
              >
              删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </section>
    <el-dialog title="家庭成员信息详情" :visible.sync="dgFromVisible" class="am-dialog-form">
      <el-form class="am-flex-center am-flex-wrap" :model="dialogFormData" inline>
        <el-form-item label="称谓" prop="appellation" label-width="100px">
          <el-input v-model="dialogFormData.appellation"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name" label-width="100px">
          <el-input v-model="dialogFormData.name"></el-input>
        </el-form-item>
        <el-form-item label="身份证" prop="identity_card" label-width="100px">
          <el-input v-model="dialogFormData.identity_card"></el-input>
        </el-form-item>
        <el-form-item label="健康状况" prop="health" label-width="100px">
          <el-select v-model="dialogFormData.health">
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
          <el-input v-model="dialogFormData.company"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="duty" label-width="100px">
          <el-input v-model="dialogFormData.duty"></el-input>
        </el-form-item>
        <el-form-item label="邮编" prop="post_code" label-width="100px">
          <el-input v-model="dialogFormData.post_code"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone_number" label-width="100px">
          <el-input v-model="dialogFormData.phone_number"></el-input>
        </el-form-item>
        <el-form-item label="政治面貌" prop="politics_status" label-width="100px">
          <el-select v-model="dialogFormData.politics_status">
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
          <el-input v-model="dialogFormData.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" label-width="100px">
        <el-button @click="handleDialogFormCancel">取消</el-button>
        <el-button type="primary" @click="handleDialogFormConform">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import { politicsStatusOptions, healthStates } from '../../../libs/personalInfo'
import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data'

export default {
  name: 'FamilyInfo',
  data () {
    return {
      politicsStatusOptions: politicsStatusOptions,
      healthStates: healthStates,
      dgFromVisible: false,
      formData: {
        appellation: '',
        name: '',
        identity_card: '',
        health: '',
        company: '',
        duty: '',
        post_code: '',
        phone_number: '',
        politics_status: '',
        remark: ''
      },
      dialogFormData: {
        appellation: '',
        name: '',
        identity_card: '',
        health: '',
        company: '',
        duty: '',
        post_code: '',
        phone_number: '',
        politics_status: '',
        remark: ''
      },
      tableLoading: false,
      tableColumns: Object.freeze([
        { label: '姓名', prop: 'name', minWidth: '80', fixed: "left" },
        { label: '称谓', prop: 'appellation', minWidth: '80' },
        { label: '身份证', prop: 'identity_card', minWidth: '120' },
        { label: '健康状况', prop: 'health', minWidth: '80' },
        { label: '单位名称', prop: 'company', minWidth: '120' },
        { label: '职务', prop: 'duty', minWidth: '80' },
        { label: '邮编', prop: 'post_code', minWidth: '70' },
        { label: '电话号码', prop: 'phone_number', minWidth: '120' },
        { label: '政治面貌', prop: 'politics_status', minWidth: '120' },
        { label: '备注', prop: 'remark', minWidth: '140' },
      ]),
      tableData: [
        {
          appellation: '母亲',
          name: '黄玉珍',
          identity_card: '330823196409273645',
          health: '1',
          company: '',
          duty: '',
          post_code: '324100',
          phone_number: '18457052062',
          politics_status: '12',
          remark: ''
        }
      ],
      rules: {
        name: [ { required: true, message: '请输入成员姓名', trigger: 'blur' } ]
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
  methods: {
    resetForm () {
      this.$refs.infoForm.resetFields()
    },
    submitForm () {
      this.$refs.infoForm.validate( ( valid ) => {
        if( valid ){
          this.tableLoading = true
          setTimeout( () => {
            this.tableLoading = false
          }, 1000 )
        }
      } )
    },
    handleEdit (row) {
      this.dgFromVisible = true
      this.dialogFormData = row
    },
    handleDelete (row) {
    },
    handleDialogFormCancel () {
      this.dgFromVisible = false
    },
    handleDialogFormConform () {
      this.dgFromVisible = false
    }
  },
  filters: {
    dataFormatter ( val, prop ) {
      if( val === '' ){
        return '--'
      } else if ( prop === 'health' ) {
        return healthStates[Number(val)-1].l
      } else if ( prop === 'politics_status' ){
        return politicsStatusOptions[Number(val)-1].l
      } else {
        return val
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>