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
@RequestMapping("V1/users/user")
public class file {
    @Autowired
    UserMapper userMapper;
    //配置连接到虚拟机中的hadoop
    public static final String HDFS_PATH = "hdfs://192.168.170.134:9000";
//    public static final String HDFS_PATH = "hdfs://39.101.190.1:9000";
    //hdfs文件系统
    FileSystem fileSystem = null;
    //获取环境对象
    Configuration configuration = null;
    @RequestMapping("/")
    public ModelAndView register(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException, URISyntaxException, InterruptedException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String action=httpServletRequest.getParameter("action");
        String name = httpServletRequest.getParameter("name");
        String password = (httpServletRequest.getParameter("password"));
        System.out.println(action);

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();

        switch (action){
            case "login":
                System.out.println(name + password);
                User user = new User(name, password);
                if (userMapper.searchUser(user) == null) {
                    writer.write("no user");

                } else {
                    if (userMapper.user(user) == null) {
                        writer.write("password erro");
                    } else {
                        writer.write(name);
                    }
                }

                break;
            case "register":
                System.out.println(name + password);
                User u=new User(name,password);
                userMapper.addUser(u);
                writer.write(name);
                hdfsaction.adddirectory(new Path("/IDEA/"+name));
                break;
        }

        return null;
    }
}
