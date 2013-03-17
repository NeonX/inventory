package com.shop.inventory.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="shop", schema="public")
@SequenceGenerator(name="shop_generator", sequenceName="shop_seq", allocationSize=1)
public class Shop extends AbstractEntity {

	@Id
	@Column(name="shop_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shop_generator")
	private Integer shopId;
	
	@Column(name="shop_code")
	private String shopCode;
	
	@Column(name="shop_name")
	private String shopName;
	
	@OneToMany(mappedBy="shop", targetEntity=ShopMember.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ShopMember> shopMember;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<ShopMember> getShopMember() {
		return shopMember;
	}

	public void setShopMember(List<ShopMember> shopMember) {
		this.shopMember = shopMember;
	}

}
