package com.jmed.dzdp.domain;

public class Order {
	
	private String order_no;
	private int code;
	private String msg;
	private String order_id;
	public String getOrder_no() {
		return order_no;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

}
