package com.tftte.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.tftte.constant.MessageConstant;
import com.tftte.constant.RedisMessageConstant;
import com.tftte.entity.Result;
import com.tftte.pojo.Member;
import com.tftte.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/27 13:35
 * @Description:
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService memberService;

    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (validateCodeInRedis != null && validateCode != null && validateCodeInRedis.equals(validateCode)) {
            Member member = memberService.findByTelephone(telephone);
            if (member == null) {
                member.setRegTime(new Date());
                member.setPhoneNumber(telephone);
                memberService.addMember(member);
            }
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone, 60*30, json);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

    }
}
