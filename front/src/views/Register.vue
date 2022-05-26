<template>
    <div class="background">
        <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
                <div class="form">
                    <el-form-item label="username" prop="username">
                        <el-input type="text" v-model="ruleForm.username"></el-input>
                    </el-form-item>

                    <el-form-item label="password1" prop="password">
                        <el-input type="password" v-model="ruleForm.password1"></el-input>
                    </el-form-item>
                    <el-form-item label="password2" prop="password">
                        <el-input type="password" v-model="ruleForm.password2"></el-input>
                    </el-form-item>
                    <el-form-item label="email" prop="email">
                        <el-input type="text" v-model="ruleForm.email"></el-input>
                    </el-form-item>
                    <el-button @click="submitCode">发送验证码</el-button>
                    <el-form-item label="验证码" prop="code">
                        <el-input type="text" v-model="ruleForm.code"></el-input>
                    </el-form-item>
                </div>

                <div class="button">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            const checkEmail = (rule, value, callback) => {
                const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
                if (!value) {
                    return callback(new Error('邮箱不能为空'))
                }
                setTimeout(() => {
                    if (mailReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入正确的邮箱格式'))
                    }
                }, 100)
            }
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
                user: {
                    username: '',
                    password1: '',
                    password2: '',
                    email: '',
                },
                ruleForm: {
                    username: '',
                    password: '',
                    email: '1439068491@qq.com',
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
                    email: [
                        {validator: checkEmail, trigger: 'blur'}
                    ],
                    code: [
                        {validator: checkCode, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.ruleForm.password1 === this.ruleForm.password2) {
                            this.user.username = this.ruleForm.username;
                            this.user.password = this.ruleForm.password1;
                            this.user.email = this.ruleForm.email;
                            const _this = this;
                            this.$axios.post('/register/' + this.ruleForm.code, this.user).then(res => {
                                _this.$message.success("注册成功，返回重新登录")
                                _this.$router.push("/login")
                        });
                        } else {
                            this.$message.warning("两次输入密码不相同")
                        }
                    } else {
                        console.log('error!');
                        return false;
                    }
                });
            },
            submitCode() {
                this.$axios.get('/register/' + this.ruleForm.email)
                    .then(res => {
                    this.$message.success("验证码发送成功")
                    }).catch(error=> {
                    this.$message.error(error);
                })
            }
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