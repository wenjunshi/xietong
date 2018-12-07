package com.xietong.automation.fm.zrf.common;

/**
 *@author wenjun
 *@time  2018年11月9日 上午9:23:04
 *@Description:
 */
public enum SLEnum {
	
	PA1(1001,"path1"),PA2(1002,"path2");
	 
	public  String imgPath;
	public  int code;
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	} 
	
	SLEnum(int code,String imgPath){
		this.code=code;
		this.imgPath=imgPath;
	}
	public static void main(String[] args) {
		String s1 = SLEnum.PA1.imgPath;
		for(SLEnum cc:SLEnum.values()){
			System.out.println(cc.getCode());
			System.out.println(cc.getImgPath());
		}
	}

}  
