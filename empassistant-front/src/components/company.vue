<template>
  <div class="warp">
    <Header></Header>
    <div v-if="careerInfo.company" class="company-inf">
      <div class="logo-box">
        <img :src="careerInfo.logo" alt>
      </div>
      <div class="com-title">
        <p class="com-name">{{careerInfo.company_name}}</p>
        <p class="com-mesg">
          <i class="el-icon-location-outline"></i>
          <span class="mesgs">{{careerInfo.company.company_address}}</span>
          <i class="el-icon-user-solid"></i>
          <span class="mesgs">{{careerInfo.company.company_size}}</span>
          <i v-if="careerInfo.company.companyTrade" class="el-icon-suitcase"></i>
          <span v-if="careerInfo.company.companyTrade" class="mesgs">{{careerInfo.company.companyTrade}}}</span>
        </p>
      </div>
    </div>
    <div v-if="careerInfo.company" class="company-talk">
      <div class="talk-title">宣讲会信息</div>
      <span class="talk-mesg">宣讲单位：{{careerInfo.company_name}}</span>
      <span class="talk-mesg">宣讲时间：{{careerInfo.meet_day}} {{careerInfo.meet_time}}</span>
      <span class="talk-mesg">所在学校：{{careerInfo.school_name}}</span>
      <span class="talk-mesg">宣讲地点：{{careerInfo.address}}</span>
      <span class="talk-mesg"></span>
      <div class="talk-title">单位简介</div>
      <span class="talk-mesg">
        {{careerInfo.company.companyProfile}}
      </span>
      <div class="talk-title">招聘职位</div>
      <div v-for="(item) in careerInfo.positionList" :key="item.positionID" class="com-post">
        <div class="post-head">
          <div @click="linkTo({name:'post',params:{id:item.positionID}})" class="post-name">{{item.position_name}}</div>
          <div class="post-pay">
            <i class="el-icon-coin"></i>{{item.positionWage}}
          </div>
          <div class="need-people">
            <i class="el-icon-user-solid"></i> {{item.num}}
          </div>
        </div>
        <div class="post-body">
          <div class="post-profe">{{item.professionals}}</div>
          <div class="degree">
            <i class="el-icon-school"></i> 本科及以上
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
            <a href="#" class="ourselves-body">找实习</a>
            <a href="#" class="ourselves-body">宣讲会</a>
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
  </div>
</template>
<script>
import Header from "./common/header";
export default {
  components:{Header},
  created() {
    console.log(this.$route.params);
    if (this.$route.params.id) {
      this.career_talk_id = this.$route.params.id;
      this.getCareerTalk()
    }
  },
  methods: {
    getCareerTalk() {
      this.axios({
        url: this.API.EMP.DETAILEMP,
        params: {
          career_talk_id: this.career_talk_id
        }
      }).then((res)=>{
        console.log(res);
        this.careerInfo = res.data.extendInfo.lists[0]
      })
    },

  },
  data() {
    return{
      career_talk_id:0,
      careerInfo:{}
    }
  },
};
</script>
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
  flex-direction: row;
  position: relative;
}
.logo-box {
  height: 100px;
  width: 100px;
  padding: 5px;
  display: inline-block;
  line-height:100px;
}
.logo-box > img {
  width: 100px;
}
.com-title {
  height: 100px;
  width: 500px;
  padding: 5px 0px 5px 100px;
  display: inline-block;
}
.com-name {
  max-width: 740px;
  line-height: 26px;
  padding-top: 10px;
  font-size: 22px;
  color: #4c4c4c;
}
.com-mesg {
  font-size: 15px;
  color: #a6a6a6;
  line-height: 26px;
  margin-top: 40px;
  max-width: 740px;
}
.mesgs {
  color: #666;
  font-size: 14px;
  line-height: 26px;
  padding: 0px 30px 0px 5px;
}
.title {
  position: absolute;
  height: 50px;
  width: 200px;
  right: 100px;
  background-color: #0287ee;
  font-size: 26px;
  font-family: "微软雅黑";
  text-align: center;
  line-height: 50px;
}
.company-talk {
  max-width: 90%;
  background-color: #f1f4f6;
  margin: 0px auto;
  box-sizing: border-box;
  margin-top: 10px;
  padding: 15px 44px 32px 37px;
}
.talk-title {
  height: 30px;
  line-height: 30px;
  border-left: 2px solid red;
  padding-left: 5px;
  font: 20px "微软雅黑";
  margin-top: 20px;
}
.talk-mesg {
  padding-left: 10px;
  display: block;
  margin-top: 20px;
}
.com-post {
  border-bottom: 1px dashed #ddd;
  padding: 10px 0;
}
.post-head {
  position: relative;
  height: 30px;
  line-height: 30px;
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  font-family: "微软雅黑";
}
.post-profe{
  max-width:40%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.post-name {
  max-width: 30%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: rgb(143, 72, 31);
  font-size: 18px;
  cursor: pointer;
}
.post-pay {
  position: absolute;
  left:45%;
  color: orangered;
}
.need-people {
  width: 100px;
}
.post-body {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}
.post-degree {
  width: 100px;
}
.foot {
  width: 100%;
  height: 250px;
  margin-top: 30px;
  background-color: #eee;
}
.container-tab {
  height: 100%;
  max-width: 90%;
  min-width: 900px;
  margin: 0px auto;
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
</style>
