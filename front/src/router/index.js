import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login"
import BlogDetail from "../views/BlogDetail"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: {path: '/login'},
    },
    {
        // 登录页面
        path: '/login',
        name: 'Login',
        component: () => import("../views/Login")
    },
    {
      // 管理员页面
        path: '/admin',
        name: 'Admin',
        component: () => import("../views/Admin")
    },
    {
        // 注册页面
        path: '/register',
        name: 'Register',
        component: () => import("../views/Register")
    },
    {
        // 用户忘记密码，修改密码
        path: '/forget',
        name: 'Forget',
        component: () => import("../views/ForgetPassword")
    },
    {
        // 个人首页
        path: '/home',
        name: 'Home',
        component: () => import("../views/Home"),
        meta: {requiresAuth: true}
    },
    {
        // 博客列表
        path: '/index',
        name: 'Index',
        component: () => import("../views/Index"),
        meta: { requiresAuth: true }
    },
    {
        // 博客详情页
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: () => import("../views/BlogDetail"),
        meta: { requiresAuth: true }
    },
    {
        // 修改博客
        path: '/blog/edit',
        name: 'Edit',
        component: () => import("../views/Edit"),
        meta: { requiresAuth: true }
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
