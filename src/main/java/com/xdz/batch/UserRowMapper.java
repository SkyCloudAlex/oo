package com.xdz.batch;

import com.xdz.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 14543 on 2017/7/28.
 */
@Component("userRowMapper")
public class UserRowMapper implements RowMapper{
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setXid(resultSet.getLong("xid"));
        user.setUsername(resultSet.getString("username"));
        user.setAge(resultSet.getString("age"));
        return user;
    }
}
