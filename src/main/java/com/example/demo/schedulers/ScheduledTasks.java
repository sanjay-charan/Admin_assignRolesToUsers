package com.example.demo.schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.dashboard.DefectHistory;
import com.example.demo.model.dashboard.TestHistory;
import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.service.dashboard.DashboardService;
import com.example.demo.service.defect.DefectService;
import com.example.demo.service.filemangement.DashService;
import com.example.demo.service.testcase.TestCaseService;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@PostConstruct
    public void onStartup() {
		long currDefectCount = defectservice.getDefectCount();
		long currTestCount = testCaseService.getTestsCount();
		dashboardService.getPrevDayCountAndUpdate("defect", currDefectCount);
		System.out.println("pass");
		dashboardService.getPrevDayCountAndUpdate("test", currTestCount);
		
    }

	@Autowired
	private DashService dashservice;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private DefectService defectservice;
	
	@Autowired
	private TestCaseService testCaseService;

	@Scheduled(cron = "0 19 21 * * *")
	public void scheduleTaskWithFixedRate() {

		long files_count = dashservice.countFiles();
		FileCount entry = new FileCount();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		entry.setFilesCount(files_count);
		entry.setTime(dtf.format(now));

		logger.info(dashservice.addEntry(entry));
	}

	@Scheduled(cron = "0/60 * * * * ?")
	public void defectHistory() {

		long currDefectCount = defectservice.getDefectCount();
		DefectHistory entry = new DefectHistory();

		long prevDefectCount = dashboardService.getPrevDayCountAndUpdate("defect", currDefectCount);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		entry.setTime(dtf.format(now));
		entry.setDefectCountStartOfDay(prevDefectCount);
		entry.setDefectCountEndOfDay(currDefectCount);
		entry.setDefectClosedCount(defectservice.getClosedDefectCount());

		logger.info(dashboardService.addEntryToDefectHistory(entry));

	}
	
	@Scheduled(cron = "0/60 * * * * ?")
	public void testHistory() {

		long currTestCount = testCaseService.getTestsCount();
		TestHistory entry = new TestHistory();

		long prevDefectCount = dashboardService.getPrevDayCountAndUpdate("test", currTestCount);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		entry.setTime(dtf.format(now));
		entry.setTestCountEndOfDay(currTestCount);
		entry.setTestCountStartOfDay(prevDefectCount);
		entry.setTestPassCount(testCaseService.getPassedTestsCount());

		logger.info(dashboardService.addEntryToTestHistory(entry));

	}
	
	
	
	
	
	

}
