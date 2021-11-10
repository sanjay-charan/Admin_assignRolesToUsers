package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;


@SpringBootTest

class EmpCrudApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@SpyBean
	private MongoTemplate mongoTemplate;

	
//	@MockBean
//	private MongoTemplate mongoTemplate;
//	
//	MongoTemplate mongoTemplate = Mockito.spy(
//		    //Instance the MongoTemplate, use any test framework
//		    new MongoTemplate(new SimpleMongoClientDbFactory("mongodb://localhost/test"))
//		);
	
//	@Autowired
//	private EmpService service;
//	
//	
//	@Test
//	public void getUsersTest() {
//		when(mongoTemplate.findAll(Employee.class)).thenReturn(Stream
//				.of(new Employee("Danile", 31))
//				.collect(Collectors.toList()));	
//		
//		assertEquals(1,service.getAllEmployees().size());
//		
//	}
//
//	@Test
//	public void addNumTest() {
//		
//		
//		assertEquals(8,service.addNum(4,4));
//		
//		
//		
//	}

}
