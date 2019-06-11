<template>
  <div class="wrap">
    <Header></Header>
    <div class="personal-msg">
      <div class="per-pic">
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-camera-solid avatar-uploader-icon"></i>
        </el-upload>
        <div class="exchange-pwd">
          <div class="user-name">
            <span class="sty-title">当前账号：</span><span class="sty-name">沙雕</span>
          </div>
          <div @click="changePwd" class="re-pwd">
            <i class="el-icon-edit">修改密码</i>
          </div>
          <div v-show="onChangePwd" class= "input-pwd">
            <div class="pwd-line">
              <div class="pwd-name">当前密码</div>
              <div><el-input placeholder="当前密码"  show-password></el-input></div>
            </div>
            <div class="pwd-line">
              <div class="pwd-name">新密码</div>
              <div><el-input placeholder="新密码"  show-password></el-input></div>
            </div>
            <div class="pwd-line">
              <div class="pwd-name">确认密码</div>
              <div><el-input placeholder="确认密码"  show-password></el-input></div>
            </div>
            <div class="pwd-line">
              <div class="save-bnt"><el-button type="success" plain>确认修改</el-button></div>
            </div>
          </div>
        </div>
      </div>
      <div class="other-msg">
        <div class="show-title">用户信息</div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">姓名</div>
            <div class="item-input">
              <el-input  placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">性别</div>
            <div class="item-input">
              <el-select placeholder="选择性别">
                <el-option label="男" value="male"></el-option>
                <el-option label="女" value="female"></el-option>
              </el-select>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所在城市</div>
            <div class="item-input">
              <el-select @change="changeProvince"  placeholder="请选择省">
                <el-option
                  v-for="item in allCity"
                  :key="item.province"
                  :label="item.province"
                  :value="item.province"
                ></el-option>
              </el-select>
              <el-select  placeholder="请选择市">
                <el-option v-for="item in curCity" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所在学校</div>
            <div class="item-input">
              <el-input  placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所学专业</div>
            <div class="item-input">
              <el-input  placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">求职意向</div>
            <div class="item-input">
              <el-input  placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">常用邮箱</div>
            <div class="item-input">
              <el-input  placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="save-bot">
          <el-button type="primary">确认修改</el-button>
        </div>
      </div>
      <div class="close-page">关闭<i class="el-icon-close"></i></div>
    </div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header.vue";
import Footsy from "./common/footsy.vue";
export default {
  components: { Header,Footsy },
  methods:{
    changePwd(){
      this.onChangePwd = !this.onChangePwd;
    }
  },
  data(){
    return{
      onChangePwd:false
    }
  }
};
</script>
<style scoped>
.wrap {
  width: 100%;
  min-width: 1080px;
  overflow: hidden;
}
.personal-msg {
  width: 90%;
  min-width: 1080px;
  margin: 10px auto;
  padding: 5px 20px;
  display: flex;
  position: relative;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.per-pic {
  width: 27%;
  min-width: 290px;
  padding: 30px 0px;
  border-right: 1px solid #ddd;
  box-sizing: border-box;
}
.avatar-uploader {
  border-radius: 6px;
  width: 200px;
  height: 200px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  margin: 0 auto;
}
.avatar-uploader:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 200px;
  line-height: 200px;
  text-align: center;
}
.avatar {
  width: 200px;
  height: 200px;
  display: block;
}
.exchange-pwd{
  margin-top:30px;
  padding: 0px 10px;
  box-sizing: border-box;
  width: 100%; 
}
.user-name{
  padding-left: 40px;
  width: 100%;
  font-family: '微软雅黑';
  text-align: left;
}
.sty-title{
  font-weight: 500;
}
.sty-name{
  font-size: 24px;
  font-weight: 500;
}
.re-pwd{
  padding-left: 40px;
  line-height: 50px;
  color: #666;
  cursor:pointer;
}
.input-pwd{
  margin:30px auto 0px;
  width: 100%; 
  padding: 10px 10px 10px 0px;
  box-sizing: border-box;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.pwd-line{
  display: flex;
  line-height: 50px;
}
.pwd-name{
  width: 100px;
  font-size: 16px;
  text-align: right;
  padding-right:10px; 
  line-height: 50px;
}
.save-bnt{
  margin: 20px auto;
}
.other-msg {
  width: 66%;
  min-width: 720px;
  padding: 10px 40px;
  box-sizing: border-box;
}
.show-title {
  width: 100%;
  padding-left: 230px;
  font-family: "微软雅黑";
  font-size: 24px;
  font-weight: 500px;
  line-height: 50px;
}
.show-msg{
  width: 100%;
  margin-top: 20px;
}
.msg-items{
  width: 100%;
  margin: 10px 0; 
  height: 40px;
  line-height: 40px;
  display: flex;
}
.item-name{
  padding-right:10px;
  text-align: right;
  width:100px;
}
.item-input .el-input{
  width: 500px;
}
.item-input .el-select .el-input{
  width: 190px;
}
.save-bot{
  width: 100%;
  height: 40px;
  padding:20px 230px;
}
.close-page{
  width: 50px;
  height: 20px;
  line-height: 20px;
  position: absolute;
  right: 15px;
  top: 10px;
  text-align: center;
  color: red;
}
</style>
