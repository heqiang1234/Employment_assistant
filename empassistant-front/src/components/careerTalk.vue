<template>
  <div class="warp">
    <Header></Header>
    <Searcher></Searcher>
    <div v-if="empInfo.list.length>0" class="career-talk">
      <div class="container">
        <div v-for="(item,index) in empInfo.list" :key="index" class="careers-card">
          <div class="company-info">
            <div class="company-name">
              <a
                @click="linkTo({name:'company',params:{id:item.career_talk_id}})"
                href="#"
              >{{item.meet_name}}</a>
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
    <div v-else class="no-reash">
      <div>
        <i>
          你想要的没有找到
          <br>
          <span>Sorry~QAQ</span>
        </i>
      </div>
    </div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header";
import Searcher from "./common/searcher";
import Footsy from "./common/footsy.vue";
export default {
  components: { Header, Searcher, Footsy },
  created() {
    if (this.$route.params.companyId) {
      this.empInfo.key = this.$route.params.companyId;
      this.searchCrTkList().then(res => {
        this.handlePageData(res.data.extendInfo);
      });
    } else if (this.$route.params.lists) {
      this.empInfo.list = this.$route.params.lists;
      this.empInfo.key = this.$route.params.searchKey;
      let res = {};
      res["pagebean"] = this.$route.params;
      this.handlePageData(res);
    } else {
      this.getCrTkList().then(res => {
        this.handlePageData(res.data.extendInfo);
      });
    }
  },
  methods: {
    getCrTkList() {
      //获取宣讲会列表
      return this.axios({
        url: this.API.EMP.GETABOUTEMP,
        params: {
          CurrentPage: this.pagination.curPage,
          PageSize: this.pagination.pageSize,
          Search_Name: this.empInfo.key
        }
      });
    },
    searchCrTkList() {
      return this.axios({
        url: this.API.EMP.GETABOUTEMP,
        params: {
          CurrentPage: this.pagination.curPage,
          PageSize: this.pagination.pageSize,
          Search_Name: this.empInfo.key
        }
      });
    },
    handleCurrentChange(val) {
      //跳转页数
      this.pagination.curJobPage = val;
      this.getCrTkList()
        .then(res=>{
          this.handlePageData(res.data.extendInfo)
        })
    },
    handlePageData(res) {
      //处理页面数据
      console.log(res);
      this.empInfo.list = res.pagebean.lists;
      if (res.pagebean.totalPage > this.pagination.curPage + 9) {
        this.pagination.totalPage = this.pagination.curPage + 9;
      } else {
        this.pagination.totalPage = res.pagebean.totalPage;
      }
    }
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
      }
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
.jobs-pagetab {
  text-align: center;
  margin-top: 20px;
}
.no-reash {
  /* display: none;  */
  color: #dfdfdf;
  font-size: 20px;
  text-align: center;
  height: 300px;
  padding-top: 150px;
  box-sizing: border-box;
}
</style>
