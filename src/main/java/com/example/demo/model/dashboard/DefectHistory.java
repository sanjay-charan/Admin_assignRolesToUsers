package com.example.demo.model.dashboard;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dash_defect_history")
public class DefectHistory {
	
	private String time;
	private long defectCountStartOfDay;
	private long defectCountEndOfDay;
	
	
	
	public DefectHistory() {
		super();
	}
	public DefectHistory(String time, long defectCountStartOfDay, long defectCountEndOfDay) {
		super();
		this.time = time;
		this.defectCountStartOfDay = defectCountStartOfDay;
		this.defectCountEndOfDay = defectCountEndOfDay;
	}
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getDefectCountStartOfDay() {
		return defectCountStartOfDay;
	}
	public void setDefectCountStartOfDay(long defectCountStartOfDay) {
		this.defectCountStartOfDay = defectCountStartOfDay;
	}
	public long getDefectCountEndOfDay() {
		return defectCountEndOfDay;
	}
	public void setDefectCountEndOfDay(long defectCountEndOfDay) {
		this.defectCountEndOfDay = defectCountEndOfDay;
	}


}
