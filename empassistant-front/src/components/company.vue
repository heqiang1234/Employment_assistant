<template>
  <div class="warp">
    <Header></Header>
    <div class="company-body">
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
            <span
              v-if="careerInfo.company.companyTrade"
              class="mesgs"
            >{{careerInfo.company.companyTrade}}}</span>
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
        <span class="talk-mesg">{{careerInfo.company.companyProfile}}</span>
        <div class="talk-title">招聘职位</div>
        <div v-for="(item) in careerInfo.positionList" :key="item.positionID" class="com-post">
          <div class="post-head">
            <div
              @click="linkTo({name:'post',params:{id:item.positionID}})"
              class="post-name"
            >{{item.position_name}}</div>
            <div class="post-pay">
              <i class="el-icon-coin"></i>
              {{item.positionWage}}
            </div>
            <div class="need-people">
              <i class="el-icon-user-solid"></i>
              {{item.num}}
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
    </div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header";
import Footsy from "./common/footsy";
export default {
  components: { Header,Footsy },
  created() {
    console.log(this.$route.params);
    if (this.$route.params.id) {
      this.career_talk_id = this.$route.params.id;
      this.getCareerTalk();
    }
  },
  methods: {
    getCareerTalk() {
      this.axios({
        url: this.API.EMP.DETAILEMP,
        params: {
          career_talk_id: this.career_talk_id
        }
      }).then(res => {
        console.log(res);
        this.careerInfo = res.data.extendInfo.pagebean[0];
      });
    }
  },
  data() {
    return {
      career_talk_id: 0,
      careerInfo: {}
    };
  }
};
</script>
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
.company-body{
  max-width: 90%;
  margin: 0px auto;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.company-inf {
  height: 167px;
  max-width: 90%;
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
  line-height: 100px;
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
  border-top: #666 solid 1px;
  max-width: 90%;
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
.post-profe {
  max-width: 40%;
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
  left: 45%;
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

</style>
