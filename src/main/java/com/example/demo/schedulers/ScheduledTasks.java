package com.example.demo.schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.dashboard.DefectHistory;
import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.service.dashboard.DashboardService;
import com.example.demo.service.defect.DefectService;
import com.example.demo.service.filemangement.DashService;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private DashService dashservice;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private DefectService defectservice;

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

		logger.info(dashboardService.addEntry(entry));

	}

}
