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
    <div class="show-post">
      <div class="container">
        <ul class="tab-list">
          <li>综合排序</li>
          <li>时间排序</li>
          <li>相关度排序</li>
        </ul>
        <div class="tab-content">
          <div class="post-card">
            <div class="post-info">
              <div class="post-head">
                <div class="post-name">Web前端</div>
                <div class="post-pay">100-150￥/天</div>
              </div>
              <div class="post-body">
                <div class="post-others">
                  <i class="el-icon-location-outline"></i>
                  <span class="post-other">北京</span>
                </div>
                <div class="post-others">
                  <i class="el-icon-time"></i>
                  <span class="post-other">4天/周</span>
                </div>
                <div class="post-others">
                  <i class="el-icon-date"></i>
                  <span class="post-other">四个月</span>
                </div>
              </div>
            </div>
            <div class="company-info">
              <div class="company-name">
                <a href="#">爱奇艺</a>
              </div>
              <div class="company-infor">互联网|2000人以上</div>
            </div>
            <div class="company-logo">
              <div class="logoImg">
                <img src="../assets/aiqiyi.jpg" alt>
              </div>
            </div>
          </div> 
        </div>
        <div class="no-reash">
            <div class="rotate-words">
              <div>
                你想要的没有找到
                <br>
                <span>Sorry ~</span>
              </div>
              <div>
                或者你试试别的吧
                <br>
                <span>Try ~</span>
              </div>
              <div>
                期待下次有你想要
                <br>
                <span>Wait in hope ~</span>
              </div>
            </div>
          </div>
      </div>
    </div>
    <div class="foot">
      <div class="container-tab">
        <div class="foot-title">
          <div class="ourselves">
            <span class="ourselves-title">产品服务</span>
            <a href="#" class="ourselves-body">找实习</a>
            <a href="#" class="ourselves-body">宣讲会</a>
            <a href="#" class="ourselves-body">找工作</a>
          </div>
          <div class="ourselves">
            <span class="ourselves-title">旗下产品</span>
            <a href="#" class="ourselves-body">校园招聘</a>
            <a href="#" class="ourselves-body">就业助手</a>
            <a href="#" class="ourselves-body">找工作</a>
          </div>
          <div class="ourselves">
            <span class="ourselves-title">关于我们</span>
            <a href="#" class="ourselves-body">了解我们</a>
            <div class="join-us">
              <a href="#" class="ourselves-body">赞助我们</a>
              <div class="join">
                <img src="../assets/QQ.jpg" alt>
              </div>
            </div>
            <a href="#" class="ourselves-body">加入我们</a>
          </div>
          <div class="ourselves">
            <span class="ourselves-title">联系我们</span>
            <div class="join-us">
              <a href="#" class="ourselves-body">团队微信</a>
              <div class="join">
                <img src="../assets/weChat.jpg" alt>
              </div>
            </div>
            <a href="#" class="ourselves-body">客户服务</a>
            <a href="#" class="ourselves-body">商务合作</a>
          </div>
        </div>
        <div class="foot-body">
          <p>
            <span>&copy;2019.2-2019.6 就业助手</span> |
            <span>赣ICP备00000000号-1</span> |
            <span>联系我们：xxx@jiuyezhushou.com</span>
          </p>
          <p>玉屏西科技有限责任公司版权所有 | 全国领先的实习生平台</p>
          <div class="internet">
            <img class="internetImg" src="../assets/record-icon.png" alt>
            <span>赣公网安备 00000000000000号</span>
          </div>
        </div>
      </div>
    </div>
    <a class="goto-top" href="#">
      <img src="../assets/right-hn.png" alt>
      回到顶部
    </a>
  </div>
