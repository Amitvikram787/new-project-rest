package com.cognizan.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cognizan.truyum.exception.CartEmptyException;
import com.cognizan.truyum.model.Cart;
import com.cognizan.truyum.model.CartItems;
import com.cognizan.truyum.model.MenuItem;

@Component
public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<String, Cart> userCarts;

	public CartDaoCollectionImpl() {
		if (userCarts == null)
			userCarts = new HashMap<>();
	}

	@Override
	public void addCartItem(String user, long menuItemId) {
		
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		boolean added=false;
		if (userCarts.containsKey(user)) {
			System.out.println("Insode if");
			Set<CartItems> userCart = userCarts.get(user).getItems();
			for (CartItems cartItems : userCart) {
				if (cartItems.getId() == menuItemId) {
					cartItems.setQuantity(cartItems.getQuantity() + 1);
					added=true;
					break;
				}
			}
			if(!added) {
				Set<CartItems> cartList = userCarts.get(user).getItems();
				CartItems cartItem = new CartItems();
				MenuItem foodItem = menuItemDao.getMenuItem(menuItemId);

				cartItem.setFoodItem(foodItem);
				cartItem.setId((int) menuItemId);
				cartItem.setQuantity(1);
				
				cartList.add(cartItem);
				Cart cart = new Cart();
				cart.setItems(cartList);
				int total=0;
				for (CartItems cItem : cartList) {
					total += (cItem.getFoodItem().getPrice() * cItem.getQuantity());
				}
				
				cart.setTotal(total);
				userCarts.put(user,cart);
			}
			
		} else {

			System.out.println("Insode else");
			CartItems cartItem = new CartItems();
			MenuItem foodItem = menuItemDao.getMenuItem(menuItemId);

			cartItem.setFoodItem(foodItem);
			cartItem.setId((int) menuItemId);
			cartItem.setQuantity(1);

			Set<CartItems> cartItems=new  HashSet<CartItems>();
			//List<CartItems> cartItems = new ArrayList<CartItems>();
			cartItems.add(cartItem);
			Cart cart = new Cart(cartItems, 0);
			userCarts.put(user, cart);
		}

	}

	@Override
	public Cart getAllCartItems(String user) throws CartEmptyException {

		if (userCarts.containsKey(user)) {
			Cart cart = userCarts.get(user);
			Set<CartItems> cartList = cart.getItems();
			if (cartList.isEmpty()) {
				throw new CartEmptyException();
			} else {
				int total = 0;
				for (CartItems menuItem : cartList) {
					total += (menuItem.getFoodItem().getPrice() * menuItem.getQuantity());
				}
				cart.setTotal(total);
				return cart;
			}
		} else {
			throw new CartEmptyException();
		}

	}

	@Override
	public void removeCartItem(String user, long menuItemId,Integer quantity) {
		
//		List<CartItems>cartList=userCarts.get(user).getItems();
//		
////	       List<MenuItem> cartList=userCarts.get(user).getMenuItemList();
//	       for(CartItems menuItem:cartList)
//	       {
//	    	   cartList.removeIf((CartItems c) -> {return c.getId() == menuItemId;});
//	       }
//	

		if(userCarts.containsKey(user)) {
			Cart cart=userCarts.get(user);
			CartItems cartItem=cart.getItems().stream().filter((item)->item.getId()==menuItemId).findAny().orElse(null);
			cartItem.setQuantity(cartItem.getQuantity()-quantity);
			if(cartItem.getQuantity()<=0) {			
			cart.getItems().remove(cartItem);
			}
			else {
				cart.setTotal((int) (cart.getTotal()-cartItem.getFoodItem().getPrice()*quantity));
			}
		}
		
		
	}

}
