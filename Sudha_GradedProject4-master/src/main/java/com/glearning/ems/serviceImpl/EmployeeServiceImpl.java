package com.glearning.ems.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.glearning.ems.exception.EmployeeNotFoundException;
import com.glearning.ems.model.Employee;
import com.glearning.ems.repository.EmployeeRepository;
import com.glearning.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
		if (existingEmployee != null) {
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			return employeeRepository.save(existingEmployee);
		}
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public List<Employee> searchEmployeesByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
	}

	@Override
	public List<Employee> getAllEmployeesSortedByFirstName(String order) {
		if (order.equalsIgnoreCase("asc")) {
            return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        } else if (order.equalsIgnoreCase("desc")) {
            return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        }else {
        	throw new IllegalArgumentException("Invalid sort order: " +order);
        }
    }

}


