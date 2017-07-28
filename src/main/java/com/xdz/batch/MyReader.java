package com.xdz.batch;

import com.xdz.dao.UserDao;
import com.xdz.model.User;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 14543 on 2017/7/27.
 */
@Component("MyReader")
public class MyReader<T> implements ItemReader {

    @Autowired
    private UserDao userDao;

    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return userDao.queryAllUser().get(0);
    }
}
