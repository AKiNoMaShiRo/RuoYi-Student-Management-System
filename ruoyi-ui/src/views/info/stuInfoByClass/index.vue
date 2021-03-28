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
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '../../components/Pagination.vue'
import { getClassInfo } from '@/api/info/classInfo.js'
import { getAllStu } from '@/api/info/stuInfo.js'

export default {
  components: { Pagination },
  data () {
    return {
      classInfo: [],
      radioOptions: [],
      currentClass: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableLoading: false,
      tableColumns: [
        { label: '学号', prop: 'studentId', minWidth: '120' },
        { label: '姓名', prop: 'name', minWidth: '100' },
        { label: '手机号码', prop: 'phoneNumber', minWidth: '100' },
        // { label: '姓名', prop: 'memberName', minWidth: '80' },
      ],
      tableData: [],
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
        } else {
          this.tableData = []
          this.total = 0
        }
      }).finally( () => {
        this.tableLoading = false
      })
    }
  },
  filters: {
    dataFormatter (val, prop) {
      if (val === '' || val === null) {
        return '--'
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
    // height: calc(100vh - 176px);
    ::v-deep .el-radio--small{
      margin-left: 8px;
      margin-bottom: 6px;
    }
  }
  // .right {
  //   width: 80%;
  //   // height: calc(100vh - 176px);
  // }
}
</style>