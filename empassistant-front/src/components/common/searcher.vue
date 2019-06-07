<template>
  <div class="search">
      <div class="search-container">
        <div class="search-input">
          <el-input placeholder="请输入内容" v-model="key" class="input-with-select">
            <el-select
              style="width:120px;"
              placeholder="选择地区"
              v-model="place"
              slot="prepend"
            >
              <el-option label="全国" value="1"></el-option>
              <el-option label="北京" value="2"></el-option>
            </el-select>
            <el-select
              style="width:120px;margin-left:15px;"
              placeholder="选择信息"
              v-model="type"
              slot="prepend"
            >
              <el-option label="搜职业" value="1"></el-option>
              <el-option label="搜公司" value="2"></el-option>
            </el-select>
            <el-button style="width:130px;" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
        <div class="search-likely">
          <span likely-title>相似搜索：</span>
          <a href="#">计算机</a>
          <a href="#">软件测试</a>
          <a href="#">哈哈哈</a>
          <a href="#">嘿嘿嘿</a>
        </div>
      </div>
    </div>
</template>
<script>
export default {

  data(){
    return{
      key:'',//搜索关键字,
      place:'',//搜索地区
      type:'',//搜索类型
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
</style>
