package com.glearning.ems.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glearning.ems.model.DomainUserDetails;
import com.glearning.ems.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user= this.userRepository
		.findByUsername(username)
		.map(DomainUserDetails::new)
		.orElseThrow(() -> new UsernameNotFoundException("invalid username"));
		return user;
	}

}
