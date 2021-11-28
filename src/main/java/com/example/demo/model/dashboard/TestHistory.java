package com.example.demo.model.dashboard;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dash_test_history")

public class TestHistory {
	
	private String time;
	private long testCountStartOfDay;
	private long testCountEndOfDay;
	private long testPassCount;
	public TestHistory(String time, long testCountStartOfDay, long testCountEndOfDay, long testPassCount) {
		super();
		this.time = time;
		this.testCountStartOfDay = testCountStartOfDay;
		this.testCountEndOfDay = testCountEndOfDay;
		this.testPassCount = testPassCount;
	}
	public TestHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getTestCountStartOfDay() {
		return testCountStartOfDay;
	}
	public void setTestCountStartOfDay(long testCountStartOfDay) {
		this.testCountStartOfDay = testCountStartOfDay;
	}
	public long getTestCountEndOfDay() {
		return testCountEndOfDay;
	}
	public void setTestCountEndOfDay(long testCountEndOfDay) {
		this.testCountEndOfDay = testCountEndOfDay;
	}
	public long getTestPassCount() {
		return testPassCount;
	}
	public void setTestPassCount(long testPassCount) {
		this.testPassCount = testPassCount;
	}

}
