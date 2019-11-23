package com.cognizan.truyum.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.cognizan.truyum.exception.UserAlredyExistsException;
import com.cognizan.truyum.model.User;

@Component
public class UserDao {
	/*
	@Autowired
	InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	public void signUp(User user) throws UserAlredyExistsException
	{
		if(!inMemoryUserDetailsManager.userExists(user.getUsername()))
		{
			inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).
					password(passwordEncoder().encode(user.getPassword())).roles("USER").build());
		}
		else
		{
			throw new UserAlredyExistsException();
		}
	}
	
	*/
	
	

}
