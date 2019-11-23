package com.cognizan.truyum.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "ct_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	private Set<CartItems> items;

	@Column(name = "ct_total")
	private int total;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name="ct_us_id")
	private User user;
   
	
	
	public Set<CartItems> getItems() {
		return items;
	}

	public void setItems(Set<CartItems> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Cart(Set<CartItems> items, int total) {
		super();
		this.items = items;
		this.total = total;
	}

	public Cart() {

	}

	public Cart(Set<CartItems> items, int total, User user) {
		super();
		this.items = items;
		this.total = total;
		this.user = user;
	}

}
