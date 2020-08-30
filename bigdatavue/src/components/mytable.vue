<template>
<div>

  <el-input v-model="newdirectory" placeholder="/" style="float:left" size="mini"></el-input>
  <el-button round style="float:left" @click="adddirectory">新建文件夹</el-button>
  <el-button round style="float:left" @click="back">返回上一级</el-button>
  <!-- :data="tableData.filter(data => !search || data.Name.toLowerCase().includes(search.toLowerCase()))" -->
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="Name"
      prop="name">
    </el-table-column>
    <el-table-column
      label="Permission"
      prop="permission">
    </el-table-column>

    <el-table-column
      label="LastModified"
      prop="lastModified">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header" >
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button  
          size="mini" v-if="tableData[scope.$index].directory"
          @click="next(tableData[scope.$index].name)">进入</el-button>
          <el-button  
          size="mini" v-if="!tableData[scope.$index].directory"
          @click="handledownload(tableData[scope.$index].name)">下载</el-button>
        <!-- <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">重命名</el-button> -->
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(tableData[scope.$index].name)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div>
  </div>
  <el-upload
  class="upload-demo"
  action=" "
  :on-change="handleChange"
  :auto-upload="false">
  <el-button size="small" type="primary">点击上传文件</el-button>

</el-upload>
  </div>
</template>

<script>
import axios from 'axios'
  export default {
    name:'mytable',
    props:['user'],
    data() {
      return {
        newdirectory:"",
        nowdirectory:"",
        re: "",
        tableData:[],
        search: ' ',
        realuser:''
      }
    },
    watch:{
      user:function(newVal){
        this.realuser=newVal;
        newVal&&this.allfile();
      }
    },
    mounted(){
      // axios.get("http://localhost:8080/bigData_war_exploded/V1/users/",{
      //   params:{
      //     action:"files",
      //     d:this.user
      //   }
      // }).then(
      //   res=>{

      //     console.log(res.data)
      //     this.tableData=res.data;

      //   }
      // ).catch(console.log)
    },
    methods: {
      //上传文件
      handleChange(file) {
        console.log(file.name);
        // this.fileList=file;
        axios.get("http://localhost:8080/bigdata_ssm_war_exploded/V1/users/",{
        params:{
          action:"upload",
          d:this.realuser+this.nowdirectory,
          filename:file.name
        }
        }).then(res=>console.log(res)).catch(res=>console.log(res))
        this.allfile()

      
      },
      // 新建目录
      adddirectory(){
        axios.get("http://localhost:8080/bigdata_ssm_war_exploded/V1/users/",{
        params:{
          action:"adddirectory",
          d:this.realuser+this.nowdirectory+"/"+this.newdirectory
        }
      }).then(res=>console.log(res)).catch(res=>console.log(res))
        console.log("添加目录")

      },
      // 显示所有文件
      allfile(){
        axios.get("http://localhost:8080/bigdata_ssm_war_exploded/V1/users/",{
        params:{
          action:"files",
          d:this.realuser+this.nowdirectory
        }
      }).then(
        res=>{

          console.log(res.data)
          this.tableData=res.data;

        }
      ).catch(console.log)

      },

      //删除
      handleDelete(name) {
        axios.get("http://localhost:8080/bigdata_ssm_war_exploded/V1/users/",{
        params:{
          action:"delete",
          d:this.realuser+this.nowdirectory+"/"+name
        }
      }).then(this.allfile("")
      ).catch(res=>console.log(res))
      

      },
      //下载
      handledownload(name){
        window.open(`http://192.168.170.138:9864/webhdfs/v1/IDEA/${this.realuser}/${this.nowdirectory}/${name}?user.name=hadoop&op=OPEN&namenoderpcaddress=192.168.170.137:9000`)

      },
      //返回上一级
      back(){
        this.nowdirectory=this.nowdirectory.slice(0,this.nowdirectory.lastIndexOf("/"));
        this.allfile("");

      },
      //进入下一级
      next(dire){
        this.nowdirectory=this.nowdirectory+"/"+dire;
        this.allfile()
      }
    },
  }
</script>
