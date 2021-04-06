<template>
  <div class="navbar">
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />

    <div class="right-menu">
      <template v-if="device!=='mobile'">
        <search id="header-search" class="right-menu-item" />
        
        <el-tooltip content="源码地址" effect="dark" placement="bottom">
          <ruo-yi-git id="ruoyi-git" class="right-menu-item hover-effect" />
        </el-tooltip>

        <el-tooltip content="文档地址" effect="dark" placement="bottom">
          <ruo-yi-doc id="ruoyi-doc" class="right-menu-item hover-effect" />
        </el-tooltip>

        <el-tooltip content="全屏显示" effect="dark" placement="bottom">
          <screenfull id="screenfull" class="right-menu-item hover-effect" />
        </el-tooltip>

        <el-tooltip content="布局大小" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
            <!-- <svg-icon class-name="search-icon" icon-class="user" @click="handleNotify"></svg-icon> -->
            <svg-icon class-name="search-icon" icon-class="user" @click.stop="click"></svg-icon>
          
          <!-- <img :src="avatar" class="user-avatar"> -->
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <!-- <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link> -->
          <el-dropdown-item
            v-if="roleName === '辅导员' || roleName === '班主任' || roleName === '副书记'"
            @click.native="handleNotify"
          >
            <span>
              通知消息
              <el-badge is-dot :hidden="hiddenBadge"></el-badge>
            </span>
          </el-dropdown-item>
          <el-dropdown-item @click.native="setting = true">
            <span>布局设置</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog title="系统通知消息" :visible.sync="showDialog">
      <section v-if="notifyMsg.length !== 0" class="notify-dialog">
        <div class="dialog-title am-p">您有以下事务待处理：</div>
        <div class="am-px">
          <section v-for="(msg, index) in notifyMsg" :key="index" class="am-px am-pb">
            {{msg.type + msg.num + '条'}}
          </section>
        </div>
      </section>
      <section v-else class="am-flex-align-center am-flex-justify-center" style="height: 100px;">
        暂无待处理事项
      </section>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import RuoYiGit from '@/components/RuoYi/Git'
import RuoYiDoc from '@/components/RuoYi/Doc'
import * as NOTIFY from '@/api/notify.js'
// import NotifyDialog from './NotifyDialog'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    RuoYiGit,
    RuoYiDoc
  },
  data () {
    return {
      hiddenBadge: true,
      showDialog: false,
      notifyMsg: []
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device'
    ]),
    ...mapState({
      roleName: state => state.user.roleName,
      userName: state => state.user.name
    }),
    setting: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    }
  },
  created () {
    let param = {}
    if (this.roleName === '辅导员') {
      param.instructorId = this.userName
    } else if (this.roleName === '副书记') {
      param.masterId = this.userName
    }
    this.getNotify(param)
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      })
    },
    getNotify (param) {
      return NOTIFY.getNotify(param).then( res => {
        // console.log(res)
        if (res.data && res.data.length !== 0) {
          res.data.forEach( item => {
            if (item.num > 0) {
              this.notifyMsg.push(item)
            }
          })
          // this.notifyMsg = res.data
        } else {
          this.notifyMsg = []
        }
        this.notifyMsg.length > 0 ? this.hiddenBadge = false : this.hiddenBadge = true
      })
    },
    handleNotify() {
      let param = {}
      if (this.roleName === '辅导员') {
        param.instructorId = this.userName
      } else if (this.roleName === '副书记') {
      param.masterId = this.userName
    }
      this.notifyMsg = []
      this.getNotify(param).finally( () => {
        this.showDialog = true
      })
      // NOTIFY.getNotify(param).then( res => {
      //   // console.log(res)
      //   if (res.data && res.data.length !== 0) {
      //     res.data.forEach( item => {
      //       if (item.num > 0) {
      //         this.notifyMsg.push(item)
      //       }
      //     })
      //     // this.notifyMsg = res.data
      //   } else {
      //     this.notifyMsg = []
      //   }
      // }).finally( () => {
      //   this.showDialog = true
      // })
    }
  }
}
</script>

<style lang="scss" scoped>
.notify-dialog {
  .dialog-title {
    font-size: 16px;
  }
}
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        // margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
