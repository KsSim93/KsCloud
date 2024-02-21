import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserInfo from '../views/UserInfo.vue'
import Login from '../views/login/Login.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/kscloud/user',
    name: 'user',
    component: UserInfo
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
