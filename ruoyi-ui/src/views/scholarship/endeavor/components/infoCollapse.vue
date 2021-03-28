<template>
  <section class="am-box">
    <div class="am-p am-title am-bd-b">国家励志奖学金申请记录</div>
    <div class="am-px am-pt" v-if="roleName !== '学生'">
      <el-form
        ref="searchForm"
        :model="searchFormData"
        label-position="right"
        label-width="60px"
        inline
      >
        <!-- :rules="searchFormRules" -->
        <el-form-item label="学年" prop="learnYear">
          <el-select size="small" v-model="searchFormData.learnYear" clearable>
            <el-option
              v-for="opt in termOpts"
              :key="opt.label"
              :value="opt.value"
              :label="opt.label"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item size="small" label="专业" prop="profession">
          <el-input v-model="searchFormData.profession" clearable></el-input>
        </el-form-item>
        <el-form-item size="small" label="状态" prop="status">
          <el-select v-model="searchFormData.status" clearable>
            <el-option label="待审批" :value="3"></el-option>
            <el-option label="已通过" :value="1"></el-option>
            <el-option label="未通过" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label=" ">
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="am-p" v-loading="loading">
      <el-collapse v-model="activeNames" @change="handleChange" v-if="showCollapse">
        <el-collapse-item v-for="item in collapseData" :key="item.name" v-bind="item">
          <template slot="title">
            <span v-if="item.status === 1" class="status-pass am-mr">
              已通过
            </span>
            <span v-else-if="item.status === 2" class="status-reject am-mr">
              未通过
            </span>
            <span v-else class="status-pending am-mr">
              待审批
            </span>
            {{ item.title }}
          </template>
          <section class="am-px">
            <div class="am-flex collapse-item">
              <div>是否破格：{{ item.isFit | collapseFormatter('isFit') }}</div>
              <div>困难认定等级：{{ item.diffLevel | collapseFormatter('diffLevel') }}</div>
              <div>专业人数：{{ item.profeSum | collapseFormatter('profeSum') }}</div>
            </div>
            <div class="am-flex collapse-item">
              <div>体测成绩：{{ item.sportGrade | collapseFormatter('grade') }}</div>
              <div>综测排名：{{ item.multipRank | collapseFormatter('multipRank') }}</div>
              <div>综测排名百分比：{{ item.multipRankRate | collapseFormatter('rate') }}</div>
            </div>
            <div>省级及以上表彰或成果：{{ item.porvincePrize | collapseFormatter('porvincePrize') }}</div>
            <div>校级表彰或成果：{{ item.schoolPrize | collapseFormatter('schoolPrize') }}</div>
          </section>
          <section class="am-flex am-flex-end am-px am-pt">
            <el-button
              size="mini"
              @click="handleUpdStatus({ status: 2, scholarshipId: item.scholarshipId })"
              v-hasPermi="['scholarship:endeavor:approve']"
              plain
            >不同意</el-button>
            <el-button
              size="mini"
              type="success"
              @click="handleUpdStatus({ status: 1, scholarshipId: item.scholarshipId })"
              v-hasPermi="['scholarship:endeavor:approve']"
              plain
            >同意</el-button>
            <el-popconfirm
              confirm-button-text="确定"
              cancel-button-text="取消"
              icon="el-icon-info"
              icon-color="red"
              title="确定撤销该申请？"
              @onConfirm="handleDelete(item.scholarshipId)"
            >
              <el-button
                type="danger"
                size="mini"
                slot="reference"
                style="margin-left: 10px;"
                plain>删除</el-button>
            </el-popconfirm>
          </section>
        </el-collapse-item>
      </el-collapse>
      <div v-else class="am-flex-center" style="height: 100px;color: #6a727a;font-size: 12px;">暂无数据</div>
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex'
import moment from 'moment'
import * as EDV from '@/api/scholarship/edvScholarship.js'
import { learnYearOptions } from '@/libs/utils.js'

