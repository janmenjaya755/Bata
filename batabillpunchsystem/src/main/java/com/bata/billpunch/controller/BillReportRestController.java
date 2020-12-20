package com.bata.billpunch.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.AdonisMasterModel;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.DistMasterModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.TotalAmtInterface;
import com.bata.billpunch.service.impl.AdonisMasterServiceImpl;
import com.bata.billpunch.service.impl.ArticleMasterServiceImpl;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.DistMasterServiceImpl;
import com.bata.billpunch.service.impl.OrederMasterServiceImpl;
import com.bata.billpunch.service.impl.PartyMasterServiceImpl;
import com.bata.billpunch.service.impl.ShopMasterServiceImpl;
import com.bata.billpunch.service.impl.StateMasterServiceImpl;
import com.bata.billpunch.service.impl.WeekMasterServiceImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillReportRestController {

	@Autowired
	private BillPunchServicesImpl services;

	@Autowired
	private AdonisMasterServiceImpl adservices;

	@Autowired
	private WeekMasterServiceImpl wservice;

	@Autowired
	private BillPunchMasterServicesImpl mservices;

	@Autowired
	private OrederMasterServiceImpl oservices;

	@Autowired
	private DistMasterServiceImpl dservices;

	@Autowired
	private ArticleMasterServiceImpl aservices;

	@Autowired
	private ShopMasterServiceImpl sservices;

	@Autowired
	private PartyMasterServiceImpl pservices;

	@Autowired
	private StateMasterServiceImpl stservices;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	public String getToken(HttpServletRequest req) {

		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}

	/********************************************************************************************
	 * Find All bill punch edp reports
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-edp-report-details/{wk}")
	public ResponseEntity<ResponseModel> getEdpReport(HttpServletRequest req, @PathVariable("wk") String wk) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		List<Integer> list = new ArrayList<>();
		list.addAll(Arrays.asList(1, 6, 1, 1, 3, 5, 15, 3, 5, 5, 5, 11, 11, 7, 11, 3, 1, 1, 4, 2, 15, 2, 2, 4, 20, 10,
				2, 11, 15, 2, 2, 4, 2, 2, 4, 2, 5, 2, 2, 4, 10, 5, 2, 11, 11, 11, 11, 5));

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> xm = mservices.getReportDetailsByWeek(wk);
			try {

				Calendar call = Calendar.getInstance();
				SimpleDateFormat d23 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String d34 = d23.format(call.getTime());
				String dd = d34.replace(':', '_');

				File f = new File("C:\\report\\" + "EDP" + dd + ".edp");// curedatewith time
				FileWriter writer = new FileWriter(f, true);
				if (Optional.ofNullable(xm).isPresent()) {
					String week = null;
					String date111 = null;

					try {
						List<WeekMasterModel> wks = wservice.getAllWeek();
						for (WeekMasterModel km : wks) {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
							String d2 = d11.format(cal.getTime());
							Date d22 = km.getWeekSdate();
							String d1 = d11.format(d22);
							Date d33 = km.getWeekEdate();
							String d3 = d11.format(d33);

							if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
								week = km.getBataWeek();
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					int year = Calendar.getInstance().get(Calendar.YEAR);
					String st = String.valueOf(year);
					for (BillPunchDetailsModel vm : xm) {

						ArticlesMasterModel art = aservices.getArticleDetails(vm.getArticleCode());

						BigDecimal value = new BigDecimal(vm.getPurchaseCost());
						String amtnew = String.valueOf(value);

						BigDecimal value1 = new BigDecimal(vm.getPurchaseCost());
						String amt = String.valueOf(value1);

						String w1 = null;
						Date d1 = vm.getCnDate();
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						String date1 = dateFormat.format(d1);
						Date d2 = vm.getRecptInvDate();
						String date11 = dateFormat.format(d2);
						if (Optional.ofNullable(vm.getResumeInvDate()).isPresent()) {
							Date d3 = vm.getResumeInvDate();
							date111 = dateFormat.format(d3);
						} else {
							date111 = "00-00-0000";
						}

						List<String> list1 = new ArrayList<>();
						list1.addAll(Arrays.asList(vm.getDcCode(), st, "1", "1", "999", vm.getShopNo(),
								vm.getInvoiceNO(), String.valueOf(vm.getDistcode()), String.valueOf(vm.getRdcPair()),
								"00000", String.valueOf(vm.getPackCaseT()), art.getArtpricemrp(),
								art.getArtpricefactory(), "0000000", amtnew, vm.getPartyCode(), "1", "9", st, week,
								vm.getCnNo(), date1.substring(0, 2), date1.substring(3, 5), date1.substring(6, 10),
								String.valueOf(vm.getTrnsportCode()), vm.getRdpermitNo(), vm.getStateCode(), amt,
								vm.getRecptInvNo(), date11.substring(0, 2), date11.substring(3, 5),
								date11.substring(6, 10), date11.substring(0, 2), date11.substring(3, 5),
								date11.substring(6, 10), vm.getResumeInvNo(), "     ", date111.substring(0, 2),
								date111.substring(3, 5), date111.substring(6, 10), vm.getResumeInvNoTwo(), "     ",
								"  ", art.getArtstndcost(), String.valueOf(Integer.parseInt(vm.getCgst()) / 100),
								String.valueOf(Integer.parseInt(vm.getSgst()) / 100), String.valueOf(Integer.parseInt(vm.getIgst()) / 100), "     "));

						for (int i = 0; i < list1.size(); i++) {
							String s1 = list1.get(i);
							int size = list.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}
						writer.write("\r\n");
					}

					writer.close();
				}

				System.out.println("Write success!");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch trans reports
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-trans-report-details/{wk}")
	public ResponseEntity<ResponseModel> getTransReport(HttpServletRequest req, @PathVariable("wk") String wk) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> xm = mservices.getReportDetailsByWeek(wk);
			List<Integer> list = new ArrayList<>();
			list.addAll(Arrays.asList(1, 2, 4, 1, 15, 7, 3, 4, 2, 5, 3, 1, 5, 6, 5, 5, 5, 5, 5, 1, 1, 7, 3, 2, 15, 8,
					20, 10, 2, 11, 15, 8, 7, 2, 2, 4, 10, 7, 8, 7, 11, 5, 4, 11, 4, 11, 4, 11));
			try {

				Calendar call = Calendar.getInstance();
				SimpleDateFormat d23 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String d34 = d23.format(call.getTime());
				String dd = d34.replace(':', '_');

				File f = new File("C:\\report\\" + "TRANS" + dd + ".trans");// curedatewith time
				FileWriter writer = new FileWriter(f, true);

				if (Optional.ofNullable(xm).isPresent()) {

					int year = Calendar.getInstance().get(Calendar.YEAR);
					String st = String.valueOf(year);
					String week = null;

					try {
						List<WeekMasterModel> wks = wservice.getAllWeek();
						for (WeekMasterModel km : wks) {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
							String d2 = d11.format(cal.getTime());
							Date d22 = km.getWeekSdate();
							String d1 = d11.format(d22);
							Date d33 = km.getWeekEdate();
							String d3 = d11.format(d33);

							if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
								week = km.getBataWeek();
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					for (BillPunchDetailsModel vm : xm) {
						BigDecimal value = new BigDecimal(vm.getPurchaseCost());
						String amt = String.valueOf(value);
						String w1 = null;
						String date = null;
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						if (Optional.ofNullable(vm.getResumeInvDate()).isPresent()) {
							Date d1 = vm.getResumeInvDate();
							date = dateFormat.format(d1);
						} else {
							date = "00-00-0000";
						}

						String cndate = dateFormat.format(vm.getCnDate());

						String recdate = dateFormat.format(vm.getRecptInvDate());

						ArticlesMasterModel art = aservices.getArticleDetails(String.valueOf(vm.getArticleCode()));

						List<String> list1 = new ArrayList<>();
						list1.addAll(Arrays.asList(vm.getDcCode(), week, st, String.valueOf(vm.getTrnsportCode()),
								vm.getInvoiceNO(), vm.getArticleCode(), String.valueOf(vm.getSizeCode()), st, week,
								vm.getShopNo(), String.valueOf(vm.getDistcode()), "1", String.valueOf(vm.getRdcPair()),
								String.valueOf(vm.getPair()), String.valueOf(vm.getPackCaseB()),
								String.valueOf(vm.getPackCaseM()), String.valueOf(vm.getPackCaseS()),
								String.valueOf(vm.getPackCaseC()), String.valueOf(vm.getPackCaseT()), vm.getDcCode(),
								vm.getRecStatus(), art.getArtsequenceno(), vm.getPartyCode(), "  ", vm.getCnNo(),
								cndate, String.valueOf(vm.getTrnsportCode()), vm.getRdpermitNo(), vm.getStateCode(),
								amt, vm.getRecptInvNo(), recdate, vm.getResumeInvNo(), date.substring(0, 2),
								date.substring(3, 5), date.substring(6, 10), vm.getResumeInvNoTwo(),
								vm.getBillOrderNo(), "        ", vm.getBillOrderNo(), art.getArtstndcost(), "     ",
								String.valueOf(Integer.parseInt(vm.getCgst()) / 100), String.valueOf(vm.getCgstamt()),
								String.valueOf(Integer.parseInt(vm.getSgst()) / 100), String.valueOf(vm.getSgstamt()),
								String.valueOf(Integer.parseInt(vm.getIgst()) / 100), String.valueOf(vm.getIgstamt())));
						for (int i = 0; i < list1.size(); i++) {
							String s1 = list1.get(i);
							int size = list.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);
						}

						writer.write("\r\n");
					}

					writer.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch contract batch reports
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-contract-batch-report-details/{wk}")
	public ResponseEntity<ResponseModel> getReportForContractBatchFile(HttpServletRequest req,
			@PathVariable("wk") String wk) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		if (response.getStatus().contentEquals("True")) {
			String w1 = null;
			List<AdonisFileDetailsInterface> xm = mservices.getAdonisDetails(wk);
			List<OrdersMasterModel> odm = mservices.getOrderDetails(xm.get(0).getordno());
			TotalAmtInterface totalamt = mservices.getTotalAmountForAdonis(wk);
			AdonisMasterModel ad = adservices.getAdonisDetails();
			String[] st = odm.get(0).getPartyCode().split("");
			String app = null;
			if (st[0].equalsIgnoreCase("9")) {
				app = "APPAREL";
			} else if (st[0].equalsIgnoreCase("8")) {

				app = "CONTRACT";
			} else if (st[0].equalsIgnoreCase("7")) {

				app = "SANDAK";
			}

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String d34 = d23.format(call.getTime());
			String dd = d34.replace(':', '_');
			File f = new File("C:\\report\\" + app + dd + ".txt");
			FileWriter writer = new FileWriter(f, true);

			List<Integer> list = new ArrayList<>();
			list.addAll(Arrays.asList(1, 7, 6, 10, 10, 7, 10, 23, 2, 17, 6, 17, 17, 2));

			List<Integer> list2 = new ArrayList<>();
			list2.addAll(Arrays.asList(1, 4, 4, 8, 6, 5, 4, 4, 30, 1, 17, 30, 10, 3, 14, 10, 23, 28, 10, 6, 7, 12, 11,
					11, 1, 4, 1));

			if (Optional.ofNullable(xm).isPresent()) {

				try {

					String week = null;

					try {
						List<WeekMasterModel> wks = wservice.getAllWeek();
						for (WeekMasterModel km : wks) {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
							String d2 = d11.format(cal.getTime());
							Date d22 = km.getWeekSdate();
							String d1 = d11.format(d22);
							Date d33 = km.getWeekEdate();
							String d3 = d11.format(d33);

							if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
								week = km.getBataWeek();
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					String pattern = "dd/MM/yyyy";
					String cdate = new SimpleDateFormat(pattern).format(new Date());

					String pat = "yyyy";
					String yr = new SimpleDateFormat(pat).format(new Date());

					List<String> list1 = new ArrayList<>();
					list1.addAll(Arrays.asList("C", "ADP3COM", ad.getConBatchNo(), cdate, "RIMS      ",
							yr.concat("/").concat(week), StringUtils.leftPad(app, 10, " "), " BILL ACCOUNTING, WEEK ",
							week, "                 ", String.valueOf(xm.size()), totalamt.gettotalcost(),
							totalamt.gettotalcost(), "  "));

					for (int i = 0; i < list1.size(); i++) {
						String s1 = list1.get(i);
						int size = list.get(i);

						if (Optional.ofNullable(s1).isPresent()) { // for present value
							String s = s1;
							StringBuilder buf = new StringBuilder(s);
							if (buf.length() == size) { // for equal size
								String output = buf.toString();
								w1 = output;
							} else {

								if (buf.length() < size) { // for less size

									while (buf.length() < size) {
										buf.insert(0, '0');
										String output = buf.toString();
										w1 = output;
									}
								} else { // for greater size
									String sx = buf.substring(0, size);
									w1 = sx;
								}
							}

						} else { // for null value
							StringBuilder buf = new StringBuilder(0);
							while (buf.length() < size) {
								buf.insert(0, '0');
								String output = buf.toString();
								w1 = output;
							}
						}
						writer.write(w1);

					}
					writer.write("\r\n");

				} catch (Exception e) {
					e.printStackTrace();
				}

				for (AdonisFileDetailsInterface vm : xm) {

					ShopMasterModel sp = sservices.getShopDetails("0".concat(vm.getrdcno()));
					DistMasterModel dt = dservices.getDistDetails(sp.getDistrictno());

					String pattern = "dd/MM/yyyy";
					String curdate = new SimpleDateFormat(pattern).format(new Date());

					Date d1 = vm.getinvdate();
					Date d2 = vm.getgrndate();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String date1 = dateFormat.format(d1);
					String date2 = dateFormat.format(d2);
					List<String> list3 = new ArrayList<>();
					list3.addAll(Arrays.asList("D", "0001", "686 ", "1041    ", StringUtils.rightPad(" ", 10, " "),
							StringUtils.rightPad("", 5, " "), StringUtils.rightPad("", 4, " "),
							StringUtils.rightPad("", 4, " "), StringUtils.rightPad(vm.getinvno(), 30, " "), "C",
							vm.getinvcost(), "Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "),
							"INR", StringUtils.rightPad(" ", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
							StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
							StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 6, "0"), vm.getpairs(),
							StringUtils.rightPad(vm.getparty(), 11, " "), StringUtils.rightPad("", 11, " "), "V",
							StringUtils.rightPad("", 14, " "), "I"));

					for (int i = 0; i < list3.size(); i++) {
						String s1 = list3.get(i);
						int size = list2.get(i);

						if (Optional.ofNullable(s1).isPresent()) {
							String s = s1;
							StringBuilder buf = new StringBuilder(s);
							if (buf.length() == size) {
								String output = buf.toString();
								w1 = output;
							} else {

								if (buf.length() < size) {

									while (buf.length() < size) {
										buf.insert(0, '0');
										String output = buf.toString();
										w1 = output;
									}
								} else {
									String sx = buf.substring(0, size);
									w1 = sx;
								}
							}

						} else {
							StringBuilder buf = new StringBuilder(0);
							while (buf.length() < size) {
								buf.insert(0, '0');
								String output = buf.toString();
								w1 = output;
							}
						}
						writer.write(w1);

					}
					writer.write("\r\n");

					List<String> list4 = new ArrayList<>();
					list4.addAll(Arrays.asList("D", "0002", "686 ", "1199    ", "1101C ", "686  ",
							StringUtils.rightPad("", 4, " "), "07  ", StringUtils.rightPad(vm.getinvno(), 30, " "), "D",
							vm.getinvcost(), "Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "),
							"INR", StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
							StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
							StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 6, "0"), vm.getpairs(),
							StringUtils.rightPad("", 11, " "), StringUtils.rightPad("", 11, " "), "G",
							StringUtils.rightPad("", 4, " "), StringUtils.rightPad("", 1, " ")));

					for (int i = 0; i < list4.size(); i++) {
						String s1 = list4.get(i);
						int size = list2.get(i);

						if (Optional.ofNullable(s1).isPresent()) {
							String s = s1;
							StringBuilder buf = new StringBuilder(s);
							if (buf.length() == size) {
								String output = buf.toString();
								w1 = output;
							} else {

								if (buf.length() < size) {

									while (buf.length() < size) {
										buf.insert(0, '0');
										String output = buf.toString();
										w1 = output;
									}
								} else {
									String sx = buf.substring(0, size);
									w1 = sx;
								}
							}

						} else {
							StringBuilder buf = new StringBuilder(0);
							while (buf.length() < size) {
								buf.insert(0, '0');
								String output = buf.toString();
								w1 = output;
							}
						}
						writer.write(w1);

					}

					writer.write("\r\n");
					List<String> list5 = new ArrayList<>();
					list5.addAll(Arrays.asList("D", "0003", "686 ", "1500    ", "01C   ", "686  ",
							StringUtils.rightPad("", 4, " "), "07  ", StringUtils.rightPad(vm.getinvno(), 30, " "), "C",
							vm.getinvcost(), "Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "),
							"INR", StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
							StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
							StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 6, "0"), vm.getpairs(),
							StringUtils.rightPad("", 11, " "), StringUtils.rightPad("", 11, " "), "G",
							StringUtils.rightPad("", 4, " "), StringUtils.rightPad("", 1, " ")));

					for (int i = 0; i < list5.size(); i++) {
						String s1 = list5.get(i);
						int size = list2.get(i);

						if (Optional.ofNullable(s1).isPresent()) {
							String s = s1;
							StringBuilder buf = new StringBuilder(s);
							if (buf.length() == size) {
								String output = buf.toString();
								w1 = output;
							} else {

								if (buf.length() < size) {

									while (buf.length() < size) {
										buf.insert(0, '0');
										String output = buf.toString();
										w1 = output;
									}
								} else {
									String sx = buf.substring(0, size);
									w1 = sx;
								}
							}

						} else {
							StringBuilder buf = new StringBuilder(0);
							while (buf.length() < size) {
								buf.insert(0, '0');
								String output = buf.toString();
								w1 = output;
							}
						}
						writer.write(w1);

					}

					writer.write("\r\n");
					List<String> list6 = new ArrayList<>();
					list6.addAll(Arrays.asList("D", "0004", "686 ", "1057    ",
							StringUtils.rightPad(dt.getDistconceptcontrollinggroup(), 6, " "), "686  ",
							StringUtils.rightPad("", 4, " "), StringUtils.rightPad("", 4, " "),
							StringUtils.rightPad(vm.getinvno(), 30, " "), "D", vm.getinvcost(),
							"Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "), "INR",
							StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
							StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
							StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 6, "0"), vm.getpairs(),
							StringUtils.rightPad("", 11, " "), StringUtils.rightPad("", 11, " "), "G",
							StringUtils.rightPad("", 4, " "), StringUtils.rightPad("", 1, " ")));

					for (int i = 0; i < list6.size(); i++) {
						String s1 = list6.get(i);
						int size = list2.get(i);

						if (Optional.ofNullable(s1).isPresent()) {
							String s = s1;
							StringBuilder buf = new StringBuilder(s);
							if (buf.length() == size) {
								String output = buf.toString();
								w1 = output;
							} else {

								if (buf.length() < size) {

									while (buf.length() < size) {
										buf.insert(0, '0');
										String output = buf.toString();
										w1 = output;
									}
								} else {
									String sx = buf.substring(0, size);
									w1 = sx;
								}
							}

						} else {
							StringBuilder buf = new StringBuilder(0);
							while (buf.length() < size) {
								buf.insert(0, '0');
								String output = buf.toString();
								w1 = output;
							}
						}
						writer.write(w1);

					}

					writer.write("\r\n");

				}
				writer.close();

				Integer nt = Integer.parseInt(ad.getConBatchNo()) + 1;

				ad.setConBatchNo(String.valueOf(nt));
				adservices.save(ad);

			}

		} else

		{
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All bill punch edp reports
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-xl-report-details-for-supply/{wk}")
	public ResponseEntity<ResponseModel> getXlReport(HttpServletRequest req, @PathVariable("wk") String wk)
			throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> xm = services.findAllApprovedDetails(wk);
			List<OrdersMasterModel> odm = mservices.getOrderDetails(xm.get(0).getBillOrderNo());
			String[] st = odm.get(0).getPartyCode().split("");
			String app = null;
			if (st[0].equalsIgnoreCase("9")) {
				app = "APPAREL";
			} else if (st[0].equalsIgnoreCase("8")) {

				app = "CONTRACT";
			} else if (st[0].equalsIgnoreCase("7")) {

				app = "SANDAK";
			}

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String d34 = d23.format(call.getTime());
			String dd = d34.replace(':', '_');

			String excelFilePath = "C:\\report\\" + "SUPPLY" + app + dd + ".xlsx";

			try {

				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("XlTest");

				writeHeaderLine(sheet);

				writeDataLines(xm, sheet);

				FileOutputStream outputStream = new FileOutputStream(excelFilePath);
				workbook.write(outputStream);
				workbook.close();

			} catch (SQLException e) {
				System.out.println("Datababse error:");
				e.printStackTrace();
			}

		} else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	@SuppressWarnings("all")
	private void writeHeaderLine(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		List<String> list1 = new ArrayList<>();
		list1.addAll(Arrays.asList("FROM LOCATION CODE", "FROM LOCATION", "PARTY CODE", "PARTY NAME", "STATE CODE",
				"STATE NAME", "TO LOCATION CODE", "TO LOCATION", "REGION", "INVOICE NO", "INVOICE DATE", "BOOKING",
				"PAIRS", "TAXABLE VALUE", "GST", "IGST", "FREIGHT", "CGST", "NET AMOUNT", "GR NO", "GR DATE", "PO NO",
				"PO DATE", "VENDOR TIN NO.", "MRP", "WSP"));

		for (int i = 0; i < list1.size(); i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(list1.get(i));
		}

	}

	@SuppressWarnings("all")
	private void writeDataLines(List<BillPunchDetailsModel> result, XSSFSheet sheet) throws SQLException {
		int rowCount = 1;

		for (BillPunchDetailsModel xm : result) {
			Row row = sheet.createRow(rowCount++);

			StateMasterModel ms = stservices
					.getStateDetails(StringUtils.leftPad(String.valueOf(xm.getFromState()), 2, "0"));

			StateMasterModel msc = stservices
					.getStateDetails(StringUtils.leftPad(String.valueOf(xm.getToState()), 2, "0"));

			List<OrdersMasterModel> ord = null;
			try {
				ord = services.getDetailsAtrnoAndOrdnoDetails(xm.getArticleCode(), xm.getBillOrderNo(),
						xm.getPartyCode(), xm.getRdcCode());
			} catch (Exception e) {
			}

			Date d1 = xm.getRecptInvDate();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = dateFormat.format(d1);
			Date d2 = xm.getGrDate();
			String date2 = dateFormat.format(d2);
			Date d3 = xm.getBillOrderDate();
			String date3 = dateFormat.format(d3);

			Double totaltax = xm.getGstamt() + xm.getIgstamt() + xm.getCgstamt() + xm.getSgstamt()
					+ Integer.parseInt(xm.getFreight()) + xm.getTcsValue();
			List<String> list1 = new ArrayList<>();
			list1.addAll(Arrays.asList(String.valueOf(xm.getFromState()), ms.getStateName(), xm.getPartyCode(),
					xm.getPartyName(), String.valueOf(xm.getToState()), msc.getStateName(), xm.getShopNo(),
					xm.getShopName(), xm.getRegion(), xm.getInvoiceNO(), date1,
					xm.getWeekYear() + "/" + xm.getBillWeekYear(), String.valueOf(xm.getPair()),
					String.valueOf(totaltax), String.valueOf(xm.getGstamt()), "0", xm.getFreight(), "0",
					String.valueOf(xm.getTotalCost()), xm.getGrNo(), date2, xm.getBillOrderNo(), date3, ms.getGstIn(),
					ord.get(0).getMrp(), xm.getInvoiceNO()));
			for (int i = 0; i < 22; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(list1.get(i));

			}

		}

	}

	/********************************************************************************************
	 * Find
	 * 
	 * @throws IOException
	 * @throws ParseException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-xl-data-for-import")
	public void getXlDataForImport(HttpServletRequest req) throws IOException, ParseException {

		// @Scheduled(fixedDelay = 1000 * 30)
		// @Scheduled(cron = "0 5 */7 * * ?", zone = "Indian/Maldives")
		// public void getXlDataForImport() throws IOException, ParseException {

		String excelFilePath = "C:\\report\\RDC_RECV.xls";

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		Workbook workbook = new HSSFWorkbook(inputStream);
		if (Optional.ofNullable(workbook).isPresent()) {

			List<BillPunchDetailsModel> list = new ArrayList<BillPunchDetailsModel>();

			for (Sheet sh : workbook) {

				for (Row r : sh) {
					if (r.getRowNum() == 0) {

					} else {
						try {

							String x1 = r.getCell(0).getStringCellValue();

							Integer x2 = Integer.parseInt(r.getCell(1).getStringCellValue());

							Integer x3 = Integer.parseInt(r.getCell(2).getStringCellValue());

							Integer x4 = Integer.parseInt(r.getCell(3).getStringCellValue());

							Date x5 = r.getCell(4).getDateCellValue();

							Integer x6 = Integer.parseInt(r.getCell(5).getStringCellValue());

							String x7 = r.getCell(6).getStringCellValue().trim();

							String x8 = r.getCell(7).getStringCellValue();

							Integer x9 = Integer.parseInt(r.getCell(8).getStringCellValue());

							String x10 = r.getCell(9).getStringCellValue();

							String x11 = r.getCell(10).getStringCellValue();

							String x12 = r.getCell(11).getStringCellValue();

							Integer x13 = Integer.parseInt(r.getCell(12).getStringCellValue());

							Integer x14 = Integer.parseInt(r.getCell(13).getStringCellValue());

							Integer x15 = Integer.parseInt(r.getCell(14).getStringCellValue());

							Integer x16 = Integer.parseInt(r.getCell(15).getStringCellValue());

							Integer x17 = Integer.parseInt(r.getCell(16).getStringCellValue());

							Integer x18 = Integer.parseInt(r.getCell(17).getStringCellValue());

							Integer x19 = Integer.parseInt(r.getCell(18).getStringCellValue());

							Integer x20 = Integer.parseInt(r.getCell(19).getStringCellValue());

							Integer x21 = Integer.parseInt(r.getCell(20).getStringCellValue());

							String x22 = r.getCell(21).getStringCellValue();

							String x24 = r.getCell(23).getStringCellValue();

							String x25 = r.getCell(24).getStringCellValue();

							String x28 = r.getCell(27).getStringCellValue();

							String d29 = r.getCell(28).getStringCellValue();
							String s2 = d29.substring(2, 4);
							String s3 = d29.substring(4, 8);
							String sn = d29.substring(0, 2).concat("-") + s2.concat("-") + s3;
							Date x29 = new SimpleDateFormat("dd-MM-yyyy").parse(sn);

							Integer x30 = Integer.parseInt(r.getCell(29).getStringCellValue());

							String x33 = r.getCell(32).getStringCellValue();

							Double x34 = Double.valueOf(r.getCell(33).getStringCellValue());

							String x35 = r.getCell(34).getStringCellValue().trim();

							String d36 = r.getCell(35).getStringCellValue();

							String s22 = d36.substring(2, 4);
							String s33 = d36.substring(4, 8);
							String snn = d36.substring(0, 2).concat("-") + s22.concat("-") + s33;
							Date x36 = new SimpleDateFormat("dd-MM-yyyy").parse(snn);

							String x40 = r.getCell(39).getStringCellValue().trim();

							System.out.println(r.getCell(40).getStringCellValue());
							String x41 = r.getCell(40).getStringCellValue();

							Double x42 = Double.valueOf(r.getCell(41).getStringCellValue());

							String x43 = r.getCell(42).getStringCellValue();

							Double x44 = Double.valueOf(r.getCell(43).getStringCellValue());

							String x45 = r.getCell(44).getStringCellValue();

							Double x46 = Double.valueOf(r.getCell(45).getStringCellValue());

							String x47 = r.getCell(46).getStringCellValue();

							Double x48 = Double.valueOf(r.getCell(47).getStringCellValue());

							Double x49 = Double.valueOf(r.getCell(48).getStringCellValue());
							Integer x50 = Integer.parseInt(r.getCell(49).getStringCellValue());

							Integer x51 = Integer.parseInt(r.getCell(50).getStringCellValue());

							BillPunchDetailsModel xm = new BillPunchDetailsModel();

							xm.setRdcCode(x1);
							xm.setBillCloseWeek(x2);
							xm.setBillCloseYear(x3);
							xm.setBillWeekDay(x4);
							xm.setTranDate(x5);
							xm.setTranCode(x6);
							xm.setInvoiceNO(x7);
							xm.setArticleCode(x8);
							try {
								xm.setArticleName(
										aservices.getArticleDetails(r.getCell(7).getStringCellValue()).getArtname());
							} catch (Exception e) {
							}

							xm.setSizeCode(x9);
							xm.setBillWeekYear(x10);
							xm.setWeekYear(x11);
							xm.setShopNo(x12);
							try {
								xm.setShopName(
										sservices.getShopDetails(r.getCell(11).getStringCellValue()).getShopname());
							} catch (Exception e) {
							}

							xm.setDistcode(x13);
							xm.setInvType(x14);
							xm.setRdcPair(x15);
							xm.setPair(x16);
							xm.setPackCaseB(x17);
							xm.setPackCaseM(x18);
							xm.setPackCaseS(x19);
							xm.setPackCaseC(x20);
							xm.setPackCaseT(x21);
							xm.setDcCode(x22);
							xm.setBillUniqueCode(x24);
							xm.setParty(x25);

							try {
								xm.setPartyCode(mservices.getOrderDetails(x40).get(0).getPartyCode());

							} catch (Exception e) {
							}

							try {
								xm.setPartyName(pservices
										.getPartiesDetails(
												StringUtils.leftPad(r.getCell(24).getStringCellValue(), 4, "0"))
										.getPartyfullname());
							} catch (Exception e) {
							}

							xm.setCnNo(x28);
							xm.setCnDate(x29);
							xm.setGrnDate(x29);
							xm.setTrnsportCode(x30);
							xm.setStateCode(x33);
							try {
								xm.setStateName(
										stservices.getStateDetails(r.getCell(32).getStringCellValue()).getStateName());
							} catch (Exception e) {
							}

							xm.setInvoiceCost(x34);
							xm.setRecptInvNo(x35);
							xm.setGrNo(x35);
							xm.setRecptInvDate(x36);
							xm.setGrDate(x36);
							xm.setBillOrderNo(x40);
							xm.setBillOrderDate(x36);
							xm.setHsnCode(x41);
							xm.setGstamt(x42);
							// xm.setGstamt(x44+x46+x48);
							xm.setCgst(x43);
							xm.setCgstamt(x44);
							xm.setSgst(x45);
							xm.setSgstamt(x46);
							xm.setIgst(x47);
							xm.setIgstamt(x48);
							xm.setPairAmount(x49);
							xm.setFromState(x50);
							xm.setToState(x51);
							xm.setStatus("RECORD_RECEIVED");
							BillPunchDetailsModel demo = services.saveXl(xm);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

			}

		}

	}

}
