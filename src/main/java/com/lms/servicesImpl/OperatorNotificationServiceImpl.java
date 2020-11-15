package com.lms.servicesImpl;

import com.lms.services.OperatorNotificationService;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Dependent
public class OperatorNotificationServiceImpl implements OperatorNotificationService {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private List<Runnable> tasks = new ArrayList<>();

    public void setTask(Runnable task){
        this.tasks.add(task);
    }
    public void initialize(){
        for(Runnable task : tasks)
            executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

}
