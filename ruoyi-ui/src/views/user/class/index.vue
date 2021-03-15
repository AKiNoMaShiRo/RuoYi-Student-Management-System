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
                    <el-form-item label="专业" prop="profession">
                        <el-select v-model="searchFormData.profession" size="small" clearable>
                            <el-option label="软件工程" value="软件工程"></el-option>
                            <!-- <el-option v-for="opt in professionOptions" :key="opt.v" :label="opt.l" :value="opt.v"></el-option> -->
                        </el-select>
                    </el-form-item>
                    <el-form-item label="年级" prop="grade">
                        <el-input
                            v-model="searchFormData.grade"
                            placeholder="请输入年级"
                            size="small"
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="辅导员" prop="instructorId">
                        <el-select v-model="searchFormData.instructorId" size="small" clearable>
                            <el-option label="张学会" value="1001"></el-option>
                            <!-- <el-option v-for="opt in instructorOptions" :key="opt.v" :label="opt.l" :value="opt.v"></el-option> -->
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
                    <el-table-column label="专业" align="center" prop="profession" :show-overflow-tooltip="true" />
                    <el-table-column label="年级" align="center" prop="grade" :show-overflow-tooltip="true" />
                    <el-table-column label="班级编号" align="center" prop="classNum" :show-overflow-tooltip="true" />
                    <el-table-column label="辅导员" align="center" prop="instructorId" :show-overflow-tooltip="true" />
                    <el-table-column
                        label="操作"
                        align="center"
                        width="160"
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
import * as CLASSINFO from '@/api/info/classInfo.js'
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
            professionOptions: [],
            classOptions: [],
            searchFormData: {
                profession: '',
                grade: null,
                instructorId: ''
            }
        }
    },
    computed: {
        tableHeight () {
        return this.total > this.currentPage ? '300px' : 'calc(300px + 40px)'
        },
    },
    created () {
        this.getInfo()
    },
    methods: {
        getInfo () {
            this.tableLoading = true
            let isAll = true
            if (this.searchFormData.profession !== '' || 
                this.searchFormData.grade !== null || this.searchFormData.instructorId !== ''){
                isAll = false
            }
            let param = {
                pageNum: this.currentPage,
                pageSize: this.pageSize
            }
            if (isAll) {
                CLASSINFO.getAllClassInfo(param).then( res => {
                    if (res.rows && res.rows.length != 0) {
                        this.total = res.total
                        this.tableData = res.rows
                    } else {
                        this.total = 0
                        this.tableData = []
                    }
                }).finally( () => {
                    this.tableLoading = false
                })
            } else {
                param = Object.assign(param, {...this.searchFormData})
                CLASSINFO.getClassInfo(param).then( res => {
                    if (res.rows && res.rows.length != 0) {
                        this.total = res.total
                        this.tableData = res.rows
                    } else {
                        this.total = 0
                        this.tableData = []
                    }
                }).finally( () => {
                    this.tableLoading = false
                })
            }
        },
        handleUpdate (row) {
            // CLASSINFO.editClassInfo()
        },
        handleDelete (row) {
            let param = row.classId
            CLASSINFO.deleteClassInfo(param).then( res => {
                if (res.msg === '操作成功') {
                    this.$message.success('删除成功')
                    this.getInfo()
                } else {
                    this.$message.error('删除失败')
                }
            })
        },
        handleSelectionChange () {},
        //    搜索按钮
        handleSearch () {
            this.getInfo()
        },
        //    重置搜索表单
        resetSearchForm () {
            this.$refs.searchForm.resetFields()
        },
        handlePaginationUpdate (param) {
            this.currentPage = param.currentPage
            this.pageSize = param.pageSize
            this.getInfo()
        }
    }
}
</script>