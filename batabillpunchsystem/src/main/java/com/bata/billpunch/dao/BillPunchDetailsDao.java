package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseStatusInterface;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PurchaseCostInterface;
import com.bata.billpunch.model.dto.TotalAmtInterface;

@Repository
public interface BillPunchDetailsDao extends JpaRepository<BillPunchDetailsModel, Long> {

	public void save(List<BillPunchDetailsModel> mn);
	
	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a ")
	public List<BillPunchDetailsModel> findWithAll();

	@Modifying
	@Query(nativeQuery = true, value = "TRUNCATE TABLE TT_BILL_PUNCH_DTLS_ONE")
	void findWithDeleteAll();
	
	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where a.WK like ?1 and a.STATUS='APPROVED'")
	public List<BillPunchDetailsModel> findWithAllApproved(String wk);

	@Query(nativeQuery = true, value = "select x.tcsPercent,x.status,x.pair,x.billWeek,x.cnDate,x.tcsApplicable,x.discountAmt,x.rdcAmount,x.invoiceNO,x.billOrderNo,x.formtype,x.partyCode,x.partyName,x.billOrderDate, sum(x.purchasePrice*x.pair)as purchaseCost from (select a.TCS_PERCENT as tcsPercent , a.STATUS as status,a.ART_CODE as artCode,a.TOT_PAIRS as pair,a.WK as billWeek,a.CN_DATE as cnDate,a.TCS_APPLICABLE as tcsApplicable, a.DISCOUNT_AMT as discountAmt ,a.PRCH_BIL_VAL as rdcAmount ,a.TR_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.form_type as formtype, a.PARTY_CODE as partyCode,a.party_name as partyName,a.bill_order_date as billOrderDate ,a.RDC_CODE as rdcCode,c.PURPRICE as purchasePrice from  TT_BILL_PUNCH_DTLS_ONE a  join tm_orders_master_dtls c on a.ORD_NO=c.ORDERNO and a.PARTY_CODE=c.PARTY_CODE and a.RDC_CODE=c.RDCNO and a.ART_CODE=c.ARTNO where (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) and a.FORM_TYPE is null group by  a.TCS_PERCENT,a.PRCH_BIL_VAL,a.TR_INV_NO,a.ORD_NO,a.form_type,a.PARTY_CODE,a.party_name,a.status,a.bill_order_date,a.DISCOUNT_AMT,a.TCS_APPLICABLE,a.CN_DATE,a.WK,a.TOT_PAIRS ,a.ART_CODE,a.RDC_CODE,c.PURPRICE)x group by x.pair,x.billWeek,x.cnDate,x.tcsApplicable,x.discountAmt,x.rdcAmount,x.invoiceNO,x.billOrderNo,x.formtype,x.partyCode,x.partyName,x.billOrderDate,x.status,x.tcsPercent")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoTest(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);
	
	
	@Query(nativeQuery = true, value = "select a.TCS_PERCENT as tcsPercent,a.STATUS as status,a.PURCHASE_COST as purchaseoffValue,a.TOT_PAIRS as pair,a.WK as billWeek,a.CN_DATE as cnDate,a.TCS_APPLICABLE as tcsApplicable, a.RCPT_INV_DATE as invdate , a.DISCOUNT_AMT as discountAmt ,a.PRCH_BIL_VAL as rdcAmount ,a.TR_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.form_type as formtype, a.PARTY_CODE as partyCode,a.party_name as partyName,a.bill_order_date as billOrderDate  from  TT_BILL_PUNCH_DTLS_ONE a  where (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) group by a.TCS_PERCENT, a.PURCHASE_COST,a.PRCH_BIL_VAL,a.TR_INV_NO,a.ORD_NO,a.form_type,a.PARTY_CODE,a.party_name,a.status,a.bill_order_date,a.DISCOUNT_AMT, a.RCPT_INV_DATE,a.TCS_APPLICABLE,a.CN_DATE,a.WK,a.TOT_PAIRS")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoManual(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)")
	public List<BillPunchDetailsModel> findWithBillNoPartyCodeAndOrderNo(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	
	@Query(nativeQuery = true, value = "select sum(x.purchasePrice*x.pair)as purchaseCost from (select c.PURPRICE as purchasePrice,a.TOT_PAIRS as pair from  TT_BILL_PUNCH_DTLS_ONE a  join tm_orders_master_dtls c on a.ORD_NO=c.ORDERNO and a.PARTY_CODE=c.PARTY_CODE and a.RDC_CODE=c.RDCNO and a.ART_CODE=c.ARTNO where (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)  group by  a.ORD_NO,a.PARTY_CODE,a.TOT_PAIRS ,a.ART_CODE,a.RDC_CODE,c.PURPRICE)x")
	public PurchaseCostInterface findWithPurchaseCost(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "SELECT * FROM TT_BILL_PUNCH_DTLS_ONE where rownum <=1 order by BIL_ID desc ")
	public BillPunchDetailsModel findwithLastBillEntry();

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.SEQ_NO like ?1")
	public List<BillPunchDetailsModel> findwithAllDetailsByUniqueId(String uniqueId);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.TR_INV_NO like ?1")
	public List<BillPunchDetailsModel> findwithInvNo(String invno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO like ?1")
	public List<BillPunchDetailsModel> findwithAllDetailsByOrdNo(String ordno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO like ?1 and a.TR_INV_NO like ?2")
	public BillPunchDetailsModel findwithAllDetailsByOrderAndInvoice(String orderno, String invno);
	
	@Query(nativeQuery = true, value = "SELECT distinct a.FORM_TYPE as formType FROM TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO like ?1 and a.TR_INV_NO like ?2 and a.PARTY_CODE like ?3 ")
	public  List<BillPurchaseStatusInterface> findwithOrderNo(String orderno,String invno,String partycode);
	
	@Query(nativeQuery = true, value = "SELECT distinct  a.PARTY_CODE as partycode,a.PARTY_NAME as partyname FROM TT_BILL_PUNCH_DTLS_ONE a ")
	public List<PartyResponseDto> findAllPartycodeAndPartyName();

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.PARTY like ?1 and a.PARTY_NAME like ?2")
	public List<BillPunchDetailsModel> findAllPartycodeAndPartyNameDetails(String partycode, String partyname);

	@Query(nativeQuery = true, value = "SELECT a.ORD_NO as billOrderNo,a.PRCH_BIL_VAL as purchaseCost,a.BIL_ID as billId,a.TR_INV_NO as invoiceNO,a.WK as billCloseWeek ,a.STATUS as status FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED' and a.WK like ?1")
	public List<BillCloseStatusDto> findWithApprovedRecords(String billcloseweek);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.STATUS in('SUBMITTED','APPROVED')")
	public List<BillPunchDetailsModel> finfWithStatus();

	@Query(nativeQuery = true, value = "SELECT distinct a.WK as billCloseWeek FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED' order by  a.WK DESC ")
	public List<BillCloseStatusDto> findWithBillCloseWeek();

	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and a.WK >=:fromwk and a.WK <=:towk and (COALESCE(:yr, null) is null or a.YR in :yr)")
	public List<BillPunchDetailsModel> findWithDetailsStrazaReport(@Param("partycode") List<String> partycode,
			@Param("fromwk") String fromwk, @Param("towk") String towk, @Param("yr") String yr);

	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:yr, null) is null or a.YR in :yr)")
	public List<BillPunchDetailsModel> findWithDetailsStrazaReportforAllWK(@Param("partycode") List<String> partycode,
			@Param("yr") String yr);
	
	//@Query(nativeQuery = true, value = "SELECT distinct a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where a.C_WEEK like ?1 and a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT distinct a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where  a.WK like ?1 and and a.STATUS='APPROVED'")
	public List<BillPunchDetailsModel> findWithBillReportByWeek(String wk);
	
	//@Query(nativeQuery = true, value = "SELECT  a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT SUM(SQ.COST)as totalcost FROM (SELECT DISTINCT a.TR_INV_NO as TR_INV_NO,a.INVOICE_COST as COST FROM TT_BILL_PUNCH_DTLS_ONE a where a.WK like ?1 and a.STATUS='APPROVED' ) SQ")
	public TotalAmtInterface findWithTotalAmt(String wk);
	
	 //@Query(nativeQuery = true, value = "SELECT a.TR_INV_NO as invno,a.INVOICE_COST as invcost, a.INV_TYPE as invtype FROM TT_BILL_PUNCH_DTLS_ONE a where a.STATUS='APPROVED' group by a.TR_INV_NO,a.INVOICE_COST, a.INV_TYPE")
		@Query(nativeQuery = true, value = "SELECT a.RDC_CODE as rdcno,a.PARTY as party,a.DISTRC_CODE as distcode,a.ORD_NO as ordno,a.TR_INV_NO as invno,a.INVOICE_COST as invcost, a.INV_TYPE as invtype,a.RCPT_INV_DATE as invdate,a.CN_DATE as grndate,a.TOT_PAIRS as pairs FROM TT_BILL_PUNCH_DTLS_ONE a where a.WK like ?1 and  a.TR_INV_NO is not null and a.STATUS='APPROVED' group by a.TR_INV_NO,a.INVOICE_COST, a.INV_TYPE,a.RCPT_INV_DATE,a.CN_DATE,a.TOT_PAIRS,a.PARTY ,a.DISTRC_CODE,a.ORD_NO,a.RDC_CODE")
	    public List<AdonisFileDetailsInterface> findWithAdonisFileDetails(String wk);

}
