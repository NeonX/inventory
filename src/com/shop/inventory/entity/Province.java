package com.shop.inventory.entity;

import java.util.Set;

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
@Table(name="province", schema="public")
@SequenceGenerator(name="province_generator", sequenceName="province_seq", allocationSize=1)
public class Province {

	@Id
	@Column(name="province_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="province_generator")
	private Integer provinceId;
	
	@Column(name="province_name")
	private String provinceName;

	@OneToMany(mappedBy="province", targetEntity=Amphur.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<Amphur> amphurs;
	
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Set<Amphur> getAmphurs() {
		return amphurs;
	}

	public void setAmphurs(Set<Amphur> amphurs) {
		this.amphurs = amphurs;
	}
	
	
}
