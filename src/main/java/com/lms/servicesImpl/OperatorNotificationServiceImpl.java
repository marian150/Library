package com.lms.servicesImpl;

import com.lms.services.OperatorNotificationService;

import javax.enterprise.context.Dependent;
import java.util.concurrent.*;

@Dependent
public class OperatorNotificationServiceImpl implements OperatorNotificationService {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private Runnable task;

    public void setTask(Runnable task){
        this.task = task;
    }
    public void initialize(){
        executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

}
