package com.shop.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="inventory", schema="public")
@SequenceGenerator(name="inventory_generator", sequenceName="inventory_seq", allocationSize=1)
public class Inventory {

	@Id
	@Column(name="inventory_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="inventory_generator")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="product_code")
	private Product product;
	
	private Long available;
	
	private Long balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
