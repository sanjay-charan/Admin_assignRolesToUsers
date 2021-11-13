package com.example.demo.model.defect;

import org.springframework.data.mongodb.core.mapping.Document;


import com.example.demo.utilities.Timestamp;

@Document(collection = "StatusHistory")
public class Status {
	private String defectId;
	private Timestamp dateTime;
	private String status;
	
	public String getDefectId() {
		return defectId;
	}
	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
