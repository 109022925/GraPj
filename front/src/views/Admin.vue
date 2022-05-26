<template>
    <div>
        <el-container style="border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu :default-active=this.show @select="choose">
                    <el-menu-item index="1">user</el-menu-item>
                    <el-menu-item index="2">blog</el-menu-item>
                    <el-menu-item index="3">comment</el-menu-item>
                    <el-menu-item index="4">star</el-menu-item>
                </el-menu>
            </el-aside>

            <el-container>
                <el-header style="text-align: right; font-size: 12px">
                </el-header>

                <el-main v-show="show==='1'">
                    <el-table :data="userData">
                        <el-table-column prop="uid" label="id" width="120"/>
                        <el-table-column prop="username" label="username" width="180"/>
                        <el-table-column prop="password" label="password" width="180"/>
                        <el-table-column prop="email" label="email" width="200"/>
                        <el-table-column prop="role" label="role" width="180"/>
                        <el-table-column label="操作" width="240">
                            <template slot-scope="scope">
                                <el-button type="text" @click="editPassword(scope.row)">修改密码</el-button>
                                <el-button type="text" @click="editEmail(scope.row)">修改邮箱</el-button>
                                <el-button @click="deleteUser(scope.row)" icon="el-icon-delete"/>
                            </template>
                        </el-table-column>
                    </el-table>
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
        name: "Admin",
        data() {
            return {
                show: '1',
                userData: undefined,
                blogData: undefined,
                commentData: undefined,
                starData: undefined,
            }
        },
        methods: {
            choose(key) {
                this.show = key
            },

            editPassword(row) {
                this.$prompt('请输入新密码', '修改', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {

                    this.$axios
                        .put("/account/psw", {
                            uid: row.uid,
                            password: value
                        })
                        .then(res => {
                            if (res.data.code === 200) this.$router.go(0)
                        })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            },

            editEmail(row) {
                this.$prompt('请输入邮箱', '修改', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                    inputErrorMessage: '邮箱格式不正确'
                }).then(({ value }) => {

                    this.$axios
                        .put("/account/email", {
                            uid: row.uid,
                            email: value
                    })
                        .then(res => {
                            if (res.data.code === 200) this.$router.go(0)
                        })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            },

            deleteUser(row) {
                const uid = row.uid
                this.$confirm('此操作将永久删除该账户, 是否继续?！！！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios
                        .delete("/account/" + uid)
                        .then(res => {
                            if (res.data.code === 200) {
                                this.$message.success("已成功删除")
                                this.$router.go(0)
                            }
                        })
                })
            },

            deleteBlog(row) {
                const bid = row.bid
                this.$confirm('此操作将永久删除该博文, 是否继续?！！！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios
                        .delete("/blog/" + bid)
                        .then(res => {
                            if (res.data.code === 200) {
                                this.$message.success("已成功删除")
                                this.$router.go(0)
                            }
                        })
                })
            },

            deleteComment(row) {
                const cid = row.cid
                this.$confirm('此操作将永久删除该评论, 是否继续?！！！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios
                        .delete("/comment/" + cid)
                        .then(res => {
                            if (res.data.code === 200) {
                                this.$message.success("已成功删除")
                                this.$router.go(0)
                            }
                        })
                })
            }
        },
        created() {
            // 获取数据
            this.$axios
                .get("/account", {headers: {token: this.$store.getters.getToken}})
                .then(res => {
                    this.userData = res.data.data
                })
            this.$axios
                .get("/blog/select", {headers: {token: this.$store.getters.getToken}})
                .then(res => {
                    this.blogData = res.data.data
                })
            this.$axios
                .get("/comment", {headers: {token: this.$store.getters.getToken}})
                .then(res => {
                    this.commentData = res.data.data
                })
            this.$axios
                .get("/user/favor/admin", {headers: {token: this.$store.getters.getToken}})
                .then(res => {
                    this.starData = res.data.data
                })
        }
    }
</script>

<style scoped>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>