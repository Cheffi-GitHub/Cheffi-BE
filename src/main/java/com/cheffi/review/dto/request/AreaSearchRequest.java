package com.cheffi.review.dto.request;

import com.cheffi.common.constant.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AreaSearchRequest {
	private final Address address;
	private final Long offset;
	private final Integer size;

	public AreaSearchRequest(Address address, Long offset, Integer size) {
		this.address = address;
		this.offset = offset;
		this.size = size;
	}
}
