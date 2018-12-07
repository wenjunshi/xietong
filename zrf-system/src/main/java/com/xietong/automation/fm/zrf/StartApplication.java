package com.xietong.automation.fm.zrf;

import java.awt.Robot;
import org.apache.log4j.Logger;
import com.xietong.automation.fm.zrf.constant.SystemConstant;
import com.xietong.automation.fm.zrf.keys.RobotKey;
import com.xietong.automation.fm.zrf.moudle.SLSystemHandler;
import com.xietong.automation.fm.zrf.utils.AdvanceTime;
import com.xietong.automation.fm.zrf.utils.ExcuteCommand;

public class StartApplication {
	private static Logger logger = Logger.getLogger(StartApplication.class);
	
	
    public static void main( String[] args ){
        run();
    }
    
    public  static void run(){
    	Robot robot;
		try {
			robot = new Robot();
			logger.info("Start: 开始打开本机上的盛立系统.");
			RobotKey.StartApplication(robot, SystemConstant.APP_PATH);
			AdvanceTime.wait(10);
			logger.info("开始操作应用.");
	    	SLSystemHandler SLHandler = new SLSystemHandler();
	    	//SLHandler.OperateSystemApp();
	    	logger.info("End： 完成关闭所有应用.");
	    	//ExcuteCommand.End_Application(robot, "new_emc.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
