package com.jmed.dzdp.controller;

import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.service.TechnicianService;
import com.jmed.dzdp.util.SignUtils;

@RestController
public class TechnicianController {
	
	@Autowired
	private TechnicianService technicianService;

    @RequestMapping(value = "/queryTechnicianList", method=RequestMethod.POST)
    @ResponseBody
    public BaseDto queryTechnicianList(@RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
    										@RequestParam(value="pageSize", required=false, defaultValue = "50") int pageSize, @RequestParam Map params ) {
    	
    	if (SignUtils.isSignMatch(params)) {
    		return technicianService.queryTechnicianList(currentPage, pageSize);
    	}
    	return new BaseDto("10001", "success", ImmutableMap.of());
    }
    
    @RequestMapping(value = "/queryAvailableTechnicians", method=RequestMethod.POST)
    @ResponseBody
    public BaseDto queryAvailableTechnicians(@RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
    										@RequestParam(value="pageSize", required=false, defaultValue = "50") int pageSize, @RequestParam Map params ) {
    	
    	if (SignUtils.isSignMatch(params)) {
    		return technicianService.queryAvailableTechnicians(currentPage, pageSize);
    	}
    	return new BaseDto("10001", "success", ImmutableMap.of());
    }

}
