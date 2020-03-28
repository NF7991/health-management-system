package com.tftte.dao;

import com.tftte.pojo.User;

/**
 * @Author: tftte
 * @Date: 2020/3/28 12:55
 * @Description:
 */
public interface UserDao {
    User findByUsername(String username);
}
