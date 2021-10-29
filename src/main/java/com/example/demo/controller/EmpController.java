package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.example.demo.model.Employee;
import com.example.demo.service.CounterGeneratorService;
import com.example.demo.service.EmpService;


@RequestMapping("/api/v1")
@RestController
public class EmpController {
	
	private EmpService employeeService;
	
	@Autowired
	private CounterGeneratorService service;
	
	public EmpController(EmpService employeeService) {
		System.out.println("GOT EMP SERVICE OBJ");
		this.employeeService=employeeService;
		
	}
	
	@GetMapping("/person")
	public List<Employee> getAllEmployees(){
		System.out.println("getAllEmployees");
		return employeeService.getAllEmployees();
		
	}
	@GetMapping("/person/{id}")
	public Employee getEmployeesById(@PathVariable("id") int id){
		System.out.println("getAllEmployees");
		return employeeService.getEmployeesById(id);
		
	}
	
	@PostMapping("/person")
	public Employee createEmployee(@RequestBody Employee employee) {
//		System.out.println(employee.getName());

		System.out.println("Create Emp");
		
		employee.setId(service.getCounter(Employee.COUNTER_NAME));
		return employeeService.addEmployee(employee);
		
		
	}
	
	@PutMapping("/person")
	public String updateEmployee(@RequestBody Employee employee) {
		
		return employeeService.updateEmployee(employee);
	}
	
	@PutMapping("/person/{id}")
	public String updateEmployee(@PathVariable("id") int id, @RequestBody Map<String,String> employee) {
		System.out.println(employee);
		return employeeService.updateEmployeeById(id,employee);
	}
	
	@DeleteMapping("/person/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		return employeeService.deleteEmployee(id);
	}

}
