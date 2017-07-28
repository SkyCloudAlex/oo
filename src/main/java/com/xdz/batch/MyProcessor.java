package com.xdz.batch;

import com.xdz.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by 14543 on 2017/7/27.
 */
@Component("myProcessor")
public class MyProcessor implements ItemProcessor<User,User> {



    public User process(User obj) throws Exception {
        User user = new User();
        user.setXid(obj.getXid());
        user.setUsername(obj.getUsername());
        user.setAge(obj.getAge());
        return user;
    }
}
