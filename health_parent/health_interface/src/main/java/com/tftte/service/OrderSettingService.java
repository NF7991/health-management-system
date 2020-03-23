package com.tftte.service;

import com.tftte.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/23 18:13
 * @Description:
 */
public interface OrderSettingService {

    void add(List<OrderSetting> data);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
