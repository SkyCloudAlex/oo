package com.xdz.service.impl;

import com.xdz.dao.BaseDao;
import com.xdz.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14543 on 2017/4/11.
 */
@Repository(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name = "baseDao")
    BaseDao baseDao;

    public void testSave(){
        baseDao.TestSave();
    }

    public List testQuery(){
        return baseDao.TestQuery();
    }

}
