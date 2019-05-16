import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home'
import register from '@/components/register'
import login from '@/components/login'

Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: home
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/login',
      name: 'login',
      component: login
    }
  ]
})
