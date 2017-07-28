package com.xdz.model;

import javax.persistence.*;

/**
 * Created by 14543 on 2017/4/11.
 */
@Entity
@Table(name = "x_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long xid;

    @Column
    private String username;

    @Column
    private String age;

    public Long getXid() {
        return xid;
    }

    public void setXid(Long xid) {
        this.xid = xid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String age) {
        this.username = username;
        this.age = age;
    }

    public User() {
    }
}
