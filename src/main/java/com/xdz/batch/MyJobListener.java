package com.xdz.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.batch.api.listener.JobListener;
import java.util.List;
import java.util.Set;

/**
 * Created by 14543 on 2017/7/30.
 */
public class MyJobListener implements JobExecutionListener {


    public void beforeJob(JobExecution jobExecution) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:job.xml" });
            JobOperator jobOperator = (JobOperator) context.getBean("jobOperator");
            Set<Long> eIds = jobOperator.getRunningExecutions("testJob");
            if (eIds.size() > 0){
                jobOperator.stop(jobExecution.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void afterJob(JobExecution jobExecution) {

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:job.xml" });
            JobOperator jobOperator = (JobOperator) context.getBean("jobOperator");
            jobOperator.startNextInstance("testJob");
            System.out.println("-----   afterJob   -----");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
