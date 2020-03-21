package com.tftte.dao;

import com.tftte.pojo.CheckGroup;

import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/21 13:32
 * @Description:
 */
public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);
}
