package com.tftte.service;

import com.tftte.entity.PageResult;
import com.tftte.entity.QueryPageBean;
import com.tftte.pojo.Setmeal;

/**
 * @Author: tftte
 * @Date: 2020/3/22 14:42
 * @Description:
 */
public interface SetmealService {
    void add(Setmeal setmeal, Integer[] checkGroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);
}
