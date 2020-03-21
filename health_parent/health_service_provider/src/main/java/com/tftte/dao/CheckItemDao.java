package com.tftte.dao;

import com.github.pagehelper.Page;
import com.tftte.pojo.CheckItem;

import java.util.List;

/**
 * @Author: tftte
 * @Date: 2020/3/19 15:45
 * @Description:
 */
public interface CheckItemDao {

    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    Long findCountByCheckItemId(Integer id);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
