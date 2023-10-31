package com.demo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.demo.demo.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(nativeQuery = true, value = "call get_employees")
    List<Employee> getAllEmployees();

	@Procedure(name = "search_emp")
    List<Employee> searchEmployeeByName(String lastName);



}
