<template>
    <div class="background">
        <div class="login_box">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
                <div class="form">
                    <el-form-item label="username" prop="username">
                        <el-input type="text" v-model="ruleForm.username"></el-input>
                    </el-form-item>

                    <el-form-item label="password" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>
                </div>

                <div class="button">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </div>

                <el-link class="register" type="primary" @click="toRegister()">注册</el-link>
                <el-link class="forget" type="primary" @click="toForget()">忘记密码？</el-link>
            </el-form>
        </div>
    </div>
</template>

<script>

    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: 'spiderman',
                    password: '12345'
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'change'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/login', this.ruleForm).then(res => {
                            const userInfo = {
                                username: res.data.data.username,
                                token: ''
                            }
                            const roles = res.data.data.roles
                            this.$store.commit("SET_USERINFO", userInfo);
                            if (roles === "writer") this.$router.push("/qq/writer")
                            else if (roles === "editor") this.$router.push("/qq/editor")
                            else if (roles === "admin") this.$router.push("/qq/admin")
                        });
                    } else {
                        console.log('error!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            toRegister() {
                this.$router.push('/qq/register')
            },
            toForget() {
                this.$router.push('/qq/forget')
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

    .login_box {
        width: 450px;
        height: 300px;
        background-color: #fff;
        border-radius: 20px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .ruleForm {
        margin-top: 50px;
    }

    .form {
        width: 350px;
    }

    .register {
        float: right;
        margin-right: 2px;
    }

    .forget {
        float: right;
        margin-right: 5px;
    }
</style>