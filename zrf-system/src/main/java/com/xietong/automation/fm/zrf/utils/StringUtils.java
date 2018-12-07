package com.xietong.automation.fm.zrf.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isIPAddress(String ip) {
		if (ip.length() < 7 || ip.length() > 15 || "".equals(ip)) {
			return false;
		}
		String regx = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(ip);
		return matcher.find();
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 获取工程目录
	 * 
	 * @return
	 */
	public static String getUserdir() {
		String dir = System.getProperty("user.dir");
		return dir;
	}

	public static void main(String[] args) {
		System.out.println(isIPAddress("255.25.25.67"));
	}
}
