package com.bata.billpunch.model.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BillPunchResponseDto {

	private String formtype;

	private String partyCode;

	private String partyName;

	private Date billOrderDate;

	private String billOrderNo;

	private Double purchaseCost;

	private String discountAmt;

	private String tcsPercent;

	private String invdate;

	private String grnDate;

	private String billWeek;

	private String status;

	private String invoiceNO;

	private Double invAmount;

	private String tcsApplicable;

	public String getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(String grnDate) {
		this.grnDate = grnDate;
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Date getBillOrderDate() {
		return billOrderDate;
	}

	public void setBillOrderDate(Date billOrderDate) {
		this.billOrderDate = billOrderDate;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public Double getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}

	public String getInvdate() {
		return invdate;
	}

	public void setInvdate(String invdate) {
		this.invdate = invdate;
	}

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

	public String getBillWeek() {
		return billWeek;
	}

	public void setBillWeek(String billWeek) {
		this.billWeek = billWeek;
	}

	public String getTcsPercent() {
		return tcsPercent;
	}

	public void setTcsPercent(String tcsPercent) {
		this.tcsPercent = tcsPercent;
	}

}
