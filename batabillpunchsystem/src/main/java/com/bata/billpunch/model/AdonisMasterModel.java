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
@Entity(name = "AdonisMasterModel")
@Table(name = "TL_ADONIS_MASTER_DTLS")
public class AdonisMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adonis_Sequence")
	@SequenceGenerator(name = "adonis_Sequence", sequenceName = "ADONIS_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "CON_BATCH_NO")
	private String conBatchNo;

	@Column(name = "APP_BATCH_NO")
	private String appBatchNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConBatchNo() {
		return conBatchNo;
	}

	public void setConBatchNo(String conBatchNo) {
		this.conBatchNo = conBatchNo;
	}

	public String getAppBatchNo() {
		return appBatchNo;
	}

	public void setAppBatchNo(String appBatchNo) {
		this.appBatchNo = appBatchNo;
	}
	
	

}
