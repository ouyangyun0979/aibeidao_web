package com.uhoo.uhooapi.app.api.util;

import java.text.DecimalFormat;

public class DecimalFormatUtil {
   public static String formatToString(Double data){
	   try{
		   if(data==null){
			  return ""; 
		   }else{
			   DecimalFormat df = new DecimalFormat("#0.00");
		       return df.format(data);
		   }
	   }catch(Exception e){
		   e.printStackTrace();
		   return "";
	   }
   }
   public static String formatToString(Integer data){
	   try{
		   if(data==null||data==0){
			  return ""; 
		   }else{
			   DecimalFormat df = new DecimalFormat("#0.00");
		       return df.format(data);
		   }
	   }catch(Exception e){
		   e.printStackTrace();
		   return "";
	   }
   }
   public static void main(String[] args) {
	   System.out.println(DecimalFormatUtil.formatToString((Double)null));
   }
}
