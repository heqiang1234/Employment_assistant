<template>
  <div class="warp">
   <Header></Header>
   <Searcher></Searcher>
    <div class="show-post">
      <div class="container bd-box">
        <div v-if="jobList.length>0"  class="tab-content">
          <div v-for="(item) in jobList" :key="item.positionID" class="post-card">
            <div class="post-info">
              <div class="post-head">
                <div @click="linkTo({name:'post',params:{id:item.positionID}})" class="post-name">{{item.position_name}}</div>
                <div class="post-pay">{{item.positionWage}}</div>
              </div>
              <div class="post-body">
                <div class="post-others">
                  <i class="el-icon-location-outline"></i>
                  <span class="post-other">{{item.workPlace}}</span>
                </div>
              </div>
            </div>
            <div class="company-info">
              <div class="company-name">
                <a href="#">{{item.company.company_name}}</a>
              </div>
              <div class="company-infor">{{item.company.companyTrade}} | {{item.company.company_size}}</div>
            </div>
            <div class="company-logo">
              <div class="logoImg">
                <img :src="item.company.company_logo" alt>
              </div>
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
          <div class="jobs-pagetab">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="curPage"
            :page-size="pageSize"
            layout="prev, pager, next, jumper"
            :page-count="pageCount"
          >
          </el-pagination>
        </div>
        
      </div>
    </div>
    <Footsy></Footsy>
    <a class="goto-top" href="#">
      <img src="../assets/right-hn.png" alt>
      回到顶部
    </a>
  </div>
</template>
<script>
import Header from './common/header.vue'
import Searcher from './common/searcher.vue'
import Footsy from "./common/footsy"
export default {
  components:{Header,Searcher,Footsy},
  created(){
    let options = this.$route.params;
    console.log(options);
    if(JSON.stringify(options)==='{}'){
      this.getMethodList();
      return;
    }
    this.jobList = options.lists;
    this.curPage = options.currPage;
    this.key = options.searchKey
    if(options.totalCount > this.curPage+9){
      this.pageCount = this.curPage+9;
    }
    else{
      this.pageCount = options.totalPage
    }
  },
  methods:{
    getMethodList(){
      //搜索相关职位
      let that = this;
      let key = this.key
      that
        .axios({
          url: that.API.JOBS.SEARCHJOBS,
          methods: "GET",
          params: {
            CurrentPage: that.curPage,
            PageSize: that.pageSize,
            Search_Id: "position_name",
            Search_Name: key
          }
        })
        .then(res => {
          console.log(res);
          if(res.data.code != "200"){
            this.$message({
              showClose: true,
              message: res.data.extendInfo.log_error,
              type: "error"
            });
            return;
          }
          that.jobList = res.data.extendInfo.pagebean_position_name.lists;
          that.curPage = res.data.extendInfo.pagebean_position_name.currPage;
          if(res.data.extendInfo.pagebean_position_name.totalPage > that.curPage+9){
            that.pageCount = that.curPage+9;
          }
          else{
            that.pageCount = res.data.extendInfo.pagebean_position_name.totalPage
          }
          })
        .catch(err => {
          console.log(err);
        });
    },
    handleCurrentChange(val){
      this.curJobPage = val;
      this.getMethodList();
    }
  },
  data(){
    return{
      jobList:[],//职位列表
      curPage:1,//当前页
      totalCount:1,//总条目数
      pageSize:12,//分页容量
      pageCount:10, //分页数量
      key:''//搜索关键字
    }
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
  border-bottom: 1px solid #dadada;
}

.bd-box{
  min-height: 400px;
}
.container {
  justify-content: space-between;
  display: flex;
  height: 100%;
  max-width: 90%;
  align-items: center;
  flex-direction: column;
}

.search {
  width: 100%;
  height: 100px;
  margin-top: 15px;
}
.search-input {
  width: 100%;
  height: 50px;
}
.el-input__inner {
  height: 50px;
  line-height: 50px;
}
.el-input-group__append {
  background-color: #98caf0;
  width: 150px;
  text-align: center;
}
.search-likely {
  margin-left:20px;
  width: 100%;
  height: 50px;
  padding: 10px 0px;
  box-sizing: border-box;
  line-height: 30px;
}
.likely-title {
  font-weight: 600;
  padding-right: 10px;
}
.search-likely > a {
  list-style: none;
  color: #aaa;
  text-decoration: none;
  padding-right: 10px;
}
.search-likely > a:hover {
  color: #0287ee;
}
.tab-list {
  margin-top: 10px;
  height: 40px;
  width: 100%;
  text-align: center;
  line-height: 40px;
  font-family: "微软雅黑";
}
.tab-list li {
  float: left;
  width: 150px;
  height: 100%;
  border-top-left-radius: 20% 100%;
  border-bottom-right-radius: 20% 100%;
  cursor: pointer;
  background-color: #ccc;
  list-style: none;
}
.tab-list li:hover {
  font-size: 16px;
  color: #0287ee;
  background: #eee;
}
.tab-list li:hover .line {
  display: block;
}
.tab-content {

  width: 90%;
  min-height: 500px;
}
.no-reash {
  /* display: none;  */
  color:#dfdfdf;
  font-size: 20px;
  text-align: center;
  height:300px;
  padding-top:150px;
  box-sizing: border-box;
}

.post-card {
  width: 100%;
  height: 120px;
  padding: 20px 0px;
  box-sizing: border-box;
  border-bottom: 1px solid #eee;
  position: relative;
}
.post-info {
  height: 100%;
  width: 40%;
  display: inline-block;
  box-sizing: border-box;
}
.post-head {
  width: 70%;
  height: 40px;
  display: flex;
  justify-content: space-between;
}
.post-name {
  cursor: pointer;
  max-width: 65%;
  line-height: 40px;
  height: 40px;
  font-family: "微软雅黑";
  font-size: 18px;
  text-align: left;
  font-weight: bolder;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.post-pay {
  max-width: 75%;
  height: 40px;
  line-height: 40px;
  text-align: right;
  color: orangered;
}
.post-body {
  width: 60%;
  height: 39px;
  display: flex;
  justify-content: space-between;
}
.post-others {
  height: 100%;
  line-height: 39px;
  padding-left: 0px;
  text-align: left;
}
.post-other {
  color: #666;
  font-size: 14px;
  padding-left: 5px;
}
.company-info {
  height: 100%;
  max-width: 40%;
  display: inline-block;
  box-sizing: border-box;
   white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.company-name {
  height: 40px;
  line-height: 40px;
}
.company-name > a {
  color: #0287ee;
  font-size: 20px;
  text-decoration: none;
}
.company-infor {
  height: 39px;
  color: #aaa;
  line-height: 39px;
}
.company-logo {
  /* width: 29%; */
  height: 100%;
  display: inline-block;
  box-sizing: border-box;
  /* position: relative; */
}
.logoImg {
  width: 79px;
  height: 79px;
  text-align: center;
  position: absolute;
  right: 20px;
  line-height:79px;
}
.logoImg > img {
  width: 100%;
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
.jobs-pagetab{
  text-align: center;
  margin-top:20px;
}
</style>

