package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Employee;

@Service
public class EmpService {
	private MongoTemplate mongoTemplate;

	@Autowired
	public EmpService(MongoTemplate mongoTemplate) {
		System.out.println("IN EMP SERVICE CLASS");
		this.mongoTemplate = mongoTemplate;

	}

	public List<Employee> getAllEmployees() {
		System.out.println("getAllEmployees");
		return mongoTemplate.findAll(Employee.class);

	}

	public Employee addEmployee(Employee employee) {

		if (employee.getName().length() == 0) {
			throw new BadRequestException("Name is empty");
		}
		Employee addedEntity = mongoTemplate.insert(employee);
		System.out.println("Added Employeee");
		return addedEntity;

//		return "Employee" + addedEntity.getName()+"Added into the database";

	}

	public Employee getEmployeesById(int id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(q, Employee.class);

	}

	public String deleteEmployee(int id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));
		Employee deletedEmp = mongoTemplate.findAndRemove(q, Employee.class);

		return "Employee" + deletedEmp.getName() + "deleted from the database";
	}

	public String updateEmployee(Employee employee) {
		mongoTemplate.save(employee);

		return employee.getName() + " updated";
	}

	public String updateEmployeeById(int id, Map<String, String> data) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();


//        }

		update.set("name", data.get("name"));
		mongoTemplate.findAndModify(q, update, Employee.class);

		// TODO Auto-generated method stub
		return "updated";
	}
	
	
	public int addNum(int a, int b) {
		return a+b;
	}

}
