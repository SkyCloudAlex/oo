package com.xdz.dao;

import com.xdz.model.User;

import java.util.List;

/**
 * Created by 14543 on 2017/4/11.
 */
public interface UserDao extends BaseDao {

    public List<User> queryAllUser();

}
