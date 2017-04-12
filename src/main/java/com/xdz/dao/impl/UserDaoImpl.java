package com.xdz.dao.impl;

import com.xdz.dao.UserDao;
import com.xdz.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/12.
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public List<User> queryAllUser(){
        String sql = "from User";
        Map param = new HashMap();
        return super.queryByHql(sql,param);
    }

}
