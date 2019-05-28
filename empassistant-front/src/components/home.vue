<template>
  <div class="wrap">
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
            <a href="http://localhost:8080/#/display">藏经阁</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="top-bar">
      <div class="container">
        <div class="left-bar">
          <div class="user-count">{{totalJobNum}}</div>
          <div class="user-count-num">共计{{totalJobNum}}个热门职业等着你</div>
        </div>
        <div class="right-bar">
          <div class="form">
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
          <div class="form-bar">
            热门搜索：
            <a href="#">项目</a>
            <a href="#">前端</a>
            <a href="#">财会</a>
            <a href="#">市场</a>
            <a href="#">行政</a>
            <a href="#">广告</a>
          </div>
        </div>
      </div>
    </div>
    <div class="mid-bar">
      <div class="container">
        <div class="list">
          <div class="list-title">全部分类</div>
          <div
            v-for="(classfy,index) in jobClassfy"
            :key="index"
            class="type-item"
            @click="searchJob"
          >
            <a  v-for="(item,index) in classfy.ovallClsf" :key="index" href="#">{{item}}</a>
            <div class="item-infor">
              <div v-for="(detail,index) in classfy.detailClsf" :key="index" class="item-list">
                <a v-for="(item,index) in detail" :key="index" href="#">{{item}}</a>
              </div>
            </div>
          </div>
        </div>
        <div class="swiper">
          <el-carousel height="288px">
            <el-carousel-item v-for="item in 4" :key="item">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
          <div class="ad-bar">
            <div class="ad-container">
              <div class="vip-companys">
                <img src="../assets/main-picture.png" title="这是我的公司" alt="广告招聘">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="company-tab">
      <div class="container-tab">
        <ul class="tab-list">
          <li
            @click="chageListType(index)"
            :data-index="index"
            v-for="(item,index) in ['热门职业','最新职业','急招职业']"
            :key="index"
            :class="curJobIndex===index?'select':''"
          >{{item}}</li>
        </ul>
        <ul class="tab-content">
          <div v-for="(item) in jobList" :key="item.career_talk_id" class="post">
            <!-- 职位盒子 -->
            <div class="post-title">
              <div class="post-head">
                <div class="post-name">{{item.position_name}}</div>
                <div class="post-pay">{{item.positionWage}}</div>
              </div>
              <div class="post-body">
                <div class="post-others">
                  <i class="el-icon-location-outline"></i>
                  <span class="post-other">{{item.workPlace}}</span>
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
            <div class="post-sepLine"></div>
            <div class="post-company">
              <div class="company-logo">
                <img :src="item.company_logo" alt>
              </div>
              <div class="company-infor">
                <div class="company-name">
                  <a href="#">{{item.companyType}}</a>
                </div>
                <div class="company-info">{{item.companyType}} || {{item.company_size}}</div>
              </div>
            </div>
          </div>
        </ul>
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
    <a class="goto-top" href="#">
      <img src="../assets/right-hn.png" alt>
      回到顶部
    </a>
  </div>
</template>

