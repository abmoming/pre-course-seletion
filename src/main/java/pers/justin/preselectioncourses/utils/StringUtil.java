package pers.justin.preselectioncourses.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * @author SJG
 *
 */
public class StringUtil {
	/**
	 * 判断是否为数字
	 * @param str
	 * @return true 是；false 不是
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	
	public static boolean isNullNEmpty(String str){
		if (str == null || str.isEmpty())
			return true;
		return false;
	}
	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		if(str!=null&&!"".equals(str.trim())){
			return true;
		}
		return false;
	}

	public static String lpad(String source, int length, char fillChar) {
		
		if (source == null || source.length() >= length)
			return source;

		StringBuilder result = new StringBuilder(length);
		int len = length - source.length(); 
		for (; len > 0; len--) {
			result.append(fillChar);
		}
		result.append(source);
		
		return result.toString();
	}

	public static String rpad(String source, int length, char fillChar) {
		
		if (source == null || source.length() >= length)
			return source;

		StringBuilder result = new StringBuilder(length);
		int len = length - source.length();
		result.append(source);
		for (; len > 0; len--) {
			result.append(fillChar);
		}
		return result.toString();
	}
	
	public static String isPhone(String phone) {
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	        return "手机号应为11位数";
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches(); 
	        if (!isMatch) { 
		        return "请填入正确的手机号";
	        }
	    }
	    
	    return "ok";
	}

}

