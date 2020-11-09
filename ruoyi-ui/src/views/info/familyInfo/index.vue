<template>
  <div class="app-container">
    <el-form ref="infoForm" :model="formData" :inline="true">
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
        <el-input v-model="formData.comment" type="textarea"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button disabsled @click="resetForm">重置</el-button>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
    <el-table
      height="300px"
     :data="tableData"
     style="height:100%"
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
      <el-table-column label="操作" min-width="120">
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
</template>

<script>
import { politicsStatusOptions, healthStates } from '../formOption'
import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data'

export default {
  name: 'FamilyInfo',
  data () {
    return {
      politicsStatusOptions: politicsStatusOptions,
      healthStates: healthStates,
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
      tableColumns: Object.freeze([
        { label: '称谓', prop: 'appellation', minWidth: '80' },
        { label: '姓名', prop: 'name', minWidth: '80' },
        { label: '身份证', prop: 'identityCard', minWidth: '180' },
        { label: '健康状况', prop: 'health', minWidth: '100' },
        { label: '单位名称', prop: 'company', minWidth: '140' },
        { label: '职务', prop: 'duty', minWidth: '100' },
        { label: '邮编', prop: 'postCode', minWidth: '80' },
        { label: '电话号码', prop: 'phoneNumber', minWidth: '120' },
        { label: '政治面貌', prop: 'politicsStatus', minWidth: '120' },
        { label: '备注', prop: 'comment', minWidth: '180' },
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
  methods: {
    resetForm () {
      this.$refs.infoForm.resetFields()
    },
    submitForm () {
    },
    handleEdit (row) {
    },
    handleDelete (row) {
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