package com.xdz.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
public interface BaseDao {

    public List TestQuery();

    public void TestSave();

    public List queryByHql(String sql,Map<String,Object> param);
}
