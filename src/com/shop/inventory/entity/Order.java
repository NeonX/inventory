package com.shop.inventory.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="order", schema="public")
public class Order extends AbstractEntity {

	@Id
	@Column(name="order_code", unique=true, nullable=false)
	private String orderCode;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="ship_name")
	private String shipName;
	
	@Column(name="ship_address")
	private String shipAddress;
	
	@Column(name="sender_name")
	private String senderName;
	
	@Column(name="sender_address")
	private String senderAddress;
	
	@Column(name="tracking_number")
	private String trackingNumber;
	
	@Column(name="ems_price")
	private Integer emsPrice = 30;
	
	@Column(name="status")
	private String status;
	
	@Column(name="note")
	private String note;
	
	@OneToMany(mappedBy="order", targetEntity=OrderDetail.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderDetail> orderDetail;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getEmsPrice() {
		return emsPrice;
	}

	public void setEmsPrice(Integer emsPrice) {
		this.emsPrice = emsPrice;
	}

	
}
