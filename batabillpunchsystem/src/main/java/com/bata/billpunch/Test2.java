package com.bata.billpunch;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
public class Test2 {
public static void main(String[] args) {
	 String test=null;
	String input = "Apple";
	StringBuffer buf = new StringBuffer(input);
	//System.out.println("length is"+buf.length());
	
	if(buf.length() < 8) {
		while (buf.length() < 8) {
			  buf.insert( 0,'0');
			 String output = buf.toString();
			  test=output;
			//  System.out.println(test);
			  //System.out.println("xxxxxxxxxxxxx");
			  
			}
	}else {
		//System.out.println("hhhhhhhhhhhhhhhhh");
	    String sx=buf.substring(0,8);
	     test=sx;
	}
	//List<String> list6 = new ArrayList<>();
	//list6.add( StringUtils.rightPad("012345", 10, "0"));
	//System.out.println(list6.get(0));
//	String s=" ";
	//System.out.println( StringUtils.rightPad("", 10, " ") );
	//System.out.println( StringUtils.rightPad("0123", 10, "0") );
	//System.out.println( StringUtils.rightPad("012345", 10, "0") );
	//System.out.println( StringUtils.leftPad("012345", 10, "0") );
	
    
	Calendar cal = Calendar.getInstance();
	//System.out.println(cal);
	//SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	//String d2 = d11.format(cal.getTime());
	//Date d22 = km.getWeekSdate();
	//String d1 = d11.format(d22);
	System.out.println("0".concat("Jannny")); 
	//System.out.println(test);
	
	
}
}
