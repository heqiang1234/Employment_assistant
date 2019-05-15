// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import qs from 'qs'
import API from './libs/api.js'
// axios.defaults.baseURL = 'http://120.79.15.183:8080/myssmp';
Vue.prototype.axios = axios;
Vue.prototype.qs = qs
Vue.prototype.API = API
Vue.config.productionTip = false;


/* eslint-disable no-new */
new Vue({
  el: '#app',   
  router,
  render: h => h(App)
})
