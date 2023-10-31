package com.demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.Employee;
import com.demo.demo.entity.User;
import com.demo.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAll")
	public ResponseEntity<List> getEmployee() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.save(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public void deleteEmployeeById(@RequestParam("id") Long id) {
		employeeService.remove(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam("lastName") String lastName) {
	    return new ResponseEntity<>( employeeService.searchEmployeesByName(lastName), HttpStatus.OK);
	}

}
