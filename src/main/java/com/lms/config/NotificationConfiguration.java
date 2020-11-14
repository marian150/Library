package com.lms.config;

import com.lms.services.NotificationService;

import javax.inject.Inject;

public class NotificationConfiguration {
    @Inject
    private NotificationService notificationService;

    public void start(){
        notificationService.initialize();
    }
}
