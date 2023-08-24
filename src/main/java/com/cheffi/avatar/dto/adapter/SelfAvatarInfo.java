package com.cheffi.avatar.dto.adapter;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.ProfilePhoto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
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

	public static SelfAvatarInfo mock() {
		return SelfAvatarInfo.builder()
			.avatarId(2L)
			.nickname("닉네임")
			.introduction("안녕하세요. 소개글 입니다.")
			.cheffiCoinCnt(500)
			.pointCnt(100)
			.photoUrl("https://undongin.com/data/editor/0107/1609980770_6067.jpg")
			.build();
	}
}
