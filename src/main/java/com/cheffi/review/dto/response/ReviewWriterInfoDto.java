package com.cheffi.review.dto.response;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.common.domain.ImageFile;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewWriterInfoDto {
	@Schema(description = "리뷰 작성자 ID", example = "1", required = true)
	private final Long id;
	@Schema(description = "리뷰 작성자 닉네임", example = "닉네임1234", required = true)
	private final String nickname;
	@Schema(description = "리뷰 작성자 프로필 사진", required = true)
	private final ImageFile photo;
	@Schema(description = "리뷰 작성자 소개글", example = "안녕하세요. 리뷰 작성자입니다.")
	private final String introduction;
	@Schema(description = "조회자(유저)가 작성자인지 여부", example = "false", required = true)
	private final boolean writtenByViewer;

	private ReviewWriterInfoDto(Long id, String nickname, ImageFile photo, String introduction,
								boolean writtenByViewer) {
		this.id = id;
		this.nickname = nickname;
		this.photo = photo;
		this.introduction = introduction;
		this.writtenByViewer = writtenByViewer;
	}

	public static ReviewWriterInfoDto of(Avatar avatar, boolean writtenByViewer) {
		return new ReviewWriterInfoDto(avatar.getId(), avatar.stringNickname(), avatar.getPhoto().getFile(),
			avatar.getIntroduction(), writtenByViewer);
	}

	public static ReviewWriterInfoDto of(Avatar avatar) {
		return of(avatar, false);
	}
}
