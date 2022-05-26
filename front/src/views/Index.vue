<template>
    <div>
        <el-container>
            <el-header id="header" style="text-align: center" height="100px">
                <img id="logo" src="../assets/img/logo.jpg">
                <span id="title">welcome to ccblog</span>
                <el-link id="privateRoom" @click="toHome">欢迎您，{{this.$store.getters.getUserInfo.username}}</el-link>
            </el-header>
            <el-main id="main">
                <Blogs url="/blog" type="getAll"></Blogs>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Blogs from "../components/Blogs";

    export default {
        name: "Index",
        components: {
            Blogs,
        },
        data() {
            return {

            }
        },
        methods: {
            toHome() {
                this.$router.push('/home')
            }
        }
    }
</script>

<style scoped>
    #privateRoom {
        margin-left: 20%;
        font-size: 20px;
    }

    #title {
        font-size: 40px;
        color: #409EFF;
    }

    #header {
        margin-top: 30px;
        margin-bottom: 30px;
    }
</style>