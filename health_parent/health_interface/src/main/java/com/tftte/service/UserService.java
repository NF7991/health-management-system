package com.tftte.service;

import com.tftte.pojo.User;

/**
 * @Author: tftte
 * @Date: 2020/3/28 11:24
 * @Description:
 */
public interface UserService {
    User findByUsername(String username);
}
