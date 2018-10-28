package com.jmed.dzdp.service;

import com.jmed.dzdp.domain.dto.BaseDto;
import com.jmed.dzdp.domain.dto.StatusDto;

public interface OrderService {

	BaseDto createOrder(String cityId, String productId, String serviceAddress, String longitude, String latitude, 
						float price, String serviceTime, String houseNumber, String cellphone, int quantity);
	
	StatusDto orderPaied(String orderId, float settlePrice);
	
	StatusDto cancelOrder(String orderId);
	
	StatusDto confirmFinishOrder(String orderId);
}
