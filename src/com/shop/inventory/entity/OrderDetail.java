package com.shop.inventory.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="order_detail", schema="public")
@SequenceGenerator(name="order_detail_generator", sequenceName="order_detail_seq", allocationSize=1)
public class OrderDetail extends AbstractEntity implements Comparable<OrderDetail> {

	@Id
	@Column(name="order_detail_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_detail_generator")
	private Long orderDetailId;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Order.class)	
	@JoinColumns({ @JoinColumn(name="order_code", referencedColumnName="order_code") })	
	@Basic(fetch=FetchType.EAGER)
	private Order order;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Product.class)	
	@JoinColumns({ @JoinColumn(name="product_code", referencedColumnName="product_code") })	
	@Basic(fetch=FetchType.EAGER)
	private Product product;

	@Column(name="sell_price")
	private Double sellPrice = 0D;
	
	@Column(name="discount_amount")
	private Double discountAmount = 0D;
	
	@Column(name="payable_confirm")
	private boolean payableConfirm = false;

	@Column(name="quantity")
	private Integer quantity = 0;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	@Column(name="reserve_complete")
	private boolean reserveComplete = false;
	
	@Transient
	private Long inventoryId;
	
	
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public boolean isPayableConfirm() {
		return payableConfirm;
	}

	public void setPayableConfirm(boolean payableConfirm) {
		this.payableConfirm = payableConfirm;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public boolean getReserveComplete() {
		return reserveComplete;
	}

	public void setReserveComplete(boolean reserveComplete) {
		this.reserveComplete = reserveComplete;
	}

	@Override
	public int compareTo(OrderDetail o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		String pdIdStr = (this.product != null)?this.product.getProductCode():"";
		String size = (this.size != null)?this.size:"";
		String color = (this.color != null)?this.color:"";
		String quantity = this.quantity.toString();
		String selfVale = pdIdStr+size+color+quantity;
		
		OrderDetail objDetail = (OrderDetail) obj;
		String objPdIdStr = (objDetail.getProduct() != null)?objDetail.getProduct().getProductCode():"";
		String objSize = (objDetail.getSize() != null)?objDetail.getSize():"";
		String objColor = (objDetail.getColor() != null)?objDetail.getColor():"";
		String objQuantity = objDetail.getQuantity().toString();
		String objVale = objPdIdStr+objSize+objColor+objQuantity;
		
		return selfVale.equals(objVale);
	}


}
