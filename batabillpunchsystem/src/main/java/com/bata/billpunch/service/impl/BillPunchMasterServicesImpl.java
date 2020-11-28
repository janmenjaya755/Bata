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

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.dao.BillPunchDetailsTestDao;
import com.bata.billpunch.dao.BillPunchMasterDao;
import com.bata.billpunch.dao.CategoriesMasterDao;
import com.bata.billpunch.dao.OrdersMasterDao;
import com.bata.billpunch.dao.PartiesMasterDao;
import com.bata.billpunch.dao.ShopMasterDao;
import com.bata.billpunch.dao.StateMasterDao;
import com.bata.billpunch.dao.TcsMasterDao;
import com.bata.billpunch.dao.WeekMasterDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchMasterModel;
import com.bata.billpunch.model.CategoriesMasterModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.TCSmasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.BillPunchResponse;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseCostInterface;
import com.bata.billpunch.model.dto.PartyNameCodeDto;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.TotalAmtInterface;

@Service
@Transactional
public class BillPunchMasterServicesImpl {

	@Autowired
	private BillPunchMasterDao bmdao;

	@Autowired
	private TcsMasterDao tcsdao;

	@Autowired
	private BillPunchDetailsDao bdao;

	@Autowired
	private ArticlesMasterDao ardao;

	@Autowired
	private PartiesMasterDao pdao;

	@Autowired
	private StateMasterDao stdao;

	@Autowired
	private ShopMasterDao shdao;

	@Autowired
	private BillPunchDetailsTestDao tdao;

	@Autowired
	private CategoriesMasterDao catdao;

	@Autowired
	private WeekMasterDao wdao;

	@Autowired
	private OrdersMasterDao ordao;

	public void saveMasterDetails(List<BillPunchMasterModel> entity) {
		bmdao.save(entity);

	}

	public void findWithDeleteAll() {
		bmdao.findWithDeleteAll();

	}

	public List<ArticlesMasterModel> findWithArticleDetails(String artname, String billno, String partycode,
			String orderno) {

		return ardao.findWithArticleDetails(artname);

	}

	public List<StateMasterModel> findWithStateDetails(String statename) {
		return stdao.findWithStateDetails(statename);
	}

	public List<ShopMasterModel> findWithShopDetails(String shopname) {
		return shdao.findWithShopDetails(shopname);
	}

	public List<CategoriesMasterModel> findWithCatagoryDetails(String catname) {
		return catdao.findWithCatDetails(catname);
	}

	public List<WeekMasterModel> findWithWeekDetails(String weekcode) {
		return wdao.findWithWeekDetails(weekcode);
	}

	public List<BillPunchResponseInterface> getDetailsByBillNo(String invoiceNO, String partycode, String orederno,
			String uniquecode, String status) {

		return bdao.findWithBillNoPartyCodeAndOrderNoTest(invoiceNO, partycode, orederno, uniquecode, status);
	}

	public BillPunchDetailsModel getDetailsByOrderNoAndInvoiceNo(String orderno, String invno) {

		return bdao.findwithAllDetailsByOrderAndInvoice(orderno, invno);
	}

	public List<ArticlesMasterModel> findAll() {
		return ardao.findAll();
	}

	public List<StateMasterModel> findAllDetails() {
		return stdao.findAll();
	}

	public List<ShopMasterModel> getAll() {
		return shdao.findAll();
	}

	public List<CategoriesMasterModel> getAllDetails() {
		return catdao.findAll();
	}

