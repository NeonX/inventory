package com.shop.inventory.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="product", schema="public")
public class Product extends AbstractEntity {

	@Id
	@Column(name="product_code", unique=true, nullable=false)
	private String productCode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="color")
	private String color;
	
	@Column(name="cost_price")
	private Double costPrice;
	
	@Column(name="sell_price")
	private Double sellPrice;
	
	@Column(name="last_sale_date")
	private Date lastSaleDate;
	
	@Column(name="status")
	private String status;
	
	@OneToOne(mappedBy="product", cascade=CascadeType.ALL)
	private Inventory inventory;
	
	@OneToMany(mappedBy="product", targetEntity=ProductAttach.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductAttach> imgAttach;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getLastSaleDate() {
		return lastSaleDate;
	}

	public void setLastSaleDate(Date lastSaleDate) {
		this.lastSaleDate = lastSaleDate;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public List<ProductAttach> getImgAttach() {
		return imgAttach;
	}

	public void setImgAttach(List<ProductAttach> imgAttach) {
		this.imgAttach = imgAttach;
	}

}
