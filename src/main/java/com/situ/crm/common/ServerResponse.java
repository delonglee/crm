package com.situ.crm.common;

import java.io.Serializable;
/*
 * 后台返回给前台的对象
 */
public class ServerResponse<T> implements Serializable{
	//当前的状态 :如 成功 失败 未登录 无权限等(程序员判断用)
	private Integer status;
	//描述信息（主要是给用户看的提示信息）
	private String msg;
	//后台返回前端的数据
	private T data;
	
	/*
	 * 构造函数可能有四种情况
	 */
	public ServerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ServerResponse(Integer status) {
		super();
		this.status = status;
	}

	public ServerResponse(Integer status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	public ServerResponse(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public ServerResponse(Integer status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	/*
	 * 描述后台返回给前台的成功状态
	 */
	
	//只是告诉前台成功的状态
	public static<T> ServerResponse<T> createSuccess() {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
	}
	//告诉前台：status和msg
	public static<T> ServerResponse<T> createSuccess(String msg) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg);
	}
	//告诉前台：status和msg data
	public static<T> ServerResponse<T> createSuccess(String msg,T data) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg,data);
	}
	
	/*
	 * 描述后台返回给前台的失败状态
	 */
	
	//只是告诉前台成功的状态
	public static<T> ServerResponse<T> createError() {
		return new ServerResponse<>(ResponseCode.ERROR.getCode());
	}
	//告诉前台：status和msg
	public static<T> ServerResponse<T> createError(String msg) {
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),msg);
	}
	//告诉前台：status和msg data
	public static<T> ServerResponse<T> createError(String msg,T data) {
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),msg,data);
	}	
		
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getmsg() {
		return msg;
	}
	public void setmsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	

}
