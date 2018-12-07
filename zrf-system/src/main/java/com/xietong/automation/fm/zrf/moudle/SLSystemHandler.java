
package com.xietong.automation.fm.zrf.moudle;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.apache.log4j.Logger;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import com.xietong.automation.fm.zrf.constant.SystemConstant;
import com.xietong.automation.fm.zrf.constant.imgPath;
import com.xietong.automation.fm.zrf.keys.Keyboard;
import com.xietong.automation.fm.zrf.keys.RobotKey;
import com.xietong.automation.fm.zrf.utils.AdvanceTime;
import com.xietong.automation.fm.zrf.utils.FileUtil;


public  class SLSystemHandler implements imgPath,SystemConstant{
	
	private static Logger logger  = Logger.getLogger(SLSystemHandler.class);
	
    public static void main(String[] args) {
		System.out.println(EXPORT_NEXT_POSITION_PATH);
	}
  
	
    /**
     * 登录系统
     * author:wenjun
     * 2018年11月9日 上午10:27:26
     * return:boolean
     * Description:
     */
	protected  boolean login(Screen screen) {
		logger.info("开始登录盛立系统......");
		Settings.MinSimilarity = 0.6;
		Robot robot;
		try {
			robot=new Robot();
			screen.click(DATAIMPORT_IMG + "1username.png");//用户名
			AdvanceTime.wait(1);
			new Keyboard().type(USERNAME.trim());
			AdvanceTime.wait(2);
			RobotKey.PressKey(robot, KeyEvent.VK_TAB);
			//screen.click(DATAIMPORT_IMG + "2password.png");//密码
			AdvanceTime.wait(1);
			new Keyboard().type(PASSWORD.trim());
			AdvanceTime.wait(2);
			screen.click(DATAIMPORT_IMG + "3login-btn.png");//点击登录
			logger.info("登录成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	/**
	 * 日终结算--开始导入
	 * @param screen
	 */
	protected void importData(Robot robot,Screen screen) {
		logger.info("日终结算--开始导入");
		Settings.MinSimilarity = 0.6;
		try {
			screen.click(DATAIMPORT_IMG + "4daily-balance.png");
			AdvanceTime.wait(1);
			screen.click(DATAIMPORT_IMG + "5browse.png");
			AdvanceTime.wait(1);
			screen.doubleClick(DATAIMPORT_IMG + "6Ddir.png");
			AdvanceTime.wait(1);
			screen.doubleClick(DATAIMPORT_IMG + "7SL-DataDir.png");
			AdvanceTime.wait(1);
			screen.doubleClick(DATAIMPORT_IMG + "8dir0227.png");
			AdvanceTime.wait(1);
			screen.click(DATAIMPORT_IMG + "9pcsure.png");
			AdvanceTime.wait(1);
			screen.click(DATAIMPORT_IMG + "10start-import.png");
			AdvanceTime.wait(3);
			screen.click(DATAIMPORT_IMG + "11success-import-sure.png");
			AdvanceTime.wait(3);
			logger.info("导入数据成功!");
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 仓位查询-隔夜仓位-导出
	 * @param screen
	 */
	protected  void NextPositionQuery(Robot robot,Screen screen) {
		logger.info("开始导出隔夜仓位数据......");
		Settings.MinSimilarity = 0.6;
		AdvanceTime.wait(3);
		String filename = FileUtil.generateFileName("nextPosition");
		FileUtil.WriteFileName2Txt("nextPosition",NEXT_POSITION_FILENAME_PATH);//将生成的文件名写进txt文件
		try {
			screen.click(NEXT_POSITION_QUERY_IMG + "1position-query.png");
			AdvanceTime.wait(1);
			screen.click(NEXT_POSITION_QUERY_IMG + "2next-position.png");
			AdvanceTime.wait(1);
			screen.click(NEXT_POSITION_QUERY_IMG + "3export-btn.png");
			AdvanceTime.wait(3);
			RobotKey.WriteString(filename);
			AdvanceTime.wait(2);
			RobotKey.PressKey(robot, KeyEvent.VK_ENTER);
			AdvanceTime.wait(2);
			screen.click(NEXT_POSITION_QUERY_IMG + "4export-success-btn.png");
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	/**
	 * 账户资金操作--导出数据
	 * author:wenjun
	 * 2018年11月9日 下午2:36:04
	 * return:void
	 */
	protected  void OperateAccountFinance(Robot robot,Screen screen){
		logger.info("开始操作账户资金......");
		Settings.MinSimilarity = 0.6;
		AdvanceTime.wait(3);
		String filename = FileUtil.generateFileName("accountFinance");
		FileUtil.WriteFileName2Txt("accountFinance",ACCOUNT_FINANCE_FILENAME_PATH);//将生成的文件名写进txt文件
		try {
			screen.click(ACCOUNTFINANCE_OPERATE_IMG + "1finance-operation.png");
			AdvanceTime.wait(2);
			screen.click(ACCOUNTFINANCE_OPERATE_IMG + "2export.png");
			AdvanceTime.wait(3);
			RobotKey.WriteString(filename);
			AdvanceTime.wait(2);
			RobotKey.PressKey(robot, KeyEvent.VK_ENTER);
			AdvanceTime.wait(2);
			screen.click(ACCOUNTFINANCE_OPERATE_IMG + "3export-ensure.png");
			AdvanceTime.wait(2);
			logger.info("开始退出盛立系统......");
			screen.click(NEXT_POSITION_QUERY_IMG + "4system.png");
			AdvanceTime.wait(1);
			screen.click(NEXT_POSITION_QUERY_IMG + "5logout.png");
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进行系统数据导入、导出操作 author:wenjun 2018年10月18日 下午6:46:22 return:void
	 * Description:
	 * @param <T>
	 */
	public  void OperateSystemApp() {
		Robot robot;
		try {
			SLSystemHandler handler = new SLSystemHandler();
			robot = new Robot();
			Screen screen = new Screen();
			handler.login(screen);
			AdvanceTime.wait(6);
			handler.importData(robot, screen);
			AdvanceTime.wait(5);
			handler.NextPositionQuery(robot,screen);
			AdvanceTime.wait(3);
			handler.OperateAccountFinance(robot,screen);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
}
