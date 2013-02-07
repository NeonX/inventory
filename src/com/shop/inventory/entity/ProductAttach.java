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
@Table(name="product_attach", schema="public")
@SequenceGenerator(name="product_attach_generator", sequenceName="product_attach_seq", allocationSize=1)
public class ProductAttach extends AbstractAttachment {

	@Id
	@Column(name="attach_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_attach_generator")
	private Long attachId;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Product.class)	
	@JoinColumns({ @JoinColumn(name="product_code", referencedColumnName="product_code") })	
	@Basic(fetch=FetchType.EAGER)
	private Product product;

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
