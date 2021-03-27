<template>
  <section class="am-box">
    <div class="am-p am-title am-bd-b">国家励志奖学金申请记录</div>
    <div class="am-p">
      <el-form
        ref="searchForm"
        :model="searchFormData"
        label-position="right"
        label-width="60px"
        inline
      >
        <!-- :rules="searchFormRules" -->
        <el-form-item label="学年" prop="learnYear">
          <el-select v-model="searchFormData.learnYear" clearable>
            <el-option
              v-for="opt in termOpts"
              :key="opt.label"
              :value="opt.value"
              :label="opt.label"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="searchFormData.profession" clearable></el-input>
        </el-form-item>
        <el-form-item label=" ">
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="am-p" v-loading="loading" v-if="showCollapse">
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item v-for="item in collapseData" :key="item.name" v-bind="item">
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
        </el-collapse-item>
      </el-collapse>
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex'
import moment from 'moment'
import * as EDV from '@/api/scholarship/edvScholarship.js'
import { learnYearOptions } from '@/libs/utils.js'

export default {
  data () {
    return {
      searchFormData: {
        learnYear: '',
        profession: ''
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
  created () {
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
      }).finally( () => {
        this.loading = false
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
</style>