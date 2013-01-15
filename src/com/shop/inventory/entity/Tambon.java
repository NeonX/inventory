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
@Table(name="tambon", schema="public")
@SequenceGenerator(name="tambon_generator", sequenceName="tambon_seq", allocationSize=1)
public class Tambon {

	@Id
	@Column(name="tambon_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tambon_generator")
	private Integer tambonId;
	
	@Column(name="tambon_name")
	private String tambonName;

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE}, targetEntity=Amphur.class)	
	@JoinColumns({ @JoinColumn(name="amphur_id", referencedColumnName="amphur_id") })	
	@Basic(fetch=FetchType.EAGER)
	private Amphur amphur;
	
	public Integer getTambonId() {
		return tambonId;
	}

	public void setTambonId(Integer tambonId) {
		this.tambonId = tambonId;
	}

	public String getTambonName() {
		return tambonName;
	}

	public void setTambonName(String tambonName) {
		this.tambonName = tambonName;
	}

	public Amphur getAmphur() {
		return amphur;
	}

	public void setAmphur(Amphur amphur) {
		this.amphur = amphur;
	}
	
	
}
