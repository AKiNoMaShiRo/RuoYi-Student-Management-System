<template>
  <div>
    <el-form
      ref="searchForm"
      label-width="80px"
      :model="searchFormData"
      :rules="searchRules"
      inline
    >
      <el-form-item label="" prop=""></el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      searchFormData: {},
      searchRules: {}
    }
  }
}
</script>