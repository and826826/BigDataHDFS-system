import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import mytable from '../components/mytable.vue'
import mylogin from '../components/mylogin.vue'
import test from '../components/test.vue'
import download from '../components/download.vue'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path:'/mytable:username',
    name:'mytable',
    component:mytable
  },
  {
    path:'/mylogin/',
    name:'mylogin',
    component:mylogin
  },
  {
    path:'/test',
    name:'test',
    component:test
  },
  {
    path:'/down',
    name:'down',
    component:download
  },
  
  {
    path: '/about/:user',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
