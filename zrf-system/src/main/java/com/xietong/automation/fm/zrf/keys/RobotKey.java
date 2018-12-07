package com.xietong.automation.fm.zrf.keys;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

import com.xietong.automation.fm.zrf.utils.StringUtils;





public class RobotKey {

	/**
	 * 按下Ctrl 组合键
	 */
	public static void KeyPressWithCtrl(Robot robot, int key) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
	}

	/**
	 * 按下WIN+R 组合键
	 */
	public static void KeyPressWINR(Robot robot) {
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.delay(100);
	}

	public static void cleanClipboard() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection("");
		clipboard.setContents(tText, null);
	}

	/**
	 * 输入字符串
	 *
	 * @param str
	 */
	public static void KeyPressString(Robot robot, String str) {
		cleanClipboard();
		robot.delay(200);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(str);
		clipboard.setContents(tText, null);
		KeyPressWithCtrl(robot, KeyEvent.VK_V);
		robot.delay(1000);
	}
	/**
	 * 输入字符串
	 *
	 * @param str
	 */
	public static void WriteString(String str) {
		try {
			Keyboard keyboard = new Keyboard();
			keyboard.type(str);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按一次某个键
	 * 
	 * @param robot
	 * @param key
	 */
	public static void PressKey(Robot robot, int key) {
		robot.keyPress(key);
		robot.delay(20);
		robot.keyRelease(key);
		robot.delay(1000);
	}

	public static void openRemoteCMD(Robot robot) {
		KeyPressWINR(robot);
		robot.delay(1000);
		KeyPressString(robot, "cmd");
		robot.delay(1000);
		PressKey(robot, KeyEvent.VK_ENTER);
	}

	/**
	 * 打开远程电脑上的APP
	 * 
	 * @param robot
	 * @param command
	 */
	public static void StartApplication(Robot robot, String path) {
		if (StringUtils.isEmpty(path)) {
			return;
		}
		KeyPressWINR(robot);
		robot.delay(1000);
		KeyPressWithCtrl(robot, KeyEvent.VK_A);
		robot.delay(1000);
		PressKey(robot, KeyEvent.VK_BACK_SPACE);
		WriteString(path);
		robot.delay(1000);
		PressKey(robot, KeyEvent.VK_ENTER);
		robot.delay(2000);
	}

	public static void main(String[] args) {
		Robot robot;
		try {
			robot = new Robot();
			StartApplication(robot, "C:\\sikuli\\img\\url.png");
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}
}
