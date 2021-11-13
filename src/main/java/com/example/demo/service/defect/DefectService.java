package com.example.demo.service.defect;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.defect.Comments;
import com.example.demo.model.defect.Defect;
import com.example.demo.model.defect.Status;
import com.example.demo.utilities.Dashboard;
import com.example.demo.utilities.Timestamp;

import java.util.List;
import java.util.Map;

@Service
public class DefectService {
	private MongoTemplate mongoTemplate;
	
	
	@Autowired
	public DefectService(MongoTemplate mongoTemplate) {
		System.out.println("Heyy, I am Employee Service Class");
		this.mongoTemplate = mongoTemplate;
	}
	
	public String addDefect(Defect defect) {
		Defect ins = mongoTemplate.insert(defect);
		System.out.println("Employee "+ins.getName()+" added into the database.");
		addStatus(defect.getId(),"New");
		return "Employee "+ins.getName()+" added into the database.";
	}
	
	public void addStatus(String id, String status) {
		Status s = new Status();
		s.setDefectId(id);
		s.setStatus(status);
		Timestamp t = new Timestamp();
		s.setDateTime(t);
		mongoTemplate.insert(s);
	}
	
	public List<Defect> getAllDefects(){
		Query q = new Query();
		q.addCriteria(Criteria.where("status").ne("Cancelled"));
		List<Defect> a = mongoTemplate.find(q,Defect.class);
		System.out.print(a);
		return a;
	}
	
	public String addComment(Comments c) {
		Timestamp t = new Timestamp();
		c.setTimestamp(t);
		mongoTemplate.insert(c);
		return "Your comment has been added into the database.";
	}
	
	public String updateDefectByID(Map<String, String> data) {
		Query select = Query.query(Criteria.where("id").is(data.get("id")));
		Update update = new Update();
		for(Map.Entry m:data.entrySet()){
			if(m.getKey().equals("status")) {
				updateDefectStatus(data);
				continue;
			}
			update.set((String) m.getKey(), m.getValue());  
	    }
		mongoTemplate.findAndModify(select, update, Defect.class);
		return "Update successful";
	}
	public String updateDefectStatus(Map<String, String> data) {
		System.out.println(data.get("status"));
		Query select = Query.query(Criteria.where("id").is(data.get("id")));
		Update update = new Update();
		update.set("status", data.get("status"));
		mongoTemplate.findAndModify(select, update, Defect.class);
		addStatus(data.get("id"),data.get("status"));
		return "The status of the defect "+data.get("id")+" has been updated to "+data.get("status");
	}
	
	public String deleteDefect(String id) {
		Query select = Query.query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("status", "Cancelled");
		mongoTemplate.findAndModify(select, update, Defect.class);
		addStatus(id,"Cancelled");
		return "The defect "+id+" is deleted successfully";
	}
	public Dashboard getDefectById(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));
		Defect a = mongoTemplate.find(q,Defect.class).get(0);
		q = new Query();
		q.addCriteria(Criteria.where("defectId").is(id));
		List <Status> b = mongoTemplate.find(q, Status.class);
		List <Comments> c = mongoTemplate.find(q, Comments.class);
		System.out.print(b);
		Dashboard d = new Dashboard();
		d.setDefect(a);
		d.setStatus(b);
		d.setComments(c);
		return d;
	}
}