export default {
  props: {
    refresh: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data () {
    return {
      searchFormData: {
        learnYear: '',
        profession: '',
        status: null
      },
      loading: false,
      showCollapse: true,
      // searchFormRules: {},
      activeNames: [],
      collapseData: [
        { 
          title: '20171344054 17软件工程2班 王圣滋',
          name: 0, 
          isFit: 0, 
          diffLevel: '一般困难', 
          profeSum: 132,
          sportGrade: 79.8,
          multipRank: 44,
          multipRankRate: 33.3,
          porvincePrize: '奖项',
          schoolPrize: '奖项' 
        },
        { 
          title: '20171344054 17软件工程2班 涂哲维', 
          name: 1, 
          isFit: 0, 
          diffLevel: '特别困难', 
          profeSum: 132,
          sportGrade: 79.8,
          porvincePrize: '奖项',
          schoolPrize: '奖项' 
        },
      ]
    }
  },
  computed: {
    ...mapState({
      roleName: state => state.user.roleName,
      userName: state => state.user.name
    }),
    termOpts () {
      //判断上半年还是下半年
      let startYear = parseInt(moment().format('MM')) < 8 ? parseInt(moment().format('yyyy')) : parseInt(moment().format('yyyy')) + 1
      let param = (startYear - 4).toString()
      return learnYearOptions(param)
    }
  },
  watch: {
    refresh () {
      this.getInfo()
    }
  },
  created () {
    this.collapseData = []
    this.getInfo()
  },
  methods: {
    getInfo (searchData) {
      this.loading = true
      let param = {...searchData}
      if (this.roleName === '学生') {
        param.studentId = this.userName
      } else if (this.roleName === '辅导员') {
        param.instructorId = this.userName
      } else if (this.roleName === '班主任') {
        param.headmasterId = this.userName
      }
      EDV.getEdv(param).then( res => {
        if (res.data && res.data.length !== 0) {
          this.collapseData = res.data
          this.collapseData.forEach( item => {
            item.title = item.learnYear + ' ' + item.studentId + ' ' + item.grade + item.profession + item.classNum + '班' + item.stuName
          })
          this.showCollapse = true
        } else {
          this.showCollapse = false
          this.collapseData = []
        }
      }).catch( () => {
        this.showCollapse = false
          this.collapseData = []
      }).finally( () => {
        this.loading = false
      })
    },
    handleUpdStatus (param) {
      EDV.updateEdvStatus(param).then( res => {
        if (res.msg === '操作成功') {
          this.$message.success('操作成功')
          this.getInfo()
        } else {
          this.$message.error('操作失败')
        }
      })
    },
    handleDelete (param) {
      EDV.deleteEdvStatus({ scholarshipId: param }).then( res => {
        if (res.msg === '操作成功') {
          this.$message.success('删除申请成功')
          this.getInfo()
        } else {
          this.$message.error('删除申请失败')
        }
      })
    },
    handleSearch () {
      this.getInfo(this.searchFormData)
    },
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    handleChange (val) {
      // console.log(val)
    }
  },
  filters: {
    collapseFormatter (value, prop) {
      if (value === ''  || value === undefined || value === null) {
        return '--'
      } else {
        if (prop === 'grade') {
          return (+value).toFixed(2)
        } else if (prop === 'rate') {
          return (+value).toFixed(2) + '%'
        } else if (prop === 'isFit') {
          return value === 1 ? '是' : '否'
        } else {
          return value
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.collapse-item {
  div {
    width: 250px;
  }
}
::v-deep .el-collapse-item__content {
  padding-bottom: 12px;
}
.status-pending {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #8e9cab, $alpha: 0.09);
  color: #8e9cab;
  border-radius: 4px;
}
.status-pass {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #31cf9a, $alpha: 0.09);
  color: #31cf9a;
  border-radius: 4px;
}
.status-reject {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  font-size: 12px;
  word-break: keep-all;
  background-color: rgba($color: #f56c6c, $alpha: 0.09);
  color: #f56c6c;
  border-radius: 4px;
}
</style>