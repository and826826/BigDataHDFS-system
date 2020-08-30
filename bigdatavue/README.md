@[TOC]
# bigdatavue

## description
它能够实现用户登录注册，不同的用户能够在hadoop文件系统中新建自己的文件夹，每个用户只能访问到自己的文件夹下的内容。现在已经基本实现了文件的上传，下载，删除，查询显示，新建任意层数文件夹，进入文件夹，目录回退等功能。

## Visuals

用户登录界面

![login](https://github.com/and826826/BigDataHDFS-system/tree/master/bigdatavue/src/assets/login.png)

文件显示界面

![table](https://github.com/and826826/BigDataHDFS-system/tree/master/bigdatavue/src/assets/table.png)

## Requirements

伪分布式配置 Hadoop3.1.3/Ubuntu18.04
IDEA 
VScode

## Development



我的Hadoop是运行在本机的ubuntu中，在Windows下连接Hadoop时使用的是ip地址，在不同环境下ip地址需要修改（主要是修改Java代码控制层中的HDFS_PATH常量）。==修改后应该就能直接使用==。

在具体使用时你可能会连接不上Hadoop，在检查core-site文件中的ip地址无误后，请检查你的Hadoop是否正常运行。
由于某些原因，我没有将下载功能放在java代码中，而是将它们写在了JavaScript中，这里利用了官方提供的一个api接口，你可以尝试在后台添加下载代码。



## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your unit tests
```
npm run test:unit
```

### Run your end-to-end tests
```
npm run test:e2e
```

### Lints and fixes files
```
npm run lint
```

