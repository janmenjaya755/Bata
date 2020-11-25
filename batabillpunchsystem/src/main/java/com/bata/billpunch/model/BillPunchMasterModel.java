package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchMasterModel")
@Table(name = "TT_BILL_PUNCH_MASTER")
public class BillPunchMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "BILLM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billms_Sequence")
	@SequenceGenerator(name = "billms_Sequence", sequenceName = "BILLMASTER_SEQ",allocationSize = 1)
	private Long billId;

	@Column(name = "TRAN_CODE")
	private String tranCode;

	@Column(name = "INV_NO")
	private String invNo;

	@Column(name = "ART_CODE")
	private String artCode;

	@Column(name = "SIZE_CODE")
	private String sizeCode;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "WEEK")
	private String week;

	@Column(name = "DEST_SHOP_NO")
	private String destshopno;

	@Column(name = "DIST_CODE")
	private String distcode;

	@Column(name = "INV_TYPE")
	private String invtype;

	@Column(name = "NO_PAIRS")
	private String nopairs;

	@Column(name = "TOT_PAIRS")
	private String totpairs;
	
	@Column(name = "PACKING_CASES_B")
	private String packingcasesb;

	@Column(name = "PACKING_CASES_M")
	private String packingcasesm;

	@Column(name = "PACKING_CASES_S")
	private String packingcasess;

	@Column(name = "PACKING_CASES_C")
	private String packingcasesc;

	@Column(name = "PACKING_CASES_T")
	private String packingcasest;
	
	
	@Column(name = "DC_CODE")
	private String dccode;
	
	@Column(name = "REC_STAT")
	private String recstat;

	@Column(name = "SEQ_NO")
	private String seqno;

	@Column(name = "PARTY_CODE")
	private String partycode;

	@Column(name = "FILLER_1")
	private String fillerone;

	@Column(name = "DEL_TAG")
	private String deltag;
	
	
	@Column(name = "CN_NO")
	private String cnno;
	
	@Column(name = "CN_DATE")
	private Date cndate;

	@Column(name = "TRNSPRT_CODE")
	private String trnsprtcode;

	@Column(name = "VEHICLE_NO")
	private String vehicleno;

	@Column(name = "RD_PERMIT_NO")
	private String rdpermitno;

	@Column(name = "PARTY_STATE")
	private String partystate;
	
	
	@Column(name = "PRCH_BIL_VAL")
	private String prchbilval;
	
	@Column(name = "RCPT_INV_NO")
	private String rcptinvno ;

	@Column(name = "RCPT_INV_DATE")
	private Date rcptinvdate;

	@Column(name = "RESUP_INVNO")
	private String resupinvno;

	@Column(name = "RESUP_INV_DATE")
	private String resupinvdate;

	@Column(name = "RD_PERMIT_NO_2")
	private String rdpermitnotwo;
	
	
	@Column(name = "ORD_NO")
	private String ordno;
	
	@Column(name = "HSN_CODE")
	private String hsncode ;

	@Column(name = "GST_AMT")
	private String gstamt;

	@Column(name = "CGST_RT")
	private String cgstrt;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_RT")
	private String sgstrt;
	
	@Column(name = "SGST_AMT")
	private String sgstamt;
	
	@Column(name = "IGST_RT")
	private String igstrt ;

	@Column(name = "IGST_AMT")
	private String igstamt;

	@Column(name = "TRANS_VAL")
	private String transval;

	@Column(name = "FROM_STATE")
	private String fromstate;

	@Column(name = "TO_STATE")
	private String tostate;
	
	@Column(name = "WTR_INV_PRN_DATE")
	private Date wtrinvprndate;
	
	@Column(name = "STATUS")
	private String status;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getArtCode() {
		return artCode;
	}

	public void setArtCode(String artCode) {
		this.artCode = artCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDestshopno() {
		return destshopno;
	}

	public void setDestshopno(String destshopno) {
		this.destshopno = destshopno;
	}

	public String getDistcode() {
		return distcode;
	}

	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}

	public String getInvtype() {
		return invtype;
	}

	public void setInvtype(String invtype) {
		this.invtype = invtype;
	}

	public String getNopairs() {
		return nopairs;
	}

	public void setNopairs(String nopairs) {
		this.nopairs = nopairs;
	}

	public String getTotpairs() {
		return totpairs;
	}

	public void setTotpairs(String totpairs) {
		this.totpairs = totpairs;
	}

	public String getPackingcasesb() {
		return packingcasesb;
	}

	public void setPackingcasesb(String packingcasesb) {
		this.packingcasesb = packingcasesb;
	}

	public String getPackingcasesm() {
		return packingcasesm;
	}

	public void setPackingcasesm(String packingcasesm) {
		this.packingcasesm = packingcasesm;
	}

	public String getPackingcasess() {
		return packingcasess;
	}

	public void setPackingcasess(String packingcasess) {
		this.packingcasess = packingcasess;
	}

	public String getPackingcasesc() {
		return packingcasesc;
	}

	public void setPackingcasesc(String packingcasesc) {
		this.packingcasesc = packingcasesc;
	}

	public String getPackingcasest() {
		return packingcasest;
	}

	public void setPackingcasest(String packingcasest) {
		this.packingcasest = packingcasest;
	}

	public String getDccode() {
		return dccode;
	}

	public void setDccode(String dccode) {
		this.dccode = dccode;
	}

	public String getRecstat() {
		return recstat;
	}

	public void setRecstat(String recstat) {
		this.recstat = recstat;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getPartycode() {
		return partycode;
	}

	public void setPartycode(String partycode) {
		this.partycode = partycode;
	}

	public String getFillerone() {
		return fillerone;
	}

	public void setFillerone(String fillerone) {
		this.fillerone = fillerone;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getCnno() {
		return cnno;
	}

	public void setCnno(String cnno) {
		this.cnno = cnno;
	}

	public Date getCndate() {
		return cndate;
	}

	public void setCndate(Date cndate) {
		this.cndate = cndate;
	}

	public String getTrnsprtcode() {
		return trnsprtcode;
	}

	public void setTrnsprtcode(String trnsprtcode) {
		this.trnsprtcode = trnsprtcode;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getRdpermitno() {
		return rdpermitno;
	}

	public void setRdpermitno(String rdpermitno) {
		this.rdpermitno = rdpermitno;
	}

	public String getPartystate() {
		return partystate;
	}

	public void setPartystate(String partystate) {
		this.partystate = partystate;
	}

	public String getPrchbilval() {
		return prchbilval;
	}

	public void setPrchbilval(String prchbilval) {
		this.prchbilval = prchbilval;
	}

	public String getRcptinvno() {
		return rcptinvno;
	}

	public void setRcptinvno(String rcptinvno) {
		this.rcptinvno = rcptinvno;
	}

	public Date getRcptinvdate() {
		return rcptinvdate;
	}

	public void setRcptinvdate(Date rcptinvdate) {
		this.rcptinvdate = rcptinvdate;
	}

	public String getResupinvno() {
		return resupinvno;
	}

	public void setResupinvno(String resupinvno) {
		this.resupinvno = resupinvno;
	}

	public String getResupinvdate() {
		return resupinvdate;
	}

	public void setResupinvdate(String resupinvdate) {
		this.resupinvdate = resupinvdate;
	}

	public String getRdpermitnotwo() {
		return rdpermitnotwo;
	}

	public void setRdpermitnotwo(String rdpermitnotwo) {
		this.rdpermitnotwo = rdpermitnotwo;
	}

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getGstamt() {
		return gstamt;
	}

	public void setGstamt(String gstamt) {
		this.gstamt = gstamt;
	}

	public String getCgstrt() {
		return cgstrt;
	}

	public void setCgstrt(String cgstrt) {
		this.cgstrt = cgstrt;
	}

	public String getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(String cgstamt) {
		this.cgstamt = cgstamt;
	}

	public String getSgstrt() {
		return sgstrt;
	}

	public void setSgstrt(String sgstrt) {
		this.sgstrt = sgstrt;
	}

	public String getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(String sgstamt) {
		this.sgstamt = sgstamt;
	}

	public String getIgstrt() {
		return igstrt;
	}

	public void setIgstrt(String igstrt) {
		this.igstrt = igstrt;
	}

	public String getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(String igstamt) {
		this.igstamt = igstamt;
	}

	public String getTransval() {
		return transval;
	}

	public void setTransval(String transval) {
		this.transval = transval;
	}

	public String getFromstate() {
		return fromstate;
	}

	public void setFromstate(String fromstate) {
		this.fromstate = fromstate;
	}

	public String getTostate() {
		return tostate;
	}

	public void setTostate(String tostate) {
		this.tostate = tostate;
	}

	public Date getWtrinvprndate() {
		return wtrinvprndate;
	}

	public void setWtrinvprndate(Date wtrinvprndate) {
		this.wtrinvprndate = wtrinvprndate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
