package com.bata.billpunch.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.StrazaPesponseDetailsModel;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchDto;
import com.bata.billpunch.model.dto.BillPunchResponse;
import com.bata.billpunch.model.dto.BillPunchResponseDto;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseCostInterface;
import com.bata.billpunch.model.dto.OrderPair;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.RdcDetailsDto;
import com.bata.billpunch.model.dto.StrazaReqModel;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillPunchRestController {

	@Autowired
	private BillPunchServicesImpl services;

	@Autowired
	private BillPunchMasterServicesImpl mservices;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	/********************************************************************************************
	 * save billpunch details
	 ********************************************************************************************/
	@PostMapping("/save-details")
	public ResponseEntity<ResponseModel> saveBillPunchDetails(@RequestBody BillPunchDto entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		ApprovalDetailsModel xm = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if ("True".contentEquals(response.getStatus())) {
			List<BillPunchDetailsModel> cm = services.save(entity, response.getUsername());
			if (!cm.isEmpty()) {
				try {
					ApprovalDetailsModel mn = new ApprovalDetailsModel();
					List<ApprovalDetailsModel> list1 = new ArrayList<>();
					mn.setBillUniqueCode(cm.get(0).getBillUniqueCode());

					mn.setFinUserStatus(cm.get(0).getStatus());
					if (Optional.ofNullable(response.getUsername()).isPresent()) {
						mn.setFinUserName(response.getUsername());
						mn.setUserRole(response.getUserrole());
					}
					List<ApprovalDetailsModel> pm = services
							.getApprovalDetailsForValidate(cm.get(0).getBillUniqueCode(), cm.get(0).getStatus());
					if (pm.isEmpty()) {
						xm = services.getApprovalDetails(mn);
					}

					list1.add(xm);
				} catch (Exception e) {
					e.printStackTrace();
				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/getall-billpunch-details")
	public ResponseEntity<ResponseModel> getAll(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAll();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data based on Unique code
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/getall-details")
	public ResponseEntity<ResponseModel> getAllBillDetails(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAll();

			Map<String, List<BillPunchDto>> map1 = new HashMap<>();
			List<BillPunchDto> list1 = new ArrayList<>();

			for (BillPunchDetailsModel pm : cm) {

				List<RdcDetailsDto> list11 = new ArrayList<>();
				RdcDetailsDto mn = new RdcDetailsDto();
				BillPunchDto entity = new BillPunchDto();
				if (Optional.ofNullable(pm.getBillUniqueCode()).isPresent()) {

					entity.setRdcList(list11);
					if (!list1.contains(pm.getBillOrderNo())) {
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setFormtype(pm.getFormtype());
						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setcPair(pm.getPair());
						entity.setTotalCost(pm.getTotalCost());
						entity.setCreatedOn(Calendar.getInstance());
						entity.setFreight(pm.getFreight());
						entity.setStatus(pm.getStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getShopNo());
						entity.setPair(pm.getPair());
						entity.setStateCode(pm.getStateCode());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setStatus(pm.getStatus());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setCreditNote(pm.getCreditNote());

						list1.add(entity);
						mn.setRdcPair(pm.getRdcPair());
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						list11.add(mn);
					} else {
						mn.setRdcPair(pm.getRdcPair());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						list11.add(mn);
					}

					map1.put(pm.getBillUniqueCode(), list1);
				}

			}
			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list1);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find bill punch data by passing primarykey
	 ********************************************************************************************/
	@GetMapping("/getdetails-by-id/{id}")
	public ResponseEntity<ResponseModel> getDetailsById(@PathVariable(value = "id") Long id, HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			BillPunchDetailsModel cm = services.getById(id);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}


	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-details-by-billno-orderno-partycode")
	public ResponseEntity<ResponseModel> getDetailsByBillNo(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<BillPunchResponseDto> list = new ArrayList<>();
		BillPurchaseCostInterface vm = null;
		if (response.getStatus().contentEquals("True")) {

			List<BillPunchResponseInterface> cm = mservices.getDetailsByBillNo(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus());

			if (Optional.ofNullable(cm).isPresent()) {
				for (BillPunchResponseInterface xm : cm) {

					
					BillPunchResponseDto m = new BillPunchResponseDto();
					m.setBillOrderDate(xm.getbillOrderDate());
					m.setBillOrderNo(xm.getbillOrderNo());
					m.setFormtype(xm.getformtype());
					m.setInvdate(xm.getinvdate());
					m.setGrnDate(xm.getcnDate());
					m.setBillWeek(xm.getbillWeek());
					m.setInvAmount(xm.getrdcAmount());
					m.setInvoiceNO(xm.getinvoiceNO());
					m.setPartyCode(xm.getpartyCode());
					m.setPartyName(xm.getpartyName());
					m.setTcsApplicable(xm.gettcsApplicable());
					if (Optional.ofNullable(xm.getdiscountAmt()).isPresent()) {
						String s = xm.getdiscountAmt() + "%";
						m.setDiscountAmt(s);
					}

					m.setStatus(xm.getstatus());
					if ("manual".equalsIgnoreCase(xm.getformtype())) {

						List<BillPunchDetailsModel> xn = services.getByOrdNo(xm.getbillOrderNo());
						m.setPurchaseCost(xn.get(0).getPurchaseCost());
					} else {

						Double amt = 0d;
							try {
								String s1 = "0" + xm.getinvoiceNO().substring(2, 6);
								vm = mservices.getOrderPurchaseCost(s1, xm.getbillOrderNo(), xm.getpartyCode(),
										xm.getarticleCode());
								Double mt = vm.getpurchaseCost() * Integer.parseInt(xm.getpair());
								amt += mt;

							} catch (Exception e) {
								e.printStackTrace();
							}
						m.setPurchaseCost(amt);

					}

					list.add(m);

				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else

		{
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-list-populate-by-billno-orderno-partycode")
	public ResponseEntity<ResponseModel> getBillPunchDetailsForPopulate(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			BillPurchaseCostInterface vm = null;
			List<BillPunchDetailsModel> cm = mservices.getDetailsByFilter(em.getInvoiceNO(),
					String.valueOf(em.getPartyCode()), em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus());
			if (Optional.ofNullable(cm).isPresent()) {
				List<BillPunchDto> list1 = new ArrayList<>();
				List<RdcDetailsDto> list11 = new ArrayList<>();
				Map<String, List<BillPunchDto>> map1 = new HashMap<>();
				BillPunchDto entity = new BillPunchDto();
				for (BillPunchDetailsModel pm : cm) {

					List<OrdersMasterModel> mb = mservices.getOrderDetails(pm.getBillOrderNo());
					Double amt = 0d;
					for (OrdersMasterModel mn : mb) {
						try {
							String s1 = "0" + pm.getInvoiceNO().substring(2, 6);
							vm = mservices.getOrderPurchaseCost(s1, pm.getBillOrderNo(),
									String.valueOf(pm.getPartyCode()), mn.getArtno());
							Double mt = vm.getpurchaseCost() * Integer.parseInt(mn.getOrderPr());
							amt += mt;

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					RdcDetailsDto mn = new RdcDetailsDto();

					if (!map1.containsKey(pm.getBillOrderNo())) {
						entity.setBillId(pm.getBilId());
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getShopNo());
						entity.setBillCloseWeek(pm.getBillCloseWeek());
						if ("manual".equalsIgnoreCase(pm.getFormtype())) {
							List<BillPunchDetailsModel> xn = services.getByOrdNo(pm.getBillOrderNo());
							entity.setPurchaseCost(xn.get(0).getPurchaseCost());
						} else {
							entity.setPurchaseCost(amt);
						}

						entity.setBillUniqueCode(pm.getBillUniqueCode());
						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setFormtype(pm.getFormtype());
						entity.setStateCode(pm.getStateCode());
						entity.setTotalCost(pm.getTotalCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						entity.setStatus(pm.getStatus());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						entity.setCreatedOn(Calendar.getInstance());
						entity.setFreight(pm.getFreight());
						entity.setPair(pm.getPair());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setGrnDate(pm.getCnDate());
						entity.setCreditNote(pm.getCreditNote());
						entity.setDiscountAmt(pm.getDiscountAmt());
						entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						entity.setTcsValue(pm.getTcsValue());
						entity.setTcsApplicable(pm.getTcsApplicable());
						entity.setTcsPercent(pm.getTcsPercent());
						list1.add(entity);
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						if ("manual".equalsIgnoreCase(pm.getFormtype())) {
							mn.setReceiveDate(pm.getReceiveDate());
						} else {
							mn.setReceiveDate(pm.getRecptInvDate());
						}
						
						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

						map1.put(pm.getBillOrderNo(), list1);
					} else {
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getRecptInvDate());
						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

					}
					entity.setRdcList(list11);

				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(entity);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	public String getToken(HttpServletRequest req) {

		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}

	/********************************************************************************************
	 * Approval details bill punch reports.
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/action-approval-bill-punch-details")
	public ResponseEntity<ResponseModel> getAllApprovalDetails(HttpServletRequest req,
			@RequestBody ApprovalDetailsModel entity) {
		BillPunchDetailsModel pm = null;
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			ApprovalDetailsModel cm = new ApprovalDetailsModel();
			List<BillPunchDetailsModel> list = new ArrayList<>();
			List<ApprovalDetailsModel> list1 = new ArrayList<>();
			cm.setBillUniqueCode(entity.getBillUniqueCode());
			cm.setRemarks(entity.getRemarks());
			if (Optional.ofNullable(entity.getApproverStatus()).isPresent()) {
				cm.setApproverStatus(entity.getApproverStatus());
			} else if (Optional.ofNullable(entity.getFinUserStatus()).isPresent()) {
				cm.setApproverStatus(entity.getFinUserStatus());
			} else if (Optional.ofNullable(entity.getAdminStatus()).isPresent()) {
				cm.setAdminStatus(entity.getAdminStatus());
			}
			if (Optional.ofNullable(response.getUsername()).isPresent()) {
				cm.setApproverName(response.getUsername());
				cm.setUserRole(response.getUserrole());
			}
			ApprovalDetailsModel xm = services.getApprovalDetails(cm);
			list1.add(xm);

			if (Optional.ofNullable(xm).isPresent()) {
				List<BillPunchDetailsModel> vm = services.getByUniqueId(entity.getBillUniqueCode());

				for (BillPunchDetailsModel model : vm) {
					if (Optional.ofNullable(xm.getApproverStatus()).isPresent()) {
						model.setStatus(xm.getApproverStatus());
					} else if (Optional.ofNullable(xm.getFinUserStatus()).isPresent()) {
						model.setStatus(xm.getFinUserStatus());
					} else if (Optional.ofNullable(xm.getAdminStatus()).isPresent()) {
						model.setStatus(xm.getAdminStatus());
					}

					pm = services.save(model);
					list.add(pm);
				}

			}
			if (!list.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);

			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);
			}

		} else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}


	/********************************************************************************************
	 * Update billpunch details by check box
	 ********************************************************************************************/
	@PostMapping("/update-billpunch-details-by-checkbox")
	public ResponseEntity<ResponseModel> updateBillPunchDetailsByCheckBox(@RequestBody List<BillPunchResponse> entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = mservices.updateByCheckBox(entity, response.getUsername());
			if (!cm.isEmpty()) {
				try {
					ApprovalDetailsModel mn = new ApprovalDetailsModel();
					List<ApprovalDetailsModel> list1 = new ArrayList<>();
					mn.setBillUniqueCode(cm.get(0).getBillUniqueCode());

					mn.setFinUserStatus(cm.get(0).getStatus());
					if (Optional.ofNullable(response.getUsername()).isPresent()) {
						mn.setFinUserName(response.getUsername());
						mn.setUserRole(response.getUserrole());
					}
					ApprovalDetailsModel xm = services.getApprovalDetails(mn);
					list1.add(xm);
				} catch (Exception e) {
					e.printStackTrace();
				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch RDC data
	 ********************************************************************************************/
	@GetMapping("/getall")
	public ResponseEntity<ResponseModel> getAllBillDetailsFromRdc(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> cm = services.getAllRdcDetails();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getall-partycode-partyname")
	public ResponseEntity<ResponseModel> getAllPartycodeAndPartyName(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<PartyResponseDto> cm = services.getAllPartycodeAndPartyName();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getdetails-by-partycode-partyname/{partycode}/{partyname}")
	public ResponseEntity<ResponseModel> getAllPartycodeAndPartyNameDetails(HttpServletRequest req,
			@PathVariable("partycode") String partycode, @PathVariable("partyname") String partyname) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAllPartycodeAndPartyNameDetails(partycode, partyname);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname ,year and week
	 ********************************************************************************************/
	@PostMapping("/getdetails-for-straza-report")
	public ResponseEntity<ResponseModel> getDetailsStrazaReport(@RequestBody StrazaReqModel model,
			HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<StrazaPesponseDetailsModel> list = new ArrayList<>();
		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getDetailsStrazaReport(model.getPartycode(), model.getFromwk(),
					model.getTowk(), model.getYr());
			try {
				for (BillPunchDetailsModel xm : cm) {
					StrazaPesponseDetailsModel vm = new StrazaPesponseDetailsModel();
					List<OrdersMasterModel> ms = services.getDetailsAtrnoAndOrdnoDetails(xm.getArticleCode(),
							xm.getBillOrderNo());

					vm.setBillOrderNo(xm.getBillOrderNo());
					vm.setPartyCode(xm.getPartyCode());
					vm.setPartyName(xm.getPartyName());
					vm.setRecptInvDate(xm.getRecptInvDate());
					vm.setInvoiceNO(xm.getInvoiceNO());
					vm.setGrNo(xm.getGrNo());
					vm.setBillWeek(xm.getWeekYear());
					vm.setPair(xm.getPair());
					vm.setGrnDate(xm.getCnDate());
					vm.setShopNo(xm.getShopNo());
					if (Optional.ofNullable(ms).isPresent()) {
						vm.setMrp(ms.get(0).getMrp());
						vm.setStdcost(ms.get(0).getStdcost());
					}

					list.add(vm);
				}
			} catch (Exception e) {
                  e.printStackTrace();
			}

			if (!list.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find current week status
	 ********************************************************************************************/

	@GetMapping("/get-all-bill-close-week")
	public ResponseEntity<ResponseModel> getBillCloseWeek(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillCloseStatusDto> cm = services.getBillCloseWeek();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find current week status
	 ********************************************************************************************/

	@GetMapping("/get-approved-records-for-final-close/{billcloseweek}")
	public ResponseEntity<ResponseModel> getCurrentWeekStatus(HttpServletRequest req,
			@PathVariable("billcloseweek") String billcloseweek) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillCloseStatusDto> cm = services.getApprovedRecords(billcloseweek);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * update closed bill status
	 ********************************************************************************************/

	@PostMapping("/update-approved-records-for-final-close")
	public ResponseEntity<ResponseModel> updateRecordsFinalClose(HttpServletRequest req,
			@RequestBody List<BillPunchDto> entity) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> cm = services.updateRecords(entity);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE_ONE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data which are submitted and accepted
	 ********************************************************************************************/
	@GetMapping("/get-all-details-by-status")
	public ResponseEntity<ResponseModel> getAllDetailsByStatus(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAllDetailsByStatus();
			Map<String, List<BillPunchDetailsModel>> map1 = new HashMap<>();
			List<BillPunchDetailsModel> list1 = new ArrayList<>();
			for (BillPunchDetailsModel xm : cm) {

				if (!map1.isEmpty()) {
					if (!map1.containsKey(xm.getBillUniqueCode())) {
						map1.put(xm.getBillUniqueCode(), cm);
						list1.add(xm);
					}
				} else {
					map1.put(xm.getBillUniqueCode(), cm);
					list1.add(xm);

				}

			}

			if (!list1.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list1);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch artno and ordno data
	 ********************************************************************************************/
	@GetMapping("/get-pair-details-by-atrno-ordno/{atrno}/{ordno}")
	public ResponseEntity<ResponseModel> getAllArtNoAndOrdNoDetails(HttpServletRequest req,
			@PathVariable("atrno") String atrno, @PathVariable("ordno") String ordno) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			OrderPair cm = services.getAllAtrnoAndOrdnoDetails(atrno, ordno);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find price by passing artno data
	 ********************************************************************************************/
	@GetMapping("/get-price-details-by-atrno/{atrno}")
	public ResponseEntity<ResponseModel> getPriceDetailsByArtno(HttpServletRequest req,
			@PathVariable("atrno") String atrno) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			PriceInterface cm = services.getPriceDetailsByartno(atrno);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

}
