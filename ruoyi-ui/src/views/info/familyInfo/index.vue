<template>
  <div class="app-container info-family">
    <section class="am-px left" style="height: 100%">
      <div class="am-title">
        <span>添加家庭成员信息</span>
      </div>
      <el-form ref="infoForm" :model="formData" label-width="80px">
            <el-form-item label="称谓" prop="appellation">
              <el-input v-model="formData.appellation"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证" prop="identityCard">
              <el-input v-model="formData.identityCard"></el-input>
            </el-form-item>
            <el-form-item label="健康状况" prop="health">
              <el-select v-model="formData.health">
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
              <el-input v-model="formData.company"></el-input>
            </el-form-item>
            <el-form-item label="职位" prop="duty">
              <el-input v-model="formData.duty"></el-input>
            </el-form-item>
            <el-form-item label="邮编" prop="postCode">
              <el-input v-model="formData.postCode"></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phoneNumber">
              <el-input v-model="formData.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item label="政治面貌" prop="politicsStatus">
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
            <el-form-item label="备注" prop="comment">
              <div class="am-textarea-sb">
                <el-input
                  v-model="formData.comment"
                  type="textarea"
                  resize="none"
                  :rows="3"
                >
                </el-input>
              </div>
            </el-form-item>
            <el-form-item label=" " label-width="100px" >
              <el-button disabsled @click="resetForm">重置</el-button>
              <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
      </el-form>
    </section>
    <section class="am-px right">
      <div class="am-title">
        <span>家庭成员信息</span>
      </div>
      <div style="height: 100%;">
        <el-table
          :data="tableData"
          height="500px"
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
      <el-form :model="dialogFormData" inline>
        <el-row type="flex" justify="space-between">
          <el-col :span="40">
            <el-form-item label="称谓" prop="appellation" label-width="100px">
              <el-input v-model="dialogFormData.appellation"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name" label-width="100px">
              <el-input v-model="dialogFormData.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证" prop="identityCard" label-width="100px">
              <el-input v-model="dialogFormData.identityCard"></el-input>
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
            <el-form-item label="邮编" prop="postCode" label-width="100px">
              <el-input v-model="dialogFormData.postCode"></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phoneNumber" label-width="100px">
              <el-input v-model="dialogFormData.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item label="政治面貌" prop="politicsStatus" label-width="100px">
              <el-select v-model="dialogFormData.politicsStatus">
                <el-option
                  v-for="politicsStatusOption in politicsStatusOptions"
                  :key="politicsStatusOption.v"
                  :label="politicsStatusOption.l"
                  :value="politicsStatusOption.v"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="40">
            <el-form-item label="备注" prop="comment" label-width="100px">
              <el-input
                v-model="dialogFormData.comment"
                type="textarea"
                resize="none"
                :rows="3"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
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
        identityCard: '',
        health: '',
        company: '',
        duty: '',
        postCode: '',
        phoneNumber: '',
        politicsStatus: '',
        comment: ''
      },
      dialogFormData: {
        appellation: '',
        name: '',
        identityCard: '',
        health: '',
        company: '',
        duty: '',
        postCode: '',
        phoneNumber: '',
        politicsStatus: '',
        comment: ''
      },
      tableColumns: Object.freeze([
        { label: '称谓', prop: 'appellation', minWidth: '80' },
        { label: '姓名', prop: 'name', minWidth: '80' },
        { label: '身份证', prop: 'identityCard', minWidth: '120' },
        { label: '健康状况', prop: 'health', minWidth: '80' },
        { label: '单位名称', prop: 'company', minWidth: '120' },
        { label: '职务', prop: 'duty', minWidth: '80' },
        { label: '邮编', prop: 'postCode', minWidth: '70' },
        { label: '电话号码', prop: 'phoneNumber', minWidth: '120' },
        { label: '政治面貌', prop: 'politicsStatus', minWidth: '120' },
        { label: '备注', prop: 'comment', minWidth: '140' },
      ]),
      tableData: [
        {
          appellation: '母亲',
          name: '黄玉珍',
          identityCard: '330823196409273645',
          health: '1',
          company: '',
          duty: '',
          postCode: '324100',
          phoneNumber: '18457052062',
          politicsStatus: '12',
          comment: ''
        }
      ]
    }
  },
  computed: {
    ...mapGetters(['sidebar']),
    isSideBar () {
      return this.sidebar.open
    }
  },
  methods: {
    resetForm () {
      this.$refs.infoForm.resetFields()
    },
    submitForm () {
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
      } else if ( prop === 'politicsStatus' ){
        return politicsStatusOptions[Number(val)-1].l
      } else {
        return val
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.info-family {
    display: flex;
    .left {
        width: 25%;
    }
    .right {
        width: 75%;
    }
}
</style>