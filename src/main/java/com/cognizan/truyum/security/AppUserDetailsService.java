package com.cognizan.truyum.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizan.truyum.exception.UserAlredyExistsException;
import com.cognizan.truyum.model.Role;
import com.cognizan.truyum.model.User;
import com.cognizan.truyum.repositery.RoleRepositery;
import com.cognizan.truyum.repositery.UserRepositery;

@Service
public class AppUserDetailsService implements UserDetailsService {
   @Autowired
	private UserRepositery userRepositery;
@Autowired
	private RoleRepositery roleRepositery;
	
	

	public AppUserDetailsService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppUserDetailsService(UserRepositery userRepositery, RoleRepositery roleRepositery) {
		super();
		this.userRepositery = userRepositery;
		this.roleRepositery = roleRepositery;
	}

	public AppUserDetailsService(UserRepositery userRepositery) {
		super();
		this.userRepositery = userRepositery;
	}

	User user;
	AppUser appUser;

	@Override
	public UserDetails loadUserByUsername(String username) {

		user = userRepositery.findbyUserName(username);
		if (user == null) {
			try {
				throw new UserAlredyExistsException();
			} catch (UserAlredyExistsException e) {

				e.printStackTrace();
			}

		} else {
			appUser = new AppUser(user);

		}
		return appUser;

	}
	
	
	
	public void signUp(User newUser) throws UserAlredyExistsException
	{
		User user=userRepositery.findbyUserName(newUser.getUsername());
		if(user==null)
		{
			Role role= roleRepositery.findByRoleId(2);
			String password=newUser.getPassword();
			Set<Role> roleList=new HashSet<Role>();
			roleList.add(role);
			newUser.setRoleList(roleList);
			newUser.setPassword(passwordEncoder().encode(password));
			userRepositery.save(newUser);
			
		}
		else
		{
			throw new UserAlredyExistsException();
		}
	}
	
	public PasswordEncoder passwordEncoder()
	
	{
		return new BCryptPasswordEncoder();
	}

}
