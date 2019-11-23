package com.cognizan.truyum.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="user")
public class User {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(Role.class);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="us_id")
	private int id;
	
	
	@Column(name="us_name")
	@NotNull(message="User Nmae is Requried")
	private String username;
	
	@Column(name="us_first_name")
	private String firstName;
	
	@Column(name="us_last_name")
	private String lastName;
	
	@Column(name="us_password")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "ur_us_id"), 
        inverseJoinColumns = @JoinColumn(name = "ur_ro_id"))
	   private Set<Role> roleList;
	
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Cart cart;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoleList() {
		return roleList;
	}


	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public User(int id, @NotNull(message = "User Nmae is Requried") String username, String firstName, String lastName,
			String password, Set<Role> roleList, Cart cart) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.roleList = roleList;
		this.cart = cart;
	}


	public User() {
		super();
		
	}
	
	
	

}
