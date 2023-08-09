package com.cheffi.avatar.dto.response;

import java.util.List;

import com.cheffi.avatar.dto.common.TagDto;

public record RecommendFollowResponse(
	Long avatarId,
	String nickname,
	String pictureUrl,
	int followers,
	List<TagDto> tags
) {

	public static final String PICTURE_URL = "https://undongin.com/data/editor/0107/1609980770_6067.jpg";

	public static List<RecommendFollowResponse> mock() {
		return List.of(new RecommendFollowResponse(2L, "구창모", PICTURE_URL, 1400, TagDto.mock()),
			new RecommendFollowResponse(3L, "신동갑", PICTURE_URL, 1500, TagDto.mock()),
			new RecommendFollowResponse(4L, "이준경", PICTURE_URL, 1600, TagDto.mock()),
			new RecommendFollowResponse(5L, "임성빈", PICTURE_URL, 2000, TagDto.mock()),
			new RecommendFollowResponse(6L, "강민호", PICTURE_URL, 800, TagDto.mock())
		);
	}
}
