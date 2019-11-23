package com.cognizan.truyum.repositery;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizan.truyum.model.Cart;
import com.cognizan.truyum.model.CartItems;
import com.cognizan.truyum.model.MenuItem;

public interface CartItemRepositery extends JpaRepository<CartItems, Integer> {
	Set<CartItems> findByCartAndFoodItem(Cart cart, MenuItem foodItem);

}
