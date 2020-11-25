package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StrazaPesponseDetailsModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	private Long billId;

	private Integer billWeekYear;

	private String billWeek;

	private String formtype;

	private String packCaseT;

	private String distcode;

	private String stdcode;

	private String artseqNo;

	private String artprevamt;

	private String artamt;

	private String cnNo;
	
	private Date grnDate;

	private String recStatus;

	private String dcCode;

	private String cnDate;

	private String sizeCode;

	private String packCaseB;

	private String packCaseM;

	private String packCaseS;

	private String packCaseC;

	private String trnsportCode;

	private String rdpermitNo;

	private Date recptInvDate;

	private String recptInvNo;

	private String resumeInvNo;

	private String resumeInvNoTwo;

	private String resumeInvDate;

	private String billUniqueCode;

	private Integer billCloseWeek;

	private String billCloseStatus;

	private String partyCode;

	private String partyName;

	private Date billOrderDate;

	private String stateName;

	private String stateCode;

	private String region;

	private String shopName;

	private String shopNo;

	private String billOrderNo;

	private Integer pair;

	private Double purchaseCost;

	private String freight;

	private Double totalCost;

	private Integer igst;

	private Integer cgst;

	private Integer sgst;

	private Integer igstamt;

	private Integer cgstamt;

	private Integer sgstamt;

	private Integer gstamt;

	private Integer cPair;

	private Integer rdcPair;

	private String invoiceNO;

	private String grNo;

	private String grDate;

	private Date receiveDate;

	private Integer weekYear;

	private String articleCode;

	private String articleName;

	private String articleNo;

	private Double pairAmount;

	private String discountAmt;

	private Double creditNote;

	private String rdcPairC;

	private String status;

	private Double invoiceCost;

	private Double tcsValue;

	private Double discountAmtVal;

	private String noOfCartons;

	private String mrp;

	private String stdcost;

	private String tcsApplicable;

	private Calendar createdOn;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Integer getBillWeekYear() {
		return billWeekYear;
	}

	public void setBillWeekYear(Integer billWeekYear) {
		this.billWeekYear = billWeekYear;
	}

	

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public String getPackCaseT() {
		return packCaseT;
	}

	public void setPackCaseT(String packCaseT) {
		this.packCaseT = packCaseT;
	}

	public String getDistcode() {
		return distcode;
	}

	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}

	public String getStdcode() {
		return stdcode;
	}

	public void setStdcode(String stdcode) {
		this.stdcode = stdcode;
	}

	public String getArtseqNo() {
		return artseqNo;
	}

	public void setArtseqNo(String artseqNo) {
		this.artseqNo = artseqNo;
	}

	public String getArtprevamt() {
		return artprevamt;
	}

	public void setArtprevamt(String artprevamt) {
		this.artprevamt = artprevamt;
	}

	public String getArtamt() {
		return artamt;
	}

	public void setArtamt(String artamt) {
		this.artamt = artamt;
	}

	public String getCnNo() {
		return cnNo;
	}

	public void setCnNo(String cnNo) {
		this.cnNo = cnNo;
	}

	public Date getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getDcCode() {
		return dcCode;
	}

	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}

	public String getCnDate() {
		return cnDate;
	}

	public void setCnDate(String cnDate) {
		this.cnDate = cnDate;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getPackCaseB() {
		return packCaseB;
	}

	public void setPackCaseB(String packCaseB) {
		this.packCaseB = packCaseB;
	}

	public String getPackCaseM() {
		return packCaseM;
	}

	public void setPackCaseM(String packCaseM) {
		this.packCaseM = packCaseM;
	}

	public String getPackCaseS() {
		return packCaseS;
	}

	public void setPackCaseS(String packCaseS) {
		this.packCaseS = packCaseS;
	}

	public String getPackCaseC() {
		return packCaseC;
	}

	public void setPackCaseC(String packCaseC) {
		this.packCaseC = packCaseC;
	}

	public String getTrnsportCode() {
		return trnsportCode;
	}

	public void setTrnsportCode(String trnsportCode) {
		this.trnsportCode = trnsportCode;
	}

	public String getRdpermitNo() {
		return rdpermitNo;
	}

	public void setRdpermitNo(String rdpermitNo) {
		this.rdpermitNo = rdpermitNo;
	}

	public Date getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(Date recptInvDate) {
		this.recptInvDate = recptInvDate;
	}

	public String getRecptInvNo() {
		return recptInvNo;
	}

	public void setRecptInvNo(String recptInvNo) {
		this.recptInvNo = recptInvNo;
	}

	public String getResumeInvNo() {
		return resumeInvNo;
	}

	public void setResumeInvNo(String resumeInvNo) {
		this.resumeInvNo = resumeInvNo;
	}

	public String getResumeInvNoTwo() {
		return resumeInvNoTwo;
	}

	public void setResumeInvNoTwo(String resumeInvNoTwo) {
		this.resumeInvNoTwo = resumeInvNoTwo;
	}

	public String getResumeInvDate() {
		return resumeInvDate;
	}

	public void setResumeInvDate(String resumeInvDate) {
		this.resumeInvDate = resumeInvDate;
	}

	public String getBillUniqueCode() {
		return billUniqueCode;
	}

	public void setBillUniqueCode(String billUniqueCode) {
		this.billUniqueCode = billUniqueCode;
	}

	public Integer getBillCloseWeek() {
		return billCloseWeek;
	}

	public void setBillCloseWeek(Integer billCloseWeek) {
		this.billCloseWeek = billCloseWeek;
	}

	public String getBillCloseStatus() {
		return billCloseStatus;
	}

	public void setBillCloseStatus(String billCloseStatus) {
		this.billCloseStatus = billCloseStatus;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public Integer getPair() {
		return pair;
	}

	public void setPair(Integer pair) {
		this.pair = pair;
	}

	public Double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getIgst() {
		return igst;
	}

	public void setIgst(Integer igst) {
		this.igst = igst;
	}

	public Integer getCgst() {
		return cgst;
	}

	public void setCgst(Integer cgst) {
		this.cgst = cgst;
	}

	public Integer getSgst() {
		return sgst;
	}

	public void setSgst(Integer sgst) {
		this.sgst = sgst;
	}

	public Integer getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(Integer igstamt) {
		this.igstamt = igstamt;
	}

	public Integer getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(Integer cgstamt) {
		this.cgstamt = cgstamt;
	}

	public Integer getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(Integer sgstamt) {
		this.sgstamt = sgstamt;
	}

	public Integer getGstamt() {
		return gstamt;
	}

	public void setGstamt(Integer gstamt) {
		this.gstamt = gstamt;
	}

	public Integer getcPair() {
		return cPair;
	}

	public void setcPair(Integer cPair) {
		this.cPair = cPair;
	}

	public Integer getRdcPair() {
		return rdcPair;
	}

	public void setRdcPair(Integer rdcPair) {
		this.rdcPair = rdcPair;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public String getGrDate() {
		return grDate;
	}

	public void setGrDate(String grDate) {
		this.grDate = grDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(Integer weekYear) {
		this.weekYear = weekYear;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public Double getPairAmount() {
		return pairAmount;
	}

	public void setPairAmount(Double pairAmount) {
		this.pairAmount = pairAmount;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Double getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(Double creditNote) {
		this.creditNote = creditNote;
	}

	public String getRdcPairC() {
		return rdcPairC;
	}

	public void setRdcPairC(String rdcPairC) {
		this.rdcPairC = rdcPairC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public Double getTcsValue() {
		return tcsValue;
	}

	public void setTcsValue(Double tcsValue) {
		this.tcsValue = tcsValue;
	}

	public Double getDiscountAmtVal() {
		return discountAmtVal;
	}

	public void setDiscountAmtVal(Double discountAmtVal) {
		this.discountAmtVal = discountAmtVal;
	}

	public String getNoOfCartons() {
		return noOfCartons;
	}

	public void setNoOfCartons(String noOfCartons) {
		this.noOfCartons = noOfCartons;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getStdcost() {
		return stdcost;
	}

	public void setStdcost(String stdcost) {
		this.stdcost = stdcost;
	}

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getBillWeek() {
		return billWeek;
	}

	public void setBillWeek(String billWeek) {
		this.billWeek = billWeek;
	}

	

}
