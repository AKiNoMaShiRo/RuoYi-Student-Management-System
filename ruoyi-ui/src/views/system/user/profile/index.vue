<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="7" :xs="24">
        <div class="am-box">
          <div class="am-p am-title am-bd-b">账号信息</div>
          <div class="am-px">
            <!-- <div class="text-center">
              <userAvatar :user="user" />
            </div> -->
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="nested" />账号
                <div class="pull-right">{{ userName }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="people" />姓名
                <div class="pull-right">{{ nickName }}</div>
              </li>
              <!-- <li class="list-group-item">
                <svg-icon icon-class="phone" />手机号码
                <div class="pull-right">{{ user.phonenumber }}</div>
              </li> -->
              <li class="list-group-item">
                <svg-icon icon-class="peoples" />系统角色
                <div class="pull-right">{{ roleName }}</div>
              </li>
              <!-- <li class="list-group-item">
                <svg-icon icon-class="tree" />所属部门
                <div class="pull-right" v-if="user.dept">{{ user.dept.deptName }} / {{ postGroup }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="peoples" />所属角色
                <div class="pull-right">{{ roleGroup }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date" />创建日期
                <div class="pull-right">{{ user.createTime }}</div>
              </li> -->
            </ul>
          </div>
        </div>
      </el-col>
      <el-col :span="17" :xs="24">
        <div class="am-box">
          <div class="am-p am-title am-bd-b">修改密码</div>
          <div class="am-p">
            <!-- <el-tabs v-model="activeTab">
              <el-tab-pane label="基本资料" name="userinfo">
                <userInfo :user="user" />
              </el-tab-pane>
              <el-tab-pane label="修改密码" name="resetPwd"> -->
                <resetPwd :user="user" />
              <!-- </el-tab-pane>
            </el-tabs> -->
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userAvatar from "./userAvatar";
import resetPwd from "./resetPwd";
import { getUserProfile } from "@/api/system/user";
import { mapState } from 'vuex'

export default {
  name: "Profile",
  components: { userAvatar, resetPwd },
  data() {
    return {
      user: {},
      roleGroup: {},
      postGroup: {},
      activeTab: "userinfo"
    };
  },
  computed: {
    ...mapState({
      userName: state => state.user.name,
      nickName: state => state.user.nickName,
      roleName: state => state.user.roleName
    })
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    }
  }
};
</script>

<style scoped>
.svg-icon {
  margin-right: 2px;
}
</style>