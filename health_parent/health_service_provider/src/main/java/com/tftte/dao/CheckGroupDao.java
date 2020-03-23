package com.tftte.dao;

import com.github.pagehelper.Page;
import com.tftte.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/21 13:32
 * @Description:
 */
public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);

    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    void deleteAssociation(Integer id);

    List<CheckGroup> findAll();
}
