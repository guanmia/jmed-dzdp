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
import com.jmed.dzdp.service.ProductService;
import com.jmed.dzdp.util.SignUtils;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

    @RequestMapping(value = "/queryProductList", method=RequestMethod.POST)
    @ResponseBody
    public BaseDto queryTechnicianList(@RequestParam(value="cityId", required=true) int cityId,
    								   @RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
    								   @RequestParam(value="pageSize", required=false, defaultValue = "50") int pageSize, 
    								   @RequestParam(value="sign", required=false) String sign, 
    								   @RequestParam Map params ) {
    	
    	if (SignUtils.isSignMatch(params)) {
    		productService.queryProdcutListWhenSignMach(cityId, currentPage, pageSize);
    	}
    	return new BaseDto("10001", "success", ImmutableMap.of());
    }

}
