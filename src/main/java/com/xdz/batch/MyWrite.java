package com.xdz.batch;

import com.xdz.dao.UserDao;
import com.xdz.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14543 on 2017/7/27.
 */
@Transactional
public class MyWrite implements ItemWriter<User> {

    @Resource(name = "userDao")
    UserDao userDao;

    public void write(List<? extends  User> list) throws Exception {

        for (User user:list) {
            System.out.println(user.getUsername());
            System.out.println(user.getAge());
            user.setAge("112");
            userDao.saveUser(user);
            userDao.addUser();
        }

    }
}
