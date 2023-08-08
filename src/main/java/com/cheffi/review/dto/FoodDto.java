package com.cheffi.review.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class FoodDto {

	@Schema(description = "메뉴 이름", example = "곱창 전골")
	private String name;
	@Schema(description = "메뉴 가격", example = "80000")
	private int price;
	
}
