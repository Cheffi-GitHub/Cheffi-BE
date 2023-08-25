package com.cheffi.avatar.dto.request;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TagsChangeRequest(
	@Schema(description = "추가할 태그들의 식별자", example = "[0, 1, 2]")
	List<Long> addTags,
	@Schema(description = "삭제할 태그들의 식별자", example = "[3, 4]")
	List<Long> removeTags) {

}
