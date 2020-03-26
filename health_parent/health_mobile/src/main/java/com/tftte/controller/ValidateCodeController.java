package com.tftte.controller;

import com.tftte.constant.MessageConstant;
import com.tftte.constant.RedisMessageConstant;
import com.tftte.entity.Result;
import com.tftte.utils.SMSUtils;
import com.tftte.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @Author: tftte
 * @Date: 2020/3/25 14:29
 * @Description:
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone) {
        try {
            Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, String.valueOf(validateCode));
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 300, String.valueOf(validateCode));
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
