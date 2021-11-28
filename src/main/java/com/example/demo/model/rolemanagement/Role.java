package com.example.demo.model.rolemanagement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Role")
public class Role {
	
	@Id
	private String id;
	private String name;
	
	public Role() {}
	
//	public Role(ERole name) {
//		this.name=name;
//	}
	
	public Role(String name) {
		this.name=name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
}
