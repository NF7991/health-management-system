package com.tftte.service;

import com.tftte.entity.PageResult;
import com.tftte.entity.QueryPageBean;
import com.tftte.pojo.CheckGroup;

import java.util.List;

/**
 * @Author: tftte
 * @Date: 2020/3/21 13:05
 * @Description:
 */
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkItemIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkItemIds);

    List<CheckGroup> findAll();

}
