package com.xdz.dao.impl;

import com.xdz.dao.UserDao;
import com.xdz.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/12.
 */
@Repository(value = "userDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final String baseSql = "from User where 1=1";

    public List<User> queryAllUser(){
        Map param = new HashMap();
        return super.queryByHql(baseSql,param,false);
    }

    public List<User> queryUserByParam( String sql,Map<String,Object> params,boolean isFuzzy){

        if(StringUtils.isNotBlank(sql)){
            return super.queryByHql(sql,params,isFuzzy);
        }else {
            return super.queryByHql(baseSql,params,isFuzzy);
        }

    }

    public void addUser(){
        super.getSession().saveOrUpdate(new User("addName","120"));
    }

    public void saveUser(User user){
        //super.getSession().saveOrUpdate(user);
        Session session = super.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
    }

}
