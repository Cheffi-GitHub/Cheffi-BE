package com.cheffi.avatar.dto.adapter;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.ProfilePhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SelfAvatarInfo(

	@Schema(description = "아바타 식별자 (아바타 = 유저 개념)")
	Long avatarId,
	@Schema(description = "유저 닉네임")
	String nickname,
	@Schema(description = "유저 소개말")
	String introduction,
	@Schema(description = "쉐피 코인")
	int cheffiCoinCnt,
	@Schema(description = "포인트")
	int pointCnt,
	@Schema(description = "프로필 사진 URL")
	String photoUrl
) {
	public static SelfAvatarInfo of(Avatar avatar, ProfilePhoto photo) {
		return SelfAvatarInfo.builder()
			.avatarId(avatar.getId())
			.nickname(avatar.getNickname())
			.introduction(avatar.getIntroduction())
			.cheffiCoinCnt(avatar.getCheffiCoinCnt())
			.pointCnt(avatar.getPointCnt())
			.photoUrl(photo != null ? photo.getUrl() : null)
			.build();
	}

	public static SelfAvatarInfo of(Avatar avatar) {
		return SelfAvatarInfo.builder()
			.avatarId(avatar.getId())
			.nickname(avatar.getNickname())
			.introduction(avatar.getIntroduction())
			.cheffiCoinCnt(avatar.getCheffiCoinCnt())
			.pointCnt(avatar.getPointCnt())
			.build();
	}

}
