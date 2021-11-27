package com.example.demo.model.dashboard;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "dash_defect_prev_day")
public class PrevDayCount {
	@Id
	private String historyType;
	private long prevCount;
	
	
	public PrevDayCount() {
		super();
	}

	public PrevDayCount(String historyType, long prevCount) {
		super();
		this.historyType = historyType;
		this.prevCount = prevCount;
	}
	
	public String getHistoryType() {
		return historyType;
	}
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}
	public long getPrevCount() {
		return prevCount;
	}
	public void setPrevCount(long prevCount) {
		this.prevCount = prevCount;
	}
	
	
	

}
