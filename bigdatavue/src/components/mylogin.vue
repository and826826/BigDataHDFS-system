<template>
<div class="div">
  <el-input
  placeholder="请输入用户名"
  v-model="username"
  clearable>
</el-input>
  <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
  <el-button type="primary" plain @click="submit">登录</el-button>
  <el-button type="primary" plain @click="register"> 注册</el-button>
<h1>{{loginresult}}</h1>
</div>
</template>


<script>
// import router from 'vue-router'
import axios from 'axios'
  export default {
 
      name:'mylogin',
    data() {
      return {
        loginresult:"",
        username:"",
        password:""
      };
    },
    methods: {
      register: function (){
        axios.get('http://localhost:8080/bigdata_ssm_war_exploded//V1/users/user/',
        {
          params:{
            name:this.username,
            password:this.password,
            action:"register"
          }
        }).then(res=>this.loginresult=res.data).catch(res=>{
          console.log(res.data)
          this.loginresult="change uername"})
      
      },

    submit: function () {

      console.log('username: ' + this.username+'password:'+this.password) //console中字符串需使用单引号''
      // this.$route.router.go({name: 'index'}) //直接使用路由的名字进行跳转
      axios.get('http://localhost:8080/bigdata_ssm_war_exploded/V1/users/user/',
      {
        params:{
          name:this.username,
          password:this.password,
          action:"login"
        }
      }).then(res=>{
        this.loginresult=res.data
        this.$router.push({path:`/about/${this.loginresult}`})
      }).catch(res=>{console.log(res.data)
      })

    }
  }
  }
</script>
<style scoped>

</style>