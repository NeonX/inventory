package com.shop.inventory.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name="cost_price")
	private Double costPrice;
	
	@Column(name="sell_price")
	private Double sellPrice;
	
	@Column(name="last_sale_date")
	private Date lastSaleDate;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy="product", targetEntity=Inventory.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Inventory> inventories;
	
	@OneToMany(mappedBy="product", targetEntity=ProductAttach.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductAttach> imgAttach;

	@Transient
	private String fileServletUrl;
	
	public Integer getAvailable(){
		Integer sumAvailable = 0;
		if(inventories != null && inventories.size() > 0){
			for(Inventory item : inventories){
				sumAvailable += item.getAvailable();
			}
		}
		return sumAvailable;
	}
	
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

	public Date getLastSaleDate() {
		return lastSaleDate;
	}

	public void setLastSaleDate(Date lastSaleDate) {
		this.lastSaleDate = lastSaleDate;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
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

	public String getFileServletUrl() {
		return fileServletUrl;
	}

	public void setFileServletUrl(String fileServletUrl) {
		this.fileServletUrl = fileServletUrl;
	}

	
}
