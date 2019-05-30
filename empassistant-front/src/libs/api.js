const api = 'http://120.79.15.183:8080/myssmp';
const API = {
  USER:{
    LOGIN:api+'/User/DoLogin',//用户登录
    REGIST:api+'/User/Save' //注册用户
  },
  JOBS:{
    GETJOBLIST:api+'/Position/selectAllPosition',
    SEARCHJOBS:api+'/Position/SearchPosition',
    DETAILJOB:api+'/Position/selectByPositionId',
  },
  EMP:{
    GETEMP:api+'/Employment/ShowEmployment' //宣讲会分页
  }
}
module.exports = API;