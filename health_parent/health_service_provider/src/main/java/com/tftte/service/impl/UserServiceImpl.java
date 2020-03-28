package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tftte.dao.PermissionDao;
import com.tftte.dao.RoleDao;
import com.tftte.dao.UserDao;
import com.tftte.pojo.Permission;
import com.tftte.pojo.Role;
import com.tftte.pojo.User;
import com.tftte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Author: tftte
 * @Date: 2020/3/28 12:48
 * @Description:
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        Integer id = user.getId();
        Set<Role> roles = roleDao.findByUserId(id);
        for (Role role : roles) {
            Integer roleId = role.getId();
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);
        }
        user.setRoles(roles);
        return user;
    }
}
