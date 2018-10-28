package com.jmed.dzdp.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.domain.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{

	public BaseDto queryProdcutList(int cityId, int currentPage, int pageSize) {
		ImmutableMap<String,Serializable> body = ImmutableMap.of("totalSize", 1, "productList", ImmutableList.of(getProductDto()));
		BaseDto dto = new BaseDto("10001", "success", body);
		return dto;
	}
	
	public BaseDto queryProdcutListWhenSignMach(int cityId, int currentPage, int pageSize) {
		ImmutableMap<String,Serializable> body = ImmutableMap.of("totalSize", 1, "productList", ImmutableList.of(getProductDto()));
		BaseDto dto = new BaseDto("10001", "success", body);
		return dto;
	}
	
	private ProductDto getProductDto() {
		return new ProductDto("10010", "蝴蝶梦", 33, 120, "简要描述",
				130, 89, 1, 1, ImmutableList.of(), "http://a.min.jpg", 1);
	}
	
}
