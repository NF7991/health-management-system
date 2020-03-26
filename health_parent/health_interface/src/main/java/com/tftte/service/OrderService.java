package com.tftte.service;

import com.tftte.entity.Result;

import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/26 13:14
 * @Description:
 */
public interface OrderService {

    Result order(Map map);

    Map findById(Integer id);
}
