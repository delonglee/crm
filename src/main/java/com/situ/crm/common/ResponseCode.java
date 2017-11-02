package com.situ.crm.common;

/*
 * 枚举
 */
public enum ResponseCode {
	//类似构造了一个对象  0对应构造函数里的code，success队形desc
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(2,"NEED_LOGIN");
	
	private int code;
	private String desc;
	
	private ResponseCode(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
