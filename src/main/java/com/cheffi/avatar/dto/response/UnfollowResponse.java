package com.cheffi.avatar.dto.response;

public record UnfollowResponse(
	Long followerId,
	Long followeeId
) {
}
