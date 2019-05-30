import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register/register'
import home from '@/components/home'
import display from '@/components/display'
import careerTalk from '@/components/careerTalk'
import company from '@/components/company'
import post from '@/components/post'

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
    },
    {
      path: '/display',
      name: 'display',
      component: display
    },
    {
      path: '/careerTalk',
      name: 'careerTalk',
      component: careerTalk
    },
    {
      path: '/company',
      name: 'company',
      component: company
    },
    {
      path: '/post',
      name: 'post',
      component: post
    },
  ]
})
