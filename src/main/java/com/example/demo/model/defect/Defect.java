package com.example.demo.model.defect;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.constants.Constants;

@Document(collection = Constants.DEFECT_COLLECTION)
public class Defect {
	private String id;
	private String name;
	private String devId;
	private String projectId;
	private String testcaseId;
	private String expResults;
	private String actResults;
	private String status;
	private int severity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTestcaseId() {
		return testcaseId;
	}
	public void setTestcaseId(String testcaseId) {
		this.testcaseId = testcaseId;
	}
	public String getExpResults() {
		return expResults;
	}
	public void setExpResults(String expResults) {
		this.expResults = expResults;
	}
	public String getActResults() {
		return actResults;
	}
	public void setActResults(String actResults) {
		this.actResults = actResults;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
