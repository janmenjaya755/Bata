package com.bata.billpunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "TCSmasterModel")
@Table(name = "TCS_MASTER_DTLS")
public class TCSmasterModel implements Serializable {

	private static final long serialVersionUID = 187767777L;

	@Id
	@Column(name = "TCS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tcs_Sequence")
	@SequenceGenerator(name = "tcs_Sequence", sequenceName = "TCS_SEQ", allocationSize = 1)
	private Long tcsId;

	@Column(name = "TCS_VAULE")
	private String tcsVaule;

	public Long getTcsId() {
		return tcsId;
	}

	public void setTcsId(Long tcsId) {
		this.tcsId = tcsId;
	}

	public String getTcsVaule() {
		return tcsVaule;
	}

	public void setTcsVaule(String tcsVaule) {
		this.tcsVaule = tcsVaule;
	}

}
