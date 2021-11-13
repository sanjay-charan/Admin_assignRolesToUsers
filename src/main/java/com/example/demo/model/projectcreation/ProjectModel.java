package com.example.demo.model.projectcreation;

import java.util.*;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = Constants.PROJECT_COLLECION)
public class ProjectModel {
	private String id;
	private String name;
	private String description;
	
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=Constants.DATE_FORMAT)
	private Date startDate;
	
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=Constants.DATE_FORMAT)
	private Date endDate;
	
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=Constants.DATE_FORMAT)
	private Date targetedRelease;

	private List<Date> updateHistory;
	private List<RequirementModel> requirements;
	
	public ProjectModel() {
		updateHistory=new ArrayList<Date>();
		requirements=new ArrayList<RequirementModel>();
	}
	
	public void addRequirements(RequirementModel requirementModel)
	{
		requirements.add(requirementModel);
	}
	
	public void updateRequirementbyIndex(int index,RequirementModel requirementModel)
	{
		requirements.set(index,  requirementModel);
	}
	
	public void addUpdateDate()
	{
		updateHistory.add(new Date());
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTargetedRelease() {
		return targetedRelease;
	}

	public void setTargetedRelease(Date targetedRelease) {
		this.targetedRelease = targetedRelease;
	}

	public List<Date> getUpdateHistory() {
		return updateHistory;
	}

	public void setUpdateHistory(List<Date> updateHistory) {
		this.updateHistory = updateHistory;
	}

	public List<RequirementModel> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<RequirementModel> requirements) {
		this.requirements = requirements;
	}

	


}
