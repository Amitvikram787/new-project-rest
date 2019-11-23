package com.cognizan.truyum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ci_id")
	private int id;
	@ManyToOne
	@JoinColumn(name="ci_me_item")
	 private MenuItem foodItem;
	
	@Column(name="ci_quantity")
	 private int quantity;
	
	
	public CartItems(int id, MenuItem foodItem, int quantity) {
		super();
		this.id = id;
		this.foodItem = foodItem;
		this.quantity = quantity;
	}
	
	@ManyToOne
	@JoinColumn(name="ci_ct_id")
	private Cart cart;
	
	public CartItems(MenuItem foodItem, int quantity, Cart cart) {
		super();
		this.foodItem = foodItem;
		this.quantity = quantity;
		this.cart = cart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MenuItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(MenuItem foodItem) {
		this.foodItem = foodItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItems() {
	}
}
