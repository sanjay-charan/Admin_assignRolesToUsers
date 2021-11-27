package com.example.demo.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.dashboard.DefectHistory;
import com.example.demo.model.dashboard.PrevDayCount;
import com.example.demo.model.filemanagement.FileCount;

@Service
public class DashboardService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public long getPrevDayCountAndUpdate(String historyType, long currDefectCount) {
		Query q = new Query(Criteria.where("historyType").is(historyType));
		PrevDayCount prevdaycount = mongoTemplate.findOne(q, PrevDayCount.class);
		Update update = new Update().set("prevCount", currDefectCount);
		mongoTemplate.upsert(q, update, PrevDayCount.class);

		return prevdaycount.getPrevCount();

	}

	public String addEntry(DefectHistory entry) {
		mongoTemplate.save(entry);
		return entry.getTime() + " start " + entry.getDefectCountStartOfDay() + " " + entry.getDefectCountEndOfDay()
				+ " added";

	}

}