</template>
<script>
export default {};
</script>
<style scoped>
.warp {
  width: 100%;
  min-width: 1080px;
}
.header {
  width: 100%;
  height: 56px;
  border-bottom: 1px solid #aaa;
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
.tab-list {
  margin-top: 10px;
  height: 40px;
  width: 100%;
  text-align: center;
  line-height: 40px;
  font-family: "微软雅黑";
}
.tab-list li {
  float: left;
  width: 150px;
  height: 100%;
  border-top-left-radius: 20% 100%;
  border-bottom-right-radius: 20% 100%;
  cursor: pointer;
  background-color: #ccc;
  list-style: none;
}
.tab-list li:hover {
  font-size: 16px;
  color: #0287ee;
  background: #eee;
}
.tab-list li:hover .line {
  display: block;
}
.tab-content {
  display: none; 
  width: 90%;
  min-height: 500px;
}
.no-reash {
  /* display: none;  */
  margin-top: 15px;
  width: 100%;
  height: 500px;
  background-image: url("../assets/sorry.gif");
  background-size: 30%;
  background-repeat: no-repeat;
  color: #666;
  overflow: hidden;
  text-align: center;
}
.rotate-words {
  max-width: 400px;
  margin: 0 auto;
  padding: 8% 0;
  text-transform: uppercase;
  font-family: "Oswald", sans-serif;
}
.rotate-words>div>span {
  display: block;
  height: 50px;
  font-size: 20px;
  text-transform: lowercase;
  opacity: 0.8;
  font-family: "Rubik", sans-serif;
}
.rotate-words>div {
  position: absolute;
  left: 3vw;
  width: 80vw;
  font-size: 40px;
  opacity: 0;
  overflow: hidden;
  line-height: 1.2em;
  animation: rotate-word 15s linear infinite 0s;
}
@keyframes rotate-word {
  0% {
    opacity: 0;
    transform: translateX(0);
    filter: blur(10px);
    transform: scale(1.2);
  }
  3% {
    opacity: 1;
    transform: translateX(0);
    filter: blur(0px);
    transform: scale(0.9);
  }
  12% {
    opacity: 1;
    transform: translateX(0);
    filter: blur(0px);
    transform: scale(1);
  }
  16% {
    opacity: 0;
    transform: translateX(0);
    filter: blur(10px);
    transform: scale(1.2);
  }
  80% {
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}
.rotate-words div:nth-child(1) {
  animation-delay: 1s;
}
.rotate-words div:nth-child(2) {
  animation-delay: 5s;
}
.rotate-words div:nth-child(3) {
  animation-delay: 10s;
}
.post-card {
  width: 100%;
  height: 120px;
  padding: 20px 0px;
  box-sizing: border-box;
  border-bottom: 1px solid #666;
}
.post-info {
  height: 100%;
  width: 40%;
  display: inline-block;
  box-sizing: border-box;
}
.post-head {
  width: 70%;
  height: 40px;
  display: flex;
  justify-content: space-between;
}
.post-name {
  width: 54%;
  line-height: 40px;
  height: 40px;
  font-family: "微软雅黑";
  font-size: 18px;
  text-align: left;
  font-weight: bolder;
}
.post-pay {
  width: 45%;
  height: 40px;
  line-height: 40px;
  text-align: right;
  color: orangered;
}
.post-body {
  width: 60%;
  height: 39px;
  display: flex;
  justify-content: space-between;
}
.post-others {
  height: 100%;
  line-height: 39px;
  padding-left: 0px;
  text-align: left;
}
.post-other {
  color: #666;
  font-size: 14px;
  padding-left: 5px;
}
.company-info {
  height: 100%;
  width: 29%;
  display: inline-block;
  box-sizing: border-box;
}
.company-name {
  height: 40px;
  line-height: 40px;
}
.company-name > a {
  color: #0287ee;
  font-size: 20px;
  text-decoration: none;
}
.company-infor {
  height: 39px;
  color: #aaa;
  line-height: 39px;
}
.company-logo {
  width: 29%;
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
.foot {
  width: 100%;
  height: 250px;
  margin-top: 30px;
  background-color: #eee;
}
.foot-title {
  width: 100%;
  height: 150px;
  box-sizing: border-box;
}
.ourselves {
  width: 24%;
  height: 150px;
  display: inline-block;
  box-sizing: border-box;
  padding: 20px 0px 0px 10%;
}
.ourselves-title {
  font-size: 18px;
  font-weight: 500;
}
.ourselves-body {
  list-style: none;
  display: block;
  color: #666;
  margin-top: 5px;
  text-decoration: none;
}
.ourselves-body:hover {
  color: #0287ee;
}
.join-us {
  width: 100%;
  position: relative;
}
.join-us:hover {
  color: #0287ee;
}
.join-us:hover .join {
  display: block;
}
.join {
  display: none;
  position: absolute;
  width: 100px;
  height: 100px;
  left: -110px;
  top: -20px;
  z-index: 99;
}
.join > img {
  width: 100px;
  height: 100px;
}
.foot-body {
  width: 100%;
  height: 100px;
  text-align: center;
  padding-top: 10px;
  font-size: 13px;
  color: #666;
  box-sizing: border-box;
}
.internet {
  display: block;
  height: 30px;
  line-height: 30px;
  box-sizing: border-box;
  padding: 5px 0;
  position: relative;
}
.internetImg {
  position: absolute;
  right: 50%;
  margin-right: 90px;
}
.internet > span {
  height: 20px;
  line-height: 20px;
  display: inline-block;
  position: absolute;
  left: 50%;
  margin-left: -88px;
}
.goto-top {
  width: 50px;
  height: 43px;
  font-size: 12px;
  color: #999;
  display: block;
  text-decoration: none;
  text-align: center;
  right: 20px;
  bottom: 100px;
  z-index: 999;
  position: fixed;
}
.goto-top:hover {
  color: #0287ee;
}
</style>

