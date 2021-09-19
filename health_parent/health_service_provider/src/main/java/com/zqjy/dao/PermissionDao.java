package com.zqjy.dao;

import com.zqjy.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(Integer userid);
}
