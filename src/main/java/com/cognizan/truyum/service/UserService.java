package com.cognizan.truyum.service;

import com.cognizan.truyum.exception.UserAlredyExistsException;
import com.cognizan.truyum.model.User;

public interface UserService {
	
	
	
	public  void signUp(User user) throws UserAlredyExistsException ;

}
