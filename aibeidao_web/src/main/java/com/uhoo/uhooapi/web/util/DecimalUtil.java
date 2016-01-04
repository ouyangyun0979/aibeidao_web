package com.uhoo.uhooapi.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.uhoo.uhooapi.app.api.App;


public class DecimalUtil {

	public static Double formatDecimal(Double d,int num){
		if(d!=null){
			 BigDecimal b=new BigDecimal(d); 
			 Double f1=b.setScale(num,BigDecimal.ROUND_HALF_UP).doubleValue();  
			 return f1;
		}else{
			return 0d;
		}
	}
	
	public static Double formatDecimal(Double d){
		 return formatDecimal(d,2);
	}
	
	public static String formatToString(Double data) {
		try {
			if (data == null) {
				return "";
			} else {
				DecimalFormat df = new DecimalFormat("#0.00");
				return df.format(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String formatToString4Mio(Double data) {
		try {
			if (data == null) {
				return "";
			} else {
				Double newData = data / App.STATIC_MILLION;
				DecimalFormat df = new DecimalFormat("#0.00");
				return df.format(newData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String formatToString(Integer data) {
		try {
			if (data == null || data == 0) {
				return "";
			} else {
				DecimalFormat df = new DecimalFormat("#0.00");
				return df.format(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		Double d=3.2259999999914d;
		System.out.println(DecimalUtil.formatDecimal(d,2));
	}
}
