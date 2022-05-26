<template>
    <div class="m-container">
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="comment.stamp" placement="top" v-for="comment in comments">
                    <el-card v-show="show==='blog'">
                        <h4 class="rl-pretty">
                            {{comment.username}}
                        </h4>
                        <p>{{comment.content}}</p>
                    </el-card>
                    <el-card v-show="show==='user'">
                        <h4 class="rl-pretty">
                            blog:  {{comment.title}}
                            <el-button icon="el-icon-delete" class="delete" @click="deleteComment(comment.cid)"></el-button>
                        </h4>
                        <p>
                            评论内容:  {{comment.content}}
                        </p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>

            <el-pagination class="m-page"
                           v-show="empty===false"
                           background
                           layout="prev, pager, next"
                           :current-page="currentPage"
                           :page-size="pageSize"
                           :total="total"
                           @current-change=page>
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Comments",
        data() {
            return {
                empty: true,
                show: '',
                comments: {},
                currentPage: 1,
                total: 0,
                pageSize: 5
            }
        },
        props: {
            type: String,
    },
        methods: {
            page(currentPage, pageSize) {
                if (this.type === "getByUid") {
                    this.show = 'user';
                    this.$axios.post('/comment/selectUid', {
                            uid: this.$store.getters.getUserInfo.userId,
                            currentPage: currentPage,
                            pageSize: this.pageSize
                        }, {
                            headers: {
                                token: this.$store.getters.getToken,
                            }
                        }
                    ).then(res => {
                        let data = res.data.data
                        if (data === undefined) this.empty = true
                        this.comments = data.records
                        this.currentPage = data.current
                        this.pageSize = data.size
                        this.total = data.total
                    });
                }
                if(this.type === "getByBid") {
                    this.pageSize = 10
                    this.show = 'blog'

                    this.$axios.post('/comment/selectBid',
                        {
                            bid: this.$route.params.blogId,
                            currentPage: currentPage,
                            pageSize: this.pageSize
                        },
                        {
                                headers: {
                                    token: this.$store.getters.getToken
                                }
                        },
                    ).then(res => {
                        let data = res.data.data
                        if (data === undefined) this.empty = true
                        this.comments = data.records
                        this.currentPage = data.current
                        this.pageSize = data.size
                        this.total = data.total
                    });
                }
            },
            deleteComment(cid) {
                this.$confirm('此操作将永久删除评论, 是否继续?', '提示', {
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
        // 渲染数据
        created() {
            this.page(this.currentPage, this.pageSize)
        }
    }
</script>

<style scoped>
    .m-page {
        margin: 0 auto;
        text-align: center;
    }

    .rl-pretty {
        text-decoration: none;
        color: black;
    }

    a:hover {
        color: skyblue;
    }

    .delete {
        float: right;
    }
</style>