package com.bigdata.controller;
import com.alibaba.fastjson.JSON;
//import javax.security.auth.login.Configuration;
//import java.nio.file.FileSystem;

import com.bigdata.mapper.UserMapper;
import com.bigdata.pojo.User;
import com.bigdata.pojo.myfile;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.hadoop.fs.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

@Controller
@CrossOrigin
@RequestMapping("V1/users")
public class hdfs {
    @Autowired
    UserMapper userMapper;
    //配置连接到虚拟机中的hadoop
    public static final String HDFS_PATH = "hdfs://192.168.170.137:9000";
    public static final String base = "/IDEA";
    //    public static final String HDFS_PATH = "hdfs://39.101.190.1:9000";
    //hdfs文件系统
    FileSystem fileSystem = null;
    //获取环境对象
    Configuration configuration = null;


    //能够新建目录 地址写到文件就可新建文件
    @RequestMapping("/")
    @CrossOrigin
    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, InterruptedException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String directory = request.getParameter("d");
        System.out.println(directory);
        Path path = new Path("/IDEA/" + directory);
        System.out.println(path);

        Configuration con = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");

        response.setCharacterEncoding("utf-8");
        response.setContentType("json;charset=utf-8");
        PrintWriter writer = response.getWriter();

        switch (action) {
            case "files":
                String jsonstr = "";
                jsonstr = JSON.toJSONString(hdfsaction.allfiles(path));
                writer.write(jsonstr);
                break;
            case "delete":
                hdfsaction.delete(path);
                break;
            case "showdatails":
                writer.write(hdfsaction.showdetails(path));
                break;
            case "adddirectory":
                hdfsaction.adddirectory(path);
                break;
            case "upload":
                String filename = request.getParameter("filename");
                hdfsaction.upload(path,filename);
                break;

        }
        return null;
    }
    @RequestMapping("/test")
    public ModelAndView method01() {
        try {
            Configuration con = new Configuration();
            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
            System.out.println("HDFS SETUP");
            FSDataOutputStream outputStream = fileSystem.create(new Path("/IDEA/test/hello5.txt"));
            System.out.println("create");
            outputStream.write("hello idea hadoop test".getBytes());
            System.out.println("create1");
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello测试");

        return null;

    }
//
//    //能够删除文件  ,删除目录 根据传入的地址定
//    @RequestMapping("/delete")
//    public ModelAndView delete() {
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            Path path = new Path("/IDEA/test");
//            fileSystem.deleteOnExit(path);
//            System.out.println("delete:" + path);
//            fileSystem.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("hello测试");
//
//        return null;
//
//    }
//
//    //显示该目录下的所有文件
//    @CrossOrigin
//    @RequestMapping("/show")
//    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
//        String r="";
//        long time=0;
//        myfile m;
//        String jsonstr="";
//        List<myfile> list = new ArrayList<myfile>();
////        Map<String,String> map=new HashMap<>();
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            Path path = new Path("/IDEA/test");
//            FileStatus[] statuses = fileSystem.listStatus(path);
//
//            for (FileStatus file : statuses) {
//                System.out.println(file);
//                time=file.getModificationTime();
//                Date date=new Date(time);
//
//                m=new myfile(file.getPath().getName(),file.getPermission().toString(),date.toString());
////                r+=JSON.toJSONString(m)+",";
////                r+=m.toString()+"=";
////                map.put(file.getPath().getName(),file.getOwner());
//                list.add(m);
//
//
//
//
////                System.out.println(file.getPath().getName() + ",dir owner:" + file.getOwner() + file.getBlockSize() + file.getPermission());
////                re+=file.getPath().getName()+'\n';
//
//            }
//
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("json;charset=utf-8");
//            PrintWriter writer=response.getWriter();
//            jsonstr=JSON.toJSONString(list);
////            writer.write(map.toString(map));
//
//            writer.write(jsonstr);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("hello测试");
//        return null;
//    }
////显示文件内容
//    @RequestMapping("/showdetails")
//    public ModelAndView showdetails() {
//        String result = "";
//        BufferedReader br = null;
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            Path path = new Path("/IDEA/test/hello1.txt");
//            FSDataInputStream fsDataInputStream = fileSystem.open(path);
//            br = new BufferedReader(new InputStreamReader(fsDataInputStream));
//            String str;
//            while ((str = br.readLine()) != null) {
//                result += str + "\n";
//
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        return null;
//
//    }
////    下载文件
//    @RequestMapping("/download")
//    public ModelAndView download(){
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            Path srcpath = new Path("/IDEA/test/hello1.txt");
//            Path dstpath= new Path("E:/");
//            fileSystem.copyToLocalFile(srcpath,dstpath);
//            } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (URISyntaxException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//
//    }
//  上传
//    @RequestMapping("/put")
//    public ModelAndView put(){
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            Path srcpath = new Path("/IDEA/test/hello1.txt");
//            Path dstpath= new Path("E:/");
//            fileSystem.copyFromLocalFile(srcpath,dstpath);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (URISyntaxException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//
//    }
//    @RequestMapping("/UP")
//    public ModelAndView method01() {
//        try {
//            Configuration con = new Configuration();
//            fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
//            //一句话搞定文件的上传
//            fileSystem.copyFromLocalFile(new Path("C:\\Users\\26363\\Downloads\\file3.txt"), new Path("/IDEA/test/"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("hello测试");
//
//        return null;
//
//    }


}


