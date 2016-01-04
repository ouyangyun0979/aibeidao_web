package com.uhoo.uhooapi.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.uhoo.uhooapi.web.util.RandomSecurityCode.SecurityCodeLevel;



public class CommonUtil {
	
	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	/** 验证邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    
    /** 验证手机
     * @param email
     * @return
     */
    public static boolean isPhone(String phone){
    	boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^\\d{11}$");
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    /**
     * 生成随机数字
     * @param num
     * @return
     */
    public static String getRadomCode(int num){
    	RandomSecurityCode radomcode=new RandomSecurityCode();
    	char[] SecurityCode=radomcode.getSecurityCode(num,SecurityCodeLevel.Simple,true);
    	String securitystr=String.valueOf(SecurityCode);
    	return securitystr;
    }
    
}
