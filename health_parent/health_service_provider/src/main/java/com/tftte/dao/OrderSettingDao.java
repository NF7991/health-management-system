package com.tftte.dao;

import com.tftte.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/23 20:23
 * @Description:
 */
public interface OrderSettingDao {
    void add(OrderSetting orderSetting);
    void editNumberByOrderDate(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);
    List<OrderSetting> getOrderSettingByMonth(Map map);

    OrderSetting findByOrderDate(Date parseString2Date);
}
