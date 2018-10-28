package com.jmed.dzdp.service;

import com.jmed.dzdp.domain.dto.BaseDto;

public interface ProductService {

	BaseDto queryProdcutList(int cityId, int currentPage, int pageSize);
	BaseDto queryProdcutListWhenSignMach(int cityId, int currentPage, int pageSize);
}
