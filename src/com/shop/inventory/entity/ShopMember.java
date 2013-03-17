package com.shop.inventory.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shop_member", schema="public")
public class ShopMember {

	@Id
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Shop.class)	
	@JoinColumns({ @JoinColumn(name="shop_id", referencedColumnName="shop_id") })	
	@Basic(fetch=FetchType.EAGER)
	private Shop shop;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
