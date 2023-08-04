package com.glearning.ems.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.ems.model.Role;
import com.glearning.ems.model.User;
import com.glearning.ems.repository.UserRepository;

@Configuration
public class SecurityDataDetails {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadUsers(ApplicationReadyEvent event) {

		User kiran = new User("kiran", this.passwordEncoder.encode("welcome"));
		User vinay = new User("vinay", this.passwordEncoder.encode("welcome"));
		User ramesh = new User("ramesh", this.passwordEncoder.encode("welcome"));

		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");
		Role superAdminRole = new Role("ROLE_SUPER_ADMIN");

		kiran.addRole(userRole);

		vinay.addRole(userRole);
		vinay.addRole(adminRole);

		ramesh.addRole(userRole);
		ramesh.addRole(adminRole);
		ramesh.addRole(superAdminRole);

		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		this.userRepository.save(ramesh);

	}

}
