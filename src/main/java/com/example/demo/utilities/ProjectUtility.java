package com.example.demo.utilities;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ProjectUtility {

	public static Query getQueryByKeyValue(String key,String value)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where(key).is(value));
		
		return query;
	}
}
