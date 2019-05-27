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
          <div class="type-item" data-type="IT互联网">
            <a href="#">IT互联网</a>
            <a href="#">软件</a>
            <a href="#">运营</a>
            <a href="#">硬件</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">软件</a>
                <a href="#">数据库</a>
                <a href="#">C#/.NET</a>
                <a href="#">Android</a>
                <a href="#">算法</a>
                <a href="#">运维</a>
                <a href="#">前端</a>
              </div>
              <div class="item-list">
                <a href="#">运营</a>
                <a href="#">新媒体</a>
                <a href="#">内容运营</a>
                <a href="#">编辑</a>
                <a href="#">SEO</a>
                <a href="#">产品运营</a>
                <a href="#">前端</a>
              </div>
              <div class="item-list">
                <a href="#">硬件</a>
                <a href="#">嵌入式</a>
                <a href="#">集成电路</a>
              </div>
              <div class="item-list">
                <a href="#">设计</a>
                <a href="#">Flash</a>
                <a href="#">UI/UE</a>
                <a href="#">特效</a>
                <a href="#">网页/美工</a>
                <a href="#">2D/3D</a>
              </div>
              <div class="item-list">
                <a href="#">通信</a>
                <a href="#">物联网</a>
                <a href="#">通信</a>
                <a href="#">射频</a>
              </div>
              <div class="item-list">
                <a href="#">产品</a>
                <a href="#">用户研究</a>
                <a href="#">产品助理</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="市场商务">
            <a href="#">市场商务</a>
            <a href="#">商务</a>
            <a href="#">销售</a>
            <a href="#">公关</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">商务</a>
                <a href="#">商务</a>
                <a href="#">招投标</a>
              </div>
              <div class="item-list">
                <a href="#">销售</a>
                <a href="#">推广</a>
                <a href="#">销售</a>
              </div>
              <div class="item-list">
                <a href="#">公关</a>
                <a href="#">媒介</a>
                <a href="#">公关</a>
              </div>
              <div class="item-list">
                <a href="#">客服</a>
                <a href="#">客户服务</a>
                <a href="#">销售支持</a>
              </div>
              <div class="item-list">
                <a href="#">市场</a>
                <a href="#">渠道</a>
                <a href="#">分析/调研</a>
                <a href="#">策划</a>
                <a href="#">品牌</a>
                <a href="#">市场</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="电子电器">
            <a href="#">电子电气</a>
            <a href="#">电子</a>
            <a href="#">电气</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">电子</a>
                <a href="#">光电</a>
                <a href="#">半导体/芯片</a>
                <a href="#">电子工程</a>
              </div>
              <div class="item-list">
                <a href="#">电气</a>
                <a href="#">电子设计</a>
                <a href="#">电气工程</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="其他类型">
            <a href="#">其他类型</a>
            <a href="#">体育快消</a>
            <a href="#">机械</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">体育快消</a>
                <a href="#">快消</a>
                <a href="#">体育</a>
              </div>
              <div class="item-list">
                <a href="#">机械制造</a>
                <a href="#">质量</a>
                <a href="#">机械设计</a>
                <a href="#">生产</a>
                <a href="#">安全</a>
                <a href="#">设备</a>
                <a href="#">自动化</a>
              </div>
              <div class="item-list">
                <a href="#">物流采购</a>
                <a href="#">采购</a>
                <a href="#">供应链</a>
                <a href="#">物流</a>
              </div>
              <div class="item-list">
                <a href="#">建筑房产</a>
                <a href="#">城规/市政</a>
                <a href="#">工程造价</a>
                <a href="#">建筑</a>
                <a href="#">土木</a>
                <a href="#">园林</a>
                <a href="#">土地开发</a>
                <a href="#">房产销售</a>
                <a href="#">给排水</a>
                <a href="#">物业管理</a>
              </div>
              <div class="item-list">
                <a href="#">生物医疗</a>
                <a href="#">医生</a>
                <a href="#">医药</a>
                <a href="#">生物</a>
                <a href="#">护理</a>
              </div>
              <div class="item-list">
                <a href="#">能源环保</a>
                <a href="#">矿产</a>
                <a href="#">能源</a>
                <a href="#">环保</a>
              </div>
              <div class="item-list">
                <a href="#">食品材料</a>
                <a href="#">材料</a>
                <a href="#">食品</a>
              </div>
              <div class="item-list">
                <a href="#">NGO公益</a>
                <a href="#">志愿者</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="人事行政">
            <a href="#">人力资源</a>
            <a href="#">猎头</a>
            <a href="#">行政</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">人力资源</a>
                <a href="#">人事/HR</a>
                <a href="#">招聘</a>
                <a href="#">企业文化</a>
              </div>
              <div class="item-list">
                <a href="#">猎头</a>
                <a href="#">猎头</a>
              </div>
              <div class="item-list">
                <a href="#">行政</a>
                <a href="#">行政</a>
                <a href="#">前台</a>
                <a href="#">助理</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="外语外贸">
            <a href="#">外语外贸</a>
            <a href="#">外语</a>
            <a href="#">外贸</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">外语</a>
                <a href="#">英语</a>
                <a href="#">日语</a>
                <a href="#">翻译</a>
              </div>
              <div class="item-list">
                <a href="#">外贸</a>
                <a href="#">报关员</a>
                <a href="#">外贸专员</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="金融法务">
            <a href="#">财经法务</a>
            <a href="#">金融</a>
            <a href="#">投资</a>
            <a href="#">法务</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">金融</a>
                <a href="#">基金</a>
                <a href="#">证券</a>
                <a href="#">风控</a>
                <a href="#">金融</a>
              </div>
              <div class="item-list">
                <a href="#">投资</a>
                <a href="#">分析师</a>
                <a href="#">投资</a>
              </div>
              <div class="item-list">
                <a href="#">法务</a>
                <a href="#">合规</a>
                <a href="#">律师</a>
                <a href="#">法务</a>
              </div>
              <div class="item-list">
                <a href="#">银行</a>
                <a href="#">客户经理</a>
                <a href="#">部门经理</a>
                <a href="#">贷款</a>
                <a href="#">大堂经理</a>
              </div>
              <div class="item-list">
                <a href="#">保险</a>
                <a href="#">业务</a>
                <a href="#">保单</a>
              </div>
              <div class="item-list">
                <a href="#">财会</a>
                <a href="#">审计</a>
                <a href="#">税务</a>
                <a href="#">财务</a>
                <a href="#">会计/出纳</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="教育咨询">
            <a href="#">教育咨询</a>
            <a href="#">教育</a>
            <a href="#">咨询</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">教育</a>
                <a href="#">教务</a>
                <a href="#">教师</a>
                <a href="#">幼教</a>
                <a href="#">培训</a>
                <a href="#">课程</a>
              </div>
              <div class="item-list">
                <a href="#">咨询</a>
                <a href="#">咨询/顾问</a>
              </div>
            </div>
          </div>
          <div class="type-item" data-type="媒体设计">
            <a href="#">媒体设计</a>
            <a href="#">广告</a>
            <a href="#">编辑</a>
            <a href="#">设计</a>
            <div class="item-infor">
              <div class="item-list">
                <a href="#">广告</a>
                <a href="#">创意</a>
                <a href="#">策划</a>
                <a href="#">AE</a>
              </div>
              <div class="item-list">
                <a href="#">编辑</a>
                <a href="#">编辑/采编</a>
                <a href="#">校队/排版</a>
              </div>
              <div class="item-list">
                <a href="#">设计</a>
                <a href="#">美术设计</a>
                <a href="#">工业设计</a>
                <a href="#">平面设计</a>
                <a href="#">视觉设计</a>
              </div>
              <div class="item-list">
                <a href="#">艺术</a>
                <a href="#">记者</a>
                <a href="#">支持/播音</a>
                <a href="#">编导</a>
              </div>
              <div class="item-list">
                <a href="#">艺术</a>
                <a href="#">演艺</a>
                <a href="#">摄影</a>
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
            PageSize: 15,
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
          console.log(that.jobList);
        });
    },
    chageListType(index) {
      this.curJobIndex = index;
    }
  },
  data() {
    return {
      totalJobNum: false, //总岗位数量
      jobList: [], //职位列表
      curJobPage: 1, //当前职位列表页数
      nextPage: true, //是否有下一页
      curJobIndex: 0 //当前职位列表类型
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
  cursor: default;
}
.type-item > a:hover {
  text-decoration: none;
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
.item-list :nth-child(1):hover {
  text-decoration: none;
  cursor: default;
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
.tab-list li:hover .line {
  display: block;
}
.tab-content {
  display: block;
  margin-top: 10px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 50px;
}
.tab-content .el-carousel {
  width: 100%;
}
.post {
  width: 32%;
  height: 195px;
  margin-top: 10px;
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
</style>