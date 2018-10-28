package com.jmed.dzdp.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jmed.dzdp.domain.Technician;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@Service
public class TechnicianServiceImpl implements TechnicianService {
	
	private static final String url = "http://api.qingxiuwang.com/1.0/service/technician.json";
	
	@Override
	public BaseDto queryTechnicianList(int currentPage, int pageSize) {
		return getTechnicianDto();
	}
	
	@Override
	public BaseDto queryAvailableTechnicians(int currentPage, int pageSize) {
		return getTechnicianIds();
	}
	
	private BaseDto getTechnicianDto() {
		List<Technician> technicians = getTechnicians();
		if (technicians != null) {
			ImmutableMap<String,Serializable> body = ImmutableMap.of("totalSize", technicians.size(), "technicianList", (Serializable)technicians);
			BaseDto technicianDto = new BaseDto("0", "success", body);
			return technicianDto;
		}
		return new BaseDto("0", "success", ImmutableMap.of());
	}
	
	private BaseDto getTechnicianIds() {
		List<Technician> technicians = getTechnicians();
		if (technicians != null) {
			ImmutableMap<String,Serializable> body = ImmutableMap.of("technicianIds", (Serializable)technicians.stream().map(Technician::getTechnicianId).collect(Collectors.toList()));
			BaseDto technicianDto = new BaseDto("0", "success", body);
			return technicianDto;
		}
		return new BaseDto("0", "success", ImmutableMap.of());
	}
	
	private List<Technician> getTechnicians() {
		try {
		HttpEntity<?> requestEntity = new HttpEntity<Object>(null);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
		String contents = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> _technicians = mapper.readValue(contents, List.class);
		List <Technician> technicians = new ArrayList<>();
		_technicians.stream().forEach(t -> technicians.add(new Technician(				
				String.valueOf(t.get("technician_id")), 
				t.get("name"), 
				Integer.parseInt(t.get("city_id")), 
				t.get("sex"), 
				Integer.parseInt(t.get("age") != null ? t.get("age") : "50"), 
				t.get("photo_url"), 
				Integer.parseInt(String.valueOf(t.get("working_age")) != null ? String.valueOf(t.get("working_age")) : "5"), 
				Integer.parseInt(String.valueOf(t.get("order_amount"))), 
				Integer.parseInt(String.valueOf(t.get("level"))), 
				Float.parseFloat(String.valueOf(t.get("longitude"))), 
				Float.parseFloat(String.valueOf(t.get("latitude"))), t.get("phone"), t.get("description"), 
				ImmutableList.of())));
		return technicians;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
