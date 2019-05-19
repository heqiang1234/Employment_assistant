 <template>
  <div class="warp">
    <div class="header">
      <div class="container">
        <h1>实习助手</h1>
        <ul class="subnav">
          <li>
            <a href="home.vue">首页</a>
          </li>
          <li>
            <a href="#">我的简历</a>
          </li>
          <li>
            <a href="#">校园招聘会</a>
          </li>
          <li>
            <a href="#">藏经阁</a>
          </li>
        </ul>
      </div> 
    </div>
    <div class="login-box">
      <div class="login-title">学生登录</div>
      <input type="text" id="username" placeholder="请输入账号" autofocus>
      <input type="password" id="password" placeholder="请输入密码">
      <div class="btn">
        <a href="#" class="forget">忘记密码</a>
        <a href="http://localhost:8080/#/register" class="register">立即注册</a>
      </div>
      <div class="submit">立即登录</div>
    </div>
    <div class="login-bottom">
      <p>就业助手你的好帮手</p>
      <span>国内领先的实习生招聘平台</span>
    </div>
  </div>
</template>

<script>
export default {
name: "login",
  name: "HelloWorld",
  data() {
    return {};
  },
  
  created() {
    this.axios(this.API.JOBS.GETJOBS).then(res => {
      console.log(res);
    });
  },
  methods: {
    login() { //提交登陆
      console.log(this.userName);
      console.log(this.pwd);
      let params = this.qs.stringify({
          username: this.userName,
          password: this.pwd
        })
      this.axios({
        url: this.API.USER.LOGIN,
        methods: "POST",
        headers: { "Content-Type": "application/json" },
        params: {
          username: this.userName,
          password: this.pwd
        },
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scope>
.warp {
  overflow: hidden;
  width: 100%;
  height: 100vh;
  background-image: url("../assets/bg.png");
  background-repeat: no-repeat;
  background-size: 100%;
}
.header {
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
  color: rgba(255,255,255,.8);
  font-size: 16px;
  padding: 0 16px;
  height: 56px;
  line-height: 56px;
  margin: 0;
  cursor: pointer;
}
.container ul li a:hover{
  color: #fff;
}
.login-box {
  width: 220px;
  height: 265px;
  background-color: #fff;
  margin-top: 60px;
  padding: 30px 40px 50px;
  z-index: 100;
  position: absolute;
  right: 20%;
}
.login-title{
  text-align: center;
  font-size: 20px;
  color: #0287ee;
}
.login-box input{
  margin-top: 20px;
  width: 100%;
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  padding: 10px 0;
  color: #262626;
  border: none;
  outline: none;
  border-bottom: 1px solid #0287ee;
}
.forget{
  text-decoration: none;
  display: inline-block;
  color: rgba(38,38,38,.85);
  font-size: 14px;
  margin: 20px 50px 20px 5%;
}
.register{
  text-decoration: none;
  display: inline-block;
  color: #0287ee;
  font-size: 14px;
  margin: 20px 5% 20px 0px;
}
.submit{
  font-size: 12px;
  color: #fff;
  width: 100%;
  height: 38px;
  line-height: 38px;
  text-align: center;
  background-color: #0287ee;
  cursor: pointer;
}
.login-bottom{
  position: absolute;
  width: 100%;
  bottom: 10%;
  text-align: center;
}
.login-bottom p{
  font-family: "微软雅黑";
  font-size: 36px;
  font-weight: bold;
  color: #595959;
  display: block;
}
.login-bottom span{
  margin-top: 10px;
  font-size: 14px;
  color: rgba(38,38,38,.5);
  display: block;
}
</style>
