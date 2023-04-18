package com.springboot.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ms.model.Employee;
import com.springboot.ms.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/firstName/{firstName}")
	public Employee getEmployeeByFistName(@PathVariable String firstName){
		Employee emp = employeeService.getUserDetailsByFirstName(firstName);
		
		return emp;
	}

}
