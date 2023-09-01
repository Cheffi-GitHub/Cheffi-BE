package com.cheffi.tag.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

	private static final Tag KOREAN_TAG = new Tag(TagType.FOOD, "한식");
	private static final Tag CHINESE_TAG = new Tag(TagType.FOOD, "중식");
	private static final Tag JAPANESE_TAG = new Tag(TagType.FOOD, "일식");
	private static final Tag SPICY_TAG = new Tag(TagType.TASTE, "매콤한");
	private static final Tag SWEET_TAG = new Tag(TagType.TASTE, "달달한");
	private static final Tag QUIET_TAG = new Tag(TagType.TASTE, "조용한");

	@InjectMocks
	private TagService tagService;

	@BeforeEach
	void setUp() {
	}

	@Nested
	@DisplayName("extractDistinctTags 메서드")
	class ExtractDistinctTags {

		private static final List<Tag> REQUESTED_TAG_LIST =
			List.of(KOREAN_TAG, CHINESE_TAG, SWEET_TAG, QUIET_TAG);

		@Test
		@DisplayName("src에 대한 from의 차집합(src - from) 태그들을 분리해낸다.")
		void givenSourceTags() {
			//when
			List<Tag> sourceTags = List.of(KOREAN_TAG, SPICY_TAG, JAPANESE_TAG);
			List<Tag> extractedTags = tagService.extractDistinctTags(sourceTags, REQUESTED_TAG_LIST);

			//then
			assertThat(extractedTags)
				.hasSize(2)
				.contains(SPICY_TAG, JAPANESE_TAG);
		}

	}

}
