package com.zqjy.dao;


import com.zqjy.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
