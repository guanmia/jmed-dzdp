package com.jmed.dzdp.service;

import com.jmed.dzdp.domain.dto.BaseDto;

public interface TechnicianService {

	BaseDto queryTechnicianList(int currentPage, int pageSize);
	BaseDto queryAvailableTechnicians(int currentPage, int pageSize);
}
