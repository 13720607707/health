package com.zqjy.dao;

import com.zqjy.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(Integer id);
}
