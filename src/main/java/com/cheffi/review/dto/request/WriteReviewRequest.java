package com.cheffi.review.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.cheffi.review.dto.MenuDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WriteReviewRequest(
	@NotBlank
	@Schema(description = "식당 식별자")
	int restaurantId,
	@NotBlank
	@Schema(description = "리뷰 제목")
	String title,
	@NotBlank
	@Schema(description = "리뷰 내용")
	String text,
	List<MenuDto> menus,
	@JsonProperty(value = "food_tags")
	@Schema(description = "작성할 리뷰의 음식 태그의 식별자 리스트 ", example = "[0, 1, 2]")
	List<Long> foodTags,
	@JsonProperty(value = "taste_tags")
	@Schema(description = "작성할 취향 태그의 식별자 리스트 ", example = "[3, 4]")
	List<Long> tasteTags) {

	public List<Long> combinedTagList() {
		List<Long> combined = new ArrayList<>();
		combined.addAll(foodTags);
		combined.addAll(tasteTags);
		return combined;
	}

}
