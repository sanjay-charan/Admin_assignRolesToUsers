package com.example.demo.controller.rolemanagement;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.rolemanagement.PendingRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.roleservices.AdminServices;
import com.example.demo.service.roleservices.UserServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserServices userServices;

	@Autowired
	AdminServices adminServices;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@PreAuthorize("#pendingRequest.getUsername() == authentication.principal.username")
	@PostMapping("/requestrole")
	public ResponseEntity<?> requestRole(@Valid @RequestBody PendingRequest pendingRequest) {
		return ResponseEntity.ok(userServices.addRoletoUser(pendingRequest.getUsername(),
				pendingRequest.getRoleRequested(), pendingRequest.getRequestType()));
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/addroletouser")
	public ResponseEntity<?> addRoleToUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity
				.ok(adminServices.addRoleToUser(dataHashMap.get("username"), dataHashMap.get("roleRequested")));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/deleterolefromuser")
	public ResponseEntity<?> deleteRoleFromUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity
				.ok(adminServices.deleteRoleFromUser(dataHashMap.get("username"), dataHashMap.get("roleRequested")));
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/addnewrole")
	public ResponseEntity<?> addNewRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity.ok(adminServices.addNewRole(dataHashMap.get("rolename")));
	}
}
