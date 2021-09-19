package com.zqjy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zqjy.dao.PermissionDao;
import com.zqjy.dao.RoleDao;
import com.zqjy.dao.UserDao;
import com.zqjy.pojo.Permission;
import com.zqjy.pojo.Role;
import com.zqjy.pojo.User;
import com.zqjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    //根据用户名查询数据库获取用户信息和角色信息，同时查询查询角色关联的权限信息
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);//基本信息，不包含角色
        if(user==null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles= roleDao.findByUserId(userId);
        //根据角色查权限
        for (Role role : roles) {
            Integer roleId = role.getId();
            //根据角色id查权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);//角色关联权限


        }
        user.setRoles(roles);



        return user;
    }
}
