 <template>
  <div class="warp" v-loading.fullscreen.lock="fullscreenLoading">
    <div class="login-header">
      <div class="container">
        <h1>实习助手</h1>
        <ul class="subnav">
          <li>
            <router-link :to="{name:'home'}">首页</router-link>
          </li>
          <li>
            <a @click="linkTo({name:'dataView'})" href="#">数据分析</a>
          </li>
          <li>
            <a @click="linkTo({name:'careerTalk'})" href="#">校园招聘会</a>
          </li>
          <li>
            <a  @click="linkTo({name:'jobs'})" href="#">藏经阁</a>
          </li>
        </ul>
      </div>
    </div>
   
    <div @keyup.enter="login" class="login-body">
      <div class="login-title">学生登录</div>
      <input type="text" v-model="userName" placeholder="请输入账号" autofocus>
      <input type="password" v-model="pwd" placeholder="请输入密码">
      <input  type="text" v-model="verifyCode" placeholder="请输入验证码">
       <img @click="changeVerifyCode" ref="verifCodeImg" :src="this.API.VERIFYCODE.GETCODE"/>
      <div class="btn">
        <a href="#" class="forget">忘记密码</a>
        <a @click="linkTo({name:'register'})" class="register">立即注册</a>
      </div>
      <div  @click="login" class="submit">立即登录</div>
    </div>
    <div class="login-footer">
      <p>就业助手你的好帮手</p>
      <span>国内领先的实习生招聘平台</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  name: "HelloWorld",
  created(){
    // document.addEventListener("keydown", function login(e){
    //   console.log(e)
    //   if(e.key==="Enter"){
    //     this.login();
    //   }
    // })
  },
  destroyed() {
    // document.removeEventListener("keydown",login)
  },
  data() {
    return {
      fullscreenLoading: false, //页面显示加载中
      userName: null, //表单用户名
      pwd: null, //表单密码
      timer: null, //节流提交时间标记
      verifyCode:'' //验证码
    };
  },

  methods: {
    login() {
      //提交登陆
      this.changeVerifyCode();//切换验证码
      //--------节流处理
      if (this.timer) {
        return;
      }
      this.timer = true;
      setTimeout(() => {
        this.timer = false;
      }, 1000);
      //--------
      //--------简单校验
      if (!this.userName) {
        this.$message({
          showClose: true,
          message: "帐号不能为空",
          type: "error"
        });
        return;
      }
      if (!this.pwd) {
        this.$message({
          showClose: true,
          message: "密码不能为空",
          type: "error"
        });
        return;
      }
      if(!this.verifyCode){
        this.$message({
          showClose: true,
          message: "请输入验证码",
          type: "error"
        });
        return;
      }
      //---------
      this.fullscreenLoading = true; //Loading效果
      console.log(this.userName);
      console.log(this.pwd);
      this.axios({
        url: this.API.USER.LOGIN,
        methods: "POST",
        headers: { "Content-Type": "application/json" },
        params: {
          UserName: this.userName,
          Password: this.pwd,
          verifyCode:this.verifyCode
        }
      })
        .then(res => {
          this.fullscreenLoading = false; //关闭Loading
          console.log(res);
          if (res.data.code == "200") {
            let newUser = false;
            this.USERSTATUS.login = true;
            this.USERSTATUS.userInfo = res.data.extendInfo.person;
             console.log(document.cookie);
            if(!res.data.extendInfo.person.user_Email) newUser=true;
            this.linkTo({ name: "home" ,params:{newUser:newUser}});
           
          } else {
            this.$message({
              showClose: true,
              message: res.data.extendInfo.login_error,
              type: "error"
            });
            return;
          }
        })
        .catch(err => {
          this.fullscreenLoading = false; //关闭Loading
          console.log(err);
        });
    },
    changeVerifyCode(){
      
      let time = new Date().getTime();
      console.log(this.$refs.verifCodeImg);
      this.$refs.verifCodeImg.src=this.API.VERIFYCODE.GETCODE+"?d="+time
    }
  }
};
</script>

<style scoped>
.warp {
  overflow: hidden;
  width: 100%;
  height: 100vh;
  background-image: url("../assets/bg.png");
  background-repeat: no-repeat;
  background-size: 100% 100vh; 
}
.login-header {
  width: 100%;
  height: 56px;
  position: relative;
  margin-top: 50px;
}
.container {
  widows: 100%;
  height: 100%;
  max-width: 1080px;
  margin: 0px auto;
}
.container h1 {
  display: inline-block;
  position: absolute;
  top: -9999px;
}
.container ul {
  list-style: none;
  height: 100%;
}
.container ul li {
  height: 100%;
  float: left;
}
.container ul li a {
  list-style: none;
  text-decoration: none;
  display: inline-block;
  vertical-align: top;
  color: rgba(255, 255, 255, 0.8);
  font-size: 16px;
  padding: 0 16px;
  height: 56px;
  line-height: 56px;
  margin: 0;
  cursor: pointer;
}
.container ul li a:hover {
  color:#0287ee;
}
.login-body {
  width: 220px;
  background-color:rgba(255, 255, 255, 1);
  margin: 40px auto 0px;
  padding: 30px 40px 50px;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
  z-index: 100;
}
.login-title {
  text-align: center;
  font-size: 20px;
  color: #0287ee;
}
.login-body input {
  margin-top: 20px;
  width: 100%;
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  padding: 10px 6px;
  color: #262626;
  border: none;
  outline: none;
  border-bottom: 1px solid #0287ee;
}
.forget {
  text-decoration: none;
  display: inline-block;
  color: rgba(38, 38, 38, 0.85);
  font-size: 14px;
  margin: 20px 50px 20px 5%;
}
.register {
  cursor:pointer;
  text-decoration: none;
  display: inline-block;
  color: #0287ee;
  font-size: 14px;
  margin: 20px 5% 20px 0px;
}
.submit {
  font-size: 12px;
  color: #fff;
  width: 100%;
  height: 38px;
  line-height: 38px;
  text-align: center;
  background-color: #0287ee;
  cursor: pointer;
}
.login-footer {
  position: absolute;
  width: 100%;
  bottom: 10%;
  text-align: center;
}
.login-footer p {
  font-family: "微软雅黑";
  font-size: 36px;
  font-weight: bold;
  color: #595959;
  display: block;
}
.login-footer span {
  margin-top: 10px;
  font-size: 14px;
  color: rgba(1, 1, 1, 0.8);
  display: block;
}
</style>
