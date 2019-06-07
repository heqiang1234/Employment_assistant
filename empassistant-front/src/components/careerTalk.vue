<template>
  <div class="warp">
    <Header></Header>
    <Searcher></Searcher>
    <div class="career-talk">
      <div class="container">
        <div v-for="(item,index) in empInfo.list" :key="index" class="careers-card">
          <div class="company-info">
            <div class="company-name">
              <a @click="linkTo({name:'company',params:{id:item.career_talk_id}})" href="#">{{item.meet_name}}</a>
            </div>
            <div class="company-type">
              <span>{{item.company.companyTrade}}</span>
              <span>{{item.company.companyType}}</span>
            </div>
          </div>
          <div class="talk-info">
             <div class="talk-body">
             <i class="el-icon-school"></i>
              <span class="talk-address">{{item.school_name}}</span>
              </div>
            <div class="talk-time">
              <i class="el-icon-time"></i>
              {{item.meet_day}} {{item.meet_time}}
            </div>
            <div class="talk-body">
             
              <i class="el-icon-location-outline"></i>
              <span class="talk-address">{{item.address}}</span>
              <i class="el-icon-view"></i>
              <span class="talk-address">{{item.hitCount}}</span>
            </div>
          </div>
          <div class="company-logo">
            <div class="logoImg">
              <img :src="item.logo" alt>
            </div>
          </div>
        </div>
        <div class="jobs-pagetab">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="pagination.curPage"
            :page-size="pagination.pageSize"
            layout="prev, pager, next, jumper"
            :page-count="pagination.totalPage"
          ></el-pagination>
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
import Searcher from './common/searcher'
export default {
  components: { Header,Searcher },
  created() {
    this.getCrTkList();
  },
  methods: {
    getCrTkList() { //获取宣讲会列表
      let that = this;
      this.axios({
        url: that.API.EMP.GETEMP,
        params: {
          CurrentPage: that.pagination.curPage,
          PageSize: that.pagination.pageSize
        }
      }).then(res => {
        console.log(res);
        that.empInfo.list = res.data.extendInfo.pagebean.lists;
        if (res.data.extendInfo.pagebean.totalPage > that.pagination.curPage + 9){that.pagination.totalPage = that.pagination.curPage + 9;}
        else that.pagination.totalPage = res.data.extendInfo.pagebean.totalPage;
          
      });
    },
     handleCurrentChange(val) {
      //跳转页数
      this.pagination.curJobPage = val;
      this.getCrTkList();
    },
  },
  data() {
    return {
      pagination: {
        curPage: 1,
        pageSize: 12,
        totalPage: 1
      },
      empInfo: {
        list: [],
        key: ""
      },
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
  margin: 0px auto;
  flex-direction: column;
}


.career-talk {
  width: 100%;
  min-height: 500px;
}
.careers-card {
  width: 90%;
  height: 120px;
  padding: 20px 0px 19px 0px;
  border-bottom: 1px solid #eee;
  box-sizing: border-box;
}
.talk-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 40%;
  display: inline-block;
  box-sizing: border-box;
}
.talk-time {
  width: 100%;
  height: 40px;
  line-height: 40px;
  text-align: left;
  color: orangered;
}
.talk-body {
  width: 100%;
  text-align: left;
}
.talk-address {
  color: #666;
  margin-right: 20px;
  font-size: 14px;
}
.company-info {
  overflow: hidden;
  height: 100%;
  width: 39%;
  display: inline-block;
  box-sizing: border-box;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding-right: 35px;
}
.company-name {
  height: 40px;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  line-height: 40px;
}
.company-name > a {
  color: #0287ee;
  font-size: 20px;
  text-decoration: none;
}
.company-type {
  height: 39px;
  width: 100%;
  display: flex;
}
.company-type > span {
  height: 39px;
  color: #aaa;
  line-height: 39px;
  padding: 0px 10px;
  display: block;
}
.company-logo {
  width: 20%;
  height: 100%;
  display: inline-block;
  box-sizing: border-box;
  position: relative;
}
.logoImg {
  width: 79px;
  height: 79px;
  text-align: center;
  position: absolute;
  right: 20px;
}
.logoImg > img {
  width: 100%;
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
.jobs-pagetab {
  margin-top:20px;
  text-align: center;
}
</style>
