<template>
    <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="ruleForm">
            <div>
                <el-form-item label="用户名" prop="username">
                    <el-input type="text" v-model="ruleForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password1"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password2"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input type="text" v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-radio v-model="ruleForm.roles" label="writer">作者</el-radio>
                <el-radio v-model="ruleForm.roles" label="editor">编辑</el-radio>
                <el-form-item label="电话号码" prop="tel">
                    <el-input type="text" v-model="ruleForm.tel"></el-input>
                </el-form-item>
                <el-form-item label="身份证号码" prop="numbers">
                    <el-input type="text" v-model="ruleForm.numbers"></el-input>
                </el-form-item>

                <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </div>

        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            const checkTel = (rule, value, callback) => {
                const telReg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
                if (!value) {
                    return callback(new Error('联系方式不能为空'))
                }
                setTimeout(() => {
                    if (telReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入正确的手机号格式'))
                    }
                }, 100)
            }
            const checkId = (rule, value, callback) => {
                const idReg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
                if (!value) {
                    return callback(new Error('身份证不能为空'))
                }
                setTimeout(() => {
                    if (idReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入正确的身份证格式'))
                    }
                }, 100)
            }

            return {
                user: {
                  uname: '',
                  upassword: '',
                  roles: '',
                  tel: '',
                  numbers: '',
                },
                ruleForm: {
                    username: '',
                    password1: '',
                    password2: '',
                    name: '',
                    roles: 'writer',
                    tel: '',
                    numbers: '',
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
                    tel: [
                        {validator: checkTel, trigger: 'blur'}
                    ],
                    numbers: [
                        {validator: checkId, trigger: 'blur'}
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
                            this.user.name = this.ruleForm.name;
                            this.user.roles = this.ruleForm.roles;
                            this.user.tel = this.ruleForm.tel;
                            this.user.numbers = this.ruleForm.numbers;
                            this.$axios.put('/register/', this.user).then(res => {
                                if (res.data.code == 200) {
                                    this.$message.success("注册成功，返回重新登录")
                                    this.$router.push("/qq/login")
                                }
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
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        }
    }
</script>

<style scoped>

</style>