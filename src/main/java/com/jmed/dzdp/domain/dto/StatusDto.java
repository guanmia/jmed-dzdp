package com.jmed.dzdp.domain.dto;

public class StatusDto{
	
	private String code;
	public StatusDto(String code, String msg) {
		
		this.code = code;
		this.msg = msg;
	}
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	

}
