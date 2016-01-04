package com.uhoo.uhooapi.web.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期格式化帮助类
 * @author yang.yun
 *
 */
public class DateFormatUtil {
	
	public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

	public static String timestamp2Str(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR);
		Date date = null;
		if (timestamp != null) {
			date = new Date(timestamp.getTime());
			return sdf.format(date);
		}else{
			return "";
		}
	}

	public static String date2Str(Date date) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR);
		return sdf.format(date);
	}

}
