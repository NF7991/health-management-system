package com.tftte.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tftte.constant.MessageConstant;
import com.tftte.entity.Result;
import com.tftte.pojo.CheckItem;
import com.tftte.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tftte
 * @Date: 2020/3/19 15:15
 * @Description:
 */

@RestController  // Controller + ResponseBody
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    CheckItemService checkItemService;

    // 新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
}
