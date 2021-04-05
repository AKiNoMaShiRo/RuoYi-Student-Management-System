<template>
  <div class="app-container">
    <section class="am-box">
      <div class="am-p am-title am-bd-b">学生信息</div>
      <div class="am-flex stu-class">
        <section class="left am-p am-page am-bd-r am-flex-wrap">
          <el-radio-group v-model="currentClass" size="small">
            <el-radio
              v-for="option in radioOptions"
              :key="option.label"
              :label="option.label"
              border
            >{{ option.value }}</el-radio>
          </el-radio-group>
        </section>
        <section class="right am-p">
          <el-table
            v-loading="tableLoading"
            :data="tableData"
            height="100%"
            highlight-current-row
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
            <el-table-column label="操作" min-width="110px">
              <template slot-scope="scope">
                <el-button type="text" size="small" icon="el-icon-view" @click="handleFamily(scope.row)">家庭成员信息</el-button>
              </template>
            </el-table-column>
          </el-table>
          <Pagination
            :total="total"
            :page-range="[10, 15, 20]"
            :current-page="currentPage"
            :page-size="pageSize"
            @onPaginationUpdate="handlePaginationUpdate"
          >
          </Pagination>
        </section>
      </div>
    </section>
    <el-dialog title="家庭成员信息详情" :visible.sync="dgVisible">
      <div class="am-p">
        <el-table
          v-loading="familyTableLoading"
          :data="familyTableData"
          height="264px"
          highlight-current-row
        >
          <el-table-column
            v-for="column in familyTableColumns"
            v-bind="column"
            :key="column.prop"
            showOverflowTooltip
          >
            <template slot-scope="scope">
              {{ scope.row[column.prop] | familyFormatter(column.prop) }}
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  nationOptions,
  politicsStatusOptions,
  graduateIntentionTypes,
  registrationStates,
  healthStates
} from '../../../libs/personalInfo'
import { mapState } from 'vuex'
import Pagination from '../../components/Pagination.vue'
import { getClassInfo } from '@/api/info/classInfo.js'
import { getAllStu } from '@/api/info/stuInfo.js'
import { regionData } from 'element-china-area-data'
import { getFamilyInfo } from '@/api/info/familyInfo'
import moment from 'moment'

