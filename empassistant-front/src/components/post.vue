<template>
  <div class="warp">
    <Header></Header>

    <div v-if="jobInfo.company" class="information">
      <div class="post-mesg">
        <div class="post-title">
          <span class="company-name">{{jobInfo.company.company_name}}</span>
          <span class="post-name">{{jobInfo.position_name}}</span>

          <div class="sign1"></div>
          <div class="sign2"></div>
        </div>

        <div class="post-body">
          <div class="mesg">
            <i class="el-icon-coin"></i>
            <span class="pay">{{jobInfo.positionWage}}</span>
            <i class="el-icon-location-outline"></i>
            <span class="address">{{jobInfo.workPlace}}</span>
            <i class="el-icon-school"></i>
            <span class="degree">{{jobInfo.professionals}}</span>
          </div>
          <div v-if="jobInfo.positionTemptation" class="bonus">福利待遇：{{jobInfo.positionTemptation}}</div>
          <div v-if="jobInfo.salaryWelfare" class="bonus">{{jobInfo.salaryWelfare}}</div>
          <div class="publish-time">招聘人数：{{jobInfo.num}}</div>
        </div>
        <div class="demant-title">职位描述</div>
        <div class="need">
          <p v-html="jobInfo.jobDescription"></p>
        </div>
        <div class="demant-title">公司简介</div>
        <div class="need">
          <p v-html="jobInfo.company.companyProfile"></p>
        </div>
      </div>
      <div class="com-mesg">
        <a class="com-title" href="#">
          <div class="com-logo">
            <img :src="jobInfo.company.company_logo" alt>
          </div>
          <p>{{jobInfo.company.company_name}}</p>
        </a>
        <div class="com-other">
          <p>领域：{{jobInfo.company.companyTrade}}</p>
          <p>规模：{{jobInfo.company.company_size}}</p>
          <p>地址：{{jobInfo.company.company_address}}</p>
        </div>
        <div class="com-map">
          <a
            class="map-img"
            :href="jobInfo.company.map_link"
            target="_blank"
            :style="{backgroundImage:'url('+jobInfo.company.image_link+')'}"
            alt
          ></a>
        </div>
        <div class="tocareer-btn">
          <el-button
            @click="linkTo({name:'company',params:{id:jobInfo.career_talk_id}})"
            type="primary"
            plain
          >查看该公司宣讲会信息</el-button>
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
    let options = this.$route.params;
    console.log(options.id);
    if (options.id) {
      this.getDetail(options.id).then(res => {
        this.jobInfo = this.formatInfo(res.data.extendInfo.List[0]);
      });
    }
  },
  methods: {
    formatInfo(obj) {
      //格式化内容信息
      console.log(obj);
      if (obj.jobDescription) {
        obj.jobDescription = obj.jobDescription
          .replace(/\n|\r\n/g, "<br/>")
          .replace(/[;；]/g, "；<br/>")
          .replace(/[-]/g, "<br/>")
          .replace("[' ']+", "<br/>")
          .replace(/['\u3002']/g, "。<br>")
          .replace("<br>s*(<br>s*)+", "<br/>");
      }
      if (obj.company.companyProfile) {
        obj.company.companyProfile = obj.company.companyProfile.replace(
          /\n|\r\n/g,
          "<br/>"
        );
      }
      return obj;
    },
    getDetail(id) {
      //获取详情
      return this.axios({
        url: this.API.JOBS.DETAILJOB,
        params: {
          Position_Id: id
        }
      });
    }
  },
  data() {
    return {
      jobInfo: {}
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
.information {
  padding: 15px 0;
  width: 90%;
  min-width: 1200px;
  margin: 15px auto;
  display: flex;
  flex-direction: row;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.post-mesg {
  width: 75%;
  box-sizing: border-box;
}
.post-title {
  padding-right: 50px;
  display: inline-block;
  background-color: #0287ee;
  height: 75px;
  margin-top: 15px;
  color: #fff;
  padding-left: 20px;
  position: relative;
}
.company-name {
  width: 100%;
  height: 20px;
  display: block;
  padding: 10px 0 0 0;
  line-height: 20px;
  font: 16px "黑体";
}
.degree {
  display: inline-block;
  max-width: 300px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  vertical-align: bottom;
}
.post-name {
  margin-top: 5px;
  width: 100%;
  display: block;
  height: 40px;
  line-height: 40px;
  font: 28px "黑体";
}
.sign1 {
  width: 0;
  height: 0;
  border-width: 0 10px 10px;
  border-style: solid;
  border-color: transparent transparent #012f55;
  position: absolute;
  transform: rotate(45deg);
  left: -14px;
  bottom: -8px;
}
.sign2 {
  width: 15px;
  height: 75px;
  background-color: #0287ee;
  position: absolute;
  left: -15px;
  bottom: 0px;
}
.mesg {
  height: 40px;
  line-height: 40px;
  margin: 10px 0;
  padding-left: 20px;
}
.pay {
  color: orangered;
  padding-right: 30px;
}
.address {
  padding: 0 30px 0 5px;
}
.bonus {
  padding-left: 20px;
  height: 40px;
  font: "黑体" 14px;
  line-height: 40px;
  margin: 10px 0px;
}
.publish-time {
  padding-left: 20px;
  font: "微软雅黑" 14px;
  line-height: 30px;
  margin: 5px 0px;
  color: #666;
}
.demant-title {
  height: 30px;
  line-height: 30px;
  border-left: 2px solid red;
  padding-left: 18px;
  font: 20px "微软雅黑";
  margin-top: 20px;
}
.need {
  padding: 0px 20px;
  margin: 10px 0;
}
.need > p {
  line-height: 25px;
  font-family: "微软雅黑";
}
.need-post {
  margin: 15px 0px;
  font-size: 18px;
  padding-left: 20px;
  font-weight: 500;
}
.com-mesg {
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
  overflow: hidden;
  margin: 0px auto;
  align-items: center;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
}
.com-logo > img {
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
</style>
