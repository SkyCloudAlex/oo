package com.xdz.service.impl;

import com.xdz.dao.BaseDao;
import com.xdz.dao.UserDao;
import com.xdz.model.User;
import com.xdz.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
@Repository(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final String normalSql = "";

    @Resource(name = "userDao")
    UserDao userDao;

    public void testSave(){
//        baseDao.TestSave();
    }

    public List testQuery(){
//        return baseDao.TestQuery();
        return new ArrayList();
    }

    public boolean login(User user){
        Map<String,Object> param = new HashMap();
        param.put("username",user.getUsername());
        param.put("age",user.getAge());
        List<User> userList = userDao.queryUserByParam(normalSql,param,false);
        return userList.size() > 0;
    }

}
