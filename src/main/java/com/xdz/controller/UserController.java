package com.xdz.controller;

import com.xdz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    UserService userService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(){
        userService.testSave();

        logger.info("test");
        logger.error("11111111111111111111111111111");
        return userService.testQuery();
    }

}