export default {
  components: { Pagination },
  data () {
    return {
      dgVisible: false,
      classInfo: [],
      radioOptions: [],
      currentClass: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableColumns: [
        { label: '学号', prop: 'studentId', minWidth: '120' },
        { label: '姓名', prop: 'name', minWidth: '90' },
        { label: '手机号码', prop: 'phoneNumber', minWidth: '110' },
        { label: '电子邮箱', prop: 'email', minWidth: '120' },
        { label: 'QQ', prop: 'qq', minWidth: '110' },
        { label: '微信号', prop: 'weChat', minWidth: '110' },
        { label: '是否已缴费注册', prop: 'registration', minWidth: '115' },
        { label: '信息上报项目', prop: 'reportItem', minWidth: '120' },
        { label: '出生日期', prop: 'birthday', minWidth: '100' },
        { label: '民族', prop: 'nation', minWidth: '90' },
        { label: '籍贯', prop: 'nativePlace', minWidth: '160' },
        { label: '政治面貌', prop: 'politicsStatus', minWidth: '100' },
        { label: '加入时间', prop: 'joinTime', minWidth: '100' },
        { label: '家庭地址', prop: 'familyAddress', minWidth: '80' },
        { label: '家庭电话', prop: 'familyPhoneNumber', minWidth: '80' },
        { label: '家庭联系人', prop: 'familyContacts', minWidth: '95' },
        { label: '家庭邮编', prop: 'familyPostcode', minWidth: '80' },
        { label: '生源地区', prop: 'studentOrigin', minWidth: '160' },
        { label: '毕业中学', prop: 'graduSchool', minWidth: '80' },
        { label: '中学邮编', prop: 'graduSchoolPostcode', minWidth: '80' },
        { label: '毕业意向调查', prop: 'graduIntention', minWidth: '120' },
        { label: '导师姓名', prop: 'teacher', minWidth: '80' },
        { label: '医保卡号', prop: 'healthCard', minWidth: '80' },
        { label: '英文名', prop: 'englishName', minWidth: '80' },
      ],
      tableData: [],
      familyTableLoading: false,
      familyTableColumns: Object.freeze([
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
      familyTableData: []
    }
  },
  computed: {
    ...mapState({
      roleName: state => state.user.roleName,
      userName: state => state.user.name
    })
  },
  watch: {
    currentClass (value) {
      this.getStuInfo({ classId: value })
    }
  },
  created () {
    let param = {
      pageNum: 1,
      pageSize: 1000
    }
    if (this.roleName === '辅导员') {
      param.instructorId = this.userName
    } else if (this.roleName === '班主任') {
      param.headmasterId = this.userName
    }
    getClassInfo(param).then( res => {
      if (res.rows && res.rows.length !== 0) {
        this.classInfo = res.rows
        res.rows.forEach( item => {
          this.radioOptions.push({
            label: item.classId, 
            value: item.grade + item.profession + item.classNum + '班'
          })
        })
        this.currentClass = this.radioOptions[0].label
      } else {
        this.classInfo = []
        this.radioOptions = []
      }
    }).catch( () => {
      this.classInfo = []
      this.radioOptions = []
    })
  },
  methods: {
    getStuInfo (param) {
      this.tableLoading = true
      getAllStu(param).then( res => {
        if (res.rows && res.rows !== 0) {
          this.tableData = res.rows
          this.total = res.total
          this.tableData.forEach( dataItem => {
            registrationStates.forEach( item => {
              if (item.v === dataItem.registration) {
                dataItem.registration = item.l
              }
            })
            if (dataItem.nation !== null) {
              dataItem.nation = nationOptions[dataItem.nation]
            }
            politicsStatusOptions.forEach( item => {
              if (item.v === dataItem.politicsStatus) {
                dataItem.politicsStatus = item.l
              }
            })
            graduateIntentionTypes.forEach( item => {
              if (item.v === dataItem.graduIntention) {
                dataItem.graduIntention = item.l
              }
            })
            if (dataItem.studentOriginP !== null) {
              dataItem.studentOrigin = this.getRegionName({
                p: dataItem.studentOriginP,
                c: dataItem.studentOriginC,
                a: dataItem.studentOriginA
              })
            } else {
              dataItem.studentOrigin = null
            }
            if (dataItem.nativePlaceP !== null) {
              dataItem.nativePlace = this.getRegionName({
                p: dataItem.nativePlaceP,
                c: dataItem.nativePlaceC,
                a: dataItem.nativePlaceA
              })
            } else {
              dataItem.nativePlace = null
            }
          })
        } else {
          this.tableData = []
          this.total = 0
        }
      }).finally( () => {
        this.tableLoading = false
      })
    },
    getRegionName (param) {
      let res = ''
      regionData.forEach( provinceItem => {
        if (provinceItem.value === param.p) {
          res += provinceItem.label
          provinceItem.children.forEach( cityItem => {
            if (cityItem.value === param.c) {
              res += '/'
              res += cityItem.label
              cityItem.children.forEach( areaItem => {
                if (areaItem.value === param.a) {
                  res += '/'
                  res += areaItem.label
                  return
                }
              })
              return
            }
          })
          return
        }
      })
      return res
    },
    getFamily (stuId) {
      this.familyTableLoading = true
      getFamilyInfo(stuId).then(res => {
        this.familyTableData = []
        if (res.data && res.data.length !== 0) {
          this.familyTableData = res.data
        }
      }).finally( () => {
        this.familyTableLoading = false
      } )
    },
    handleFamily (stu) {
      // console.log(stu.studentId)
      this.dgVisible = true
      this.getFamily(stu.studentId)
    },
    handlePaginationUpdate (param) {
      this.currentPage = param.pageNum
      this.pageSize = param.pageSize
    }
  },
  filters: {
    dataFormatter (val, prop) {
      if (val === '' || val === null) {
        return '--'
      } else {
        if (prop === 'reportItem') {
          let res = val === 'yes' ? '建档立卡学生' : '非建档立卡学生'
          return res
        } else if (prop === 'birthday' || prop === 'joinTime') {
          return moment(val).format('yyyy-MM-DD')
        } else {
          return val
        }
      }
    },
    familyFormatter ( val, prop ) {
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
.stu-class {
  flex: 1;
  .left {
    width: 250px;
    min-height: calc(100vh - 176px);
    // height: calc(100vh - 176px);
    ::v-deep .el-radio--small{
      margin-left: 8px;
      margin-bottom: 6px;
      width: 180px;
      display: flex;
      justify-content: center;
      .el-radio__input{
        display: none;
        // visibility: hidden;
      }
    }
  }
  .right {
    min-height: calc(100vh - 176px);
    width: calc(100% - 250px);
    // width: 80%;
    // height: calc(100vh - 176px);
  }
}
</style>