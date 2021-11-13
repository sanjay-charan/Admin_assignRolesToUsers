package com.example.demo.model.projectcreation;

import java.util.ArrayList;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.constants.Constants;

@Document(collection = Constants.COUNTER_COLLECTION)
public class Counter {

	@Id
	private String id;
	private int seq;
	private  ArrayList<Integer> requirementCounter = new ArrayList<Integer>();
	
	public Counter() {
		super();
	}
	
	public void addRequirementCount()
	{
		requirementCounter.add(0);
	}
	
	public void setRequirementCountByIndex(int index,int value)
	{
		requirementCounter.set(index, value);
	}
	
	public ArrayList<Integer> getRequirementCounter() {
		return requirementCounter;
	}

	public void setRequirementCounter(ArrayList<Integer> requirementCounter) {
		this.requirementCounter = requirementCounter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
