import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
// 引入自定义的axios.js
import './axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// 路由拦截
import './router/permission'
import { Message } from 'element-ui';


Vue.use(mavonEditor).use(Element)


Vue.prototype.$message = Message
Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
