package com.cheffi.avatar.dto.response;

import java.util.List;

import com.cheffi.avatar.domain.Avatar;

public record GetFollowResponse(
	Long avatarId,
	String nickname,
	String pictureUrl
) {

	public static final String PICTURE_URL = "https://undongin.com/data/editor/0107/1609980770_6067.jpg";
	public static GetFollowResponse of(Avatar avatar) {
		return new GetFollowResponse(avatar.getId(), avatar.getNickname(), avatar.getPictureUrl());
	}

	public static List<GetFollowResponse> mock() {
		return List.of(new GetFollowResponse(2L, "구창모", PICTURE_URL),
			new GetFollowResponse(3L, "신동갑", PICTURE_URL),
			new GetFollowResponse(4L, "이준경", PICTURE_URL),
			new GetFollowResponse(5L, "임성빈", PICTURE_URL),
			new GetFollowResponse(6L, "강민호", PICTURE_URL)
		);
	}
}
