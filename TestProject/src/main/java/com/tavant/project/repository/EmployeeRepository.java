package com.tavant.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavant.project.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
