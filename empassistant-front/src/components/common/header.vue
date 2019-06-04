<template>
     <div class="header">
      <div class="container">
        <h1>实习助手</h1>
        <ul class="subnav">
          <li>
            <router-link :to="{name:'home'}">首页</router-link>
          </li>
          <li>
            <a href="http://localhost:8080/#/login">我的简历</a>
          </li>
          <li>
            <a href="http://localhost:8080/#/careerTalk">校园招聘会</a>
          </li>
          <li>
            <a  @click="linkTo({name:'jobs'})" href="#">藏经阁</a>
          </li>
        </ul>
      </div>
    </div>
</template>
<style>
  .header {
  width: 100%;
  height: 56px;
  border-bottom: 1px solid #eaeaea;
}
.container {
  justify-content: space-between;
  display: flex;
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
</style>
