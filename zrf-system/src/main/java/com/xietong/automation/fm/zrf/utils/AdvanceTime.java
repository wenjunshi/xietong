package com.xietong.automation.fm.zrf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvanceTime {
	
	public static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		return sdf.format(new Date());
	}
	
    public static String getDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	return sdf.format(new Date());
    }
    
	public static void main(String[] args) {
        FileUtil.generateFileName("finance");
		FileUtil.WriteFileName2Txt("finance","D:/filename");
		//System.out.println(getCurrentTime());
		//FileUtil.WriteFileName2Txt("position");
	}
	
	public static void wait(int n){
		if(n>0){
			try {
				Thread.sleep(n*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			  try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
   }
	public  static void mswait(long n){
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	
}