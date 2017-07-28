package com.xdz.batch;

import com.xdz.model.User;

/**
 * Created by 14543 on 2017/7/27.
 */
public class MyMessage {

    private User user;

    private String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
