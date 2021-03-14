<template>
    <div class="app-container">
        <section class="am-box">
            <div class="am-title am-p am-bd-b">学生账号列表</div>
            <div class="am-px-lg am-pt">
                <el-form
                    :model="searchFormData"
                    ref="searchForm"
                    label-position="right"
                    label-width="80px"
                    inline
                >
                    <el-form-item label="学生姓名" prop="nickName">
                        <el-input
                            v-model="searchFormData.nickName"
                            placeholder="请输入学生姓名"
                            size="small"
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="所属班级" prop="classNum">
                        <el-select
                            v-model="searchFormData.classNum"
                            placeholder="请输入学生姓名"
                            size="small"
                            clearable
                        >
                        <el-option v-for="opt in classOptions" :key="opt.v" :label="opt.l" :value="opt.v"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleSearch">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetSearchForm">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <div class="am-px-lg am-pb">
                <el-table
                    v-loading="tableLoading"
                    :data="tableData"
                    :height="tableHeight"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="50" align="center" />
                    <el-table-column label="用户账号" align="center" prop="userName" :show-overflow-tooltip="true" />
                    <el-table-column label="用户姓名" align="center" prop="nickName" :show-overflow-tooltip="true" />
                    <el-table-column label="用户角色" align="center" prop="roleName" :show-overflow-tooltip="true" />
                    <el-table-column label="状态" align="center">
                        <template slot-scope="scope">
                        <el-switch
                            v-model="scope.row.status"
                            active-value="0"
                            inactive-value="1"
                            @change="handleStatusChange(scope.row)"
                        ></el-switch>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        align="center"
                        width="160"
                        class-name="small-padding fixed-width"
                    >
                        <template slot-scope="scope">
                        <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="handleUpdate(scope.row)"
                            v-hasPermi="['system:user:edit']"
                        >修改</el-button>
                        <el-button
                            v-if="scope.row.userId !== 1"
                            size="mini"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete(scope.row)"
                            v-hasPermi="['system:user:remove']"
                        >删除</el-button>
                        <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-key"
                            @click="handleResetPwd(scope.row)"
                            v-hasPermi="['system:user:resetPwd']"
                        >重置</el-button>
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
            </div>
        </section>
    </div>
</template>

<script>
import Pagination from '../../components/Pagination.vue'

export default {
    components: {
        Pagination
    },
    data () {
        return {
            total: 0,
            currentPage: 1,
            pageSize: 10,
            tableLoading: false,
            tableData: [],
            classOptions: [],
            searchFormData: {
                nickName: '',
                classNum: ''
            }
        }
    },
    computed: {
    tableHeight () {
      return this.total > this.currentPage ? '300px' : 'calc(300px + 40px)'
    }
  },
    methods: {
        handleUpdate () {},
        handleDelete () {},
        handleResetPwd () {},
        handleSelectionChange () {},
        //    搜索按钮
        handleSearch () {},
        //    重置搜索表单
        resetSearchForm () {
            this.$refs.searchForm.resetFields()
        },
        handlePaginationUpdate (param) {
            this.currentPage = param.currentPage
            this.pageSize = param.pageSize
        }
    }
}
</script>