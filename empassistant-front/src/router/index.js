import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register/register'
import home from '@/components/home'
import jobs from '@/components/jobs'
import careerTalk from '@/components/careerTalk'
import company from '@/components/company'
import post from '@/components/post'
import personalCenter from '@/components/personalCenter'
import dataView from '@/components/dataView'
import blank from '@/components/blank'

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
      path: '/jobs',
      name: 'jobs',
      component: jobs
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
    {
      path: '/personalCenter',
      name: 'personalCenter',
      component: personalCenter
    },
    {
      path:'/dataView',
      name:'dataView',
      component:dataView
    },
    {
      path:'/blank',
      name:'blank',
      component:blank
    }
  ]
})
