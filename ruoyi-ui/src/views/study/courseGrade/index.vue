<template>
  <div class="app-container">
    <section v-if="roleName !== '学生'" class="am-box am-mb">
      <!-- 表格/图表切换tab -->
      <div class="am-bd-b">
        <ol class="am-py am-tabs-inline am-no-shrink">
          <li :class="{ active: activeIndex === 'table' }" @click="handleTabClick('table')">
            <span>学生成绩详情</span>
          </li>
          <li :class="{ active: activeIndex === 'chart' }" @click="handleTabClick('chart')">
            <span>班级成绩分析</span>
          </li>
        </ol>
      </div>
      <section v-show="activeIndex === 'table'">
        <!-- <div class="am-p am-title am-bd-b">课程成绩</div> -->
        <StuTable v-if="roleName === '学生'"></StuTable>
        <OthTable v-else></OthTable>
      </section>
      <section v-show="activeIndex === 'chart'">
        <!-- <div class="am-p am-title am-bd-b">课程平均分</div> -->
        <div class="am-p am-page">
          <el-form
            ref="searchForm"
            label-width="85px"
            :model="searchFormData"
            :rules="searchRules"
            inline
          >
            <!-- <el-form-item label="学号" prop="studentId">
              <el-input size="small" v-model="searchFormData.studentId" clearable></el-input>
            </el-form-item> -->
            <el-form-item label="课程名称" prop="courseName">
              <el-autocomplete
                size="small"
                v-model="searchFormData.courseName"
                :fetch-suggestions="queryCourseNameAsync"
                prefix-icon="el-icon-search"
                clearable
              ></el-autocomplete>
            </el-form-item>
            <!-- <el-form-item label="年级" prop="grade">
              <el-input size="small" v-model.number="searchFormData.grade"></el-input>
            </el-form-item>
            <el-form-item label="专业" prop="profession">
              <el-input size="small" v-model="searchFormData.profession"></el-input>
            </el-form-item>
            <el-form-item label="班级序号" prop="classNum">
              <el-input size="small" v-model.number="searchFormData.classNum"></el-input>
            </el-form-item> -->
            <el-form-item label=" ">
              <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
            </el-form-item>
          </el-form>
          <section>
            <div class="am-p am-flex" style="height: 300px;">
              <grade-bar-chart
                v-loading="loadingChart"
                v-if="showChart"
                :xAxis="chartOptions.xAxis"
                :series="chartOptions.series"
                :width="chartOptions.width" />
            </div>
          </section>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import StuTable from './components/stuTable'
import OthTable from './components/othTable'
import GradeBarChart from './components/gradeBarChart'
import * as COURSEGRADE from '@/api/grade/courseGrade.js';
import { getClassInfo, getProfessionType } from '@/api/info/classInfo.js'

const animationDuration = 6000

export default {
  components: { StuTable, OthTable, GradeBarChart },
  data () {
    return {
      activeIndex: 'table',
      showChart: false,
      loadingChart: false,
      searchFormData: {
        courseName: '',
        grade: '',
        profession: '',
        classNum: ''
      },
      searchRules: {
        courseName: [ {required: true, message: '请输入课程名称', trigger: 'blur'} ],
        // grade: [
        //   {required: true, message: '请输入年级', trigger: 'blur'},
        //   {type: 'number', message: '年级必须为数字值'}
        // ],
        // profession: [ {required: true, message: '请输入专业名称', trigger: 'blur'} ],
        // classNum: [
        //   {required: true, message: '请输入班级序号', trigger: 'blur'},
        //   {type: 'number', message: '班级序号必须为数字值'}
        // ]
      },
      authedClass: [], //    有权限的所有班级{classId, profession, grade, classNum}
      // classList: [], //    √班级信息{classId: null, label: ''}  X复选框的值
      chartOptions: {
        width: '1100px',
        xAxis: [],
        series: [{
          name: '',
          type: 'bar',
          barWidth: '18',
          data: [],
          animationDuration
        }]
      }
    }
  },
  computed: {
    ...mapState({
      userName: state => state.user.name,
      roleName: state => state.user.roleName
    })
  },
  methods: {
    handleTabClick (str) {
      this.activeIndex = str
    },
    //    课程名称模糊搜索
    queryCourseNameAsync (queryString, callback) {
      if (queryString !== '') {
        COURSEGRADE.getGradeName({ courseType: queryString }).then( res => {
          let list = []
          if (res.data && res.data.length !== 0) {
            res.data.forEach( item => {
              list.push({ value: item.courseName })
            })
          }
          callback(list)
        })
      }
    },
    resetSearchForm () {
      this.$refs.searchForm.resetFields()
    },
    getData (queryRole) {
      let param = new Array()
      this.authedClass.forEach( item => {
        param.push({ ...queryRole, classId: item.classId })
      })
      // console.log(param)
      COURSEGRADE.getAllGradeAvg(param).then( res => {
        if (res.data && res.data.length !== 0) {
          // console.log(res.data)
          res.data.forEach( item => {
            if (item === null) {
              this.chartOptions.series[0].data.push(0)
            } else {
              this.chartOptions.series[0].data.push(item)
            }
          })
        }
      }).finally( () => {
        console.log(this.chartOptions)
        setTimeout( () => {
          this.showChart = true
          this.loadingChart = false
        }, 100)
      })
    },
    //    TODO 获取柱状图数据
    getChartData () {
      this.showChart = false
      this.loadingChart = true
      let param = {}
      this.chartOptions.series[0].name = this.searchFormData.courseName
      if (this.roleName === '辅导员' || this.roleName === '班主任') {
        this.roleName === '辅导员' ? param.instructorId = this.userName : param.headmasterId = this.userName
        getClassInfo(param).then( res => {
          if (res.rows && res.rows.length !== 0) {
            this.authedClass = res.rows
            this.authedClass.forEach( item =>{
              let str = item.grade + item.profession + item.classNum + '班'
              this.chartOptions.xAxis.push(str)
              // this.classList.push({ classId: item.classId, label: str })
            })
            this.getData(param)
          }
        })
      } else {
        getProfessionType().then( res => {
          if (res.data && res.data.length !== 0) {
            res.data.forEach( item => {
              this.authedClass.push({ profession : item })
            })
            // console.log(this.authedClass)
            this.authedClass.forEach( item =>{
              this.chartOptions.xAxis.push(item.profession)
              this.classList.push({ classId: '', label: item.profession })
            })
            this.getData()
          }
        })
      }
    },
    handleSearch () {
      // console.log(this.searchFormData)
      this.$refs.searchForm.validate( valid => {
        if (valid) {
          this.getChartData()
          // let param = {}
          // for (let key in this.searchFormData) {
          //   if (this.searchFormData[key] !== '') {
          //     param[key] = this.searchFormData[key]
          //   }
          // }
          // COURSEGRADE.getGradeAvg(param).then( res => {

          // })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/assets/styles/variables.scss';
.am-tabs-inline {
  display: flex;
  overflow: hidden;
  padding-inline-start: 12px;
  margin: 0px;
  li {
    margin-right: $gap-l;
    font-size: 14px;
    font-weight: 500;
    color: #455a74;
    list-style: none;
    span {
      display: inline-block;
      border-bottom: 2px solid transparent;
      cursor: pointer;
      white-space: nowrap;
    }
    &.active {
      span {
        color: $menuActiveText;
        border-color: $menuActiveText;
        pointer-events: none;
      }
    }
    &:hover {
      span {
        color: $menuActiveText;
      }
    }
  }
}
</style>