package com.cognizan.truyum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Table(name="menu_item")
public class MenuItem {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MenuItem.class);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="me_id")
	private int id;
	
	
	@Column(name="me_name")
	private String name;
	
	@Column(name="me_price")
	private float price;
	
	@Column(name="me_active")
	private boolean active;
	
	@Column(name="me_category")
	private String category;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Column(name="me_date_of_launch")
	private Date dateOfLaunch;
	
	@Column(name="me_free_delivery")
	private  boolean freeDelivery;
	
	@Column(name="me_url")
	private String imgSrc;
	
	public MenuItem(int id, String name, float price, boolean active, String category, Date dateOfLaunch,
			boolean freeDelivery, String imgSrc) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
		this.category = category;
		this.dateOfLaunch = dateOfLaunch;
		this.freeDelivery = freeDelivery;
		this.imgSrc = imgSrc;
		LOGGER.info("menuItem in constructor");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}
	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}
	public boolean isFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public MenuItem() {
		super();
		LOGGER.info("MenuItem default constructor");
	}
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", price=" + price + ", active=" + active + ", category="
				+ category + ", dateOfLaunch=" + dateOfLaunch + ", freeDelivery=" + freeDelivery + ", imgSrc=" + imgSrc
				+ "]";
	}
	
	
	

}
