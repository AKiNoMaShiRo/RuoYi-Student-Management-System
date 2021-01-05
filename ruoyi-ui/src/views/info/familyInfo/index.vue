<template>
  <div class="app-container info-family">
    <section class="am-px am-no-shrink left">
      <div class="am-title">添加家庭成员信息</div>
      <div class="am-px scrollbar-vertical" :style="{'height': formHeight}">
        <el-scrollbar>
          <el-form ref="infoForm" label-width="80px" class="am-pr" :model="formData" :rules="rules">
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
                  :rows="5"
                >
                </el-input>
              </div>
            </el-form-item>
          </el-form>
        </el-scrollbar>
      </div>
      <div class="am-py am-text-center">
        <el-button disabsled @click="resetForm">重置</el-button>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </div>
    </section>
    <section class="am-px right">
      <div class="am-title">家庭成员信息</div>
      <div :style="{'height': tableHeight}">
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          :height="tableHeight"
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
        <el-form-item label="备注" prop="comment" label-width="100px">
          <el-input v-model="dialogFormData.comment"></el-input>
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
      tableLoading: false,
      tableColumns: Object.freeze([
        { label: '姓名', prop: 'name', minWidth: '80', fixed: "left" },
        { label: '称谓', prop: 'appellation', minWidth: '80' },
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
      ],
      rules: {
        name: [ { required: true, message: '请输入成员姓名', trigger: 'blur' } ]
      }
    }
  },
  computed: {
    tableHeight () {
      // 面包屑50 tagsView34 app-container内边距20*2 标题42
      return this.$store.state.settings.tagsView ? `calc(100vh - 166px)` : `calc(100vh - 136px)`
    },
    formHeight () {
      // 面包屑50 tagsView34 app-container内边距20*2 标题42 按钮36+24
      return this.$store.state.settings.tagsView ? `calc(100vh - 226px)` : `calc(100vh - 196px)`
    }
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
    min-width: 300px;
    width: 30%;
  }
  .right {
    width: 70%;
  }
}
.left ::v-deep .el-form {
  .el-select,
  .el-input__inner {
    width: 100%;
  }
}
.scrollbar-vertical{
  overflow: hidden;
}
</style>