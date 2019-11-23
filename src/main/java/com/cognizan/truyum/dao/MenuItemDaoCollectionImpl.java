package com.cognizan.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizan.truyum.model.MenuItem;

@Component
public class MenuItemDaoCollectionImpl implements MenuItemDao {
	
	private static List<MenuItem>menuItemList;
	
	@SuppressWarnings("unchecked")
	public MenuItemDaoCollectionImpl() {
		 ApplicationContext context= new ClassPathXmlApplicationContext("menu-item.xml");
		 menuItemList=context.getBean("menuItemList1",java.util.ArrayList.class);
		 System.out.println(menuItemList);
		 ((ConfigurableApplicationContext) context).close();
		 
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer=new ArrayList<>();
		for(MenuItem menuItem: menuItemList)
		{
			Date dateOfLaunch=menuItem.getDateOfLaunch();
			Date today=new Date();
			boolean Active= menuItem.isActive();
			if((dateOfLaunch.before(today)|| dateOfLaunch.equals(today))&& Active)
			{
				menuItemListCustomer.add(menuItem);
			}
			
		}
		
		
		
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		System.out.println("hello");
	     for (int i =0;i<menuItemList.size();i++)
	     {
	    	 System.out.println("bhai");
	    	 if(menuItemList.get(i).getId()== menuItem.getId())
	    	 {
	    		 System.out.println("amit");
	    		 menuItemList.set(i, menuItem);
	    		 break;
	    	 }
	     }
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		
		MenuItem menuItem=null;
		for(MenuItem item :menuItemList)
		{
			if(item.getId()==menuItemId)
			{
				menuItem=item;
				break;
			}
		}
		
		return menuItem ;
	}

}
