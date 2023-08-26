package com.cheffi.avatar.dto.common;

import java.util.List;

import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TagDto(
	@Schema(description = "태그의 식별자")
	Long tagId,
	@Schema(description = "태그의 이름")
	String name,
	@Schema(description = "태그의 타입")
	TagType tagType
) {

	public static TagDto of(Tag tag) {
		return new TagDto(tag.getId(), tag.getName(), tag.getTagType());
	}

	private static TagDto mock(Long tagId, String name, TagType tagType) {
		return new TagDto(tagId, name, tagType);
	}

	public static List<TagDto> mock() {
		return List.of(TagDto.mock(1L,"매운맛", TagType.TASTE),
			TagDto.mock(2L, "짠맛", TagType.TASTE),
			TagDto.mock(3L, "한식", TagType.FOOD),
			TagDto.mock(4L, "양식", TagType.FOOD),
			TagDto.mock(5L, "위스키", TagType.FOOD)
		);
	}
}
