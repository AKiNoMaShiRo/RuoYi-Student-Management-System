<template>
  <div :class="className" :style="{ height:height, width:width }" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      required: false,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '280px'
    },
    //    X轴标签
    xAxis: {
      type: Array,
      required: false,
      default: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    series: {
      type: Array,
      required: true,
      default: () => [{
          name: 'pageA',
          type: 'bar',
          stack: 'vistors',
          barWidth: '18',
          data: [79, 52, 200, 334, 390, 330, 220],
          animationDuration
        }, {
          name: 'pageB',
          type: 'bar',
          stack: 'vistors',
          barWidth: '18',
          data: [80, 52, 200, 334, 390, 330, 220],
          animationDuration
        }, {
          name: 'pageC',
          type: 'bar',
          stack: 'vistors',
          barWidth: '18',
          data: [30, 52, 200, 334, 390, 330, 220],
          animationDuration
        }]
    },
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.xAxis,
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          // axisTick: {
          //   alignWithLabel: true
          // }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          axisLabel: {
            clickable: true,
            formatter: function (value) {
              return value + '%'
            }
          },
        }],
        tooltip: {
          trigger: "axis",
          borderColor: 'transparent',
          backgroundColor: 'rgba(0,0,0,0.50)',
          useHTML: true,
          padding: [5, 10, 6, 10],
          axisPointer: {
            type: "shadow",
            shadowStyle: {
              color: 'rgba(204,214,235,0.25)'
            }
          }
        },
        series: this.series
      })
    }
  }
}
</script>
