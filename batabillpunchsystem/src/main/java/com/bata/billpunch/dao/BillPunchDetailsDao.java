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
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.TotalAmtInterface;

@Repository
public interface BillPunchDetailsDao extends JpaRepository<BillPunchDetailsModel, Long> {

	public void save(List<BillPunchDetailsModel> mn);

	@Modifying
	@Query(nativeQuery = true, value = "TRUNCATE TABLE TT_BILL_PUNCH_DTLS_ONE")
	void findWithDeleteAll();
	
	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO='F0D1834'")
	public List<BillPunchDetailsModel> findWithAllApproved();

	@Query(nativeQuery = true, value = "select a.WK as billWeek,a.CN_DATE as cnDate,a.TCS_APPLICABLE as tcsApplicable,a.RCPT_INV_DATE as invdate ,a.DISCOUNT_AMT as discountAmt ,a.PRCH_BIL_VAL as rdcAmount ,a.TR_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.form_type as formtype,a.PARTY_CODE as partyCode,a.party_name as partyName,a.status,a.bill_order_date as billOrderDate from  TT_BILL_PUNCH_DTLS_ONE a where  (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) group by a.INVOICE_COST,a.PRCH_BIL_VAL,a.TR_INV_NO,a.ORD_NO,a.form_type,a.PARTY_CODE,a.party_name,a.status,a.bill_order_date,a.DISCOUNT_AMT,a.RCPT_INV_DATE,a.TCS_APPLICABLE,a.CN_DATE,a.WK")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoTest(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "select  a.* from  TT_BILL_PUNCH_DTLS_ONE a where  (COALESCE(:invoiceNO, null) is null or a.TR_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) ")
	public List<BillPunchDetailsModel> findWithBillNoPartyCodeAndOrderNo(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "SELECT * FROM TT_BILL_PUNCH_DTLS_ONE where rownum <=1 order by BIL_ID desc ")
	public BillPunchDetailsModel findwithLastBillEntry();

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.SEQ_NO like ?1")
	public List<BillPunchDetailsModel> findwithAllDetailsByUniqueId(String uniqueId);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO like ?1")
	public List<BillPunchDetailsModel> findwithAllDetailsByOrdNo(String ordno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.ORD_NO like ?1 and a.TR_INV_NO like ?2")
	public BillPunchDetailsModel findwithAllDetailsByOrderAndInvoice(String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT distinct  a.PARTY_CODE as partycode,a.PARTY_NAME as partyname FROM TT_BILL_PUNCH_DTLS_ONE a ")
	public List<PartyResponseDto> findAllPartycodeAndPartyName();

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.PARTY like ?1 and a.PARTY_NAME like ?2")
	public List<BillPunchDetailsModel> findAllPartycodeAndPartyNameDetails(String partycode, String partyname);

	@Query(nativeQuery = true, value = "SELECT a.ORD_NO as billOrderNo,a.PRCH_BIL_VAL as purchaseCost,a.BIL_ID as billId,a.TR_INV_NO as invoiceNO,a.C_WEEK as billCloseWeek ,a.STATUS as status FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED' and a.C_WEEK like ?1")
	public List<BillCloseStatusDto> findWithApprovedRecords(String billcloseweek);

	@Query(nativeQuery = true, value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_ONE a where a.STATUS in('SUBMITTED','APPROVED')")
	public List<BillPunchDetailsModel> finfWithStatus();

	@Query(nativeQuery = true, value = "SELECT distinct a.C_WEEK as billCloseWeek FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED' order by  a.C_WEEK DESC ")
	public List<BillCloseStatusDto> findWithBillCloseWeek();

	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and a.WK >=:fromwk and a.WK <=:towk and (COALESCE(:yr, null) is null or a.YR in :yr)")
	public List<BillPunchDetailsModel> findWithDetailsStrazaReport(@Param("partycode") List<String> partycode,
			@Param("fromwk") String fromwk, @Param("towk") String towk, @Param("yr") String yr);

	@Query(nativeQuery = true, value = "select a.* from  TT_BILL_PUNCH_DTLS_ONE a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:yr, null) is null or a.YR in :yr)")
	public List<BillPunchDetailsModel> findWithDetailsStrazaReportforAllWK(@Param("partycode") List<String> partycode,
			@Param("yr") String yr);
	
	//@Query(nativeQuery = true, value = "SELECT distinct a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where a.C_WEEK like ?1 and a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT distinct a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where  a.ORD_NO='F0D1834'")
	public List<BillPunchDetailsModel> findWithBillReportByWeek(String Week);
	
	//@Query(nativeQuery = true, value = "SELECT  a.* FROM TT_BILL_PUNCH_DTLS_ONE a  where a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT SUM(SQ.COST)as totalcost FROM (SELECT DISTINCT a.TR_INV_NO as TR_INV_NO,a.INVOICE_COST as COST FROM TT_BILL_PUNCH_DTLS_ONE a) SQ")
	public TotalAmtInterface findWithTotalAmt();
	
	 //@Query(nativeQuery = true, value = "SELECT a.TR_INV_NO as invno,a.INVOICE_COST as invcost, a.INV_TYPE as invtype FROM TT_BILL_PUNCH_DTLS_ONE a where a.STATUS='APPROVED' group by a.TR_INV_NO,a.INVOICE_COST, a.INV_TYPE")
		@Query(nativeQuery = true, value = "SELECT a.RDC_CODE as rdcno,a.PARTY as party,a.DISTRC_CODE as distcode,a.ORD_NO as ordno,a.TR_INV_NO as invno,a.INVOICE_COST as invcost, a.INV_TYPE as invtype,a.RCPT_INV_DATE as invdate,a.CN_DATE as grndate,a.TOT_PAIRS as pairs FROM TT_BILL_PUNCH_DTLS_ONE a where a.TR_INV_NO is not null and a.ORD_NO='F0D1834' group by a.TR_INV_NO,a.INVOICE_COST, a.INV_TYPE,a.RCPT_INV_DATE,a.CN_DATE,a.TOT_PAIRS,a.PARTY ,a.DISTRC_CODE,a.ORD_NO,a.RDC_CODE")
	    public List<AdonisFileDetailsInterface> findWithAdonisFileDetails();

}
