<template>
    <div>
        <el-container style="border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu :default-active=this.show @select="choose">
                    <el-menu-item index="1">个人信息</el-menu-item>
                    <el-menu-item index="2">投递记录</el-menu-item>
                    <el-menu-item index="3">在线投递</el-menu-item>
                </el-menu>
            </el-aside>

            <el-container>
                <el-header style="text-align: right; font-size: 12px">
                </el-header>

                <el-main v-show="show==='1'">
                    <el-descriptions title="个人信息">
                        <el-descriptions-item label="用户名">{{user_info.username}}</el-descriptions-item>
                        <el-descriptions-item label="姓名">{{user_info.name}}</el-descriptions-item>
                        <el-descriptions-item label="联系方式">{{user_info.tel}}</el-descriptions-item>
                        <el-descriptions-item label="身份证号码">{{user_info.numbers}}</el-descriptions-item>
                        <el-descriptions-item label="角色">
                            <el-tag size="small">{{user_info.roles}}</el-tag>
                        </el-descriptions-item>
                    </el-descriptions>
                </el-main>
                <el-main v-show="show==='2'">
                    <el-table :data="blogData">
                        <el-table-column prop="bid" label="id" width="120"/>
                        <el-table-column prop="username" label="author" width="180"/>
                        <el-table-column prop="title" label="title" width="180"/>
                        <el-table-column prop="time" label="time" width="180"/>
                        <el-table-column label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button @click="deleteBlog(scope.row)" icon="el-icon-delete"/>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-main>
                <el-main v-show="show==='3'">
                    <el-table :data="commentData">
                        <el-table-column prop="cid" label="id" width="120"/>
                        <el-table-column prop="title" label="title" width="180"/>
                        <el-table-column prop="username" label="author" width="180"/>
                        <el-table-column prop="content" label="content" width="240"/>
                        <el-table-column prop="stamp" label="time" width="180"/>
                        <el-table-column label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button @click="deleteComment(scope.row)" icon="el-icon-delete"/>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-main>
                <el-main v-show="show==='4'">
                    <el-table :data="starData">
                        <el-table-column prop="fid" label="id" width="120"/>
                        <el-table-column prop="username" label="username" width="180"/>
                        <el-table-column prop="title" label="title" width="180"/>
                    </el-table>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Writer",
        data() {
            return {
                show: '1',
                user_info: undefined,
                sent_history: undefined,

            }
        },
        methods: {
            choose(key) {
                this.show = key
            },


        },

        created() {
            this.$axios
                .get('/writer/' + this.$store.getters.getUserInfo.username)
                .then(res => {
                    this.user_info = res.data.data
                })
        }
    }
</script>

<style scoped>

</style>