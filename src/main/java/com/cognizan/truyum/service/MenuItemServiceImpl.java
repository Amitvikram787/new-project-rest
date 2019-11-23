package com.cognizan.truyum.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizan.truyum.dao.MenuItemDao;
import com.cognizan.truyum.model.MenuItem;
import com.cognizan.truyum.repositery.MenuItemRepositery;
@Service
public class MenuItemServiceImpl implements MenuItemService {
	
	@Autowired
	private MenuItemRepositery menuItemRepositery;
	
	@Autowired
	private MenuItemDao menuItemDao;

	@Override
	@Transactional
	public List<MenuItem> getMenuItemListAdmin() {
		
		//return menuItemDao.getMenuItemListAdmin();
		return menuItemRepositery.findAll();
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
	
		return menuItemRepositery.getMenuItemsCustomer();
	}

	@Override
	@Transactional
	public MenuItem getMenuItem(long id) {
		
		//return menuItemDao.getMenuItem(id);
		return menuItemRepositery.findById((int) id).get();
	}

	@Override
	@Transactional
	public void modifyMenuItem(MenuItem menuItem) {
		
    // menuItemDao.modifyMenuItem(menuItem);
		menuItemRepositery.save(menuItem);
	}

}
