package com.example.demo.service.testcase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.testcase.TestCaseCounter;
import com.example.demo.model.testcase.TestCaseModel;
import com.example.demo.constants.Constants;
import com.example.demo.utilities.TestCaseUtility;

@Service
public class TestCaseService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperation;

	public long getTestsCount() {

		Query query = new Query();
		query.addCriteria(Criteria.where("status").ne("Passed"));
		return mongoTemplate.count(query, TestCaseModel.class);

	}
	
	public long getPassedTestsCount() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("Passed"));
		return mongoTemplate.count(query, TestCaseModel.class);
	}

	public TestCaseService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public TestCaseModel getByTestCaseId(String id) {
		return mongoTemplate.findOne(TestCaseUtility.getQueryByKeyValue("id", id), TestCaseModel.class);
	}

	public List<TestCaseModel> getAllTestCase() {

		return mongoTemplate.findAll(TestCaseModel.class);
	}

	public String addTestCase(TestCaseModel testcaseModel) {

		if (mongoTemplate.insert(testcaseModel) != null)
			return " Inserted";
		else
			return "Not Inserted";

	}

	public TestCaseCounter uniqueValue(String key) {

		Update update = new Update();
		update.inc(Constants.TESTCASE_COUNTER_DOCUMENT_SEQUENCE_COLUMN, 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true).upsert(true);
		TestCaseCounter counter = mongoOperation.findAndModify(TestCaseUtility.getQueryByKeyValue("_id", key), update,
				options, TestCaseCounter.class);

		return counter;

	}

	public String updateProject(TestCaseModel testCaseModel, String id) {
		TestCaseModel requestedTestCase = getByTestCaseId(id);

		if (testCaseModel == null) {
			requestedTestCase.setStatus(Constants.TESTCASE_STATUS_ONHOLD);
			mongoTemplate.save(requestedTestCase);
			return "testCase Deleted";
		}

		if (testCaseModel.getName() != null) {
			requestedTestCase.setName(testCaseModel.getName());
		}
		if (testCaseModel.getDescription() != null) {
			requestedTestCase.setDescription(testCaseModel.getDescription());
		}
		if (testCaseModel.getExpectedResults() != null) {
			requestedTestCase.setExpectedResults(testCaseModel.getExpectedResults());
		}
		if (testCaseModel.getActualResults() != null) {
			requestedTestCase.setActualResults(testCaseModel.getActualResults());
		}
		if (testCaseModel.getStatus() != null && !testCaseModel.getStatus().equals(Constants.TESTCASE_STATUS_ONHOLD)) {
			requestedTestCase.setStatus(testCaseModel.getStatus());
		}

		mongoTemplate.save(requestedTestCase);

		return "TestCase " + requestedTestCase.getId() + " Updated";

	}

}
