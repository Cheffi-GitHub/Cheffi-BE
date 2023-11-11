package com.cheffi.review.service;

import org.springframework.stereotype.Service;

import com.cheffi.common.config.data.RegionProperties;
import com.cheffi.common.constant.Address;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegionService {

	private final RegionProperties regionProperties;

	public boolean contains(Address address) {
		return regionProperties.contains(address);
	}
}
