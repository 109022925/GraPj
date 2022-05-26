<template>
    <div>
        <el-container>
            <el-header id="header" style="text-align: center">
                <img id="logo" src="../assets/img/logo.jpg" @click="toIndex">
                {{this.$store.getters.getUserInfo.username}}的个人空间
                <el-button class="logout" type="text" @click="logout">退出</el-button>
            </el-header>
            <el-main id="main">
                <el-menu :default-active=this.show class="menu" mode="horizontal" @select="choose">
                    <el-menu-item index="1" class="main-item">
                        发表博客
                    </el-menu-item>
                    <el-menu-item index="2" class="main-item">
                        收藏夹
                    </el-menu-item>
                    <el-submenu index="3" class="main-item">
                        <template slot="title">已发表记录</template>
                        <el-menu-item index="3-1">博客</el-menu-item>
                        <el-menu-item index="3-2">评论</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="4" class="main-item" @click="toUpdate">
                        更改密码
                    </el-menu-item>
                </el-menu>
                <BlogEdit show="home" v-show="show==='1'"></BlogEdit>
                <Blogs :url=this.urlStar type="getByUid" v-show="show==='2'"></Blogs>
                <Blogs :url="this.urlHas" type="getByUid" v-show="show==='3-1'"></Blogs>
                <Comments type="getByUid" v-show="show==='3-2'"></Comments>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Blogs from "../components/Blogs";
    import BlogEdit from "../components/BlogEdit";
    import Comments from "../components/Comments";

    export default {
        name: "Home",
        components: {
            Comments,
            Blogs,
            BlogEdit
        },
        data() {
            return {
                urlStar: '',
                urlHas: '',
                show: '1',
            }
        },
        methods: {
            toUpdate() {
                this.$router.push('/forget');
            },
            choose(key) {
                this.show = key;
            },
            toIndex() {
                this.$router.push('/index')
            },
            logout() {
                this.$confirm('账号将退出登录, 是否继续?', 'warn', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios
                        .get("/logout")
                        .then(res => {
                            if (res.data.code === 200) {
                                this.$store.commit("REMOVE_INFO")
                                this.$message.success("已退出当前账号")
                                this.$router.push("/login")
                            }
                        })
                })
            }
        },
        created() {
            this.urlStar = '/user/favor'
            this.urlHas = '/blog/selectUid'
        }
    }
</script>

<style scoped>
    #header {
        margin-top: 30px;
        margin-bottom: 30px;
    }

    #main {
        margin-left: 50px;
    }

    .menu {
        width: 720px;
    }

    .main-item {
        width: 180px;
    }

    .logout {
        float: right;
        margin-right: 5px;
    }
</style>
