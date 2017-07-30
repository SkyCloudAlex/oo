package com.xdz.controller;

import com.sun.deploy.net.HttpResponse;
import com.xdz.model.User;
import com.xdz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
        Map map = new HashMap();
        map.put("name","aaa");
        return map;
    }

    @RequestMapping(value = "/testBatch")
    @ResponseBody
    public void testBatch(){
            try {
                ExecutorService executorService = Executors.newFixedThreadPool(10);

                for (int i = 0; i < 5; i++) {
                    executorService.execute(new Runnable() {
                        public void run() {
                            batch();
                        }
                    });
                }
                executorService.shutdown();
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    private void batch(){
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:job.xml" });
            SimpleJobLauncher launcher = (SimpleJobLauncher) context.getBean("jobLauncher");
            Job job = (Job) context.getBean("testJob");

            JobExecution result = launcher.run(job, new JobParameters());
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void batch(){
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:job.xml" });
//        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
//
//        Job job = (Job) context.getBean("testJob");
//
//        try {
//            JobExecution result = launcher.run(job, new JobParameters());
//            System.out.println(result.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}