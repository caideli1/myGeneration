package com.caideli.springBoot.service.excel;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 判断字符是否空白 null和""返回true，其他返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str)
			return true;
		return "".equals(str.trim());
	}

	/**
	 * 判断字符是否非空白 null和""返回false，其他返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String getString(Object str) {
		if (null == str)
			return "";
		return str.toString();
	}

	/**
	 * 手机号码格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str,int length) {
		if (StringUtils.isBlank(str))
			return false;
		Pattern p = Pattern.compile("^\\d{"+length+"}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * 内地手机号码格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		   if (StringUtils.isBlank(str))
		      return false;
		   Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
		   Matcher m = p.matcher(str);
		   return m.matches();
	}

	/**
	 * 邮箱格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return str.matches(regex);
	}

	/**
	 * 身份证格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str) {
		String regex = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";
		return str.matches(regex);
	}

	/**
	 * 中文校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regex = "[\u4e00-\u9fa5]*";
		return str.matches(regex);
	}

	/**
	 * 获取uuid(已去掉横杠-)
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 为数字补零
	 * 
	 * @param num
	 *            数字
	 * @param len
	 *            位数
	 * @return
	 */
	public static String addZeros(int num, int len) {
		String str = num + "";
		while (str.length() < len) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 生成随机字符（包括数字和字母）
	 * 
	 * @param count
	 * @return
	 */
	public static String randomString(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

	/**
	 * 生成随机字母
	 * 
	 * @param count
	 * @return
	 */
	public static String randomLetters(int count) {
		return RandomStringUtils.randomAlphabetic(count);
	}

	/**
	 * 生成随机数字
	 * 
	 * @param count
	 * @return
	 */
	public static String randomNumber(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	public static String getSortMobile(String mobile) {
		String m = "***" + mobile.substring(7);
		return m;
	}
	
	@Deprecated
	public static String getRandomString(int length) { 
		return RandomStringUtils.random(length, true, true).toUpperCase();
	}
	
	
	/**
	 * 
	 * Reason:   随机生成
	 * Date:     2017年6月8日 下午2:30:14
	 * @author   hebingjiu
	 * @version  
	 * @see
	 */
	public static String generateBillNo(String prefix, Date time, int totalLength) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = df.format(time);
		String orderPrefix = prefix + nowTime;
		String gen = RandomStringUtils.randomNumeric(totalLength - orderPrefix.length());
		String orderNum = orderPrefix + gen;
		return orderNum;
	}

	/**
	 * 验证密码
	 * @param input
	 * @return
	 */
	public static boolean rexCheckPassword(String input) {
		// 6-20 位，字母、数字、特定字符
		//String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
		String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>]){6,20}$";
		return input.matches(regStr);
	}

	public static boolean isDigits(String input){
		String regex = "^\\d+$";
		return Pattern.matches(regex, input);
	}

	/**
	 * 提出一个字符串中的特殊字符，只保留字母和数字
	 * @param cs
	 * @return
	 */
	public static String getAlphanumeric(CharSequence cs) {
		if (org.apache.commons.lang3.StringUtils.isBlank(cs)) {
			return "";
		} else {
			int sz = cs.length();
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < sz; ++i) {
				if (Character.isLetterOrDigit(cs.charAt(i))) {
					stringBuffer.append(cs.charAt(i));
				}
			}
			return stringBuffer.toString();
		}
	}

	/**
	 * 剔除一个字符串中的特殊字符，只保留字母
	 */
	public static String getAlph(CharSequence cs) {
		if (org.apache.commons.lang3.StringUtils.isBlank(cs)) {
			return "";
		} else {
			int sz = cs.length();
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < sz; ++i) {
				if (Character.isLetter(cs.charAt(i))) {
					stringBuffer.append(cs.charAt(i));
				}
			}
			return stringBuffer.toString();
		}
	}



	public static void main(String[] args){
		// System.out.println(rexCheckPassword("19xxxxxxxx"));
		System.out.println(getAlphanumeric("3344  &&^^% ((( 999922 SSSSS sss &7*8"));
	}



}
