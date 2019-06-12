<template>
  <div class="regi-body">
    <div class="bg">
      <canvas id="canvas"></canvas>
    </div>
    <div @keyup.enter="regist" class="regi-box">
      <div class="regi-title">注册</div>
      <div class="regi-input">
        <input type="text" v-model="userName" placeholder="请输入账号" autofocus>
        <input type="password" v-model="pwd" placeholder="请输入密码">
        <input type="password" v-model="rePassword" placeholder="确认密码">
      </div>
      <div class="agree">
        注册即代表同意
        <a href="#">《就业助手协议》</a>
      </div>
      <div @click="regist" class="regist">立即注册</div>
    </div>
  </div>
</template>

<script>
import { canvas } from "./canvas";
export default {
  mounted() {
    canvas();
  },
  methods: {
    regist() {
      let that = this;
      //------节流处理
      if (this.mkSubmit) {
        return;
      } else {
        this.mkSubmit = true;
        setTimeout(() => {
          this.mkSubmit = false;
        }, 1000);
      }
      //--------------
      //--------简单校验
      if (!this.userName || !this.pwd || !this.rePassword) {
        this.$message({
          showClose: true,
          message: "信息未完整填写~",
          type: "error"
        });
        return;
      } else if (this.rePassword !== this.pwd) {
        this.$message({
          showClose: true,
          message: "两次输入的密码不一致~",
          type: "error"
        });
        return;
      }
      //--------------
      this.fullscreenLoading = true; //Loading效果
      this.axios({
        url: that.API.USER.REGIST,
        methods: "post",
        headers: { "Content-Type": "application/json" },
        params: {
          UserName: this.userName,
          Password: this.pwd
        }
      })
        .then(res => {
          console.log(res);
          this.fullscreenLoading = false;
          this.mkSubmit = 1;
          if (res.data.code != "200") {
            this.$message({
              showClose: true,
              message: res.data.extendInfo.login_error,
              type: "error"
            });
            return;
          }
          that.$message({
            showClose: true,
            message: "注册成功，正在跳转至登陆页面",
            type: "success",
            duration: 1500
          });
          setTimeout(() => {
            this.replaceTo({
              path: "/login"
            });
          }, 1000);
        })
        .catch(err => {
          this.fullscreenLoading = false;
          console.log(err);
        });
    }
  },
  data() {
    return {
      userName: "",
      pwd: "",
      rePassword: "",
      mkSubmit: false
    };
  }
};
</script>

<style>
.regi-body {
  width: 100%;
  height: 100vh;
  overflow: hidden;
}
::-webkit-scrollbar {
  display: none;
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

canvas {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}
.regi-box {
  position: absolute;
  width: 350px;
  height: 400px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 6px;
  top: 15%;
  right: 0%;
  left: 0%;
  margin: auto;
  z-index: 999;
}
.regi-title {
  width: 100%;
  height: 40px;
  margin-top: 15px;
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
  background-color: inherit;
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
