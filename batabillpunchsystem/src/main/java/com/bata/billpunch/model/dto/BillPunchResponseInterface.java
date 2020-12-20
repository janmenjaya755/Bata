package com.bata.billpunch.model.dto;

import java.util.Date;

public interface BillPunchResponseInterface {

	public String getformtype();

	public String getpartyCode();

	public String getpartyName();

	public Date getbillOrderDate();

	public String getbillOrderNo();

	public Double getinvAmount();

	public Double getrdcAmount();

	public String getstatus();

	public String getarticleCode();
	
	public String getpurchaseCost();
	
	public String getpurchaseoffValue();

	public String getinvoiceNO();

	public String getpair();

	public String getdiscountAmt();

	public String getinvdate();
	
	public String getbillWeek();
	
	public String getcnDate();

	public String gettcsApplicable();
	
	public String gettcsPercent();
	

}
