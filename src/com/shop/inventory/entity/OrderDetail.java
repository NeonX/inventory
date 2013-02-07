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

@Entity
@Table(name="order_detail", schema="public")
@SequenceGenerator(name="order_detail_generator", sequenceName="order_detail_seq", allocationSize=1)
public class OrderDetail extends AbstractEntity {

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
	private Double sellPrice;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="status")
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
