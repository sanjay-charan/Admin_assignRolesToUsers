package com.example.demo.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.FileCount;
import com.example.demo.service.DashService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
    private DashService dashservice;
    
    
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
    	
    	long files_count = dashservice.countFiles();
    	FileCount entry = new FileCount();
    	
    	entry.setFiles_count(files_count);
    	entry.setTime(dateTimeFormatter.format(LocalDateTime.now()));
    	
        logger.info(dashservice.addEntry(entry), dateTimeFormatter.format(LocalDateTime.now()) );
    }
    
//    public void scheduleTaskWithFixedDelay() {}
//
//    public void scheduleTaskWithInitialDelay() {}
//
//    public void scheduleTaskWithCronExpression() {}
}

