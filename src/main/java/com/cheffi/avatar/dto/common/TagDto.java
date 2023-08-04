package com.cheffi.avatar.dto.common;

import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;

public record TagDto(
	String name,
	TagType tagType
) {

	public static TagDto of(Tag tag) {
		return new TagDto(tag.getName(), tag.getTagType());
	}

	public static TagDto mock(String name, TagType tagType) {
		return new TagDto(name, tagType);
	}
}
