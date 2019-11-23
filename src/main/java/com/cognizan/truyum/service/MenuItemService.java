package com.cognizan.truyum.service;

import java.util.List;

import com.cognizan.truyum.model.MenuItem;

public interface MenuItemService {
	public List<MenuItem> getMenuItemListAdmin();
	public List<MenuItem> getMenuItemListCustomer();
	public MenuItem getMenuItem(long id);
	public void modifyMenuItem(MenuItem menuItem);
}
