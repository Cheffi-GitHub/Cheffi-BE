package com.cheffi.cs.dto;

import java.time.LocalDateTime;

import com.cheffi.common.domain.ImageFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetBlockResponse(
	@JsonIgnore
	Long cursor,
	@Schema(description = "아바타 ID", example = "1", required = true)
	Long id,
	@Schema(description = "아바타 닉네임", example = "고구마맛탕", required = true)
	String nickname,
	@Schema(description = "아바타 프로필 사진 URL", required = true)
	ImageFile photo,
	LocalDateTime blockedDate
) {

	@QueryProjection
	public GetBlockResponse(
		Long cursor, Long id, String nickname, ImageFile photo, LocalDateTime blockedDate) {
		this.cursor = cursor;
		this.id = id;
		this.nickname = nickname;
		this.photo = photo;
		this.blockedDate = blockedDate;
	}
}
