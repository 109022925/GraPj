<template>
    <div>
        <el-button type="primary" @click="toBack">返回</el-button>
        <p>找回密码</p>
        <el-form v-show="1===change" :model="ruleForm" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input type="text" v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-button type="primary" @click="verifyUsername()">
                下一步
            </el-button>
        </el-form>
        <el-form v-show="2===change" :model="ruleForm" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="用户名" prop="username">
                <p>{{this.ruleForm.username}}</p>
            </el-form-item>
            <el-form-item label="身份证号码" prop="code">
                <el-input type="text" v-model="ruleForm.numbers"></el-input>
            </el-form-item>
            <el-button type="primary" @click="verifyNumbers()">
                下一步
            </el-button>
        </el-form>
        <el-form v-show="3===change" :model="ruleForm" ref="ruleForm" label-width="100px" class="ruleForm">
            <el-form-item label="请输入密码" prop="password">
                <el-input type="password" v-model="ruleForm.password1"></el-input>
            </el-form-item>
            <el-form-item label="再次确认密码" prop="password">
                <el-input type="password" v-model="ruleForm.password2"></el-input>
            </el-form-item>
            <el-button type="primary" @click="modifyPassword()">
                完成修改
            </el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Forget",
        data() {
            // const checkId = (rule, value, callback) => {
            //     const idReg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
            //     if (!value) {
            //         return callback(new Error('身份证不能为空'))
            //     }
            //     setTimeout(() => {
            //         if (idReg.test(value)) {
            //             callback()
            //         } else {
            //             callback(new Error('请输入正确的身份证格式'))
            //         }
            //     }, 100)
            // }
            return {
                change: 1,
                ruleForm: {
                    username: '',
                    password1: '',
                    password2: '',
                    numbers: '',
                },
            }
        },
        methods: {
            toBack() {
                this.$router.go(-1)
            },

            // 1、确认用户名是否存在
            verifyUsername() {
                this.$axios
                    .get('/forget/verify_u/' + this.ruleForm.username)
                    .then(res => {
                    if (res.data.code === 200) {
                        this.change = 2
                    }
                })
            },

            // 2、核查身份证号
            verifyNumbers() {
                console.log(this.ruleForm.numbers)
                this.$axios
                    .get('/forget/verify_n/' + this.ruleForm.numbers)
                    .then(res => {
                    if (res.data.code === 200) {
                        this.change = 3
                    }
                })
            },

            // 3、修改密码
            modifyPassword() {
                if (this.ruleForm.password1 === this.ruleForm.password2) {
                    this.$axios.put('/forget/', {
                        password: this.ruleForm.password1
                    })
                    .then(res => {
                        if (res.data.code === 200) {
                            this.$message.success("密码更改成功，返回重新登录")
                            this.$router.push("/qq/login")
                        }
                    })
                } else this.$message.warning("两次输入密码不相同，请重新输入")
            }

        }
    }
</script>

<style scoped>

</style>