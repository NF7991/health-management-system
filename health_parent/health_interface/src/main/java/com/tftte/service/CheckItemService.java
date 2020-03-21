package com.tftte.service;

import com.tftte.entity.PageResult;
import com.tftte.entity.QueryPageBean;
import com.tftte.pojo.CheckItem;

import java.util.List;

/**
 * @Author: tftte
 * @Date: 2020/3/19 15:21
 * @Description:
 */
public interface CheckItemService {

    void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
