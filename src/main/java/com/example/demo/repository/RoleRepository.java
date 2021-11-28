package com.example.demo.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.rolemanagement.ERole;
import com.example.demo.model.rolemanagement.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	Role findByName(String roleUser);
	
}
