package com.xietong.automation.fm.zrf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfigFile {
	/**
	 * @param key
	 * @return
	 */
	public  String getSystemConfig(String key) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("system-config.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	
	
	
	
	public static void main(String[] args) {
		LoadConfigFile load = new LoadConfigFile();
		String ip = load.getSystemConfig("ip-address");
		System.out.println("ip-address is =================>"+ip);
	}
}
