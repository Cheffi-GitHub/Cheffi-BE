package com.cheffi.tag.service.validation;

import java.util.List;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.tag.domain.Tag;

public abstract class TagValidationPolicy {

	private static final int TASTE_TAG_MIN = 5;
	private static final int FOOD_TAG_MIN = 3;

	public abstract void validateTagsOfAvatar(List<Tag> tags, List<Long> foodTagIds, List<Long> tasteTagIds);

	protected void checkSize(List<Tag> tags, List<Long> foodTagIds, List<Long> tasteTagIds) {
		if (tags.size() != foodTagIds.size() + tasteTagIds.size())
			throw new BusinessException(ErrorCode.BAD_AVATAR_TAG_REQUEST);

		if (foodTagIds.size() < FOOD_TAG_MIN || tasteTagIds.size() < TASTE_TAG_MIN)
			throw new BusinessException(ErrorCode.BAD_AVATAR_TAG_REQUEST);
	}

}
