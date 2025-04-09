package com.bofa.util;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	public CustomUserDetailsService() {
		// TODO Auto-generated constructor stub
	}

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if("admin".equals(username)) {
			String encodedPassword = passwordEncoder.encode("password");
			return new User("admin", encodedPassword, new ArrayList<>());
		}
		
		throw new UsernameNotFoundException("User not found: ");
	}

}
