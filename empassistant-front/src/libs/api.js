const api = 'http://120.79.15.183:8080/myssmp';
const API = {
  USER:{
    LOGIN:api+'/User/DoLogin',//用户登录
    REGIST:api+'/User/Save', //注册用户
    UPDATE:api+'/User/UpdateUser', //修改用户信息
    CHECKLOGIN:api+'/User/DoCheckLogin',//验证用户是否仍在登陆
    LOGOUT:api+'/User/LoginOut'//退出登录
  },
  JOBS:{
    GETJOBLIST:api+'/Position/selectAllPosition', //职位分页
    SEARCHJOBS:api+'/Position/SearchPosition',  //关键字搜索职位
    DETAILJOB:api+'/Position/selectByPositionId', //职位详情页
  },
  EMP:{
    GETEMP:api+'/Employment/ShowEmployment', //宣讲会分页
    DETAILEMP:api+'/Employment/ShowAllEmploymentInfo', //宣讲会详情
    SEARCHEMP:api+'/Employment/SearchEmployment',//搜索宣讲会
    GETABOUTEMP:api+'/Employment/ShowEmploymentInfoByCompany'//查看职位相关宣讲会
  },
  UPLOAD:{
    UPIMG:api+'/Upload/UploadImg/' //上传图片
  },
  OTHER:{
    GETADIMG:api+'/Photo/ShowEmploymentPhoto' //首页广告图片
  },
  VERIFYCODE:{
    GETCODE:api+'/VerifyCode/Kaptcha'
  }
}
module.exports = API;