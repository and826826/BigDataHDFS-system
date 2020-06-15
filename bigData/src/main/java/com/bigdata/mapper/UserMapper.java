package com.bigdata.mapper;

import com.bigdata.pojo.User;


import java.util.List;

public interface UserMapper {

    void  addUser(User user);

    User  searchUser(User user);
    User  user(User user);



}


