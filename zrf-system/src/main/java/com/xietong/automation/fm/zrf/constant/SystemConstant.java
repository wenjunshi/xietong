
package com.xietong.automation.fm.zrf.constant;

import com.xietong.automation.fm.zrf.utils.LoadConfigFile;



public interface SystemConstant {
	
	static LoadConfigFile load = new LoadConfigFile();
	public  static final String APP_PATH =load.getSystemConfig("app-path");
	public  static final String USERNAME =load.getSystemConfig("username");
	public  static final String PASSWORD=load.getSystemConfig("password");
	//账户资金操作-导出路径
	public  static final String EXPORT_FINANCE_OPERATE_PATH=load.getSystemConfig("export-finance-path");
	//仓位查询-隔夜仓位-导出路径
	public  static final String EXPORT_NEXT_POSITION_PATH=load.getSystemConfig("export-position-path");
	
	public  static final String ACCOUNT_FINANCE_FILENAME_PATH =load.getSystemConfig("finance-filename-path");
	
	public  static final String NEXT_POSITION_FILENAME_PATH =load.getSystemConfig("position-filename-path");
	
  
}
