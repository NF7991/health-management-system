package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tftte.dao.CheckItemDao;
import com.tftte.pojo.CheckItem;
import com.tftte.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tftte
 * @Date: 2020/3/19 15:43
 * @Description:
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
