package com.cheffi.avatar.dto.response;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TagsChangeResponse(
	@Schema(description = "추가된 태그들의 식별자", example = "[0, 1, 2]")
	List<Long> addedTags,
	@Schema(description = "삭제된 태그들의 식별자", example = "[3, 4]")
	List<Long> removedTags) {
}
