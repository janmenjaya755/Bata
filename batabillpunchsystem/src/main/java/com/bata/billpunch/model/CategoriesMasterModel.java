package com.bata.billpunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "CategoriesMasterModel")
@Table(name = "TM_CATEGORY_MASTER_DTLS")
public class CategoriesMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "CAT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cats_Sequence")
	@SequenceGenerator(name = "cats_Sequence", sequenceName = "CATAGORY_SEQ",allocationSize = 1)
	private Long catId;

	@Column(name = "MCAT")
	private String mcat;

	@Column(name = "SUBCAT")
	private String subcat;
	
	@Column(name = "CATNAME")
	private String catname;

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getMcat() {
		return mcat;
	}

	public void setMcat(String mcat) {
		this.mcat = mcat;
	}

	public String getSubcat() {
		return subcat;
	}

	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	
}
