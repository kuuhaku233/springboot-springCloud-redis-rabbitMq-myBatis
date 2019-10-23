package com.userorder.service.impl;

import com.userorder.mapper.UserMapper;
import com.userorder.pojo.User;
import com.userorder.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User selectUser(Integer id) {
        return userMapper.selectUser(id);
    }
}
