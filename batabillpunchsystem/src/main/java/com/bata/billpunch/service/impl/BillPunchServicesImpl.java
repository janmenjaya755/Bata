package com.bata.billpunch.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.ApprovalDetailsDao;
import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.dao.BillPunchDetailsDemoDao;
import com.bata.billpunch.dao.BillPunchMasterDao;
import com.bata.billpunch.dao.BillPunchRdcDetailsDao;
import com.bata.billpunch.dao.OrdersMasterDao;
import com.bata.billpunch.dao.WeekMasterDao;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchDto;
import com.bata.billpunch.model.dto.OrderPair;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.RdcDetailsDto;

@Service
@Transactional
public class BillPunchServicesImpl {

	@Autowired
	private ApprovalDetailsDao apdao;

	@Autowired
	private BillPunchDetailsDao bdao;

	@Autowired
	private OrdersMasterDao odao;
	
	@Autowired
	private ArticlesMasterDao artdao;

	@Autowired
	private BillPunchMasterDao bmdao;

	@Autowired
	private WeekMasterDao wdao;

	@Autowired
	private BillPunchRdcDetailsDao brdao;

	@Autowired
	private BillPunchDetailsDemoDao ddao;

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> save(BillPunchDto pm,String userName) {
		BillPunchDetailsModel vm = null;
		List<BillPunchDetailsModel> bm = new ArrayList<>();

		if (Optional.ofNullable(pm.getFormtype()).isPresent() && pm.getFormtype().equalsIgnoreCase("manual")) {

			if (!pm.getRdcList().isEmpty()) {
				StringBuilder sb1 = new StringBuilder("");
				if (Optional.ofNullable(bdao.findwithLastBillEntry()).isPresent()) {

					BillPunchDetailsModel s = bdao.findwithLastBillEntry();
					String v = s.getBillUniqueCode();
					String[] p = v.split("_");
					Integer number = Integer.valueOf(p[p.length - 1]) + 1;

					sb1.append("BL").append("_").append(number);

				} else {

					Integer number = 01;
					sb1.append("BL").append("_").append(number);

				}
				if (Optional.ofNullable(pm.getBillId()).isPresent()) {
					try {
						bdao.deleteById(pm.getBillId());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				for (RdcDetailsDto lm : pm.getRdcList()) {
					if (Optional.ofNullable(lm.getBillId()).isPresent()) {
						try {
							bdao.deleteById(lm.getBillId());

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
				if (Optional.ofNullable(pm.getBillOrderNo()).isPresent()) {

					for (RdcDetailsDto lm : pm.getRdcList()) {
						BillPunchDetailsModel entity = new BillPunchDetailsModel();
						entity.setBillUniqueCode(sb1.toString());

						try {
							List<WeekMasterModel> wk = wdao.findAll();
							for (WeekMasterModel km : wk) {
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
								String d2 = d11.format(cal.getTime());
								Date d22 = km.getWeekSdate();
								String d1 = d11.format(d22);
								Date d33 = km.getWeekEdate();
								String d3 = d11.format(d33);

								if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
									entity.setBillCloseWeek(Integer.parseInt(km.getBataWeek()));
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						if (Optional.ofNullable(lm.getArticleCode()).isPresent()
								|| Optional.ofNullable(lm.getReceiveDate()).isPresent()
								|| Optional.ofNullable(lm.getPairAmount()).isPresent()
								|| Optional.ofNullable(lm.getRdcPair()).isPresent()
								|| Optional.ofNullable(lm.getWeekYear()).isPresent()) {

							entity.setBillCloseStatus(pm.getBillCloseStatus());
							entity.setCreditNote(pm.getCreditNote());
							entity.setRdcPairC(lm.getRdcPairC());
							entity.setPurchaseCost(pm.getPurchaseCost());
							entity.setInvoiceCost(pm.getInvoiceCost());
							entity.setDiscountAmt(pm.getDiscountAmt());
							entity.setDiscountAmtVal(pm.getDiscountAmtVal());
							entity.setTcsApplicable(pm.getTcsApplicable());
							entity.setTcsPercent(pm.getTcsPercent());
							entity.setTcsValue(pm.getTcsValue());
							entity.setBillOrderDate(pm.getBillOrderDate());
							entity.setBillOrderNo(pm.getBillOrderNo());
							entity.setBillWeekYear(pm.getBillWeekYear());
							entity.setShopName(pm.getShopName());
							entity.setShopNo(pm.getShopNo());
							entity.setCreatedOn(Calendar.getInstance());
							entity.setFreight(pm.getFreight());
							entity.setPair(pm.getPair());
							entity.setPartyCode(pm.getPartyCode());
							entity.setPartyName(pm.getPartyName());
							entity.setRegion(pm.getRegion());
							entity.setStateName(pm.getStateName());
							entity.setStateCode(pm.getStateCode());
							entity.setShopName(pm.getShopName());
							entity.setStatus(pm.getStatus());
							entity.setIgst(pm.getIgst());
							entity.setCgst(pm.getCgst());
							entity.setSgst(pm.getSgst());
							entity.setTotalCost(pm.getTotalCost());
							entity.setFormtype(pm.getFormtype());
							entity.setInvoiceNO(pm.getInvoiceNO());
							entity.setGrNo(pm.getGrNo());
							entity.setGrDate(pm.getGrDate());
							entity.setCnDate(pm.getGrnDate());
							entity.setCreatedBy(userName);
							entity.setWeekYear(lm.getWeekYear());
							entity.setArticleCode(lm.getArticleCode());
							entity.setArticleName(lm.getArticleName());
							entity.setPairAmount(lm.getPairAmount());
							entity.setRdcPair(lm.getRdcPair());
							entity.setReceiveDate(lm.getReceiveDate());
							entity.setNoOfCartons(lm.getNoOfCartons());

							vm = bdao.save(entity);

							bm.add(vm);
						} 
					}
				} else {
					throw new ResourceAccessException(" Please fill up the BillOrderNo.");
				}

			} else {

				StringBuilder sb1 = new StringBuilder("");
				if (Optional.ofNullable(bdao.findwithLastBillEntry()).isPresent()) {

					BillPunchDetailsModel s = bdao.findwithLastBillEntry();
					String v = s.getBillUniqueCode();
					String[] p = v.split("_");
					Integer number = Integer.valueOf(p[p.length - 1]) + 1;

					sb1.append("BL").append("_").append(number);
					// entity.setBillUniqueCode(sb1.toString());

				} else {

					Integer number = 01;
					sb1.append("BL").append("_").append(number);

				}

				if (Optional.ofNullable(pm.getBillId()).isPresent()) {
					bdao.deleteById(pm.getBillId());

				}
				if (Optional.ofNullable(pm.getBillOrderNo()).isPresent()) {

					BillPunchDetailsModel entity = new BillPunchDetailsModel();
					entity.setBillUniqueCode(sb1.toString());

					try {
						List<WeekMasterModel> wk = wdao.findAll();
						for (WeekMasterModel km : wk) {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
							String d2 = d11.format(cal.getTime());
							Date d22 = km.getWeekSdate();
							String d1 = d11.format(d22);
							Date d33 = km.getWeekEdate();
							String d3 = d11.format(d33);

							if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
								entity.setBillCloseWeek(Integer.parseInt( km.getBataWeek()));
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					entity.setCreditNote(pm.getCreditNote());
					entity.setBillCloseStatus(pm.getBillCloseStatus());
					entity.setPurchaseCost(pm.getPurchaseCost());
					entity.setInvoiceCost(pm.getInvoiceCost());
					entity.setDiscountAmt(pm.getDiscountAmt());
					entity.setDiscountAmtVal(pm.getDiscountAmtVal());
					entity.setTcsApplicable(pm.getTcsApplicable());
					entity.setTcsPercent(pm.getTcsPercent());
					entity.setTcsValue(pm.getTcsValue());
					entity.setBillOrderDate(pm.getBillOrderDate());
					entity.setBillOrderNo(pm.getBillOrderNo());
					entity.setBillWeekYear(pm.getBillWeekYear());
					entity.setPair(pm.getPair());
					entity.setShopName(pm.getShopName());
					entity.setShopNo(pm.getShopNo());
					entity.setCreatedOn(Calendar.getInstance());
					entity.setFreight(pm.getFreight());
					entity.setPair(pm.getPair());
					entity.setPartyCode(pm.getPartyCode());
					entity.setPartyName(pm.getPartyName());
					entity.setRegion(pm.getRegion());
					entity.setStateName(pm.getStateName());
					entity.setStateCode(pm.getStateCode());
					entity.setShopName(pm.getShopName());
					entity.setShopNo(pm.getShopNo());
					entity.setStatus(pm.getStatus());
					entity.setIgst(pm.getIgst());
					entity.setCgst(pm.getCgst());
					entity.setSgst(pm.getSgst());
					entity.setTotalCost(pm.getTotalCost());
					entity.setFormtype(pm.getFormtype());
					entity.setInvoiceNO(pm.getInvoiceNO());
					entity.setGrNo(pm.getGrNo());
					entity.setGrDate(pm.getGrDate());
					entity.setCnDate(pm.getGrnDate());
					entity.setCreatedBy(userName);

					vm = bdao.save(entity);

					bm.add(vm);
				} else {
					throw new ResourceAccessException(" Please fill up the BillOrderNo.");
				}

			}
		} else {

			for (RdcDetailsDto lm : pm.getRdcList()) {
				if (Optional.ofNullable(lm.getBillId()).isPresent()) {
					BillPunchDetailsModel entity = bdao.findById(lm.getBillId()).get();

					entity.setBillCloseStatus(pm.getBillCloseStatus());
					entity.setFormtype(pm.getFormtype());
					entity.setPurchaseCost(pm.getPurchaseCost());
					entity.setInvoiceCost(pm.getInvoiceCost());
					entity.setDiscountAmt(pm.getDiscountAmt());
					entity.setDiscountAmtVal(pm.getDiscountAmtVal());
					entity.setTcsApplicable(pm.getTcsApplicable());
					entity.setTcsPercent(pm.getTcsPercent());
					entity.setTcsValue(pm.getTcsValue());
					entity.setBillOrderDate(pm.getBillOrderDate());
					entity.setBillOrderNo(pm.getBillOrderNo());
					// entity.setBillWeek(pm.getBillWeek());
					// entity.setBillWeekYear(pm.getBillWeekYear());
					// entity.setBillCloseWeek(pm.getBillCloseWeek());
					// entity.setCreatedOn(Calendar.getInstance());
					entity.setFreight(pm.getFreight());
					entity.setPartyCode(pm.getPartyCode());
					entity.setPartyName(pm.getPartyName());
					entity.setPair(pm.getPair());
					entity.setRegion(pm.getRegion());
					entity.setStateName(pm.getStateName());
					entity.setStateCode(pm.getStateCode());
					entity.setShopName(pm.getShopName());
					entity.setShopNo(pm.getShopNo());
					entity.setStatus(pm.getStatus());
					entity.setIgst(pm.getIgst());
					entity.setCgst(pm.getCgst());
					entity.setSgst(pm.getSgst());
					entity.setTotalCost(pm.getTotalCost());
					entity.setInvoiceNO(pm.getInvoiceNO());
					entity.setGrNo(pm.getGrNo());
					entity.setGrDate(pm.getGrDate());
					entity.setCnDate(pm.getGrnDate());
					entity.setCreditNote(pm.getCreditNote());
					entity.setCreatedBy(userName);
					entity.setRdcPairC(lm.getRdcPairC());
					entity.setWeekYear(lm.getWeekYear());
					entity.setArticleCode(lm.getArticleCode());
					entity.setArticleName(lm.getArticleName());
					entity.setPairAmount(lm.getPairAmount());
					// entity.setGrDate(lm.getGrDate());
					entity.setReceiveDate(lm.getReceiveDate());
					entity.setNoOfCartons(lm.getNoOfCartons());

					vm = bdao.save(entity);
					bm.add(vm);

				}

			}
		}

		return bm;

	}

	public List<BillPunchDetailsModel> getAll() {
		return bdao.findAll();
	}

	public List<BillPunchDetailsModel> findAll() {

		return bdao.findAll();
	}
	
	public List<BillPunchDetailsModel> findAllApprovedDetails(String wk) {

		return bdao.findWithAllApproved(wk);
	}

	@SuppressWarnings("all")
	public BillPunchDetailsModel getById(Long id) {

		return bdao.findById(id).get();
	}

	public ApprovalDetailsModel getApprovalDetails(ApprovalDetailsModel cm) {

		return apdao.save(cm);
	}

	public BillPunchDetailsModel save(BillPunchDetailsModel vm) {
		return bdao.save(vm);
	}

	public BillPunchDetailsModel saveXl(BillPunchDetailsModel list) {
		return ddao.save(list);
	}

	public List<BillPunchDetailsModel> getByUniqueId(String uniqueId) {
		return bdao.findwithAllDetailsByUniqueId(uniqueId);
	}

	public List<BillPunchDetailsModel> getByOrdNo(String ordno) {
		return bdao.findwithAllDetailsByOrdNo(ordno);
	}
	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> update(BillPunchDto pm) {
		List<BillPunchDetailsModel> lm = new ArrayList<>();
		BillPunchDetailsModel vm = null;
		if (!pm.getRdcList().isEmpty()) {
			for (RdcDetailsDto xm : pm.getRdcList()) {
				BillPunchDetailsModel entity = bdao.findById(xm.getBillId()).get();
				try {
					List<WeekMasterModel> wk = wdao.findAll();
					for (WeekMasterModel lmm : wk) {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
						String d2 = d11.format(cal.getTime());
						Date d22 = lmm.getWeekSdate();
						String d1 = d11.format(d22);
						Date d33 = lmm.getWeekEdate();
						String d3 = d11.format(d33);

						if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
							entity.setBillCloseWeek(Integer.parseInt(lmm.getBataWeek()));
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				entity.setCreditNote(pm.getCreditNote());
				entity.setRdcPairC(xm.getRdcPairC());
				entity.setBillCloseStatus(pm.getBillCloseStatus());
				entity.setFormtype(pm.getFormtype());
				entity.setPurchaseCost(pm.getPurchaseCost());
				entity.setInvoiceCost(pm.getInvoiceCost());
				entity.setDiscountAmt(pm.getDiscountAmt());
				entity.setDiscountAmtVal(pm.getDiscountAmtVal());
				entity.setTcsApplicable(pm.getTcsApplicable());
				entity.setTcsValue(pm.getTcsValue());
				entity.setBillOrderDate(pm.getBillOrderDate());
				entity.setBillOrderNo(pm.getBillOrderNo());
				entity.setBillWeekYear(pm.getBillWeekYear());
				entity.setCreatedOn(Calendar.getInstance());
				entity.setFreight(pm.getFreight());
				entity.setPartyCode(pm.getPartyCode());
				entity.setPartyName(pm.getPartyName());
				entity.setPair(pm.getPair());
				entity.setShopName(pm.getShopName());
				entity.setShopNo(pm.getShopNo());
				entity.setRegion(pm.getRegion());
				entity.setStateName(pm.getStateName());
				entity.setStateCode(pm.getStateCode());
				entity.setShopName(pm.getShopName());
				entity.setShopNo(pm.getShopNo());
				entity.setStatus(pm.getStatus());
				entity.setIgst(pm.getIgst());
				entity.setCgst(pm.getCgst());
				entity.setSgst(pm.getSgst());
				entity.setTotalCost(pm.getTotalCost());
				entity.setGrDate(pm.getGrDate());
				entity.setCnDate(pm.getGrnDate());
				entity.setGrNo(pm.getGrNo());
				entity.setInvoiceNO(pm.getInvoiceNO());
				entity.setWeekYear(xm.getWeekYear());
				entity.setArticleCode(xm.getArticleCode());
				entity.setPairAmount(xm.getPairAmount());
				entity.setRdcPair(xm.getRdcPair());
				entity.setArticleName(xm.getArticleName());
				entity.setReceiveDate(xm.getReceiveDate());
				entity.setNoOfCartons(xm.getNoOfCartons());

				vm = bdao.save(entity);
				lm.add(vm);

			}
		}
		return lm;

	}

	public List<BillPunchDetailsModel> getAllRdcDetails() {
		return bdao.findAll();
	}

	public List<PartyResponseDto> getAllPartycodeAndPartyName() {

		return bdao.findAllPartycodeAndPartyName();


	}

	public List<BillPunchDetailsModel> getAllPartycodeAndPartyNameDetails(String partycode, String partyname) {

		if (partycode == null) {
			partycode = "%";
		}
		if (partyname == null) {
			partyname = "%";
		}

		return bdao.findAllPartycodeAndPartyNameDetails(partycode, partyname);
	}

	public List<BillCloseStatusDto> getApprovedRecords(String billcloseweek) {
		return bdao.findWithApprovedRecords(billcloseweek);
	}

	public List<BillPunchDetailsModel> getAllDetailsByStatus() {
		return bdao.finfWithStatus();
	}

	public List<ApprovalDetailsModel> getApprovalDetailsForValidate(String billUniqueCode, String status) {
		return apdao.findwithApprovalDetailsForValidate(billUniqueCode, status);
	}
	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> updateRecords(List<BillPunchDto> entity) {
		List<BillPunchDetailsModel> lm = new ArrayList<>();

		BillPunchDetailsModel vm = null;
		for (BillPunchDto xm : entity) {
			BillPunchDetailsModel pm = bdao.findById(xm.getBillId()).get();

			pm.setStatus("CLOSED");

			vm = bdao.save(pm);
			lm.add(vm);

		}
		return lm;

	}

	public List<BillCloseStatusDto> getBillCloseWeek() {
		return bdao.findWithBillCloseWeek();
	}

	public OrderPair getAllAtrnoAndOrdnoDetails(String atrno, String ordno) {

		return odao.findWithPairByOrderNoAndArtno(atrno, ordno);
	}

	public List<OrdersMasterModel> getDetailsAtrnoAndOrdnoDetails(String atrno, String ordno) {

		return odao.findWithByOrderNoAndArtno(atrno, ordno);
	}

	public PriceInterface getPriceDetailsByartno(String atrno) {
		return artdao.findWithPriceByArtno(atrno);
	}

	public List<BillPunchDetailsModel> getDetailsStrazaReport(List<String> partycode, String fromwk, String towk,
			String yr) {

		List<BillPunchDetailsModel> cm = null;

		if (fromwk == null && towk == null) {
			cm = bdao.findWithDetailsStrazaReportforAllWK(partycode, yr);
		}

		else {
			cm = bdao.findWithDetailsStrazaReport(partycode, fromwk, towk, yr);
		}

		return cm;

	}

}