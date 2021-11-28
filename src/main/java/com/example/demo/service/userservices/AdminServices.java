package com.example.demo.service.userservices;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.rolemanagement.ERole;
import com.example.demo.model.rolemanagement.Role;
import com.example.demo.model.rolemanagement.User;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;



@Service
public class AdminServices {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public User addRoleToUser(String username, String roleRequested) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		
		Role role = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("name").is("ROLE_"+roleRequested.toUpperCase())), Role.class);
		Update update = new Update().addToSet("roles", role);
		
		return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(false), User.class);
	}
	
	
//	public User deleteRoleFromUser(String username, String roleRequested) {
//		
//		return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(false), User.class);
//	}
	
	public MessageResponse addNewRole(String rolename) {
		Role role = new Role(rolename);
		mongoTemplate.save(role);
		return new MessageResponse("Role added successfully");
	}
	
	
}
