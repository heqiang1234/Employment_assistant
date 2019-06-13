<template>
  <div @keyup.enter="search"  class="search">
      <div class="search-container">
        <div class="search-input">
          <el-input placeholder="请输入内容" v-model="key" class="input-with-select">
            <el-select
                style="width:120px;margin-left:15px;"
                placeholder="选择搜索内容"
                slot="prepend"
                v-model="searchType"
              >
                <el-option label="搜岗位" value = 1></el-option>
                <el-option label="搜宣讲会" value = 2></el-option>
              </el-select>
            <el-button @click="search" style="width:130px;" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
        <div  @click="getClassfyJobs" class="search-likely">
          <span likely-title>热门搜索：</span>
         <a href="#">项目</a>
            <a href="#">前端</a>
            <a href="#">财会</a>
            <a href="#">市场</a>
            <a href="#">行政</a>
            <a href="#">广告</a>
        </div>
      </div>
    </div>
</template>
<script>
import { searchJobData } from "../../libs/utils";
export default {
  methods:{
    search(){
      console.log(this.searchType);
      switch(this.searchType){
        case "搜岗位":
        case '1':this.toJobsResult(this.key,this.API.JOBS.SEARCHJOBS);break;
        case "搜宣讲会":
        case '2':this.toJobsResult(this.key,this.API.EMP.SEARCHEMP);break;
      }
    },
    getClassfyJobs(e) {
      //搜索相关职位
      let that = this;
      let key = e.target.innerText;
      console.log(key);
      this.toJobsResult(key,this.API.JOBS.SEARCHJOBS)
    },
    toJobsResult(key,url,curPage=1,pageSize=12,Search_Id="position_name"){
      console.log(key);
      console.log(url);
       searchJobData(key, url,curPage,pageSize,Search_Id)
        .then(res => {
          console.log(res);
          if (res.data.code != "200") {
            this.$message({
              showClose: true,
              message: res.data.extendInfo.login_error,
              type: "error"
            });
            return;
          }
          let initInfo = res.data.extendInfo.pagebean;
          initInfo["searchKey"] = key;
          if(this.searchType == 1 || this.searchType=="搜岗位") 
          {
            if(this.$route.path == '/jobs')this.linkTo({name:'blank'});
            this.replaceTo({ name: "jobs", params: initInfo });
          }
          else if(this.searchType == 2) {
            if(this.$route.path == '/careerTalk') this.linkTo({name:'blank'})
            this.replaceTo({name:'careerTalk',params:initInfo});
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  data(){
    return{
      key:'',//搜索关键字,
      searchType:'搜岗位',//搜索类型
    }
  }
}
</script>
<style>
  .search {
  width: 100%;
  height: 100px;
  margin-top: 15px;
}
.search-input {
  width: 100%;
  height: 40px;
}
.el-input__inner {
  height: 40px;
  line-height: 40px;
}
 .el-input-group__append {
  width: 150px;
  text-align: center;
}
.search-likely {
  width: 100%;
  height: 50px;
  padding: 10px 0px;
  display: block;
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
.search-container {
  height: 100%;
  max-width: 90%;
  margin: 0px auto;
  flex-direction: column;
}
.el-scrollbar__wrap{
  margin-bottom:0 !important;
}
</style>
