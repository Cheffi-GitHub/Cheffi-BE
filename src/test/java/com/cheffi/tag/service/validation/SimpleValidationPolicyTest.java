package com.cheffi.tag.service.validation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;

@ExtendWith(MockitoExtension.class)
class SimpleValidationPolicyTest {

	@Spy
	private SimpleValidationPolicy simpleValidationPolicy;

	@Nested
	@DisplayName("validateTagsOfAvatar 메서드")
	class ValidateTagsOfAvatar {

		private static final Tag KOREAN_TAG = new Tag(TagType.FOOD, "한식");
		private static final Tag CHINESE_TAG = new Tag(TagType.FOOD, "중식");
		private static final Tag JAPANESE_TAG = new Tag(TagType.FOOD, "일식");
		private static final Tag SPICY_TAG = new Tag(TagType.TASTE, "매콤한");
		private static final Tag SWEET_TAG = new Tag(TagType.TASTE, "달달한");
		private static final Tag QUIET_TAG = new Tag(TagType.TASTE, "조용한");
		private static final Tag TRENDY_TAG = new Tag(TagType.TASTE, "트렌디한");

		private static final List<Tag> REQUESTED_TAG_LIST =
			List.of(KOREAN_TAG, CHINESE_TAG,
				SPICY_TAG, SWEET_TAG, TRENDY_TAG);

		private List<Long> foodTagIds;
		private List<Long> tasteTagIds;

		@BeforeEach
		void setUp() {
			ReflectionTestUtils.setField(KOREAN_TAG, "id", 1L);
			ReflectionTestUtils.setField(CHINESE_TAG, "id", 2L);
			ReflectionTestUtils.setField(JAPANESE_TAG, "id", 3L);
			ReflectionTestUtils.setField(SPICY_TAG, "id", 4L);
			ReflectionTestUtils.setField(SWEET_TAG, "id", 5L);
			ReflectionTestUtils.setField(QUIET_TAG, "id", 6L);
			ReflectionTestUtils.setField(TRENDY_TAG, "id", 7L);
		}

		@Test
		@DisplayName("유효한 요청에는 성공한다.")
		void givenValidRequest() {
			//given
			foodTagIds = List.of(1L, 2L);
			tasteTagIds = List.of(4L, 5L, 7L);
			doNothing()
				.when(simpleValidationPolicy)
				.checkSize(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds);

			//then
			assertThatNoException().isThrownBy(
				//when
				() -> simpleValidationPolicy.validateTagsOfAvatar(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds)

			);
		}

		@Test
		@DisplayName("태그 리스트에 요청된 식별자가 존재하지 않으면 실패한다.")
		void givenInvalidIdRequest() {
			//given
			foodTagIds = List.of(1L, 2L, 3L);
			tasteTagIds = List.of(4L, 5L, 7L);
			doNothing()
				.when(simpleValidationPolicy)
				.checkSize(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds);

			//then
			assertThatExceptionOfType(BusinessException.class).isThrownBy(
				//when
				() -> simpleValidationPolicy.validateTagsOfAvatar(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds)

			);
		}

		@Test
		@DisplayName("태그 리스트에 요청된 식별자가 존재하더라도, 타입이 맞지 않으면 실패한다.")
		void givenInvalidTagTypeRequest() {
			//given
			foodTagIds = List.of(1L, 4L);
			tasteTagIds = List.of(2L, 5L, 7L);
			doNothing()
				.when(simpleValidationPolicy)
				.checkSize(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds);

			//then
			assertThatExceptionOfType(BusinessException.class).isThrownBy(
				//when
				() -> simpleValidationPolicy.validateTagsOfAvatar(REQUESTED_TAG_LIST, foodTagIds, tasteTagIds)

			);
		}

	}

}
