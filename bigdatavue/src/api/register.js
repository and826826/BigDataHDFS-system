import axios from 'axios'
const base="http://localhost:8080/bigdata_ssm_war_exploded/V1/users/user/"
export async function login(username,password){
    axios.get(base,
      {
        params:{
          name:username,
          password:password,
          action:"login"
        }
      }).then(res=>{
        this.loginresult=res.data
        this.$router.push({path:`/about/${this.loginresult}`})
      }).catch(res=>{console.log(res.data)
      })

}
