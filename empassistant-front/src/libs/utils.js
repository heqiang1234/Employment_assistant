
import axios from 'axios';
export const linkTo = function(obj){
  console.log(obj)
  this.$router.push(obj);
}

export const replaceTo = function(obj){
  console.log(obj);
  this.$router.replace(obj)
}

export const searchJobData = (key,url,curPage=1,pageSize=12,Search_Id="position_name")=>{
  return axios({
    url:url,
    params:{
      CurrentPage: curPage,
      PageSize:pageSize,
      Search_Id:Search_Id,
      Search_Name:key
    }
  })
}