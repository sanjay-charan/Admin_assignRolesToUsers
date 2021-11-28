package com.example.demo.controller.rolemanagement;

import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.rolemanagement.*;
import com.example.demo.payload.request.*;
import com.example.demo.payload.response.*;
import com.example.demo.repository.*;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.service.userservices.AdminServices;
import com.example.demo.service.userservices.UserServices;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(userServices.registerUser(loginRequest.getUsername(), loginRequest.getPassword()));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){
		return ResponseEntity.ok(userServices.userSignIn(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword()));
	}
	@PreAuthorize("#pendingRequest.getUsername() == authentication.principal.username")
	@PostMapping("/requestrole")
	public ResponseEntity<?> requestRole(@Valid @RequestBody PendingRequest pendingRequest){
		return ResponseEntity.ok(userServices.addRoletoUser(pendingRequest.getUsername(), pendingRequest.getRoleRequested(), pendingRequest.getRequestType()));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/addroletouser")
	public ResponseEntity<?> addRoleToUser(@Valid @RequestBody HashMap<String, String> dataHashMap){
		return ResponseEntity.ok(adminServices.addRoleToUser(dataHashMap.get("username"),dataHashMap.get("roleRequested")));
	}
	
//	@PostMapping("/deleterolefromuser")
//	public ResponseEntity<?> deleteRoleFromUser(@Valid @RequestBody HashMap<String, String> dataHashMap){
//		return ResponseEntity.ok(adminServices.deleteRoleFromUser(dataHashMap.get("username"),dataHashMap.get("roleRequested")));
//	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/addnewrole")
	public ResponseEntity<?> addNewRole(@Valid @RequestBody HashMap<String, String> dataHashMap){
		return ResponseEntity.ok(adminServices.addNewRole(dataHashMap.get("rolename")));
	}
}
