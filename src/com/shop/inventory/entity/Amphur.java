package com.shop.inventory.entity;

import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="amphur", schema="public")
@SequenceGenerator(name="amphur_generator", sequenceName="amphur_seq", allocationSize=1)
public class Amphur {

	@Id
	@Column(name="amphur_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="amphur_generator")
	private Integer amphurId;
	
	@Column(name="amphur_name")
	private String amphurName;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Province.class)	
	@JoinColumns({ @JoinColumn(name="province_id", referencedColumnName="province_id") })	
	@Basic(fetch=FetchType.EAGER)
	private Province province;

	@OneToMany(mappedBy="amphur", targetEntity=Tambon.class, cascade={CascadeType.ALL})	
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<Tambon> tambons;
	
	public Integer getAmphurId() {
		return amphurId;
	}

	public void setAmphurId(Integer amphurId) {
		this.amphurId = amphurId;
	}

	public String getAmphurName() {
		return amphurName;
	}

	public void setAmphurName(String amphurName) {
		this.amphurName = amphurName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Set<Tambon> getTambons() {
		return tambons;
	}

	public void setTambons(Set<Tambon> tambons) {
		this.tambons = tambons;
	}
	
	
	
}
