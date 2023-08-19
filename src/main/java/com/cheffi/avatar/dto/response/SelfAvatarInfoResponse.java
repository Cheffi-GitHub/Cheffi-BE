package com.cheffi.avatar.dto.response;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.common.constant.Address;

import lombok.Builder;

@Builder
public record SelfAvatarInfoResponse(
	Long id,
	String nickname,
	String pictureUrl,
	String introduction,
	Address address,
	int cheffiCoinCnt,
	int pointCnt
) {
	public static SelfAvatarInfoResponse of(Avatar avatar) {
		return SelfAvatarInfoResponse.builder()
			.id(avatar.getId())
			.nickname(avatar.getNickname())
			.introduction(avatar.getIntroduction())
			.address(avatar.getAddress())
			.cheffiCoinCnt(avatar.getCheffiCoinCnt())
			.pointCnt(avatar.getPointCnt())
			.build();
	}

	public static SelfAvatarInfoResponse mock() {
		return SelfAvatarInfoResponse.builder()
			.id(2L)
			.nickname("닉네임")
			.pictureUrl("https://undongin.com/data/editor/0107/1609980770_6067.jpg")
			.introduction("안녕하세요. 소개글 입니다.")
			.address(Address.cityAddress("서울시", "종로구"))
			.cheffiCoinCnt(500)
			.pointCnt(100)
			.build();
	}
}
