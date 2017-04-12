package com.xdz.dao.impl;

import com.xdz.dao.BaseDao;
import com.xdz.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
@Repository(value = "baseDao")
@Transactional
public class BaseDaoImpl<T> implements BaseDao {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.openSession();
    }

    public List TestQuery(){
        Query query = getSession().createQuery("from " + User.class.getName() );
        List<T> result = query.list();
        return query.list();
    }

    public void TestSave(){
        User user = new User();
        user.setUsername("testName");
        getSession().saveOrUpdate(user);
    }

    public List queryByHql(String sql,Map<String,Object> param){
        Query query = getSession().createQuery(sql);
        for (Map.Entry<String,Object> entry:param.entrySet()) {
            if(entry.getValue() instanceof BigDecimal){
                query.setBigDecimal(entry.getKey(),BigDecimal.valueOf(Double.valueOf(String.valueOf(entry.getValue()))));
            }else if(entry.getValue() instanceof Integer){
                query.setInteger(entry.getKey(),Integer.parseInt(String.valueOf(entry.getValue())));
            }else if (entry.getValue() instanceof Double){
                query.setDouble(entry.getKey(),Double.valueOf(String.valueOf(entry.getValue())));
            }else if (entry.getValue() instanceof Boolean){
                query.setBoolean(entry.getKey(),Boolean.valueOf(String.valueOf(entry.getValue())));
            }else {
                query.setParameter(entry.getKey(),entry.getValue());
            }
        }
        return query.list();
    }

    public List queryByHql(String sql,Map<String,Object> param,boolean isFuzzy){
        if (isFuzzy){
            return queryByHqlFuzzy(sql,param);
        }else {
            return queryByHql(sql,param);
        }
    }

    public List queryByHqlFuzzy(String sql,Map<String,Object> param){
        StringBuffer sb = new StringBuffer(sql);
        sb.append(" where 1 = 1");
        for (Map.Entry<String,Object> entry:param.entrySet()) {
            sb.append(" and " + entry.getKey() + " like %" + entry.getValue() + "%");
        }
        Query query = getSession().createQuery(sb.toString());
        return query.list();
    }
}
