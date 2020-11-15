package com.lms.servicesImpl;

import com.lms.repositories.NotificationRepository;
import com.lms.services.NotificationService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.concurrent.*;

@Dependent
public class NotificationServiceImpl implements NotificationService {

    @Inject
    private NotificationRepository notificationRepository;
    private ScheduledExecutorService executor;

    public void initialize() {
        executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            //notificationRepository.checkForOverdue();
        };
        executor.scheduleAtFixedRate(task, 0, 12, TimeUnit.SECONDS);
    }
    public void stop(){
        executor.shutdown();
    }
}
