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
@Entity(name = "BillPunchDetailsModelTestOne")
@Table(name = "TT_BILL_PUNCH_DTLS_TEST_ONE")
public class BillPunchDetailsModelTestOne implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "BL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bls_Sequence")
	@SequenceGenerator(name = "bls_Sequence", sequenceName = "BLS_SEQ", allocationSize = 1)
	private Long billId;

	@Column(name = "YEAR")
	private String billWeekYear;

	@Column(name = "WEEK")
	private String billWeek;

	@Column(name = "FORM_TYPE")
	private String formtype;

	@Column(name = "PACKING_CASES_T")
	private String packCaseT;

	@Column(name = "DIST_CODE")
	private String distcode;

	@Column(name = "STD_CODE")
	private String stdcode;

	@Column(name = "ART_SEQUENCE_NO")
	private String artseqNo;

	@Column(name = "ART_PRICE_PREV")
	private String artprevamt;

	@Column(name = "ART_PRICE_MRP")
	private String artamt;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "REC_STAT")
	private String recStatus;

	@Column(name = "DC_CODE")
	private String dcCode;

	@Column(name = "CN_DATE")
	private String cnDate;

	@Column(name = "SIZE_CODE")
	private String sizeCode;

	@Column(name = "PACKING_CASES_B")
	private String packCaseB;

	@Column(name = "PACKING_CASES_M")
	private String packCaseM;

	@Column(name = "PACKING_CASES_S")
	private String packCaseS;

	@Column(name = "PACKING_CASES_C")
	private String packCaseC;

	@Column(name = "TRNSPRT_CODE")
	private String trnsportCode;

	@Column(name = "RD_PERMIT_NO")
	private String rdpermitNo;

	@Column(name = "RCPT_INV_DATE")
	private String recptInvDate;

	@Column(name = "RCPT_INV_NO")
	private String recptInvNo;

	@Column(name = "RESUP_INVNO")
	private String resumeInvNo;

	@Column(name = "RD_PERMIT_NO_2")
	private String resumeInvNoTwo;

	@Column(name = "RESUP_INV_DATE")
	private String resumeInvDate;

	@Column(name = "SEQ_NO")
	private String billUniqueCode;

	@Column(name = "BILL_CLOSE_WEEK")
	private String billCloseWeek;

	@Column(name = "BILL_CLOSE_STATUS")
	private String billCloseStatus;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "BILL_ORDER_DATE")
	private Date billOrderDate;

	@Column(name = "STATE_NAME")
	private String stateName;

	@Column(name = "STATE_CODE")
	private String stateCode;

	@Column(name = "REGION")
	private String region;

	@Column(name = "SHOP_NAME")
	private String shopName;

	@Column(name = "DEST_SHOP_NO")
	private String shopNo;

	@Column(name = "ORD_NO")
	private String billOrderNo;

	@Column(name = "TOT_PAIRS")
	private String pair;

	@Column(name = "PURCHASE_COST")
	private Double purchaseCost;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "TOTAL_COST")
	private Double totalCost;

	@Column(name = "IGST_RT")
	private Integer igst;

	@Column(name = "CGST_RT")
	private Integer cgst;

	@Column(name = "SGST_RT")
	private Integer sgst;

	@Column(name = "IGST_AMT")
	private Integer igstamt;

	@Column(name = "CGST_AMT")
	private Integer cgstamt;

	@Column(name = "SGST_AMT")
	private Integer sgstamt;

	@Column(name = "GST_AMT")
	private Integer gstamt;

	@Column(name = "C_PAIR")
	private String cPair;

	@Column(name = "NO_PAIRS")
	private String rdcPair;

	@Column(name = "INV_NO")
	private String invoiceNO;

	@Column(name = "GRNO")
	private String grNo;

	@Column(name = "GR_DATE")
	private String grDate;

	@Column(name = "RECEIVE_DATE")
	private Date receiveDate;

	@Column(name = "WEEK_YEAR")
	private String weekYear;

	@Column(name = "ART_CODE")
	private String articleCode;

	@Column(name = "ARTICLE_NAME")
	private String articleName;

	@Column(name = "ARTICLE_NO")
	private String articleNo;

	@Column(name = "TRANS_VAL")
	private Double pairAmount;

	@Column(name = "DISCOUNT_AMT")
	private String discountAmt;

	@Column(name = "CREDIT_NOTE")
	private Double creditNote;

	@Column(name = "RDC_PAIR_C ")
	private String rdcPairC;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "INVOICE_COST")
	private Double invoiceCost;

	@Column(name = "TCS_VALUE ")
	private Double tcsValue;

	@Column(name = "DIS_AMT_VAL")
	private Double discountAmtVal;

	@Column(name = "NO_OF_CARTONS")
	private String noOfCartons;

	@Column(name = "TCS_APPLICABLE")
	private String tcsApplicable;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getBillWeekYear() {
		return billWeekYear;
	}

	public void setBillWeekYear(String billWeekYear) {
		this.billWeekYear = billWeekYear;
	}

	public String getBillWeek() {
		return billWeek;
	}

	public void setBillWeek(String billWeek) {
		this.billWeek = billWeek;
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

	public String getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(String recptInvDate) {
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

	public String getBillCloseWeek() {
		return billCloseWeek;
	}

	public void setBillCloseWeek(String billCloseWeek) {
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

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
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

	public String getcPair() {
		return cPair;
	}

	public void setcPair(String cPair) {
		this.cPair = cPair;
	}

	public String getRdcPair() {
		return rdcPair;
	}

	public void setRdcPair(String rdcPair) {
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

	public String getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(String weekYear) {
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

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	/*
	 * public List<ApprovalDetailsModel> getApprovalList() { return approvalList; }
	 * 
	 * public void setApprovalList(List<ApprovalDetailsModel> approvalList) {
	 * this.approvalList = approvalList; }
	 */

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

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

}
