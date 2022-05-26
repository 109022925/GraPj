import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    // 定义全局参数，其它页面可以直接获取到state中的内容。
    // sessionStorage中只能存字符串，不能存入对象。所以我们存入序列化的json字符串。
    state: {
        token: localStorage.getItem("token"),
        // 通过反序列化获取sessionStorage中的userInfo对象
        userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
        // uid: JSON.parse(sessionStorage.getItem("uid")),
        // username: JSON.parse(sessionStorage.getItem("username"))
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token;
            localStorage.setItem("token", token);
        },
        SET_USERINFO: (state, userInfo) => {
          state.userInfo = userInfo;
          sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
        },
        // remove
        REMOVE_INFO: (state) => {
            state.token = '';
            state.userInfo = '';
            localStorage.removeItem("token");
            sessionStorage.removeItem("userInfo")
            localStorage.setItem("token", '');
            sessionStorage.setItem("userInfo", JSON.stringify(''));
        }
    },
    getters: {
        // get
        getToken: state => {
            return state.token;
        },
        getUserInfo: state => {
            return state.userInfo;
        },
        // getUid: state => {
        //     return state.uid;
        // },
        // getUsername: state => {
        //     return state.username;
        // },
    },
    actions: {},
    modules: {}
})
