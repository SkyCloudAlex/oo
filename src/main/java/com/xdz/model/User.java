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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
