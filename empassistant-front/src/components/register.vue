<template>
  <div class="regi-body">
    <div class="bg">
      <ul class="ul-box">
        <li class="sport1"><img src="../assets/reg-bg.png" alt=""></li>
        <li class="sport2"><img src="../assets/reg-bg1.png" alt=""></li>
        <li class="sport3"><img src="../assets/reg-bg.png" alt=""></li>
        <li class="sport4"><img src="../assets/reg-bg1.png" alt=""></li>
      </ul>
    </div>
    <div class="regi-box">
      <div class="regi-title">学生注册</div>
      <div class="regi-input">
        <input type="text" id="username" placeholder="请输入账号" autofocus>
        <input type="password" id="password" placeholder="请输入密码">
        <input type="password" id="repassword" placeholder="确认密码">
      </div>
      <div class="agree">
        注册即代表同意
        <a href="#">《就业助手协议》</a>
      </div>
      <div class="regist">立即注册</div>
    </div>
  </div>
</template>
<style>
.regi-body {
  width: 100%;
  height: 100vh;
  overflow: hidden;
}
.bg {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.ul-box {
  width: 100%;
  height: 100%;
  list-style: none;
}
.ul-box li {
  float: left;
  width: 100%;
  height: 100%;
}
.ul-box li img{
  width: 100%;
}
.sport1{
  animation: sport-1 15s linear 0s infinite normal;
}
.sport2{
  animation: sport-2 10s linear 0s infinite normal;
}
@keyframes sport-1 {
  0% {
    top: 0px;
  }
  100% {
    top: -50vh;
  }
}
@keyframes sport-2 {
  0% {
    top: 0px;
  }
  100% {
    top: -50vh;
  }
}
.regi-box {
  position: absolute;
  width: 350px;
  height: 400px;
  background-color: #fff;
  border-radius: 6px;
  top: 15%;
  right: 30%;
  z-index: 999;
}
.regi-title {
  width: 100%;
  height: 40px;
  text-align: center;
  line-height: 40px;
  color: #0287ee;
  font-size: 26px;
}
.regi-input {
  width: 220px;
  margin: 0 auto;
}
.regi-input > input {
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
.agree {
  width: 220px;
  font-size: 13px;
  height: 56px;
  margin: 0px auto;
  font-family: "微软雅黑";
  line-height: 56px;
  cursor: pointer;
  color: rgba(38, 38, 38, 0.5);
}
.agree a {
  text-decoration: none;
  color: #0287ee;
}
.agree a:hover {
  text-decoration: underline;
}
.regist {
  font-size: 12px;
  color: #fff;
  width: 220px;
  height: 38px;
  margin: 10px auto;
  line-height: 38px;
  text-align: center;
  background-color: #0287ee;
  cursor: pointer;
}
</style>
