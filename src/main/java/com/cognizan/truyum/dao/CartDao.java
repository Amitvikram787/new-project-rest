package com.cognizan.truyum.dao;

import com.cognizan.truyum.exception.CartEmptyException;
import com.cognizan.truyum.model.Cart;

public interface CartDao {
	public void addCartItem(String user ,long menuItemId);
	public Cart getAllCartItems(String user ) throws CartEmptyException;
	public void removeCartItem(String user , long menuItemId,Integer quantity);

}
