package com.glearning.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.ems.model.Employee;
import com.glearning.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		Employee updatedEmployee = employeeService.updateEmployee(employee, id);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Deleted employee id - " + id, HttpStatus.OK);
	}

	@GetMapping("/search/{firstName}")
	public ResponseEntity<List<Employee>> searchEmployeesByFirstName(@PathVariable String firstName) {
		List<Employee> employees = employeeService.searchEmployeesByFirstName(firstName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/sort")
	public ResponseEntity<List<Employee>> getAllEmployeesSortedByFirstName(
			@RequestParam(required = false, name = "order") String order) {
		List<Employee> employees;
		if(order.equalsIgnoreCase("asc")) {
			employees= employeeService.getAllEmployeesSortedByFirstName("asc");
		}else {
			employees= employeeService.getAllEmployeesSortedByFirstName("desc");
		}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

}
