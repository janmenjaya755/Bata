package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchDetailsModelTest")
@Table(name = "TT_BILL_PUNCH_DTLS")
public class BillPunchDetailsModelTest implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "BILL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bills_Sequence")
	@SequenceGenerator(name = "bills_Sequence", sequenceName = "BILLPUNCH_SEQ",allocationSize = 1)
	private Long billId;

	@Column(name = "BILL_WEEK_YEAR")
	private String billWeekYear;

	@Column(name = "BILL_WEEK")
	private String billWeek;
	
	@Column(name = "FORM_TYPE")
	private String formtype;
	
	@Column(name = "PACKING_CASES_T")
	private String packCaseT;
	
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
	
	
	@Column(name = "BILL_UNIQUE_CODE")
	private String billUniqueCode;
	
	@Column(name = "BILL_CLOSE_WEEK")
	private Integer billCloseWeek;

	@Column(name = "BILL_CLOSE_STATUS")
	private String billCloseStatus;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "BILL_NO")
	private String billNo;

	@Column(name = "BILL_DATE")
	private Date billDate;

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
	
	@Column(name = "SHOP_NO")
	private String shopNo;

	@Column(name = "BILL_ORDER_NO")
	private String billOrderNo;

	@Column(name = "PAIR")
	private String pair;

	@Column(name = "PURCHASE_COST")
	private String purchaseCost;

	@Column(name = "SALE_TAX")
	private String saleTax;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "TOTAL_COST")
	private String totalCost;

	@Column(name = "SCH")
	private String sch;

	@Column(name = "PST")
	private String pst;

	@Column(name = "CCST")
	private String ccst;
	
	@Column(name = "IGST")
	private String igst;

	@Column(name = "CGST")
	private String cgst;

	@Column(name = "SGST")
	private String sgst;

	@Column(name = "C_PAIR")
	private String cPair;

	@Column(name = "C_PAIR_COST")
	private String cPairCost;
	
	@Column(name = "RDC_PAIR")
	private String rdcPair;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	@Column(name = "INVOICE_NO")
	private String invoiceNO;

	@Column(name = "SHIP_NO")
	private String shipNo;

	@Column(name = "CONSIGN")
	private String consign;

	@Column(name = "CONSIGN_DATE")
	private Date consignDate;
	
	@Column(name = "GRNO")
	private String grNo;

	@Column(name = "GR_DATE")
	private Date grDate;
	
	@Column(name = "RECEIVE_DATE")
	private Date receiveDate;

	@Column(name = "WEEK_YEAR")
	private String weekYear;

	@Column(name = "DIFFERENCE")
	private Integer difference;

	@Column(name = "ARTICLE_CODE")
	private String articleCode;
	
	@Column(name = "ARTICLE_NAME")
	private String articleName;
	
	@Column(name = "ARTICLE_NO")
	private String articleNo;
	
	@Column(name = "PAIR_AMOUNT")
	private Double pairAmount;

	

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "BILL_RDC_ID_FK") private List<BillPunchRdcDetailsModel>
	 * rdcList;
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "BILL_ID_FK")
	private List<ApprovalDetailsModel> approvalList;



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



	public String getBillNo() {
		return billNo;
	}



	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}



	public Date getBillDate() {
		return billDate;
	}



	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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



	public String getPurchaseCost() {
		return purchaseCost;
	}



	public void setPurchaseCost(String purchaseCost) {
		this.purchaseCost = purchaseCost;
	}



	public String getSaleTax() {
		return saleTax;
	}



	public void setSaleTax(String saleTax) {
		this.saleTax = saleTax;
	}



	public String getFreight() {
		return freight;
	}



	public void setFreight(String freight) {
		this.freight = freight;
	}



	public String getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}



	public String getSch() {
		return sch;
	}



	public void setSch(String sch) {
		this.sch = sch;
	}



	public String getPst() {
		return pst;
	}



	public void setPst(String pst) {
		this.pst = pst;
	}



	public String getCcst() {
		return ccst;
	}



	public void setCcst(String ccst) {
		this.ccst = ccst;
	}



	public String getcPair() {
		return cPair;
	}



	public void setcPair(String cPair) {
		this.cPair = cPair;
	}



	public String getcPairCost() {
		return cPairCost;
	}



	public void setcPairCost(String cPairCost) {
		this.cPairCost = cPairCost;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Calendar getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
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



	public String getGrNo() {
		return grNo;
	}



	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}



	public Date getGrDate() {
		return grDate;
	}



	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}



	public Date getReceiveDate() {
		return receiveDate;
	}



	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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



	public List<ApprovalDetailsModel> getApprovalList() {
		return approvalList;
	}



	public void setApprovalList(List<ApprovalDetailsModel> approvalList) {
		this.approvalList = approvalList;
	}



	public String getWeekYear() {
		return weekYear;
	}



	public void setWeekYear(String weekYear) {
		this.weekYear = weekYear;
	}



	public String getIgst() {
		return igst;
	}



	public void setIgst(String igst) {
		this.igst = igst;
	}



	public String getCgst() {
		return cgst;
	}



	public void setCgst(String cgst) {
		this.cgst = cgst;
	}



	public String getSgst() {
		return sgst;
	}



	public void setSgst(String sgst) {
		this.sgst = sgst;
	}



	public String getFormtype() {
		return formtype;
	}



	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}



	public String getRdcPair() {
		return rdcPair;
	}



	public void setRdcPair(String rdcPair) {
		this.rdcPair = rdcPair;
	}



	public String getPackCaseT() {
		return packCaseT;
	}



	public void setPackCaseT(String packCaseT) {
		this.packCaseT = packCaseT;
	}



	public String getCnNo() {
		return cnNo;
	}



	public void setCnNo(String cnNo) {
		this.cnNo = cnNo;
	}



	public String getCnDate() {
		return cnDate;
	}



	public void setCnDate(String cnDate) {
		this.cnDate = cnDate;
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



	public String getDcCode() {
		return dcCode;
	}



	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}



	public String getRecStatus() {
		return recStatus;
	}



	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}



	public String getRecptInvNo() {
		return recptInvNo;
	}



	public void setRecptInvNo(String recptInvNo) {
		this.recptInvNo = recptInvNo;
	}



}
