<template>
  <div class="warp">
    <div class="header">
      <div class="container">
        <h1>实习助手</h1>
        <ul>
          <li>
            <a href="#">首页</a>
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
    <div class="top">
        <div class="container">
            <div class="top-leftImg">
                <img class="leftImg" src="../assets/main-picture.png" alt="">
            </div>
        </div>
    </div>
    <div class="top-bar">
        <div class="container">
            <div class="left-bar">
                <div class="user-count">0018983</div>
                <div class="user-count-num">共计0018983个热门职业等着你</div>
            </div>
            <div class="right-bar">
                <div class="form">
                    <div class="bnt1">全国 <img src="../assets/arrow1.png" alt=""></div>
                    <div class="bnt1 bnt2">搜职业 <img src="../assets/arrow1.png" alt=""></div>
                    <div class="bnt3"><input class="search" type="text"  placeholder="请输入您期望的职位" autofocus></div>
                    <div class="bnt4">立即搜索</div>
                </div>
                <div class="form-bar">
                    热门搜索：
                    <a href="">项目</a>
                    <a href="">前端</a>
                    <a href="">财会</a>
                    <a href="">市场</a>
                    <a href="">行政</a>
                    <a href="">广告</a>
                </div>
            </div>
        </div>
    </div>
  </div>
</template>

<style scope>
*{
    margin: 0px;
    padding: 0px;
}
.warp {
  width: 100%;
  min-width: 1080px;
  background: #fefefe;
}
.header {
  width: 100%;
  height: 56px;
  border-bottom: 1px solid #333;
}
.container {
  height: 100%;
  max-width: 90%;
  margin: 0px auto;
  position: relative;
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
  color: rgba(3, 3, 3, 0.8);
  font-size: 16px;
  padding: 0 16px;
  line-height: 56px;
  cursor: pointer;
}
.container ul li a:hover {
  color: #02a0e9;
}
.top{
    width: 100%;
    min-width: 1080px;
    height: 172px;
    background-color: #02a0e9;
}
.top-leftImg{
    width: 260px;
    height: 100%;
    display: inline-block;
    position: relative;
}
.leftImg{
    position: absolute;
    bottom: 0px;
    left: 22px;
    display: block;
}
.top-bar{
    width: 100%;
    padding-top: 20px;
    height: 120px;
    box-sizing: border-box;
    position: absolute;
    left: 0px;
}
.left-bar{
    width: 25%;
    height: 100px;
    padding-top: 20px;
    background-color: #eee;
    display: inline-block;
    box-sizing:border-box;
}
.user-count{
    width: 234px;
    height: 50px;
    box-sizing:border-box;
    line-height: 30px;
    margin: 0px auto;
    padding: 10px 0px;
    text-align: center;
    font-size: 28px;
    color: #02a0e9;
    background-color: #fafafa;
}
.user-count-num{
    width: 100%;
    line-height: 24px;
    font-size: 12px;
    text-align: center;
    height: 24px;
    color: #333;
}
.right-bar{
    width: 73%;
    height: 100px;
    padding-top: 20px;
    display: inline-block;
    box-sizing:border-box;
    position: absolute;
    right: 0px;
}
.form{
    display: flex;
    align-items: center;
    width: 100%;
    height: 46px;
    border: 2px solid #02a0e9;
    /* box-sizing:border-box; */
    font-size: 14px;
    font-family: "微软雅黑";
}
.bnt1{
    display: inline-block;
    width: 10%;
    box-sizing:border-box;
    text-align: center;
    cursor: pointer;
    border-right: 1px solid #02a0e9;
}
.bnt2{
    width: 11%;
}
.bnt3{
    width: 50%;
    box-sizing:border-box;
    padding-left:12px; 
    display: inline-block;
}
.search{
    border: none;
    outline: none;
    display: inline-block;
    width: 398px;
    margin: 0px;
    padding: 0px;
    font-size: 16px;
}
.bnt4{
    cursor: pointer;
    justify-content: center;
    align-items: center;
    height: 100%;
    flex: 1;
    /* box-sizing:border-box; */
    background:#02a0e9;
    color: #fafafa;
    font-size: 16px;
    display: flex;
}
.bnt4:hover{
    background: #abcdef;
}
.form-bar{
    width: 100%;
    height: 24px;
    margin-top: 5px; 
    color: #6aa2e4;
    text-decoration: none;
    line-height: 28px;
    font-size: 14px;
}
.form-bar>a{
    color: #6aa2e4;
    padding: 0px 5px;
    text-decoration: none;
    font-size: 14px;
}
.form-bar>a:hover{
    text-decoration: underline;
}
</style>
