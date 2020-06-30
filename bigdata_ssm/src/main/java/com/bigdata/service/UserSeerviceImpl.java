package com.bigdata.service;

import com.bigdata.mapper.UserMapper;
import com.bigdata.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSeerviceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void regist(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User login(User user) {
        return userMapper.searchUser(user);

    }
}
