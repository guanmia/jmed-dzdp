package com.jmed.dzdp.domain.dto;

import java.io.Serializable;
import java.util.Map;

public class BaseDto{
	
	private String code;
	private String msg;
	private Map<String, Serializable> body;
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
	public Map<String, Serializable> getBody() {
		return body;
	}
	public void setBody(Map<String, Serializable> body) {
		this.body = body;
	}
	public BaseDto(String code, String msg, Map<String, Serializable> body) {
		this.code = code;
		this.msg = msg;
		this.body = body;
	}

}
