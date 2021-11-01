package com.example.demo.service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.Counter;

import org.springframework.data.mongodb.core.query.Criteria;


@Service
public class CounterGeneratorService {
	
	
	@Autowired
	private MongoOperations mongoOperations;
	
	
	public int getCounter(String CounterName) {
		Query q = new Query(Criteria.where("name").is(CounterName));
		Update update = new Update().inc("count", 1);
		
		Counter currentCount=mongoOperations.findAndModify(q,update,options().returnNew(true).upsert(true),Counter.class);

		return Objects.isNull(currentCount)?1:currentCount.getCount();
		
		
		
		
		
	}

}
