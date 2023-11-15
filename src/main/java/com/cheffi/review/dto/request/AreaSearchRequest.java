package com.cheffi.review.dto.request;

import org.hibernate.validator.constraints.Range;

import com.cheffi.common.constant.Address;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class AreaSearchRequest {
	@Valid
	private final Address address;
	@Parameter(description = "검색을 시작할 커서(포함) 최초 조회시는 0을 넣어주세요")
	@NotNull
	@PositiveOrZero
	private final Long cursor;
	@Parameter(description = "검색 사이즈")
	@NotNull
	@Range(min = 1, max = 16)
	final Integer size;

	public AreaSearchRequest(String province, String city, Long cursor, Integer size) {
		this.address = Address.cityAddress(province, city);
		this.cursor = cursor;
		this.size = size;
	}
}
