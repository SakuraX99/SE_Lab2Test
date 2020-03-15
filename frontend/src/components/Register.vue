<template>
  <div id="base_register">
    <el-form :model="registerForm" :rules="rules" class="register_container" label-position="left"
             label-width="0px" v-loading="loading" :ref="registerForm">
      <h3 class="register_title">Register</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="registerForm.username"
                  auto-complete="off" placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="registerForm.password"
                  auto-complete="off" placeholder="password"></el-input>
      </el-form-item>
      <el-form-item prop="fullname">
        <el-input type="text" v-model="registerForm.fullname"
                  auto-complete="off" placeholder="fullname"></el-input>
      </el-form-item>
      <el-form-item prop="usertype">
        <el-radio-group v-model="registerForm.usertype" @change="userTypeChange">
          <el-radio label="Admin" border>Admin</el-radio>
          <el-radio label="Contributor" border>Contributor</el-radio>
          <el-radio label="Reviewer" border>Reviewer</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #afb4db;border: none" v-on:click="register(registerForm)">register</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    const dataValid = (rule, value, callback) => {
      if(!value || value === '') {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      registerForm: {
        username: '',
        password: '',
        fullname: '',
        usertype: ''
      },
      rules: {
        // blur 失去鼠标焦点时触发验证
        username: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        password: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        fullname: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        usertype: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}]
      },
      loading: false
    }
  },
  methods: {
    userTypeChange() {
    },
    register (formName) {
      this.$refs[formName].validate(valid => {
        if(valid){
          this.$axios.post('/register', {
              username: this.registerForm.username,
              password: this.registerForm.password,
              fullname: this.registerForm.fullname,
              authorities: [this.registerForm.usertype]
            }
          )
            .then(resp => {
              // 根据后端的返回数据修改
              if(resp.status === 200 && resp.data.hasOwnProperty("id")) {
                // 跳转到login
                alert('successful registration')
                this.$router.replace('/login')
              } else{
                alert('register error')
              }
            })
            .catch(error => {
              console.log(error)
              alert('register error')
            })
        } else {
          alert('wrong submit')
        }
      })
    }
  }
}
</script>

<style scoped>
  #base_register{
    background: url("../assets/background/checkerboard-cross.png") repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  .register_container{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .register_title{
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
