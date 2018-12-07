package com.xietong.automation.fm.zrf.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.xietong.automation.fm.zrf.common.FMSystemException;
import com.xietong.automation.fm.zrf.constant.SystemConstant;

public class FileUtil implements SystemConstant{

	public static Logger logger = Logger.getLogger(FileUtil.class);

	public static void main(String[] args) {
		
		System.out.println(generateFileName("nextPosition"));
		System.out.println(generateFileName("accountFinance"));
		
	}
	
	/**
	 * 判断导出的finance或position 文件是否存在 author:wenjun 2018年10月18日 下午2:03:50
	 * return:boolean Description:
	 */
	public static boolean isExportFileExist(String TempPath, String exportPath) {
		File exportFile = new File(TempPath);
		if (exportFile.exists() && exportFile.isFile()) {
			String content = readFile(TempPath);
			
			String pattern = Pattern.quote(System.getProperty("file.separator"));
            String[] splittedFileName = content.split(pattern);
			String prefix = splittedFileName[splittedFileName.length - 1];
			File file = new File(exportPath);
			if (!file.exists()) {
				file.mkdirs();
				return false;
			}
			File[] fs = file.listFiles();
			for (File fe : fs) {
				if (!fe.isDirectory()) {
					if (fe.getName().equals(prefix + ".txt")) {
						logger.info("导出的文件在    " + exportPath + "下存在!");
						return true;
					}
				}
			}
		}
		logger.error(exportPath + "导出文件不存在!");
		return false;
	}
	/**
	 * 读取文件， author:wenjun 2018年10月17日 下午5:52:08 return:String Description:
	 */
	public static String readFile(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String conts;
		if (file.exists() && file.isFile()) {
			try {
				fis = new FileInputStream(file);
				isr = new InputStreamReader(fis, "utf-8");
				br = new BufferedReader(isr);
				while ((conts = br.readLine()) != null) {
					return conts;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
					isr.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				System.err.println("文件路径:" + path);
				throw new FMSystemException("请检查路径下文件是否存在!");
			} catch (FMSystemException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 生成导出文件名 author:wenjun 2018年10月11日 上午10:23:35
	 */
	public static String generateFileName(String target) {
		StringBuffer buffer = new StringBuffer();
		if (target.equals("nextPosition")) {
			buffer.append(EXPORT_NEXT_POSITION_PATH);
			buffer.append(target);
			buffer.append(AdvanceTime.getCurrentTime());
			return buffer.toString();
		}
		if (target.equals("accountFinance")) {
			buffer.append(EXPORT_FINANCE_OPERATE_PATH);
			buffer.append(target);
			buffer.append(AdvanceTime.getCurrentTime());
			return buffer.toString();
		}
		return "";
	}

	/**
	 * 
	 * author:wenjun 2018年10月12日 上午9:44:13 return:void Description:将生成的文件名写进txt
	 */
	public static void WriteFileName2Txt(String description, String filePath) {
		File file = new File(filePath);
		FileOutputStream fos = null;
		String content = generateFileName(description);
		try {
			fos = new FileOutputStream(file);
			byte[] by = content.getBytes();
			fos.write(by);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		if (!StringUtils.isEmpty(filePath)) {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				logger.info(file + "  文件已经存在!");
				return true;
			} else {
				logger.error(file + "此文件不存在!");
				try {
					throw new FMSystemException(file + "文件不存在!");
				} catch (FMSystemException e) {
					e.printStackTrace();
				}
			}
		} else {
			logger.error(filePath + "文件路径不能为空!");
			throw new RuntimeException("文件路径不能为空!");
		}
		return false;
	}
}
