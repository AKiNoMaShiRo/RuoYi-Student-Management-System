<template>
  <section class="am-box">
    <div class="am-p am-title am-bd-b">国家励志奖学金申请记录</div>
    <div class="am-p">
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
export default {
  data () {
    return {
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
  methods: {
    handleChange (val) {
      console.log(val)
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