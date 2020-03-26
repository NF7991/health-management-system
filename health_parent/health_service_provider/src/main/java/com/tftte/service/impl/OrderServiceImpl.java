package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tftte.constant.MessageConstant;
import com.tftte.dao.MemberDao;
import com.tftte.dao.OrderDao;
import com.tftte.dao.OrderSettingDao;
import com.tftte.entity.Result;
import com.tftte.pojo.Member;
import com.tftte.pojo.Order;
import com.tftte.pojo.OrderSetting;
import com.tftte.service.OrderService;
import com.tftte.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/26 14:12
 * @Description:
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Result order(Map map) {
        String orderDate = (String) map.get("orderDate");
        try {
            OrderSetting orderSetting = orderSettingDao.findByOrderDate(DateUtils.parseString2Date(orderDate));
            if (orderSetting == null) {
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            }
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            if (reservations >= number) {
                return new Result(false, MessageConstant.ORDER_FULL);
            }
            String telephone = (String) map.get("telephone");
            Member member = memberDao.findByTelephone(telephone);
            if (member != null) {
                Integer memberId = member.getId();
                Date date = DateUtils.parseString2Date(orderDate);
                String setmealId = (String) map.get("setmealId");
                Order order = new Order(memberId, date, Integer.parseInt(setmealId));
                List<Order> list = orderDao.findByCondition(order);
                if (list != null && list.size() > 0) {
                    return new Result(false, MessageConstant.HAS_ORDERED);
                }
            } else {
                member = new Member();
                member.setName((String) map.get("name"));
                member.setPhoneNumber(telephone);
                member.setIdCard((String) map.get("idCard"));
                member.setSex((String) map.get("sex"));
                member.setRegTime(new Date());
                memberDao.add(member);
            }
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(DateUtils.parseString2Date(orderDate));
            order.setOrderType((String) map.get("orderType"));
            order.setOrderStatus(Order.ORDERSTATUS_NO);
            order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
            orderDao.add(order);

            orderSetting.setReservations(orderSetting.getReservations() + 1);

            orderSettingDao.editReservationsByOrderDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
    }

    @Override
    public Map findById(Integer id) {
        Map map = orderDao.findById4Detail(id);
        if (map != null) {
            Date orderDate = (Date) map.get("orderDate");
            try {
                map.put("orderDate", DateUtils.parseDate2String(orderDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
