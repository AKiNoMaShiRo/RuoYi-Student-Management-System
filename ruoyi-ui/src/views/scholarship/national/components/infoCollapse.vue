<template>
  <section class="am-box">
    <div class="am-p am-title am-bd-b">国家奖学金申请记录</div>
    <div class="am-p">
      <el-collapse v-model="activeNames" @change="handleChange">
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
              <div>专业人数：{{ item.profeSum | collapseFormatter('profeSum') }}</div>
              <div>体测成绩：{{ item.sportGrade | collapseFormatter('grade') }}</div>
            </div>
            <div class="am-flex collapse-item">
              <div>必修与选修课折合成绩排名：{{ item.gradeRank | collapseFormatter('gradeRank') }}</div>
              <div>必修与选修课折合成绩排名百分比：{{ item.gradeRankRate | collapseFormatter('rate') }}</div>
            </div>
            <div class="am-flex collapse-item">
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
            <el-button size="mini" type="danger" plain>删除</el-button>
          </section>
        </el-collapse-item>
      </el-collapse>
    </div>
  </section>
</template>

<script>
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
    handleUpdStatus () {},
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
    width: 300px;
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
