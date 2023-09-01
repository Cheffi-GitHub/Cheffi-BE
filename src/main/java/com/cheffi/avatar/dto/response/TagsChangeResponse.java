package com.cheffi.avatar.dto.response;

import java.util.List;

import com.cheffi.avatar.dto.common.TagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TagsChangeResponse(
	@Schema(description = "변경된 음식 태그의 리스트", example = "[0, 1, 2]")
	List<TagDto> foodTags,

	@Schema(description = "변경된 취향 태그의 리스트", example = "[3, 4]")
	List<TagDto> tasteTags) {
}
