<template>
    <div>
        <div>
            <div class="m-blog">
                <h2>{{blog.title}}</h2>
                <h4 class="author">author:  {{blog.author}}</h4>
                <el-button icon="el-icon-star-off" class="start-button" v-show="star===false" @click="starOn">
                    收藏
                </el-button>
                <el-button icon="el-icon-star-on" class="start-button" v-show="star===true" @click="starOff">
                    取消收藏
                </el-button>
                <el-button icon="el-icon-edit" v-show="ownBlog===true" @click="editBlog"></el-button>
                <el-button icon="el-icon-delete" v-show="ownBlog===true" @click="deleteBlog"></el-button>


                <el-divider></el-divider>
                <div class="markdown-body" v-html="blog.content"></div>
            </div>
            <div class="show-comment">
                <h3>评论</h3>
                <Comments type="getByBid"></Comments>
            </div>
            <div>
                <el-input
                        class="input"
                        type="textarea"
                        :rows="2"
                        v-model="message"
                        placeholder="留下你的评论...">
                </el-input>
                <el-button @click="submit">提交</el-button>
            </div>
        </div>
        <div>

        </div>
    </div>
</template>

<script>
    import 'mavon-editor/dist/markdown/github-markdown.min.css'
    import Comments from "../components/Comments";

    export default {
        name: "BlogDetail",
        components: {
            Comments
        },
        data() {
            return {
                blog: {
                    bid: '',
                    uid: '',
                    title: '',
                    description: '',
                    content: '',
                    author: ''
                },
                message: '',
                ownBlog: true,
                star: false,
            }
        },
        methods: {
            editBlog() {
                this.$router.push({
                    name: 'Edit',
                    params: {
                        bid: this.blog.bid
                    }
                })
            },
            deleteBlog() {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios
                        .delete("/blog/" + this.blog.bid)
                        .then(res => {
                            if (res.data.code === 200) {
                                this.$message.success("已成功删除")
                                this.$router.push("/index")
                            }
                        })
                })
            },
            starOn() {
                this.$axios.post("/user/favor/add", {
                    uid: this.$store.getters.getUserInfo.userId,
                    bid: this.blog.bid
                })
                    .then(res => {
                    this.star = true
                })
            },
            starOff() {
                this.$axios.post("/user/favor/cancel", {
                    uid: this.$store.getters.getUserInfo.userId,
                    bid: this.blog.bid
                })
                    .then(res => {
                    this.star = false
                })
            },
            submit() {
                const comment = {
                    content: this.message,
                    bid: this.blog.bid,
                    uid: this.$store.getters.getUserInfo.userId
                }
                this.$axios
                    .post("/comment", comment, {headers: {token: this.$store.getters.getToken}})
                    .then(res => {
                        if (res.data.code === 200) {
                            this.$router.go(0)
                        } else this.$message.error("提交失败请稍后重试")
                    })
            }
        },
        created() {
            const blogId = parseInt(this.$route.params.blogId)
            if (blogId) {
                // 获取blog内容
                this.$axios
                    .get("/blog/select/" + blogId)
                    .then(res => {
                        const blog = res.data.data.blog
                        this.blog.bid = parseInt(blog.bid)
                        this.blog.uid = blog.uid
                        this.blog.title = blog.title
                        this.blog.description = blog.description
                        this.blog.author = res.data.data.author
                        // MarkdownIt 渲染内容
                        let MarkdownIt = require("markdown-it")
                        let md = new MarkdownIt();
                        this.blog.content = md.render(blog.content)

                        // 判别是否是当前用户的blog
                        if (this.$store.getters.getUserInfo.userId === this.blog.uid) this.ownBlog = true
                        else this.ownBlog = false
                })

                // 获取收藏信息
                this.$axios
                    .get("/user/favor", {
                        params: {
                            bid: blogId,
                            uid: this.$store.getters.getUserInfo.userId
                        }
                    })
                    .then(res => {
                        this.star = res.data.data;
                    })
            }
        }
    }
</script>

<style scoped>
    .m-blog {
        margin-top: 10px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 100%;
        min-height: 540px;
        padding: 10px;
    }

    .markdown-body {
        background: white;
        color: black;
    }

    .start-button {
        text-decoration: none;
        color: skyblue;
    }

    .author {
        float: right;
    }

    .show-comment {
        margin-top: 20px;
    }

    .input {
        max-width: 66%;
        min-width: 50%;
    }
</style>