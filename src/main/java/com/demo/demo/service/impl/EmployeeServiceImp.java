package com.demo.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.entity.Employee;
import com.demo.demo.repository.EmployeeRepository;
import com.demo.demo.service.EmployeeService;



@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

	@Override
	public void remove(Long id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public List<Employee> searchEmployeesByName(String lastName) {
		
		return employeeRepository.searchEmployeeByName(lastName);
	}



}