	public List<WeekMasterModel> findDetails() {
		return wdao.findAll();
	}

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> updateByCheckBox(List<BillPunchResponse> entity, String userName) {
		List<BillPunchDetailsModel> list = new ArrayList<>();
		for (BillPunchResponse xm : entity) {

			List<BillPunchDetailsModel> vn = bdao.findWithBillNoPartyCodeAndOrderNo(xm.getBillNo(), xm.getPartyCode(),
					xm.getBillOrderNo(), null, null);
			for (BillPunchDetailsModel vm : vn) {
				try {
					List<WeekMasterModel> wk = wdao.findAll();
					for (WeekMasterModel lm : wk) {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
						String d2 = d11.format(cal.getTime());
						Date d22 = lm.getWeekSdate();
						String d1 = d11.format(d22);
						Date d33 = lm.getWeekEdate();
						String d3 = d11.format(d33);

						if (d2.compareTo(d1) >= 0) {
							if (d2.compareTo(d3) <= 0) {
								vm.setBillCloseWeek(Integer.parseInt(lm.getBataWeek()));
							}
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				vm.setStatus(xm.getStatus());
				vm.setCreatedBy(userName);

				if (!"no".equalsIgnoreCase(xm.getDiscountAmt())) {
					vm.setDiscountAmt(xm.getDiscountAmt());
					vm.setDiscountAmtVal((vm.getPurchaseCost() * Double.parseDouble(xm.getDiscountAmt())) / 100);
				}

				if ("yes".equalsIgnoreCase(xm.getTcsApplicable())) {
					vm.setTcsApplicable(xm.getTcsApplicable());

					if (!Optional.ofNullable(vm.getIgst()).isPresent()) {
						vm.setIgst(0);
					}
					if (!Optional.ofNullable(vm.getCgst()).isPresent()) {
						vm.setCgst(0);
					}
					if (!Optional.ofNullable(vm.getSgst()).isPresent()) {
						vm.setSgst(0);
					}
					if (!Optional.ofNullable(vm.getFreight()).isPresent()) {
						vm.setFreight("0");
					}
					if (!Optional.ofNullable(vm.getCreditNote()).isPresent()) {
						vm.setCreditNote(0d);
					}
					Double amt = vm.getPurchaseCost() + vm.getIgst() + vm.getCgst() + vm.getSgst() + vm.getCreditNote()
							+ Integer.parseInt(vm.getFreight());
					TCSmasterModel tcs = tcsdao.findAll().get(0);
					vm.setTcsPercent(tcs.getTcsVaule());
					vm.setTcsValue((amt * Double.parseDouble(tcs.getTcsVaule())) / 100);
					vm.setTotalCost(amt + (amt * Double.parseDouble(tcs.getTcsVaule())) / 100);
				}

				BillPunchDetailsModel pm = bdao.save(vm);
				list.add(pm);

			}
		}
		return list;
	}

	public List<PartyNameCodeDto> findAllParty() {
		List<PartyNameCodeDto> list = new ArrayList<>();
		List<PartyResponseDto> xm = pdao.findWithAllPartycodeAndPartyName();
		for (PartyResponseDto vm : xm) {
			PartyNameCodeDto cm = new PartyNameCodeDto();
			String s = vm.getpartycode().concat(" ").concat("-").concat(" ").concat(vm.getpartyname());
			cm.setPartycode(vm.getpartycode());
			cm.setPartyname(vm.getpartyname());
			cm.setPartynamecode(s);
			list.add(cm);
		}

		return list;

	}

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> getDetailsByBillNoTest(String invno, String xyz, String ordno, String pk,
			String ss) {
		return tdao.findWithBillNoPartyCodeAndOrderNoTest(null, null, "L0D1075", null, null);
	}

	public BillPurchaseCostInterface getOrderPurchaseCost(String rdcNo, String billOrderNo, String partyCode,
			String articleCode) {
		return ordao.findWithPurchaseCost(rdcNo, billOrderNo, partyCode, articleCode);
	}

	public List<OrdersMasterModel> getOrderDetails(String billOrderNo) {
		return ordao.findWithBillOrderDetailsByOrderNo(billOrderNo);
	}

	public List<BillPunchDetailsModel> getDetailsByFilter(String invoiceNO, String partyCode, String billOrderNo,
			String billUniqueCode, String status) {

		return bdao.findWithBillNoPartyCodeAndOrderNo(invoiceNO, partyCode, billOrderNo, billUniqueCode, status);

	}

	public List<BillPunchDetailsModel> getReportDetailsByWeek(String wk) {
		return bdao.findWithBillReportByWeek(wk);
	}

	public TotalAmtInterface getTotalAmountForAdonis(String wk) {
		return bdao.findWithTotalAmt(wk);
	}

	public List<AdonisFileDetailsInterface> getAdonisDetails(String wk) {
		return bdao.findWithAdonisFileDetails(wk);
	}

}
