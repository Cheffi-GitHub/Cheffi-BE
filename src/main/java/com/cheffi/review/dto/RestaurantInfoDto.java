package com.cheffi.review.dto;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.review.domain.Restaurant;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class RestaurantInfoDto {

	@Schema(description = "식당 식별자", example = "1L")
	private Long id;
	@Schema(description = "식당 이름", example = "을밀대")
	private String name;

	@Schema(description = "맛집 상세주소")
	private DetailedAddress address;

	@Builder
	public RestaurantInfoDto(Long id, String name, DetailedAddress detailedAddress) {
		this.id = id;
		this.name = name;
		this.address = detailedAddress;
	}

	public static RestaurantInfoDto of(Restaurant restaurant) {
		return new RestaurantInfoDto(restaurant.getId(), restaurant.getName(), restaurant.getDetailedAddress());
	}
}
