package com.bigdata.service;

import com.bigdata.pojo.User;

public interface UserService {
    void regist(User user);
    User  login(User user);
}
