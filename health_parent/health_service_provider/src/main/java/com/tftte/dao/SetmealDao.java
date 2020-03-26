package com.tftte.dao;

import com.github.pagehelper.Page;
import com.tftte.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/22 15:22
 * @Description:
 */
public interface SetmealDao {

    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map map);

    Page<Setmeal> findByCondition(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);
}
