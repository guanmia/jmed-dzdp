package com.jmed.dzdp.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.domain.dto.StatusDto;
import com.jmed.dzdp.service.OrderService;
import com.jmed.dzdp.util.SignUtils;

@RestController
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

    @RequestMapping(value = "/createOrder", method=RequestMethod.POST)
    @ResponseBody
    public BaseDto createOrder(@RequestParam(value="cityId", required=true) String cityId,
								@RequestParam(value="productId", required=true) String productId,
								@RequestParam(value="serviceAddress", required=true) String serviceAddress,
								@RequestParam(value="longitude", required=true) String longitude,
								@RequestParam(value="latitude", required=true) String latitude,
								@RequestParam(value="price", required=true) float price,
								@RequestParam(value="serviceTime", required=true) String serviceTime,
								@RequestParam(value="houseNumber", required=true) String houseNumber,
								@RequestParam(value="cellphone", required=true) String cellphone,
								@RequestParam(value="serviceId", required=false) String serviceId,
								@RequestParam(value="packageId", required=false) String packageId,
								@RequestParam(value="quantity", required=false) int quantity,
								@RequestParam(value="technicianId", required=false) String technicianId,
								@RequestParam(value="comment", required=false) String comment,
								@RequestParam(value="extraInfo", required=false) String extraInfo,
								@RequestParam Map params) {
    	logger.info("params是{}" + params);
    	if (SignUtils.isSignMatch(params)) {
    		return orderService.createOrder(cityId, productId, serviceAddress, longitude, latitude, price,serviceTime, houseNumber, cellphone, quantity);
    	}
    	logger.warn("大众点评的sign {} 与我们的算的sign {} 不同, appkey是 {}", params.get("sign"), SignUtils.makeSign(params, SignUtils.getAppkey()), SignUtils.getAppkey());
    	return new BaseDto("10001", "success", ImmutableMap.of());
    }
    
    @RequestMapping(value = "/orderPaied", method=RequestMethod.POST)
    @ResponseBody
    public StatusDto orderPaied(@RequestParam(value="orderId", required=true) String orderId,
    							@RequestParam(value="settlePrice", required=true) float settlePrice,
    							@RequestParam Map params) {
    	logger.info("params是{}" + params);
    	if (SignUtils.isSignMatch(params)) {
    		return orderService.orderPaied(orderId, settlePrice);
    	}
    	logger.warn("大众点评的sign {} 与我们的算的sign {} 不同, appkey是 {}", params.get("sign"), SignUtils.makeSign(params, SignUtils.getAppkey()), SignUtils.getAppkey());
    	return new StatusDto("10001", "success");
    }
    
    @RequestMapping(value = "/cancelOrder", method=RequestMethod.POST)
    @ResponseBody
    public StatusDto cancelOrder(@RequestParam(value="orderId", required=true) String orderId,
    							@RequestParam(value="comment", required=false) String comment,
    							@RequestParam Map params) {
    	logger.info("params是{}" + params);
    	if (SignUtils.isSignMatch(params)) {
    		return orderService.cancelOrder(orderId);
    	}
    	logger.warn("大众点评的sign {} 与我们的算的sign {} 不同", params.get("sign"), SignUtils.makeSign(params, SignUtils.getAppkey()));
    	return new StatusDto("10001", "success");
    }
    
    @RequestMapping(value = "/confirmFinishOrder", method=RequestMethod.POST)
    @ResponseBody
    public StatusDto confirmFinishOrder(@RequestParam(value="orderId", required=true) String orderId,
    									@RequestParam Map params) {
    	logger.info("params是{}" + params);
    	if (SignUtils.isSignMatch(params)) {
    		return orderService.confirmFinishOrder(orderId);
    	}
    	logger.warn("大众点评的sign {} 与我们的算的sign {} 不同, appkey是 ", params.get("sign"), SignUtils.makeSign(params, SignUtils.getAppkey()));
    	return new StatusDto("10001", "success");
    }
    

}
