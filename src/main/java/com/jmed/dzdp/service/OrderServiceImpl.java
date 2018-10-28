package com.jmed.dzdp.service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.jmed.dzdp.domain.Order;
import com.jmed.dzdp.domain.Technician;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.domain.dto.StatusDto;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final static String url = "http://api.qingxiuwang.com/1.0/service/";
	private final RestTemplate restTemplate = new RestTemplate();
	public BaseDto createOrder(String cityId, String productId, String serviceAddress, String longitude, String latitude, 
			float price, String serviceTime, String houseNumber, String cellphone, int quantity) {
		
		return doCreateOrder(cityId,  productId,  serviceAddress,  longitude,  latitude, price,  serviceTime,  houseNumber,  cellphone, quantity);
	}
	
	public StatusDto orderPaied(String orderId, float settlePrice) {
		return doOrderPaid(orderId);
	}
	
	public StatusDto cancelOrder(String orderId) {
		return doCancelOrder(orderId);	
	}
	
	public StatusDto confirmFinishOrder(String orderId) {
		return doConfirmFinishOrder(orderId);
	}
	
	private StatusDto getFakeStatus() {
		return new StatusDto("0", "success");
	}
	
	private BaseDto doCreateOrder(String cityId, String productId, String serviceAddress, String longitude, String latitude, 
			float price, String serviceTime, String houseNumber, String cellphone, int quantity) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cityId", cityId);
			map.put("productId", "660");
			map.put("serviceAddress", serviceAddress);
			map.put("longitude", longitude);
			map.put("latitude", latitude);
			map.put("price", price/quantity);
			map.put("serviceTime", serviceTime);
			map.put("houseNumber", houseNumber);
			map.put("cellphone", cellphone);
			map.put("quantity", quantity);
			ResponseEntity<String> response = this.restTemplate.postForEntity(url + "createOrder.json", new JSONObject(map), String.class);
			String json = response.getBody();
			return json.contains("success") ? new BaseDto("0", "success", ImmutableMap.of("orderId", mapper.readValue(json, Order.class).getOrder_id())) 
											: new BaseDto("-1", "error", ImmutableMap.of());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private StatusDto doOrderPaid(String orderId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			ResponseEntity<String> response = this.restTemplate.postForEntity(url + "orderPay.json", new JSONObject(map), String.class);
			String json = response.getBody();
			return json.contains("success") ? new StatusDto("0", "success") 
											: new StatusDto("-1", "error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private StatusDto doCancelOrder(String orderId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			ResponseEntity<String> response = this.restTemplate.postForEntity(url + "cancelOrder.json", new JSONObject(map), String.class);
			String json = response.getBody();
			return json.contains("success") ? new StatusDto("0", "success") 
											: new StatusDto("-1", "error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private StatusDto doConfirmFinishOrder(String orderId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			ResponseEntity<String> response = this.restTemplate.postForEntity(url + "finishOrder.json", new JSONObject(map), String.class);
			String json = response.getBody();
			return json.contains("success") ? new StatusDto("0", "success") 
											: new StatusDto("-1", "error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
