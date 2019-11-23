package com.cognizan.truyum.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizan.truyum.exception.CartEmptyException;
import com.cognizan.truyum.model.Cart;
import com.cognizan.truyum.model.CartItems;
import com.cognizan.truyum.model.MenuItem;
import com.cognizan.truyum.model.User;
import com.cognizan.truyum.repositery.CartItemRepositery;
import com.cognizan.truyum.repositery.CartRepositery;
import com.cognizan.truyum.repositery.MenuItemRepositery;
import com.cognizan.truyum.repositery.UserRepositery;

@Service
public class CartServiceImpl  {
	
	@Autowired
    private UserRepositery userRepository;
    @Autowired
    private CartItemRepositery  cartItemRepository;
    @Autowired
    private CartRepositery cartRepository;
    @Autowired
    private MenuItemRepositery menuItemRepository;

    @Transactional
    public Cart getAllCartItems(String username) throws CartEmptyException {
           User user = userRepository.findbyUserName(username);
           Cart cart = cartRepository.getAllCartItems(user.getId());
           if ( cart == null ) {
                  throw new CartEmptyException();
           } else if(cart.getTotal() == 0){
                  throw new CartEmptyException();
           }else {
                  System.out.println(cart);
                  return cart;
           }
    }
    @Transactional
    public void addToCartItem(String username, long menuItemId) {
           User user = userRepository.findbyUserName(username);
           MenuItem menuItem = menuItemRepository.findById((int) menuItemId).get();
           boolean done = false;
           Cart cart = cartRepository.findByUserId(user.getId());
           if (cart != null) {
                  System.out.println(cart.toString());
                  System.out.println("Cart is Not Empty");
                  Set<CartItems> cartItems = cartItemRepository.findByCartAndFoodItem(cart, menuItem);
                  for (CartItems cartItem : cart.getItems()) {
                        if (cartItem.getFoodItem().getId()== menuItemId) {
                               cartItem.setQuantity(cartItem.getQuantity() + 1);
                               cartItemRepository.save(cartItem);
                               cartItems.add(cartItem);
                               done = true;
                               break;
                        }
                  }
                  cart.setItems(cartItems);
                  if (!done) {
                        CartItems cartItem = new CartItems(menuItem, 1, cart);
                        cartItemRepository.save(cartItem);
                  }

                  cart.setTotal(cart.getTotal() + (int) menuItem.getPrice());
                  cartRepository.save(cart);
           } else {
                  System.out.println("Cart is Empty");
                  Set<CartItems> newCartSet = new HashSet<CartItems>();
                  Cart newCart = new Cart( newCartSet,(int) menuItem.getPrice(),user);
                  cartRepository.save(newCart);
                  CartItems cartItem = new CartItems(menuItem, 1, newCart);
                  System.out.println(cartItem);
                  newCartSet.add(cartItem);
                  cartItemRepository.save(cartItem);

           }

    }
    @Transactional
    public void removeCartItem(String username, long menuItemId) {
           User user = userRepository.findbyUserName(username);
           MenuItem menuItem = menuItemRepository.findById((int)menuItemId).get();
           Cart cart = cartRepository.findByUserId(user.getId());
           Set<CartItems> cartItems = cartItemRepository.findByCartAndFoodItem(cart, menuItem);
           for (CartItems cartItem : cart.getItems()) {
                  if (cartItem.getFoodItem().getId() == menuItemId) {
                        if (cartItem.getQuantity() >1) {
                               cartItem.setQuantity(cartItem.getQuantity() - 1);
                               cartItemRepository.save(cartItem);
                               cartItems.add(cartItem);
                               cart.setTotal((int)(cart.getTotal() - menuItem.getPrice()));
                               cartRepository.save(cart);
                        } else {
                               cartItemRepository.delete(cartItem);
                               cart.setTotal((int)(cart.getTotal() - menuItem.getPrice()));
                               cartRepository.save(cart);
                               break;
                        }
                  }
           }
    }

}
