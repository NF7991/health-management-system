package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tftte.dao.CheckGroupDao;
import com.tftte.pojo.CheckGroup;
import com.tftte.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tftte
 * @Date: 2020/3/21 13:31
 * @Description:
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        if (checkItemIds != null && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkItemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
