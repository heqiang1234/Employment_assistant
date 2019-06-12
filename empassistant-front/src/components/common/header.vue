<template>
  <div class="header">
    <div class="header-container">
      <h1>实习助手</h1>
      <ul class="subnav">
        <li  v-for="(item,index) in hd" :key="index">
          <a  @click="changeHd(index,item)">{{item.label}}</a>
        </li>
      
      </ul>
      <el-button @click="linkTo({name:'login'})" v-if="!hasLog" plain>请登录</el-button>
      <div v-else class="userInfo">
        <el-dropdown placement="top">
          <img class="user-avt" v-if="userInfo.userImg" :src="userInfo.userImg">
          <img class="user-avt" v-else src="../../assets/default_headpic.png">
          <i class="el-icon-arrow-down el-icon--right"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="linkTo({name:'personalCenter'})">{{userInfo.user_RealName}}</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  created() {
    this.checkLogin().then(res => {
      console.log(res);
      let result = res.data.extendInfo;
      if (result.code == "401") {
        this.USERSTATUS.login = false;
        this.USERSTATUS.userInfo = {};
        console.log("用户未登录或登陆过期");
      } else if (result.person) {
        console.log("用户已登录");
        this.USERSTATUS.login = true;
        this.USERSTATUS.userInfo = result.person;
      }
      if (this.USERSTATUS.login) {
        this.syncUserStatus()
      }
    });
  },
  methods: {
    checkLogin() {
      return this.axios({
        url: this.API.USER.CHECKLOGIN
      });
    },
    syncUserStatus(){
      this.userInfo = this.USERSTATUS.userInfo;
      this.hasLog = this.USERSTATUS.login;
      console.log('用户登陆状态：'+this.hasLog);
    },
    logout(){//用户登出
    console.log('用户退出登录')
      this.axios({
        url:this.API.USER.LOGOUT
      }).then((res)=>{
        console.log(res);
        let result = res.data.extendInfo;
        if(res.data.code == "200"){
        this.USERSTATUS.login = false;
        this.USERSTATUS.userInfo={}
        this.syncUserStatus();
        console.log(this.$route.path);
        if(this.$route.path=="/personalCenter"){
          this.replaceTo({name:'home'})
        }
        }
        this.$message({
              showClose: true,
              message: result.suc_ere,
              type: "success"
            });
            return;
      })
    },
    changeHdIndex(index){
      console.log(index);
      this.curIndex = index;
      console.log(this.curIndex);
    },
    changeHd(index,item){
       this.changeHdIndex(index);
      this.linkTo(item.link)
    }
  },
  data() {
    return {
      userInfo: {},
      hasLog: false,
      hd:[
        {
          label:'首页',
          link:{name:'home'}
        },
        {
          label:'数据分析',
          link:{name:'dataView'}
        },
        {
          label:'校园招聘会',
          link:{name:'careerTalk'}
        },
        {
          label:'藏经阁',
          link:{name:'jobs'}
        }
      ],
      curIndex:1,
    };
  }
};
</script>
<style>
.hd-select{
  color:#0287ee !important;
  border-bottom: 1px solid #0287ee;
}
.header {
  width: 100%;
  height: 56px;
  border-bottom: 1px solid #eaeaea;
}
.header-container {
  padding: 0 10%;
  justify-content: space-between;
  display: flex;
  height: 100%;
  margin: 0px auto;
  align-items: center;
}
.header-container h1 {
  display: inline-block;
  position: absolute;
  top: -9999px;
}
.subnav {
  list-style: none;
  height: 100%;
}
.subnav li {
  height: 100%;
  float: left;
}
.subnav li a {
  text-decoration: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;

  padding: 0 16px;
  line-height: 56px;
  margin: 0;
  cursor: pointer;
}
.subnav a:hover {
  color: #0287ee;
}
.user-avt {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  vertical-align: bottom;
  cursor: pointer;
}
</style>
