<template>
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
                <img src="../../assets/QQ.jpg" alt>
              </div>
            </div>
            <a href="#" class="ourselves-body">加入我们</a>
          </div>
          <div class="ourselves">
            <span class="ourselves-title">联系我们</span>
            <div class="join-us">
              <a href="#" class="ourselves-body">团队微信</a>
              <div class="join">
                <img src="../../assets/weChat.jpg" alt>
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
            <img class="internetImg" src="../../assets/record-icon.png" alt>
            <span>赣公网安备 00000000000000号</span>
          </div>
        </div>
      </div>
    </div>
</template>
<style scoped>
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
.jobs-pagetab{
  text-align: center;
  margin-top:20px;
}
</style>
