package com.cognizan.truyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizan.truyum.dao.UserDao;
import com.cognizan.truyum.exception.UserAlredyExistsException;
import com.cognizan.truyum.model.User;
import com.cognizan.truyum.security.AppUserDetailsService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	//UserDao userdao;
	AppUserDetailsService appUserDetailsService;
	

	@Override
	public void signUp(User user) throws UserAlredyExistsException {
		appUserDetailsService.signUp(user);
	
	}

}
