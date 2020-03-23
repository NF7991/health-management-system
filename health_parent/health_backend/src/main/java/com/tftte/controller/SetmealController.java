package com.tftte.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tftte.constant.MessageConstant;
import com.tftte.constant.RedisConstant;
import com.tftte.entity.PageResult;
import com.tftte.entity.QueryPageBean;
import com.tftte.entity.Result;
import com.tftte.pojo.Setmeal;
import com.tftte.service.SetmealService;
import com.tftte.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author: tftte
 * @Date: 2020/3/22 13:10
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString() + extension;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkGroupIds) {
        try {
            setmealService.add(setmeal, checkGroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.pageQuery(queryPageBean);
    }
}
