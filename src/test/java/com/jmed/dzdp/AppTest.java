package com.jmed.dzdp;


import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.DZDPServiceLauncher;
import com.jmed.dzdp.domain.Order;
import com.jmed.dzdp.domain.Product;
import com.jmed.dzdp.domain.Technician;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.domain.dto.StatusDto;
import com.jmed.dzdp.util.SignUtils;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DZDPServiceLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

	@LocalServerPort
	private int port;


	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("currentPage", "1");


		ResponseEntity<String> response = this.testRestTemplate.postForEntity( "http://localhost:" + this.port + "/queryAvailableTechnicians" , map, String.class  );
		String json = response.getBody();

		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestCheckPos() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", 1);
		map.put("longitude", 121.420333);
		map.put("latitude", 31.215377);
		ResponseEntity<String> response = restTemplate.postForEntity("http://api.qingxiuwang.com/1.0/service/checkPos.json", new JSONObject(map), String.class);
		String contents = response.getBody();
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void convertJsonToObject() throws Exception {
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(null);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://api.qingxiuwang.com/1.0/service/technician.json", requestEntity, String.class);
		String contents = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		/*JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createJsonParser(contents);
		MappingIterator<Technician> technicians = mapper.readValues(parser, Technician.class);*/
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
				Arrays.asList(t.get("tags").split(",")))));
		//List<Technician> _technicians =  technicians.readAll();
		int size = technicians.size();
		_technicians.size();
	}
	@Test
	public void TestSign() {
		String appKey = SignUtils.getAppkey();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("methodName", "queryAvailableTechnicians");
		map.put("productId", "4321");
		map.put("serviceAddress", "北京市朝阳区");
		map.put("partnerId", "dianping");
		map.put("version", "1.0");
		map.put("sign", "E23728FE106A530A40A18503BB9450FD");
		String sign = SignUtils.makeSign(map, appKey);
		String resultToBe = "E23728FE106A530A40A18503BB9450FD";
		assertTrue(sign.equals(resultToBe));
	}
	
	@Test
	public void TestCreateOrder() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", 1);
		map.put("productId", 660);
		map.put("serviceAddress", "dongguan");
		map.put("longitude", 1000.1);
		map.put("latitude", 2000.1);
		map.put("price", 1);
		map.put("serviceTime", "2017-10-01 10:00:00");
		map.put("houseNumber", "1");
		map.put("cellphone", "1");
		map.put("quantity", 1);
		//HttpEntity<Object> entity = new HttpEntity<Object>("parameters", new JSONObject(map));
		ResponseEntity<String> response = restTemplate.postForEntity( "http://api.qingxiuwang.com/1.0/service/createOrder.json", new JSONObject(map), String.class );
		
		String json = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		BaseDto baseDto = json.contains("success") ? new BaseDto("0", "success", ImmutableMap.of("orderId", mapper.readValue(json, Order.class).getOrder_id())) 
				: new BaseDto("-1", "error", ImmutableMap.of());
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestOrderPaid() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", 15613);
		ResponseEntity<String> response = restTemplate.postForEntity( "http://api.qingxiuwang.com/1.0/service/" + "orderPay.json", new JSONObject(map), String.class);
		String json = response.getBody();
		StatusDto statusDto = json.contains("success") ? new StatusDto("0", "success") 
										: new StatusDto("-1", "error");
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestCancelOrder() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", 15613);
		ResponseEntity<String> response = restTemplate.postForEntity( "http://api.qingxiuwang.com/1.0/service/" + "cancelOrder.json", new JSONObject(map), String.class);
		String json = response.getBody();
		StatusDto statusDto = json.contains("success") ? new StatusDto("0", "success") 
										: new StatusDto("-1", "error");
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestFinishOrder() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", 15613);
		ResponseEntity<String> response = restTemplate.postForEntity( "http://api.qingxiuwang.com/1.0/service/" + "finishOrder.json", new JSONObject(map), String.class);
		String json = response.getBody();
		StatusDto statusDto = json.contains("success") ? new StatusDto("0", "success") 
										: new StatusDto("-1", "error");
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestIsProductValid() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> requestEntity = new HttpEntity<Object>(null);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://api.qingxiuwang.com/1.0/service/goods.json", requestEntity, String.class);
		String contents = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		Product[] products = mapper.readValue(contents, Product[].class);
		boolean isValid = Arrays.stream(products).filter(p -> p.getProduct_id() == 660).findFirst().isPresent();
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void TestAvaiableTimeSlot() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("cityId", 1);
		map.add("productId", 660);
		map.add("serviceAddress", "dongguan");
		map.add("longitude", 1000.1);
		map.add("latitude", 2000.1);

		ResponseEntity<String> response = this.testRestTemplate.postForEntity( "http://localhost:" + this.port + "/queryAvailableTimeslots", map , String.class );
		String json = response.getBody();

		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
