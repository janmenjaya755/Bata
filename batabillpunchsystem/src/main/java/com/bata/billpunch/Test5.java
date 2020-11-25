package com.bata.billpunch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test5 {
	
	public static void main(String[] args) throws ParseException {
		
	//	String s="12-02-2020";
		//Date x36 = new SimpleDateFormat("dd-MM-yyyy").parse(s);
		//System.out.println(x36);
		//String date1="24-09-2020";
		//DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
     //   String d1 = dateFormat.format(date1);
      //  System.out.println(d1);
        //System.out.println(date1.substring(0, 2));
        	//	System.out.println(date1.substring(3, 5));
        				//System.out.println(date1.substring(6, 10));
        
         String pattern = "2020-23-12 23:44:33";
         String pat=pattern.replace(':', '_');
         
       // String sds =new SimpleDateFormat(pattern).format(new Date());
        System.out.println(pat);
        
	}

}
