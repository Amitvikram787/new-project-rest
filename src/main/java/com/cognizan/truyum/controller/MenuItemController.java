package com.cognizan.truyum.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizan.truyum.TruyumConstants;
import com.cognizan.truyum.model.MenuItem;
import com.cognizan.truyum.security.AppUserDetailsService;
import com.cognizan.truyum.service.MenuItemService;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {
	public static Logger LOGGER= LoggerFactory.getLogger(MenuItemController.class);
	@Autowired
	AppUserDetailsService appUserDetailsService;
	@Autowired
	private MenuItemService menuItemService;
	
	@GetMapping
	public List<MenuItem> getAllMenuItems()
	
	{
		LOGGER.debug("Inside All Menu items");
		List<MenuItem> menuItemList;
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String user=authentication.getName();
		LOGGER.debug("Username "+ user);
		if(user !="anonymousUser")
		{
			UserDetails userDetails= appUserDetailsService.loadUserByUsername(user);
			LOGGER.debug("userdatails"+ userDetails);
			String role = userDetails.getAuthorities().toArray()[0].toString();
			TruyumConstants.LOGGER.debug("Role"+ role);
			if(role.equals("ADMIN"))
			{
				LOGGER.debug("Inside Menu ItemList Admin get");
				menuItemList=menuItemService.getMenuItemListAdmin();
				
			}
			else
			{
				LOGGER.debug("Inside Menu ItemList Customer get");
				menuItemList=menuItemService.getMenuItemListCustomer();
				
			}
		}
		else
		{
			LOGGER.debug("Inside Menu ItemList customer get");
			menuItemList=menuItemService.getMenuItemListCustomer();
			
		}
		
		return menuItemList;
		
	}
	@GetMapping("/{id}")
	public MenuItem getMenuItem(@PathVariable long id)
	{
		return menuItemService.getMenuItem(id);
	}
	@PutMapping
	public  void modifyMenuItem(@RequestBody MenuItem menuItem)
	{
		menuItemService.modifyMenuItem(menuItem);
	}

}
