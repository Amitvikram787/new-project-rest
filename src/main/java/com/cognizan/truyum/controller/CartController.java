package com.cognizan.truyum.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizan.truyum.exception.CartEmptyException;
import com.cognizan.truyum.model.Cart;
import com.cognizan.truyum.service.CartServiceImpl;

@RestController
@RequestMapping("/carts")
public class CartController {
	public static Logger LOGGER= LoggerFactory.getLogger(CartController.class);
	@Autowired
	private CartServiceImpl cartService;
	
	
	@GetMapping("/{user}")
	public Cart getAllCartItems(@PathVariable String user)throws CartEmptyException
	
	{
		LOGGER.debug("Inside getAllCart Items");
		return cartService.getAllCartItems(user);
	
	}
	
	@PostMapping("/{user}/{menuItemId}")
	public  void addAllCartItem(@PathVariable String user , @PathVariable long menuItemId)
	{
		LOGGER.debug("Inside getAllCart Items");
		cartService.addToCartItem(user, menuItemId);
		
	}
	
	@DeleteMapping("/{user}/{menuItemId}")
	public void removeCartItem(@PathVariable String  user, @PathVariable long menuItemId,@PathParam("quantity") Integer quantity)
	{
		LOGGER.debug("Inside getAllCart Items");
		cartService.removeCartItem(user, menuItemId);
	}
}
