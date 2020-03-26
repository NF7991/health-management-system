package com.tftte.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.tftte.constant.MessageConstant;
import com.tftte.constant.RedisMessageConstant;
import com.tftte.entity.Result;
import com.tftte.pojo.Order;
import com.tftte.service.OrderService;
import com.tftte.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/25 17:34
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");

        if (validateCode != null && validateCodeInRedis != null && validateCodeInRedis.equals(validateCode)) {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = null;
            try {
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result.isFlag()) {
                try {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, (String) map.get("orderDate"));
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    //根据预约ID查询预约相关信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
