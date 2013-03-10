package com.shop.inventory.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;

import org.jboss.seam.core.Expressions;

import com.shop.inventory.web.utils.WebUtils;

public class AppUtils {

	public static SimpleDateFormat formatter_thai = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
	public static SimpleDateFormat formatter_thaiPresent = new SimpleDateFormat("dd/MM/yy", new Locale("th", "TH"));
	public static Locale thLocale = new Locale("th", "TH");
	public static Object getObjectByEL(String expressionlabel){
		Object  obj = null;
		try{
			obj = Expressions.instance().createValueExpression(expressionlabel).getValue();
		}catch(Exception ex){
			obj = null;
		}
		return obj;
	}
	
	public static String getMessageByEL(String keyName){
		String expressionlabel = "#{messages['"+keyName+"']}";
		Object obj = getObjectByEL(expressionlabel);
		if(obj!=null)
			return (String)obj;
		return "";
	}
	
	public static String dateToString(Date date, String pattern, Locale locale){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat(pattern,locale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static boolean isNullOrEmpty(Object obj){
		
		if(obj == null){
			return true;
		}
		
		if(obj.getClass().equals(String.class)){
			if(obj.toString().trim().length() == 0){
				return true;
			}
		}
		
		return false;
	}
	
	public static long calDateDiff(Date fromDate, Date toDate){
		
		long result = 0;
		
		if(fromDate != null && toDate != null){
			Calendar frmCal = Calendar.getInstance();
			Calendar toCal = Calendar.getInstance();
			
			frmCal.setTime(fromDate);
			toCal.setTime(toDate);
			
			if(frmCal.before(toCal)){
				result = ((toCal.getTimeInMillis()-frmCal.getTimeInMillis())/86400000)+1;
			}
		}
		
		return result;
	}
	
	public static boolean endDateNotBeforeStartDate(Date startDate, Date endDate){
		
		try{
			if(startDate != null && endDate != null){
				Calendar startCal = Calendar.getInstance();
				Calendar endCal = Calendar.getInstance();
				
				startCal.setTime(startDate);
				endCal.setTime(endDate);
				
				return startCal.before(endCal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static Date  getDateWithoutTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static String dateToString(Date date){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat("dd/MM/yyyy", thLocale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static String dateToString(Date date, String pattern){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat(pattern,thLocale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static String formatPatternDateStr(String dateStr, String pattern){
		
		Date dateVal = stringToDate(dateStr);
		
		if(dateVal != null){
			try{
				dateStr = new SimpleDateFormat(pattern, thLocale).format(dateVal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateStr;
	}
	
	public static String getMimeType(String fileName){
		if(fileName != null && fileName.trim().length() > 0){
			return new MimetypesFileTypeMap().getContentType(fileName);
		}else{
			return null;
		}
	}
	
	public static String formatPatternDateStr(String dateStr, String pattern, Locale locale){
		
		Date dateVal = stringToDate(dateStr);
		
		if(dateVal != null){
			try{
				dateStr = new SimpleDateFormat(pattern, locale).format(dateVal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateStr;
	}
	
	public static Date stringToDate(String dateStr){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat("dd/MM/yyyy", thLocale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	
	public static Date stringToDate(String dateStr, String pattern){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat(pattern, thLocale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static Date stringToDate(String dateStr, String pattern, Locale locale){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat(pattern, locale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static String numberFormater(Object num, String... patterns){
		try{
			if(num == null){
				num = 0;
			}
			
			String defaultPattern = "#,###,##0.00";
			if(patterns.length > 0){
				defaultPattern = patterns[0];
			}
			
			NumberFormat formatter = new DecimalFormat(defaultPattern);
			return formatter.format(num);
		}catch (Exception e) {
			return "0";
		}
	}
	
	public static Double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static Double roundTwoDecimalsFloor(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		twoDForm.setRoundingMode(RoundingMode.FLOOR);
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static Double roundTwoDecimalsHalfUp(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		twoDForm.setRoundingMode(RoundingMode.HALF_UP);
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static BigDecimal stringToBigDecimal(String value){
		
		BigDecimal result = new BigDecimal(0);
		
		if(value != null && value.trim().length() > 0){
			
			try{
				
				String tmpVal = value.replace(",", "");
				result = new BigDecimal(tmpVal);
				return result;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	public static Double numStrToDouble(String value){
		
		Double result = 0D;
		
		if(value != null && value.trim().length() > 0){
			
			try{
				
				String tmpVal = value.replace(",", "");
				result = Double.parseDouble(tmpVal);
				return result;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	public static String getServletDownloadUrl(String productCode, String fname, String type){
		String url ="";
		String context = WebUtils.getHostContextUrl()+"/attach_file/downloadfile";
		
		String param1 = "projid="+productCode;
		String param2 = "fname="+fname;
		String param3 = "ptype="+type;
		
		if(productCode != null){
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}
		
		return url;
	}
	
	public static Double roundUpDoubleValue(Double doubleVal){
		try{
			if(doubleVal != null){
				int decimalPlace = 2;
				BigDecimal bd = new BigDecimal(doubleVal);
			    bd = bd.setScale(decimalPlace ,BigDecimal.ROUND_UP);
			    return bd.doubleValue();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return 0D;
	}
	
	public static String generateProductCodeStr(){
		
		Long code = null;
		long start = 1000000;
		long end = 9999999;
		long range = end - start + 1;
		Random rand = new Random();
		
		long fraction = (long) (range * rand.nextDouble());
		code = fraction + start;
	
		return code.toString();
	}
}
