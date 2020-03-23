package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tftte.dao.CheckGroupDao;
import com.tftte.entity.PageResult;
import com.tftte.entity.QueryPageBean;
import com.tftte.pojo.CheckGroup;
import com.tftte.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
        this.setCheckGroupAndCheckItem(checkGroup, checkItemIds);
    }

    private void setCheckGroupAndCheckItem(CheckGroup checkGroup, Integer[] checkItemIds) {
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

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.edit(checkGroup);
        checkGroupDao.deleteAssociation(checkGroup.getId());

        this.setCheckGroupAndCheckItem(checkGroup, checkItemIds);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
