package com.demo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.demo.dto.UserDto;
import com.demo.demo.entity.Employee;
import com.demo.demo.entity.User;

public interface EmployeeService{
	List<Employee> getAllEmployees();
	
    void remove(Long id);

	Employee save(Employee employee);
	
	List<Employee>searchEmployeesByName(String last_name);

}
