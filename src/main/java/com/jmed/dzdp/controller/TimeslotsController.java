package com.jmed.dzdp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.service.TechnicianService;
import com.jmed.dzdp.service.TimeSlotsService;
import com.jmed.dzdp.util.SignUtils;

@RestController
public class TimeslotsController {
	
	@Autowired
	private TimeSlotsService timeSlotsService;

    @RequestMapping(value = "/queryAvailableTimeslots", method=RequestMethod.POST)
    @ResponseBody
    public BaseDto query(@RequestParam(value="cityId", required=true) int cityId,
    										@RequestParam(value="productId", required=true) int productId,
    										@RequestParam(value="serviceAddress", required=true) String serviceAddress,
    										@RequestParam(value="longitude", required=true) float longitude,
    										@RequestParam(value="latitude", required=true) float latitude,
    										@RequestParam Map params) {
    	
    	if (SignUtils.isSignMatch(params)) {
    		return timeSlotsService.queryAvailableTimeSlots(cityId, productId, serviceAddress, longitude, latitude);
    	}
    	return new BaseDto("10001", "success", ImmutableMap.of());
    }

}
