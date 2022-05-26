<template>
    <div class="background">
        <img id="logo" src="../assets/img/logo.jpg" @click="toBack">
        <p>找回密码</p>
        <el-form v-show="1===change" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="username" prop="username">
                <el-input type="text" v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-button type="primary" @click="sendCode()">
                下一步
            </el-button>
        </el-form>
        <el-form v-show="2===change" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="username" prop="username">
                <p>{{this.ruleForm.username}}</p>
            </el-form-item>
            <el-form-item label="code" prop="code">
                <el-input type="text" v-model="ruleForm.code"></el-input>
            </el-form-item>
            <el-button type="primary" @click="verifyCode()">
                下一步
            </el-button>
        </el-form>
        <el-form v-show="3===change" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="请输入密码" prop="password">
                <el-input type="password" v-model="ruleForm.password1"></el-input>
            </el-form-item>
            <el-form-item label="再次确认密码" prop="password">
                <el-input type="password" v-model="ruleForm.password2"></el-input>
            </el-form-item>
            <el-button type="primary" @click="updateCode()">
                完成修改
            </el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Update",
        data() {
            const checkCode = (rule, value, callback) => {
                const codeReg = /^\d{6}$/
                if (!value) {
                    return callback(new Error('验证码不能为空'))
                }
                setTimeout(() => {
                    if (codeReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入六位验证码'))
                    }
                }, 100)
            }
            return {
                change: 1,
                ruleForm: {
                    username: '',
                    password1: '',
                    password2: '',
                    email: '',
                    code: '',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password1: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    password2: [
                        {required: true, message: '请再次输入密码', trigger: 'blur'}
                    ],
                    code: [
                        {validator: checkCode, trigger: 'blur'}
                    ]
                },
            }
        },
        methods: {
            // 1|发送验证码
            sendCode() {
                this.$axios.get('/update/input/' + this.ruleForm.username).then(res => {
                    if (res.data.code === 200) {
                        this.ruleForm.email = res.data.data;
                        this.$message.success("已向您的邮箱" + this.ruleForm.email + "成功发送验证码")
                        this.change = 2
                    } else if (res.data.code === 400) {
                        this.$message.error("验证码发送失败")
                    } else if (res.data.code === 401) {
                        this.$message.warning("用户名不存在!!!")
                    }
                }).catch(error => {
                    this.$message.error(error);
                })
            },

            // 2|确认验证码
            verifyCode() {
                this.$axios.get('/update/verify/' + this.ruleForm.code).then(res => {
                    if (res.data.code === 200) {
                        this.$message.success("验证码验证成功")
                        this.change = 3
                    } else if (res.data.code === 401) {
                        this.$message.warning("验证码错误!!!")
                    }
                }).catch(error => {
                    this.$message.error(error)
                })
            },

            // 3|修改密码
            updateCode() {
                if (this.ruleForm.password1 === this.ruleForm.password2) {
                    this.$axios.put('/update',
                        {
                            password: this.ruleForm.password1,
                        }
                    ).then(res => {
                        this.$message.success("修改成功，请重新登录")
                        this.$router.push('/login')
                    })
                }
            },

            toBack() {
                this.$router.back(-1)
            }
        },
        created() {
            this.ruleForm.username = this.$store.getters.getUserInfo.username
        }
    }
</script>

<style scoped>
    .background {
        position: fixed;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
        background-color: #409EFF;
    }
</style>