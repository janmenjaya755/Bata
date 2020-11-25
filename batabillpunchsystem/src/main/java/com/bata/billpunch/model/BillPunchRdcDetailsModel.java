package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchRdcDetailsModel")
@Table(name = "TL_BILL_RDC_DETAILS")
public class BillPunchRdcDetailsModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "BILL_RDC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bills_rdc_Sequence")
	@SequenceGenerator(name = "bills_rdc_Sequence", sequenceName = "BILLP_RDC_SEQ",allocationSize = 1)
	private Long billRdcId;

	@Column(name = "BILL_RDC_ID_FK")
	private Long billpunchFk;

	@Column(name = "INVOICE_NO")
	private String invoiceNO;

	@Column(name = "SHIP_NO")
	private String shipNo;

	@Column(name = "CONSIGN")
	private String consign;

	@Column(name = "CONSIGN_DATE")
	private Date consignDate;

	@Column(name = "WEEK_YEAR")
	private Long weekYear;

	@Column(name = "PAIR")
	private Long pair;

	@Column(name = "C_PAIR")
	private Long cPair;

	@Column(name = "DIFFERENCE")
	private Integer difference;

	@Column(name = "ARTICLE_CODE")
	private String articleCode;

	@Column(name = "PAIR_AMOUNT")
	private Double pairAmount;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_DATE")
	private Calendar submissionDate;

	public Long getBillRdcId() {
		return billRdcId;
	}

	public void setBillRdcId(Long billRdcId) {
		this.billRdcId = billRdcId;
	}

	public Long getBillpunchFk() {
		return billpunchFk;
	}

	public void setBillpunchFk(Long billpunchFk) {
		this.billpunchFk = billpunchFk;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getShipNo() {
		return shipNo;
	}

	public void setShipNo(String shipNo) {
		this.shipNo = shipNo;
	}

	public String getConsign() {
		return consign;
	}

	public void setConsign(String consign) {
		this.consign = consign;
	}

	public Date getConsignDate() {
		return consignDate;
	}

	public void setConsignDate(Date consignDate) {
		this.consignDate = consignDate;
	}

	public Long getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(Long weekYear) {
		this.weekYear = weekYear;
	}

	public Long getPair() {
		return pair;
	}

	public void setPair(Long pair) {
		this.pair = pair;
	}

	public Long getcPair() {
		return cPair;
	}

	public void setcPair(Long cPair) {
		this.cPair = cPair;
	}

	public Integer getDifference() {
		return difference;
	}

	public void setDifference(Integer difference) {
		this.difference = difference;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public Double getPairAmount() {
		return pairAmount;
	}

	public void setPairAmount(Double pairAmount) {
		this.pairAmount = pairAmount;
	}

	public Calendar getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Calendar submissionDate) {
		this.submissionDate = submissionDate;
	}

}