<script>
export default {
  name: "home",
  created() {
    this.getJobList();
  },
  methods: {
    getJobList() {
      //获取职位列表
      let that = this;
      if (!that.nextPage) return;
      that
        .axios({
          url: this.API.JOBS.GETJOBLIST,
          methods: "GET",
          params: {
            PageSize: 12,
            CurrentPage: that.curJobPage
          }
        })
        .then(res => {
          console.log(res);
          let totalJob = "" + res.data.extendInfo.List.totalCount;
          for (let i = 0; i < 8 - totalJob.length; i++) {
            totalJob = "0" + totalJob;
          }
          if (!that.totalJobNum) {
            that.totalJobNum = totalJob;
          }
          that.jobList = res.data.extendInfo.List.lists;
        });
    },
    chageListType(index) {//切换职位信息列表
      this.curJobIndex = index;
    },
    searchJob(e){//搜索相关职位
    let that = this;
      console.log(e);
      that.axios({
        url:that.API.JOBS.SEARCHJOBS,
        methods:'GET',
        params:{
          CurrentPage:1,
          PageSize:12,
          Search_Id:'position_name',
          Search_Name:e.target.innerText
        }
      })
      .then((res)=>{
        console.log(res);
      })
      .catch((err)=>{
        console.log(err);
      })
    }
  },
  data() {
    return {
      totalJobNum: false, //总岗位数量
      jobList: [], //职位列表
      curJobPage: 1, //当前职位列表页数
      nextPage: true, //是否有下一页
      curJobIndex: 0, //当前职位列表类型
      tabPages: [
        //职位信息标签页
      ],
      jobClassfy: [
        //职位分类
        {
          ovallClsf: [
            //总体分类
            "IT互联网",
            "软件",
            "运营",
            "硬件"
          ],
          detailClsf: [
            //具体分类
            ["软件", "数据库", "C#/.NET", "Android", "算法", "运维", "前端"],
            ["运营", "新媒体", "内容运营", "编辑", "SEO", "产品运营"],
            ["硬件", "嵌入式", "集成电路"],
            ["设计", "Flash", "UI/UE", "特效", "网页/美工", "2D/3D"],
            ["通信", "物联网", "通信", "射频"],
            ["产品", "用户研究", "产品助理"]
          ]
        },
        {
          ovallClsf: ["市场商务", "商务", "销售", "公关"],
          detailClsf: [
            ["商务",'商务', "招投标"],
            ["销售",'销售', "推广"],
            ["公关", '公关',"媒介"],
            ["客服", "客户服务", "销售支持"],
            ["市场", "渠道", "分析/调研", "策划", "品牌", "市场"]
          ]
        },
        {
          ovallClsf: ["电子电器", "电子", "电气"],
          detailClsf: [
            ["电子", "光电", "半导体/芯片", "电子工程"],
            ["电气", "电子设计", "电气工程"]
          ]
        },
        {
          ovallClsf: ["其它类型", "体育快消", "机械"],
          detailClsf: [
            ["体育快消", "快消", "体育"],
            ["机械制造", "质量", "机械设计", "生产", "安全", "设备", "自动化"],
            ["物流采购", "采购", "供应链", "物流"],
            [
              "建筑/房产",
              "城规/市规",
              "工程造价",
              "建筑",
              "土木",
              "园林",
              "土地开发",
              "房产销售",
              "排水",
              "物业管理"
            ],
            ["生物医疗", "医生", "医药", "生物", "护理"],
            ["能源环保", "矿产", "能源", "环保"],
            ["食品材料", "材料", "食品"],
            ["NGO公益", "志愿者"]
          ]
        },
        {
          ovallClsf: ["人力资源", "猎头", "行政"],
          detailClsf: [
            ["人力资源", "人事/HR", "招聘", "企业文化"],
            ["猎头", "猎头"],
            ["物流采购", "采购", "供应链", "物流"],
            ["行政", "行政", "前台", "助理"]
          ]
        },
        {
          ovallClsf: ["外语外贸", "外语", "外贸"],
          detailClsf: [
            ["外语", "英语", "日语", "翻译"],
            ["外贸", "报关员", "外贸专员"]
          ]
        },
        {
          ovallClsf: ["金融法务", "金融", "投资", "法务"],
          detailClsf: [
            ["金融", "基金", "证券", "风控", "金融"],
            ["投资", "分析师", "投资"],
            ["法务", "合规", "律师", "法务"],
            ['银行','客户经理','部门经理','贷款','大堂经理'],
            ['保险','业务','保单'],
            ['财会','审计','税务','财务','会计/出纳']
          ]
        },
        {
          ovallClsf: ["教育咨询", "教育", "咨询"],
          detailClsf: [
            ["教育", "教务", "教师", "幼教", "培训",'课程'],
            ["咨询", "咨询/顾问"],
          ]
        },
        {
          ovallClsf: ["媒体设计", "广告", "编辑",'设计'],
          detailClsf: [
            ["广告", "创意", "策划", "AE"],
            ["编辑", "编辑/采编",'校对/排版'],
            ['设计','美术设计','工业设计','平面设计','视觉设计'],
            ['艺术','记者','支持/播音','编导'],
            ['艺术','演艺','摄影']
          ]
        }
      ]
    };
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 288px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.wrap {
  width: 100%;
  min-width: 1080px;
}
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
.top-header {
  width: 100%;
  height: 172px;
  background: #67b0e7;
}
.leftImg {
  width: 23%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
}
.top-bar {
  margin-top: 20px;
  width: 100%;
  height: 120px;
  padding-top: 20px;
  box-sizing: border-box;
}
.left-bar {
  width: 23%;
  height: 100px;
  padding-top: 20px;
  background: #fafafa;
  display: inline-block;
  box-sizing: border-box;
}
.user-count {
  font-weight: 400;
  letter-spacing: 5px;
  width: 95%;
  height: 50px;
  color: #0287ee;
  font-size: 30px;
  text-align: center;
  line-height: 30px;
  padding: 10px 0px;
  margin: 0px auto;
  background: #fff;
  box-sizing: border-box;
}
.user-count-num {
  line-height: 24px;
  font-size: 12px;
  text-align: center;
  height: 24px;
  color: #333;
}
.right-bar {
  width: 75%;
  height: 100px;
  padding-top: 20px;
  display: inline-block;
  box-sizing: border-box;
}
.form {
  width: 100%;
  height: 50px;
}
.form-bar {
  color: #6aa2e4;
  line-height: 28px;
  font-size: 14px;
}
.form-bar > a {
  text-decoration: none;
  color: #6aa2e4;
  padding-right: 15px;
}
.form-bar > a:hover {
  text-decoration: underline;
}
.mid-bar {
  width: 100%;
  padding: 20px 0px 20px 0px;
  box-sizing: border-box;
}
.list {
  width: 23%;
  height: 408px;
  font-size: 14px;
  background: #fafafa;
  display: inline-block;
  z-index: 999;
  box-sizing: border-box;
}
.list-title {
  height: 40px;
  line-height: 40px;
  padding: 0 15px;
  box-sizing: border-box;
  border: solid 1px transparent;
  border-bottom: solid 1px #eee;
}
.type-item {
  height: 40px;
  line-height: 40px;
  padding-left: 15px;
  box-sizing: border-box;
  border: solid 1px transparent;
  border-bottom: solid 1px #eee;
  position: relative;
}

.type-item:hover {
  background-color: #fff;
  border: solid 1px #f5f5f5;
  border-right: none;
  border-left: none;
  z-index: 999;
}
.type-item:hover .item-infor {
  display: block;
}
.type-item :nth-child(1) {
  color: #000;
  font-weight: 500;
  font-size: 15px;
}
.type-item > a {
  text-decoration: none;
  color: #555;
  padding-right: 10px;
  cursor:pointer
}
.type-item > a:hover {
  cursor: pointer;
  text-decoration: underline;
}
.item-infor {
  position: absolute;
  left: 100%;
  top: -10px;
  width: 250%;
  background-color: #fff;
  border: solid 1px #d5d5d5;
  z-index: 99;
  display: none;
}
.item-list {
  padding-right: 20px;
}
.item-list > a {
  text-decoration: none;
  color: #555;
  padding-left: 15px;
}
.item-list > a:hover {
  text-decoration: underline;
}

.item-list :nth-child(1) {
  color: #000;
  font-size: 15px;
  font-weight: 500;
}
.swiper {
  width: 75%;
  height: 408px;
}
.ad-bar {
  width: 100%;
  height: 120px;
  padding-bottom: 20px;
  box-sizing: border-box;
}
.ad-container {
  margin-top: 20px;
}
.vip-companys {
  width: 100px;
  height: 100px;
  background: #aaa;
  display: flex;
  align-items: center;
}
.vip-companys > img {
  width: 100px;
  height: 40px;
}
.vip-companys:hover {
}
.company-tab {
  width: 100%;
}
.container-tab {
  height: 100%;
  max-width: 90%;
  min-width: 900px;
  margin: 0px auto;
}
.tab-list {
  height: 40px;
  width: 100%;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  font-family: "Microsoft Yahei";
  background: #f3f3f3;
}
.tab-list li {
  float: left;
  width: 100px;
  height: 100%;
  cursor: pointer;
  list-style: none;
  position: relative;
}
.tab-list .select {
  background: #fff;
  color: #0287ee;
}
.tab-list .select::before {
  width: 100%;
  content: "";
  height: 4px;
  background: #0287ee;
  position: absolute;
  top: 0;
  left: 0;
}

.tab-list li:hover {
  color: #0287ee;
}
.tab-content {
  display: block;
  margin-top: 10px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  margin-bottom: 50px;
}
.tab-content .el-carousel {
  width: 100%;
}
.post {
  width: 380px;
  height: 195px;
  margin: 5px 20px;
  padding: 0px 10px;
  box-sizing: border-box;
  border: 1px solid #dadada;
  background: #fff;
}
.post-title {
  height: 100px;
  text-align: center;
  padding: 20px 0px;
  box-sizing: border-box;
}
.post-head {
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: space-between;
}
.post-name {
  cursor: pointer;
  max-width: 59%;
  overflow: hidden;
  line-height: 30px;
  height: 30px;
  font-family: "微软雅黑";
  font-size: 18px;
  text-align: left;
  font-weight: bolder;
  color: rgba(0, 0, 0, 0.7);
}
.post-sepLine {
  height: 1px;
  width: 100%;
  background: #dadada;
}
.post-pay {
  font-size: 14px;
  width: 40%;
  height: 30px;
  line-height: 30px;
  text-align: right;
  color: #fd8150;
}
.post-body {
  width: 80%;
  height: 30px;
  display: flex;
  justify-content: space-between;
}
.post-others {
  width: 30%;
  height: 100%;
  line-height: 30px;
  padding-left: 0px;
  text-align: left;
}
.post-other {
  color: #666;
  font-size: 14px;
  margin-left: -2px;
}
.post-company {
  height: 90px;
  width: 100%;
  padding-top: 15px;
  box-sizing: border-box;
  line-height: 90px;
  display: flex;
  flex-direction: row;
}
.company-logo {
  width: 60px;
  height: 60px;
  overflow: hidden;
}
.company-logo > img {
  width: 100%;
}
.company-infor {
  width: 70%;
  height: 50px;
  padding-left: 20px;
}
.company-name {
  height: 30px;
  line-height: 30px;
}
.company-name > a {
  color: #0287ee;
  text-decoration: none;
}
.company-info {
  height: 30px;
  color: #aaa;
  line-height: 30px;
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