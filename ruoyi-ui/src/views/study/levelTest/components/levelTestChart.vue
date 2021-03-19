<template>
  <section>
    <div class="am-p am-flex" style="height: 300px;">
        <test-bar-chart
          v-loading="loadingChart"
          v-if="showChart"
          :xAxis="chartOptions.xAxis"
          :series="chartOptions.series"
          :width="chartOptions.width" />
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex'
import { getClassInfo, getProfessionType } from '@/api/info/classInfo.js'
import TestBarChart from './testBarChart'
import * as LEVELTEST from '@/api/grade/levelTest.js'

const animationDuration = 6000

export default {
  components: {
    TestBarChart
  },
  data () {
    return {
      showChart: false,
      loadingChart: false,
      authedClass: [ ], //    有权限的所有班级{classId, profession, grade, classNum}
      classList: [], //    √班级信息{classId: null, label: ''}  X复选框的值
      testTypeList: [ '大学英语CET4', '大学英语CET6', '计算机等级考试二级', '普通话等级考试' ],
      chartOptions: {
        width: '1100px',
        xAxis: [],
        series: [{
          name: '大学英语CET4',
          type: 'bar',
          barWidth: '12',
          data: [],
          animationDuration
        }, {
          name: '大学英语CET6',
          type: 'bar',
          stack: 'vistors',
          barWidth: '12',
          data: [],
          animationDuration
        }, {
          name: '计算机等级考试二级',
          type: 'bar',
          barWidth: '12',
          data: [],
          animationDuration
        }, {
          name: '普通话等级考试',
          type: 'bar',
          barWidth: '12',
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
  created () {
    this.getInfo()
  },
  methods: {
    getChartData (type) {  //参数为考试种类
      let param = new Array()  //对象数组
      this.authedClass.forEach( item => {
        param.push({
          testType: type,
          ...item
        })
      })
      return LEVELTEST.getPassRateByLT(param).then( res => {
        this.chartOptions.series.forEach( item => {
          if (item.name === type) {
            if (res.data && res.data.length !== 0){
              item.data = res.data
            }
          }
        })
      })
    },
    getInfo () {
      this.loadingChart = true
      let param = {}
      if (this.roleName === '辅导员') {
        param.instructorId = this.userName
        getClassInfo(param).then( res => {
          if (res.rows && res.rows.length !== 0) {
            this.authedClass = res.rows
            this.authedClass.forEach( item =>{
              let str = item.grade + item.profession + item.classNum + '班'
              this.chartOptions.xAxis.push(str)
              this.classList.push({ classId: item.classId, label: str })
            })
            let promises = new Array(4)
            this.testTypeList.forEach( (typeItem, index) => {
              promises[index] = this.getChartData(typeItem)
            })
            Promise.all(promises).then( () => {
              this.showChart = true
              this.loadingChart = false
            })
          } else {
            this.$message.error(('暂无权限'));
          }
        }).catch( () => {
          this.$message.error(('获取查看权限失败'));
        })
      } else {
        getProfessionType().then( res => {
          if (res.data && res.data.length !== 0) {
            res.data.forEach( item => {
              this.authedClass.push({ profession : item })
            })
            console.log(this.authedClass)
            this.authedClass.forEach( item =>{
              this.chartOptions.xAxis.push(item.profession)
              this.classList.push({ classId: '', label: item.profession })
            })
            console.log(this.chartOptions.xAxis)
            let promises = new Array(4)
            this.testTypeList.forEach( (typeItem, index) => {
              promises[index] = this.getChartData(typeItem)
            })
            Promise.all(promises).then( () => {
              this.showChart = true
              this.loadingChart = false
            })
          }
        }).catch( () => {
          this.$message.error(('获取查看权限失败'));
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.chart {
  flex: 1;
  .left {
    width: 20%;
    label {
      margin-left: 10px;
      margin-right: 10px;
      margin-bottom: 12px;
      width: 190px;
      display: flex;
      justify-content: center;
      ::v-deep .el-checkbox__input {
        display: none;
      }
      ::v-deep .el-checkbox__label {
        display: block;
      }
    }
  }
  .right {
    width: 80%;
  }
}
</style>