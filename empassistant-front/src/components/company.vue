<template>
  <div class="warp">
    <Header></Header>
    <div class="company-body">
      <div v-if="careerInfo.company" class="company-inf">
        <div class="logo-box">
          <img :src="careerInfo.logo" alt>
        </div>
        <div class="com-titles">
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
      <div class="co-msg">
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
        <div class="com-mesgs">
          <a class="com-title" href="#">
            <div class="com-logo">
              <img :src="careerInfo.company.company_logo" />
            </div>
            <p></p>
          </a>
          <div class="com-other">
            <p>领域：</p>
            <p>规模：{{careerInfo.company.company_size}}</p>
            <p>地址：{{careerInfo.company.company_address}}</p>
          </div>
          <div class="com-map">
            <a
              class="map-img"
              target="_blank"
              :href="careerInfo.company.map_link"
              :style="{backgroundImage:'url('+careerInfo.company.image_link+')'}"
            ></a>
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
  components: { Header, Footsy },
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

.company-body {
  width: 90%;
  min-width: 1080px;
  margin: 0px auto;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.company-inf {
  height: 167px;
  width: 70%;
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
.com-titles {
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
.co-msg {
  width: 100%;
  display: flex;
  margin-top: 10px;
  padding-top: 10px;
  border-top: #eee solid 1px;
}
.com-right {
  width: 30%;
  box-sizing: border-box;
  height: 400px;
  border-left: 1px solid #eee;
}
.company-talk {
  width: 70%;
  box-sizing: border-box;
  padding: 0px 25px;
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
.com-mesgs {
  width: 25%;
  box-sizing: border-box;
  padding: 0px 20px;
  border-left: 1px solid #eee;
}
.com-title {
  display: block;
  margin-top: 15px;
  text-decoration: none;
  color: orangered;
  text-align: center;
  border-bottom: 1px solid #eee;
}
.com-title > p {
  margin: 10px 0px;
}
.com-logo {
  width: 84px;
  height: 84px;
  display: flex;
  margin: 0 auto;
  overflow: hidden;
  align-items: center;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
}
.com-logo> img {
  width: 84px;
}
.com-other {
  padding: 20px 0px;
}
.com-other > p {
  color: #666;
  padding: 3px 0;
}
.com-map {
  width: 255px;
  height: 255px;
  margin: 10px auto;
  overflow: hidden;
  border-radius: 5px;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
}
.tocareer-btn {
  width: 185px;
  margin: 10px auto;
}
.map-img {
  display: block;
  width: 100%;
  height: 100%;
}
.com-credit {
  width: 255px;
  height: 185px;
  margin: 20px auto;
  background-image: url("../assets/celify.png");
  background-repeat: no-repeat;
  background-size: 255px;
  border-radius: 5px;
  text-align: center;
}
.com-grade {
  padding-top: 50px;
  color: #e98f36;
  font-size: 24px;
}
.com-credit > p {
  margin-bottom: 10px;
}
.com-grade-text {
  font-size: 18px;
}
.com-text {
  font-size: 14px;
}
.com-text > span {
  color: #e98f36;
}
.com-text > a {
  color: #1e649f;
  text-decoration: none;
  font-size: 13px;
}
.com-text > a:hover {
  text-decoration: #1e649f underline;
}
.com-post {
  border-bottom: 1px dashed #ddd;
  padding: 5px 0;
  margin: 10px 0;
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
