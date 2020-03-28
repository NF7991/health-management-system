package com.tftte.dao;

import com.tftte.pojo.Role;

import java.util.Set;

/**
 * @Author: tftte
 * @Date: 2020/3/28 12:58
 * @Description:
 */
public interface RoleDao {
    Set<Role> findByUserId(Integer id);
}
