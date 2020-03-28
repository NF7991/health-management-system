package com.tftte.dao;

import com.tftte.pojo.Permission;

import java.util.Set;

/**
 * @Author: tftte
 * @Date: 2020/3/28 13:01
 * @Description:
 */
public interface PermissionDao {

    Set<Permission> findByRoleId(Integer roleId);
}
