package com.example.demo.schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.service.filemangement.DashService;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
    private DashService dashservice;
    
    
    @Scheduled(fixedRate = 60*1000)
    public void scheduleTaskWithFixedRate() {
    	
    	long files_count = dashservice.countFiles();
    	FileCount entry = new FileCount();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	
    	entry.setFiles_count(files_count);
    	entry.setTime(dtf.format(now));
    	
        logger.info(dashservice.addEntry(entry) );
    }
    
//    public void scheduleTaskWithFixedDelay() {}
//
//    public void scheduleTaskWithInitialDelay() {}
//
//    public void scheduleTaskWithCronExpression() {}
}

