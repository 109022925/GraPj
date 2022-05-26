<template>
    <div class="m-container">
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="blog.time" placement="top" v-for="blog in blogs">
                    <el-card>
                        <h4>
                            <router-link :to="{name: 'BlogDetail', params: {blogId: blog.bid}}" class="rl-pretty">
                                {{blog.title}}
                            </router-link>
                        </h4>
                        <h4 class="author" v-show="type==='getAll'">author:  {{blog.username}}</h4>
                        <p>{{blog.description}}</p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>

            <el-pagination class="m-page"
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
        name: "Blogs",
        data() {
            return {
                blogs: {},
                currentPage: 1,
                total: 0,
                pageSize: 5
            }
        },
        props: [
            'url', 'type'
        ],
        methods: {
            page(currentPage, pageSize) {
                if (this.type==="getAll") {
                    this.$axios.get(this.url,
                        {
                            params: {
                                currentPage: currentPage,
                                pageSize: this.pageSize
                            },
                            headers: {
                                token: this.$store.getters.getToken
                            }
                        },
                    ).then(res => {
                        let data = res.data.data
                        this.blogs = data.records
                        this.currentPage = data.current
                        this.pageSize = data.size
                        this.total = data.total
                    });
                }
                if (this.type==="getByUid") {
                    this.$axios.post(this.url, {
                            uid: this.$store.getters.getUserInfo.userId,
                            currentPage: currentPage,
                            pageSize: this.pageSize
                        }, {
                            headers: {
                                token: this.$store.getters.getToken
                            }
                        },
                    ).then(res => {
                        let data = res.data.data
                        this.blogs = data.records
                        this.currentPage = data.current
                        this.pageSize = data.size
                        this.total = data.total
                    });
                }
            },
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

    .author {
        float: right;
    }
</style>