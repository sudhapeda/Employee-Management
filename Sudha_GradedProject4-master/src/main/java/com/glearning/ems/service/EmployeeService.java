package com.glearning.ems.service;

import java.util.List;

import com.glearning.ems.model.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee, long id);

    void deleteEmployee(Long id);

    List<Employee> searchEmployeesByFirstName(String firstName);

    List<Employee> getAllEmployeesSortedByFirstName(String order);


}
