<template>
  <div v-if="total > pageSize" class="element-pager">
    <el-pagination
      background
      layout="prev, pager, next, sizes, jumper"
      :total="total"
      :current-page="currentPage"
      :page-sizes="pageRange"
      :page-size="pageSize"
      @size-change="handlePageSizeChange"
      @current-change="handlePageChange"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    total: {
      type: Number,
      required: true,
      default: 0
    },
    currentPage: {
      type: Number,
      required: false,
      default: 1
    },
    pageSize: {
      type: Number,
      required: false,
      default: 15
    },
    pageRange: {
      type: Array,
      required: false,
      default: () => [15, 20, 30]
    }
  },
  data() {
    return {
      pageCount: 15
    }
  },
  created() {
    this.pageCount = this.pageSize
  },
  methods: {
    handlePageSizeChange(pageCount) {
      this.pageCount = pageCount
      this.$emit('onPaginationUpdate', {
        currentPage: 1,
        pageSize: this.pageCount
      })
    },
    handlePageChange(page) {
      this.$emit('onPaginationUpdate', {
        currentPage: page,
        pageSize: this.pageCount
      })
    }
  }
}
</script>
