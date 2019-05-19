// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import {Message,Input,Select,Button,Option,Carousel,CarouselItem,Icon} from 'element-ui';
import axios from 'axios';
import API from './libs/api.js'
// axios.defaults.baseURL = 'http://120.79.15.183:8080/myssmp';
Vue.prototype.axios = axios; //请求插件
Vue.prototype.API = API; //接口地址
Vue.config.productionTip = false;
Vue.use(Input);
Vue.use(Select);
Vue.use(Button);
Vue.use(Option);
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Icon)
Vue.prototype.$message = Message;//信息组件


/* eslint-disable no-new */
new Vue({
  el: '#app',   
  router,
  render: h => h(App)
})
