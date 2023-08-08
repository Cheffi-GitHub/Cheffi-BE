package com.cheffi.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class FoodDto {

	@Schema(description = "메뉴 이름", example = "곱창 전골")
	private final String name;
	@Schema(description = "메뉴 가격", example = "80000")
	private final int price;

	public FoodDto(String name, int price) {
		this.name = name;
		this.price = price;
	}
}
