package com.xdz.dao;

import com.xdz.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
public interface UserDao extends BaseDao {

    public List<User> queryAllUser();

    public List<User> queryUserByParam( String sql,Map<String,Object> params,boolean isFuzzy);

    public void addUser();

    public void saveUser(User user);

}
