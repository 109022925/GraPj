<template>
    <div>
        <div class="m-substance">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="description">
                    <el-input type="textarea" v-model="ruleForm.description"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <mavon-editor
                            v-model="ruleForm.content"
                            :placeholder="'开始创作，开始你的表演···'"
                            :toolbars="toolbars"
                            :toolbarsBackground="'#FFC1C1'"
                            @change="change"
                            ref="md"
                            style="height: 70vh">
                    </mavon-editor>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>

    export default {
        name: "BlogEdit",
        data() {
            return {
                html: '',
                ruleForm: {
                    bid: '',
                    uid: '',
                    title: '',
                    description: '',
                    content: '',
                    time: '',
                },
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
                    ],
                    description: [
                        {required: true, message: '请输入描述', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入内容', trigger: 'blur'}
                    ],
                },
                //参数
                toolbars: {
                    bold: true, // 粗体
                    italic: true, // 斜体
                    header: true, // 标题
                    underline: true, // 下划线
                    strikethrough: true, // 中划线
                    mark: true, // 标记
                    superscript: true, // 上角标
                    subscript: true, // 下角标
                    quote: true, // 引用
                    ol: true, // 有序列表
                    ul: true, // 无序列表
                    link: true, // 链接
                    imagelink: true, // 图片链接
                    code: true, // code
                    table: true, // 表格
                    fullscreen: true, // 全屏编辑
                    readmodel: true, // 沉浸式阅读
                    htmlcode: true, // 展示html源码
                    help: true, // 帮助
                    /* 1.3.5 */
                    undo: true, // 上一步
                    redo: true, // 下一步
                    trash: true, // 清空
                    save: true, // 保存（触发events中的save事件）
                    /* 1.4.2 */
                    navigation: true, // 导航目录
                    /* 2.1.8 */
                    alignleft: true, // 左对齐
                    aligncenter: true, // 居中
                    alignright: true, // 右对齐
                    /* 2.2.1 */
                    subfield: true, // 单双栏模式
                    preview: true, // 预览
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.ruleForm.uid = this.$store.getters.getUserInfo.userId
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.ruleForm.bid) {
                            this.$axios
                                .put("/blog", this.ruleForm)
                                .then(res => {
                                    this.$alert('操作成功！', '提示', {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            this.$router.push("/index")
                                        }
                                    })
                                })
                        } else {
                            this.$axios.post("/blog", this.ruleForm, {
                            }).then(res => {
                                this.$alert('操作成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        this.$router.push("/index")
                                    }
                                });
                            })
                        }
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            // 编辑区发生变化的回调事件(render: value 经过markdown解析后的结果)
            change(value, render) {
                this.html = render
            }
        },
        created() {
            const bid = this.$route.params.bid
            if (bid) {
                this.$axios.get("/blog/select/" + bid).then(res => {
                    const blog = res.data.data.blog
                    this.ruleForm.bid = blog.bid
                    this.ruleForm.title = blog.title
                    this.ruleForm.description = blog.description
                    this.ruleForm.content = blog.content
                })
            }
        }
    }
</script>

<style scoped>
    .m-substance {
        text-align: center;
    }
</style>