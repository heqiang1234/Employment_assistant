const api = 'http://120.79.15.183:8080/myssmp';
const API = {
  USERS:{
    LOGIN:api+'/dologin',//用户登录
    REGIST:api+'/save' //注册用户
  },
  JOBS:{
    GETJOBS:api+'/showemployment'
  }
}
module.exports = API;