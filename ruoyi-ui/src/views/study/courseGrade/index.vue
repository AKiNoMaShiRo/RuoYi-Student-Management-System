<template>
  <div class="app-container">
    <section v-if="roleName !== '学生'" class="am-box am-mb">
      <!-- 表格/图表切换tab -->
      <div class="am-bd-b">
        <ol class="am-py am-tabs-inline am-no-shrink">
          <li :class="{ active: activeIndex === 'table' }" @click="handleTabClick('table')">
            <span>成绩查询</span>
          </li>
          <li :class="{ active: activeIndex === 'chart' }" @click="handleTabClick('chart')">
            <span>图表分析</span>
          </li>
        </ol>
      </div>
    </section>
    <section class="am-box">
      <div class="am-p am-title am-bd-b">普通课程成绩</div>
      <StuTable v-if="roleName === '学生'"></StuTable>
      <OthTable v-else></OthTable>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import StuTable from './components/stuTable'
import OthTable from './components/othTable'

export default {
  components: { StuTable, OthTable },
  data () {
    return {
      activeIndex: 'table',
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