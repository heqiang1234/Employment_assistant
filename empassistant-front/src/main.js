// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import {Image,Dropdown,DropdownMenu,DropdownItem,Alert,Checkbox,Message,Input,Select,Button,Option,Carousel,CarouselItem,Icon,Loading,Upload,DatePicker,Pagination,Dialog} from 'element-ui';
import axios from 'axios';
import echarts from 'echarts'
Vue.prototype.$echarts = echarts 
import API from './libs/api.js'
import {linkTo,replaceTo} from './libs/utils'
axios.defaults.withCredentials=true;//让ajax携带cookie
Vue.prototype.USERSTATUS = {
  login:false,
  userInfo:{}
}
Vue.prototype.axios = axios; //请求插件
Vue.prototype.API = API; //接口地址
Vue.config.productionTip = false;
Vue.use(Image);
Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Input);
Vue.use(Select);
Vue.use(Button);
Vue.use(Option);
Vue.use(Carousel);
Vue.use(CarouselItem);
Vue.use(Icon);
Vue.use(Loading);
Vue.use(Pagination);
Vue.use(Upload);
Vue.use(DatePicker);
Vue.use(Dialog);
Vue.use(Checkbox);
Vue.use(Alert);
// Vue.use(Backtop)
Vue.prototype.$message = Message;//信息组件
Vue.prototype.linkTo = linkTo;
Vue.prototype.replaceTo = replaceTo;
/* eslint-disable no-new */
new Vue({
  el: '#app',   
  router,
  render: h => h(App)
})
