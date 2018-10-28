package com.jmed.dzdp.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.domain.Product;
import com.jmed.dzdp.domain.TimeSlot;
import com.jmed.dzdp.domain.dto.BaseDto;
@Service
public class TimeSlotsServiceImpl implements TimeSlotsService{
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public 	BaseDto queryAvailableTimeSlots(int cityId, int productId, String serviceAddress, float longitude, float latitude) {
		if (isProductIdValid(productId) && isPosValid(cityId, longitude, latitude)) {
			return getFakeTimeSlots();
		} else if (latitude < 0 || longitude < 0) {
			return  new BaseDto("20001", "success", ImmutableMap.of());
		}
		return getFakeTimeSlots();
	}

	private BaseDto getFakeTimeSlots() {
		LocalDate date = LocalDate.now();
		char[] timeSlotChar_1 = {'0'/*0:00*/,'0'/*00:30*/,'0','0','0','0','0','0','0','0','0','0','0','0','0','0','1',
				'0','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0'};
		
//		char[] timeSlotChar_2 = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
//				'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
//				'0','0','0','0','0','0','0','0','1','1'};
		
		//char[] timeSlotChar_3 = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
		//		'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
		//		'0','0','0','0','0','0','0','1','1','1'};
		TimeSlot timeSlot_2 = new TimeSlot(date.plusDays(2), timeSlotChar_1);
		TimeSlot timeSlot_3 = new TimeSlot(date.plusDays(3), timeSlotChar_1);
		TimeSlot timeSlot_4 = new TimeSlot(date.plusDays(4), timeSlotChar_1);
		TimeSlot timeSlot_5 = new TimeSlot(date.plusDays(5), timeSlotChar_1);
		TimeSlot timeSlot_6 = new TimeSlot(date.plusDays(6), timeSlotChar_1);
		TimeSlot timeSlot_7 = new TimeSlot(date.plusDays(7), timeSlotChar_1);
		ImmutableMap<String,Serializable> body = ImmutableMap.of("totalSize", ImmutableList.of(timeSlot_2, timeSlot_3, timeSlot_4, timeSlot_5, timeSlot_6, timeSlot_7).size(), "timeList", 
				ImmutableList.of(timeSlot_2, timeSlot_3, timeSlot_4, timeSlot_5, timeSlot_6, timeSlot_7));
		BaseDto timeSlotsDto = new BaseDto("0", "success", body);
		return timeSlotsDto;
	}
	
	private Boolean isProductIdValid(int product_id) {
		try {
			HttpEntity<?> requestEntity = new HttpEntity<Object>(null);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://api.qingxiuwang.com/1.0/service/goods.json", requestEntity, String.class);
			String contents = responseEntity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			Product[] products = mapper.readValue(contents, Product[].class);
			boolean isVlaid = Arrays.stream(products).filter(p -> p.getProduct_id() == product_id).findFirst().isPresent();
			return isVlaid;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	private Boolean isPosValid(int cityId, float longitude, float latitude) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cityId", cityId);
			map.put("longitude", longitude);
			map.put("latitude", latitude);
			ResponseEntity<String> response = this.restTemplate.postForEntity("http://api.qingxiuwang.com/1.0/service/checkPos.json", new JSONObject(map), String.class);
			String contents = response.getBody();
			return contents.equalsIgnoreCase("0") ? false : true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
}
