package com.glearning.ems.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.glearning.ems.model.Employee;
import com.glearning.ems.repository.EmployeeRepository;

@Configuration
public class EmployeeData {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadEmployees(ApplicationReadyEvent event) {
		
		Employee debasish=new Employee();
		debasish.setFirstName("Debasish");
		debasish.setLastName("jena");
		debasish.setEmail("debasish@gmail.com");
		
		Employee chandan=new Employee();
		chandan.setFirstName("Chandan");
		chandan.setLastName("das");
		chandan.setEmail("chandan@gmail.com");
		
		Employee vikram=new Employee();
		vikram.setFirstName("Vikram");
		vikram.setLastName("rout");
		vikram.setEmail("vikram@gmail.com");
		
		Employee rajesh=new Employee();
		rajesh.setFirstName("rajesh");
		rajesh.setLastName("verma");
		rajesh.setEmail("rajesh@gmail.com");
		
		employeeRepository.save(debasish);
		employeeRepository.save(chandan);
		employeeRepository.save(vikram);
		employeeRepository.save(rajesh);
		
	}
	

}
