package com.xietong.automation.fm.zrf.common;

public class FMSystemException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;
	private int code;

	public FMSystemException() {
       super();
	}

	public FMSystemException(int code, String message) {
		super(message);
		this.code = code;
	}

	public FMSystemException( String message) {
		super(message);
		this.message = message;
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}


}
