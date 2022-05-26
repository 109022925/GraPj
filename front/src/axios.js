import axios from 'axios'
import Element from 'element-ui'
import store from './store'
import router from './router'

axios.defaults.withCredentials = true;
axios.defaults.baseURL = 'http://localhost'

// 前置拦截(请求拦截器)，所有的请求都会经过请求拦截器
axios.interceptors.request.use(config => {
        // 在发送请求前的操作
        console.log("请求拦截")
        return config
    },
    // 请求错误处理
    error => {
        console.log(error)
        error.msg = "请求错误！"
        return Promise.reject(error.msg)
    })

// 后置拦截(响应拦截器)
axios.interceptors.response.use(response => {
        // 在接受响应后的操作
        const res = response.data
        console.log("响应拦截")
        if (res.code === 200) {
            return response
        } else {
            // 弹窗错误信息（ type: 'error'）
            Element.Message({
                message: res.msg,
                type: 'error',
                duration: 2 * 1000
            })
            return Promise.reject(res.msg)
        }
    },
    // 响应错误处理(捕获并处理后台异常信息)
    error => {
        console.log(error)
        if (error.response.data) {
            error.msg = error.response.data.msg
            console.log("-------------------------")
            console.log(error.msg)
            console.log("-------------------------")
        }
        if (error.response.status === 401) {
            store.commit('REMOVE_INFO')
            router.push("/login")
            error.msg = "请重新登录！"
        }
        if (error.response.status === 403) {
            error.msg = "权限不足，无法访问！"
        }
        Element.Message({
            message: error.msg,
            type: 'error',
            duration: 2 * 1000
        })
        return Promise.reject(error.msg)
    })