package com.cheffi.avatar.dto.request;

import static com.cheffi.common.dto.ValidationGroups.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class RecommendFollowRequest {

	@NotEmpty(groups = NotEmptyGroup.class)
	@Parameter(description = "검색할 태그의 ID 리스트 [NotEmpty, NotNull, 1 이상]", example = "[1, 2, 3]")
	List<Long> tags;

	public RecommendFollowRequest(List<Long> tags) {
		this.tags = tags;
	}

	/**
	 * 메서드명이 is로 시작해야 Validator 가 인식 가능하다.
	 */
	@AssertTrue(message = "모든 태그 ID는 1 이상이어야 합니다.", groups = AfterEmptyCheckGroup.class)
	public boolean isPositive() {
		return tags != null && tags.stream().allMatch(i -> i > 0);
	}

}
