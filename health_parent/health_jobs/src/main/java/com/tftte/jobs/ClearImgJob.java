package com.tftte.jobs;

import com.tftte.constant.RedisConstant;
import com.tftte.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author: tftte
 * @Date: 2020/3/22 22:17
 * @Description:
 */
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set != null) {
            for (String picName : set) {
                QiniuUtils.deleteFileFromQiniu(picName);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, picName);
            }
        }
    }
}
