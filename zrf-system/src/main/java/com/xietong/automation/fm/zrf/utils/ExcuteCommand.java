package com.xietong.automation.fm.zrf.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.xietong.automation.fm.zrf.keys.RobotKey;




public class ExcuteCommand {
	public static Logger logger = Logger.getLogger(ExcuteCommand.class);

	/**
	 * 打开MSTSC 远程连接
	 * @param ip
	 * @param port
	 * @return
	 */

	public static boolean Connect_MSTSC(String ip, String port) {
		if (!StringUtils.isIPAddress(ip) || StringUtils.isEmpty(port)) {
			logger.error("请检查配置文件中远程电脑的IP和端口号!");
			return false;
		}
		String start = "cmd.exe /c start /b mstsc /v:";
		String cmd = ip + ":" + port;
		run_cmd(start + cmd);
		return true;
	}

	/**
	 * 关闭应用
	 * @param app
	 */
	public static void End_Application(Robot robot, String app) {
		if (!app.endsWith(".exe")) {
			return;
		}
		RobotKey.KeyPressWINR(robot);
		AdvanceTime.wait(1);
		RobotKey.KeyPressWithCtrl(robot, KeyEvent.VK_A);
		AdvanceTime.wait(1);
		RobotKey.PressKey(robot, KeyEvent.VK_BACK_SPACE);
		AdvanceTime.wait(1);
		String command = "cmd.exe /c taskkill /F /IM " + app;
		//RobotKey.WriteString(command);
		RobotKey.KeyPressString(robot, command);
		AdvanceTime.wait(1);
		RobotKey.PressKey(robot, KeyEvent.VK_ENTER);
	}

	public static void End_MSTSC() {
		AdvanceTime.wait(10);
		String end = "cmd.exe /c start /b taskkill /f /im mstsc.exe";
		run_cmd(end);
	}

	/**
	 * 根据ping IP判断是否是目标机器
	 * 
	 * @param command
	 * @return
	 */
	public static boolean isRemotePC(Robot robot){
		RobotKey.KeyPressWINR(robot);
		robot.delay(1000);
		RobotKey.KeyPressWithCtrl(robot, KeyEvent.VK_A);
		robot.delay(1000);
		RobotKey.PressKey(robot, KeyEvent.VK_BACK_SPACE);
		RobotKey.WriteString("cmd");
		robot.delay(1000);
		RobotKey.WriteString("ipconfig");
		robot.delay(200);
		RobotKey.PressKey(robot, KeyEvent.VK_ENTER);
		robot.delay(2000);
		return true;
	}
	
	
	
	
	
	
	public static boolean isRemotePC2(String command) {
		logger.info("------excecute cmd command: " + command);
		String s = "IPv4";
		String line = null;
		StringBuilder sb = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
				if (line.contains(s)) {
					String[] contents = line.split(":");
					String ipaddress = contents[1].trim();
					System.out.println("Ping 获取的IP地址为:"+ipaddress);
					LoadConfigFile loadFile = new LoadConfigFile();
					if (!ipaddress.equals(loadFile.getSystemConfig("ip-address"))){
						logger.info("不是在远程目标PC上的操作,将会中止执行!");
						return false;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
       String command = "E:\\TOOLS\\editplus\\editplus.exe";
	   run_cmd(command);
	
	}

	public static void run_cmd(String strcmd) {
		Runtime rt = Runtime.getRuntime();
		Process ps = null; // Process可以控制该子进程的执行或获取该子进程的信息。
		try {
			ps = rt.exec(strcmd); // 该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
			ps.waitFor(); // 等待子进程完成再往下执行。
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int i = ps.exitValue(); // 接收执行完毕的返回值
		if (i == 0) {
			System.out.println("命令执行完成.");
		} else {
			System.out.println("命令执行失败.");
		}
		ps.destroy(); // 销毁子进程
		ps = null;
	}
}
