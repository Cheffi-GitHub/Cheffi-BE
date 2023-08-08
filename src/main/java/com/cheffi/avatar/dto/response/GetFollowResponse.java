package com.cheffi.avatar.dto.response;

public record GetFollowResponse(
	Long avatarId,
	String nickname,
	String pictureUrl
) {
}
