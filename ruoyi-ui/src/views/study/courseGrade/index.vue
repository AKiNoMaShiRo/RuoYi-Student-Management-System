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
        <div class="am-p">
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
            <el-form-item label="年级" prop="grade">
              <el-input size="small" v-model.number="searchFormData.grade"></el-input>
            </el-form-item>
            <el-form-item label="专业" prop="profession">
              <el-input size="small" v-model="searchFormData.profession"></el-input>
            </el-form-item>
            <el-form-item label="班级序号" prop="classNum">
              <el-input size="small" v-model.number="searchFormData.classNum"></el-input>
            </el-form-item>
            <el-form-item label=" ">
              <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import StuTable from './components/stuTable'
import OthTable from './components/othTable'
import * as COURSEGRADE from '@/api/grade/courseGrade.js';

export default {
  components: { StuTable, OthTable },
  data () {
    return {
      activeIndex: 'table',
      searchFormData: {
        courseName: '',
        grade: '',
        profession: '',
        classNum: ''
      },
      searchRules: {
        courseName: [ {required: true, message: '请输入课程名称', trigger: 'blur'} ],
        grade: [
          {required: true, message: '请输入年级', trigger: 'blur'},
          {type: 'number', message: '年级必须为数字值'}
        ],
        profession: [ {required: true, message: '请输入专业名称', trigger: 'blur'} ],
        classNum: [
          {required: true, message: '请输入班级序号', trigger: 'blur'},
          {type: 'number', message: '班级序号必须为数字值'}
        ]
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
    handleSearch () {
      // console.log(this.searchFormData)
      this.$refs.searchForm.validate( valid => {
        if (valid) {
          COURSEGRADE.getGradeAvg(this.searchFormData).then( res => {

          })
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