package com.tftte.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tftte.dao.MemberDao;
import com.tftte.pojo.Member;
import com.tftte.service.MemberService;
import com.tftte.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tftte
 * @Date: 2020/3/27 15:20
 * @Description:
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void addMember(Member member) {
        if (member.getPassword() != null) {
            String s = MD5Utils.md5(member.getPassword());
            member.setPassword(s);
        }
        memberDao.add(member);
    }
}
