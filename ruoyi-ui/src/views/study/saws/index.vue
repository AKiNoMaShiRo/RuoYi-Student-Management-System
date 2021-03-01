<template>
  <div class="app-container">
      <section class="am-box am-mb">
        <div class="am-p am-title am-bd-b">学业预警查询</div>
        <div class="am-p">
          <el-form ref="sawsForm" label-width="80px" :model="formData" :rules="rules" inline>
            <el-form-item label="学号" prop="student_id">
              <el-input v-model="formData.student_id" clearable></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="formData.name" clearable></el-input>
            </el-form-item>
            <el-form-item label="院系" prop="department">
              <el-select v-model="formData.department" filterable clearable>
                <el-option
                  v-for="(department, index) in departments"
                  :key="index"
                  :label="department"
                  :value="department"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="专业" prop="profession">
              <el-input v-model="formData.profession" clearable></el-input>
            </el-form-item>
            <el-form-item label="班级" prop="class">
              <el-input v-model.number="formData.class" clearable></el-input>
            </el-form-item>
            <el-form-item label="预警等级" prop="alarm_level">
              <el-select v-model="formData.alarm_level" clearable>
                <el-option label="一级预警" value="1"></el-option>
                <el-option label="二级预警" value="2"></el-option>
                <el-option label="三级预警" value="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label=" ">
                <el-button size="small" disabsled @click="resetForm">重置</el-button>
                <el-button size="small" type="primary" @click="submitForm">查询</el-button>
            </el-form-item>
          </el-form>
        </div>
      </section>
      <section class="am-box">
        <div class="am-p am-title am-bd-b">学业预警情况</div>
        <div class="am-p">
          <div class="am-pb am-flex-end">
            <ol class="alarm-legend am-flex">
                <li class="am-flex-center"><i></i>一级预警</li>
                <li class="am-flex-center"><i></i>二级预警</li>
                <li class="am-flex-center"><i></i>三级预警</li>
            </ol>
          </div>
          <div style="height: 100%;">
            <el-table v-loading="tableLoading" :data="tableData" height="300px">
                <el-table-column label="学号" prop="student_id" min-width="110" showOverflowTooltip></el-table-column>
                <el-table-column label="姓名" prop="name" min-width="80" showOverflowTooltip></el-table-column>
                <el-table-column label="院系" prop="department" min-width="140" showOverflowTooltip></el-table-column>
                <el-table-column label="专业" prop="profession" min-width="100" showOverflowTooltip></el-table-column>
                <el-table-column label="班级" prop="class" min-width="60" showOverflowTooltip></el-table-column>
                <el-table-column label="预警等级" prop="alarm_level" min-width="100" showOverflowTooltip>
                  <template slot-scope="scope">
                    <span v-if=" scope.row.alarm_level === '一级预警' " class="am-alarm-badge am-alarm-level-1">一级预警</span>
                    <span v-else-if=" scope.row.alarm_level === '二级预警' " class="am-alarm-badge am-alarm-level-2">二级预警</span>
                    <span v-else-if=" scope.row.alarm_level === '三级预警' " class="am-alarm-badge am-alarm-level-3">三级预警</span>
                    <span v-else class="fe-alarm-badge"></span>
                  </template>
                </el-table-column>
                <el-table-column label="预警原因" prop="alarm_reason" min-width="100"></el-table-column>
                <el-table-column label="备注" prop="comment" min-width="80"></el-table-column>
              <template></template>
            </el-table>
            <Pagination
              :total="total"
              :page-range="[15, 20, 30]"
              :current-page="currentPage"
              :page-size="pageSize"
              @onPaginationUpdate="handlePaginationUpdate"
            >
            </Pagination>
          </div>
        </div>
      </section>
  </div>
</template>

<script>
import Pagination from '@/views/components/Pagination.vue'
import { DEPARTMENTS } from '@/libs/teachUnit.js'

export default {
  components: {
    Pagination
  },
  data () {
    return {
      departments: DEPARTMENTS,
      total: 100,
      currentPage: 1,
      pageSize: 15,
      tableLoading: false,
      tableData: [
          { student_id: '20171344049', name: '孔允孔允', department: '计算机与软件学院', profession: '软件工程', class: '2', alarm_level: '一级预警', alarm_reason: '略略略', comment: '无'}
      ],
      formData: {
        student_id: '',
        name: '',
        department: '',
        profession: '',
        class: '',
        alarm_level: '',
        alarm_reason: '',
        comment: ''
      },
      rules: {
        class: [ { type: 'number', message: '请输入一个阿拉伯数字', trigger: 'blur' } ]
      }
    }
  },
  methods: {
      resetForm () {
        this.$refs.sawsForm.resetFields()
      },
      submitForm () {
        this.$refs.sawsForm.validate( ( valid ) => {
          if( valid ){
            this.tableLoading = true
            setTimeout( () => {
              this.tableLoading = false
            }, 1000)
          }
        } )
      },
      handlePaginationUpdate () {}
  }
}
</script>

<style lang="scss" scoped>
// .study-saws {
//   display: flex;
//   .left {
//     min-width: 300px;
//     width: 30%;
//   }
//   .right {
//     width: 70%;
//   }
// }
// .left ::v-deep .el-form {
//   .el-select,
//   .el-input__inner {
//     width: 100%;
//   }
// }
ul, ol {
  list-style: none;
  margin: 0;
}
.alarm-legend {
  font-size: 12px;
  li {
    margin-left: 16px;
    i {
      display: inline-block;
      width: 14px;
      height: 14px;
      margin-right: 6px;
      border-radius: 2px;
    }
    &:nth-child(1) i {
      border: 1px solid rgba(240, 65, 52, 0.7);
      background: rgba(240, 65, 52, 0.3);
    }
    &:nth-child(2) i {
      border: 1px solid rgba(255, 147, 29, 0.7);
      background: rgba(255, 147, 29, 0.3);
    }
    &:nth-child(3) i {
      background: rgba(255, 215, 133, 0.3);
      border: 1px solid rgba(255, 215, 133, 0.7);
    }
    &:nth-child(4) i {
      background: rgba(78, 216, 255, 0.3);
      border: 1px solid rgba(78, 216, 255, 0.7);
    }
  }
}
.am-alarm-level-1 {
  background: rgba(240, 65, 52, 0.3);
  border: 1px solid rgba(240, 65, 52, 0.7);
}
.am-alarm-level-2 {
  background: rgba(255, 147, 29, 0.3);
  border: 1px solid rgba(255, 147, 29, 0.7);
}

.am-alarm-level-3 {
  background: rgba(255, 215, 133, 0.3);
  border: 1px solid rgba(255, 215, 133, 0.7);
}
.am-alarm-level-4 {
  background: rgba(78, 216, 255, 0.3);
  border: 1px solid rgba(78, 216, 255, 0.7);
}
.am-alarm-badge {
  display: inline-table;
  padding: 0 6px;
  line-height: 20px;
  border-radius: 4px;
  color: #455a74;
  font-size: 12px;
  word-break: keep-all;
  cursor: pointer;
}
</style>