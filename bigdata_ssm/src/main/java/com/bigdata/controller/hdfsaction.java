package com.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.bigdata.pojo.myfile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class hdfsaction {
    public static final String HDFS_PATH = "hdfs://192.168.170.137:9000";



//显示目录下所有文件
    public static List<myfile>  allfiles(Path path) throws URISyntaxException, IOException, InterruptedException {
        Configuration con = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
        long time = 0;
        myfile m;
        List<myfile> list = new ArrayList<myfile>();
        FileStatus[] statuses = fileSystem.listStatus(path);
        for (FileStatus file : statuses) {
            System.out.println(file);
            time = file.getModificationTime();
            Date date = new Date(time);
            m = new myfile(file.getPath().getName(), file.getPermission().toString(), date.toString(),file.isDirectory());
            list.add(m);
        }
        return list;

    }
    //添加目录
    public static void adddirectory(Path path) throws URISyntaxException, IOException, InterruptedException {
        Configuration con = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
        fileSystem.mkdirs(new Path(path+"/"));

    }
    //删除文件
    public static void delete(Path path) throws URISyntaxException, IOException, InterruptedException {
        Configuration con = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
        fileSystem.deleteOnExit(path);
//            System.out.println("delete:" + path);
            fileSystem.close();


    }
    //查看文件内容
    public static String showdetails(Path path){
        String result = "";
        BufferedReader br = null;
        try {
            Configuration con = new Configuration();
            FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
            FSDataInputStream fsDataInputStream = fileSystem.open(path);
            br = new BufferedReader(new InputStreamReader(fsDataInputStream));
            String str;
            while ((str = br.readLine()) != null) {
                result += str + "\n";

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
    //上传文件
    public static void upload(Path path,String filename) throws URISyntaxException, IOException, InterruptedException {
        Configuration con = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), con, "hadoop");
        //一句话搞定文件的上传
        fileSystem.copyFromLocalFile(new Path("C:/Users/26363/Desktop/"+filename),path);
        System.out.println("hello测试");


    }

}
