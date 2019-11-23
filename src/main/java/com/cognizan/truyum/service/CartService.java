package com.cognizan.truyum.service;

import com.cognizan.truyum.exception.CartEmptyException;
import com.cognizan.truyum.model.Cart;

public interface CartService {
	
	public Cart getAllcartItems(String user) throws CartEmptyException;
	public  void addCartItem(String user, long menuItemId);
	public void removeCartItem(String user, long menuItemId,Integer quantity);

}
