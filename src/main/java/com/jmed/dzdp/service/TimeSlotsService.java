package com.jmed.dzdp.service;

import com.jmed.dzdp.domain.dto.BaseDto;

public interface TimeSlotsService {

	BaseDto queryAvailableTimeSlots(int cityId, int productId, String serviceAddress, float longitude, float latitude);

}
