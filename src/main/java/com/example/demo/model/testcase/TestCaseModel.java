package com.example.demo.model.testcase;

import org.springframework.data.mongodb.core.mapping.Document;


import com.example.demo.constants.Constants;

@Document(collection =Constants.TESTCASE_COLLECTION)
public class TestCaseModel {
	private String projectId;
	private String projectname;
	private String requirementId;
	private String id;
	private String name;
	private String description;
	private String expectedResults;
	private String actualResults;
	private String status;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
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

	public String getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	public String getActualResults() {
		return actualResults;
	}

	public void setActualResults(String actualResults) {
		this.actualResults = actualResults;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
