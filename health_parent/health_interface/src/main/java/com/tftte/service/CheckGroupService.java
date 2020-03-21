package com.tftte.service;

import com.tftte.pojo.CheckGroup;

/**
 * @Author: tftte
 * @Date: 2020/3/21 13:05
 * @Description:
 */
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkItemIds);
}
