<template>

<el-upload
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-change="handleChange"
  :auto-upload="false">
  <el-button size="small" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>

</template>



<script>
// import router from 'vue-router'
import axios from 'axios'
  export default {
    name:'download',
    data() {
      return {
        fileList:"",
        realuser:"test"
      };
    },
    methods: {
      handleChange(file) {
        console.log(file.name);
        // this.fileList=file;
        axios.get("http://localhost:8080/bigData_war_exploded/V1/users/",{
        params:{
          action:"upload",
          d:this.realuser+"/",
          filename:file.name
        }
        }).then(res=>console.log(res)).catch(res=>console.log(res))

      
      }
    }
  }

</script>
<style scoped>

</style>