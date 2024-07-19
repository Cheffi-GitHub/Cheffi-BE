package com.cheffi.avatar.dto.response;

import com.cheffi.common.domain.ImageFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetFollowResponse(
	@JsonIgnore
	Long cursor,
	@Schema(description = "아바타 ID", example = "1", required = true)
	Long id,
	@Schema(description = "아바타 닉네임", example = "고구마맛탕", required = true)
	String nickname,
	@Schema(description = "아바타 프로필 사진", required = true)
	ImageFile photo,
	@Schema(description = "현재 조회하는 유저가 팔로우 했는지 여부", required = true)
	Boolean following
) {

	@QueryProjection
	public GetFollowResponse(Long cursor, Long id, String nickname, ImageFile photo, Boolean following) {
		this.cursor = cursor;
		this.id = id;
		this.nickname = nickname;
		this.photo = photo;
		this.following = following;
	}

}
