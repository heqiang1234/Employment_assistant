<template>
  <div class="warp">
    <div class="header">
      <div class="container">
        <h1>实习助手</h1>
        <ul class="subnav">
          <li>
            <a href="http://localhost:8080/#/">首页</a>
          </li>
          <li>
            <a href="http://localhost:8080/#/login">我的简历</a>
          </li>
          <li>
            <a href="http://localhost:8080/#/careerTalk">校园招聘会</a>
          </li>
          <li>
            <a href="http://localhost:8080/#/display">藏经阁</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="search">
      <div class="container">
        <div class="search-input">
          <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
            <el-select style="width:120px;" placeholder="选择地区" v-model="select" slot="prepend">
              <el-option label="全国" value="1"></el-option>
              <el-option label="北京" value="2"></el-option>
            </el-select>
            <el-select
              style="width:120px;margin-left:15px;"
              placeholder="选择信息"
              v-model="select"
              slot="prepend"
            >
              <el-option label="搜职业" value="1"></el-option>
              <el-option label="搜公司" value="2"></el-option>
            </el-select>
            <el-button style="width:130px;" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
        <div class="search-likely">
          <span likely-title>相似搜索：</span>
          <a href="#">计算机</a>
          <a href="#">软件测试</a>
          <a href="#">哈哈哈</a>
          <a href="#">嘿嘿嘿</a>
        </div>
      </div>
    </div>
    <div class="career-talk">
      <div class="container">
        <div class="careers-card">
          <div class="company-info">
            <div class="company-name">
              <a href="#">上海亮朵家饰用品有限公司</a>
            </div>
            <div class="company-type">
                <span >民营企业</span>
                <span >批发和零售业</span>
            </div>
          </div>
          <div class="talk-info">
            <div class="talk-time">
              <i class="el-icon-time"></i>
              2019-5-24 13:00
            </div>
            <div class="talk-body">
              <i class="el-icon-location-outline"></i>
              <span class="talk-address">麦庐园大活D03</span>
              <i class="el-icon-view"></i>
              <span class="talk-address">899</span>
            </div>
          </div>
          <div class="company-logo">
            <div class="logoImg">
              <img src="../assets/aiqiyi.jpg" alt>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.warp {
  width: 100%;
  min-width: 1080px;
}
.header {
  width: 100%;
  height: 56px;
}
.container {
  height: 100%;
  max-width: 90%;
  margin: 0px auto;
}
.container h1 {
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
  color: #222;
  padding: 0 16px;
  line-height: 56px;
  margin: 0;
  cursor: pointer;
}
.subnav a:hover {
  color: #0287ee;
}
.search {
  width: 100%;
  height: 100px;
  margin-top: 15px;
}
.search-input {
  width: 100%;
  height: 50px;
}
.el-input__inner {
  height: 50px;
  line-height: 50px;
}
.el-input-group__append {
  background-color: #98caf0;
  width: 150px;
  text-align: center;
}
.search-likely {
  width: 100%;
  height: 50px;
  padding: 10px 0px;
  display: block;
  box-sizing: border-box;
  line-height: 30px;
}
.likely-title {
  font-weight: 600;
  padding-right: 10px;
}
.search-likely > a {
  list-style: none;
  color: #aaa;
  text-decoration: none;
  padding-right: 10px;
}
.search-likely > a:hover {
  color: #0287ee;
}
.career-talk {
  width: 100%;
  max-height: 850px;
}
.careers-card {
  width: 90%;
  height: 120px;
  padding: 20px 0px 19px 0px;
  border-bottom: 1px solid #666;
  box-sizing: border-box;
}
.talk-info {
  height: 100%;
  width: 40%;
  display: inline-block;
  box-sizing: border-box;
}
.talk-time {
  width: 100%;
  height: 40px;
  line-height: 40px;
  text-align: left;
  color: orangered;
}
.talk-body {
  width: 100%;
  height: 39px;
  line-height: 39px;
  text-align: left;
}
.talk-address {
  color: #666;
  width: 30%;
  font-size: 14px;
  padding: 0 40% 0 2%;
}
.company-info {
  height: 100%;
  width: 34%;
  display: inline-block;
  box-sizing: border-box;
}
.company-name {
  height: 40px;
  width: 100%;
  text-align: center;
  line-height: 40px;
}
.company-name > a {
  color: #0287ee;
  font-size: 20px;
  text-decoration: none;
}
.company-type {
  height: 39px;
  width: 100%;
  display: flex;
  justify-content:center;
}
.company-type>span{
  height: 39px;
  color: #aaa;
  line-height: 39px;
  padding:0px 10px; 
  display:block;
}
.company-logo {
  width: 20%;
  height: 100%;
  display: inline-block;
  box-sizing: border-box;
  position: relative;
}
.logoImg {
  width: 79px;
  height: 79px;
  text-align: center;
  position: absolute;
  right: 20px;
}
.logoImg > img {
  width: 100%;
}
</style>
