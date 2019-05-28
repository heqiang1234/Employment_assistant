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
    <div class="company-inf">
        <div class="logo-box">
            <img src="../assets/aiqiyi.jpg" alt="">
        </div>
        <div class="com-title">
            <p class="com-name">爱艺奇</p>
            <p class="com-mesg">
                <i class="el-icon-location-outline"></i>
                <span class="mesgs">北京</span>
                <i class="el-icon-user-solid"></i>
                <span class="mesgs">5000人以上</span>
                <i class="el-icon-suitcase"></i>
                <span class="mesgs">互联网</span>
            </p>
        </div>
        <div class="title">校园招聘会</div>
    </div>
    <div class="company-talk">
        <div class="talk-title">宣讲会信息</div>
        <span class="talk-mesg">宣讲单位：凯捷咨询（中国）有限公司</span>
        <span class="talk-mesg">宣讲时间：2019年05月29日 14:00</span>
        <span class="talk-mesg">所在学校：江西财经大学</span>
        <span class="talk-mesg">宣讲地点：麦庐园荟庐软件学院报告厅H102</span>
        <span class="talk-mesg">点击人次：1311</span>
        <div class="talk-title">单位简介</div>
        <span class="talk-mesg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;凯捷集团是欧洲第一，全球并列第三的IT 咨询公司，我们是全球领先的咨询、信息技术和外包服务提供商，
            在全世界40 多个国家拥有超过20 万名员工，2018 年凯捷全球营业额达132 亿欧元。
        </span>
        <span class="talk-mesg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;凯捷中国在北京、沈阳、上海、昆山、杭州、广州、佛山、香港和台北都设有分公司，帮助客户创建并提供适
            应其业务需求的最优化解决方案。凯捷在中国的主要业务集中在汽车行业、能源电力行业、快消品行业以及互联网行业，
            提供的解决方案和服务包括ERP（包括SAP 和Oracle）、人力资源解决方案（包括PeopleSoft 和SuccessFactors）、
            测试、CRM（包括Seibel、Salesforce 和Dynamics）、互联网开发和数字化体验等。
        </span>
        <div class="talk-title">招聘职位</div>
        <div class="post">
            
        </div>
    </div>
  </div>
</template>
<style scoped>
.warp {
  width: 100%;
  min-width: 1080px;
  background: #eee;
}
.header {
  width: 100%;
  height: 56px;
}
.container {
  height: 100%;
  max-width: 90%;
  background-color: #f1f4f6;
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
.company-inf {
  height: 167px;
  max-width: 90%;
  background-color: #f1f4f6;
  margin: 0px auto;
  box-sizing: border-box;
  margin-top: 20px;
  padding: 25px 44px 32px 37px;
  display: flex;
  flex-direction:row;
  position: relative;
}
.logo-box{
    height: 100px;
    width: 100px;
    padding: 5px;
    display: inline-block;
}
.logo-box>img{
    width: 100px;
    height: 100px;
}
.com-title{
    height: 100px;
    width: 500px;
    padding: 5px 0px 5px 100px;
    display: inline-block;
}
.com-name{
    max-width: 740px;
    line-height: 26px;
    padding-top: 10px;
    font-size: 22px;
    color: #4C4C4C;
}
.com-mesg{
    font-size: 15px;
    color: #A6A6A6;
    line-height: 26px;
    margin-top: 40px;
    max-width: 740px;
}
.mesgs {
  color: #666;
  font-size: 14px;
  line-height: 26px;
  padding:0px 30px 0px 5px;
}
.title{
    position:absolute;
    height:50px;
    width:200px;
    right:100px;
    background-color: #0287ee;
    font-size: 26px;
    font-family: "微软雅黑";
    text-align: center;
    line-height: 50px;
}
.company-talk{
  max-width: 90%;
  background-color: #f1f4f6;
  margin: 0px auto;
  box-sizing: border-box;
  margin-top: 10px;
  padding: 15px 44px 32px 37px;
}
.talk-title{
    height: 30px;
    line-height: 30px;
    border-left: 2px solid red;
    padding-left: 5px;
    font: 20px "微软雅黑";
    margin-top: 20px;
}
.talk-mesg{
    padding-left: 10px;
    display: block;
    margin-top: 20px;
}

</style>
