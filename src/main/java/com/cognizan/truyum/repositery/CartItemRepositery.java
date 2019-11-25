package com.cognizan.truyum.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizan.truyum.model.Cart;
import com.cognizan.truyum.model.CartItems;
import com.cognizan.truyum.model.MenuItem;

public interface CartItemRepositery extends JpaRepository<CartItems, Integer> {
	List<CartItems> findByCartAndFoodItem(Cart cart, MenuItem foodItem);

}
