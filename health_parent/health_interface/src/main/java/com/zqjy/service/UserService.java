package com.zqjy.service;

import com.zqjy.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
