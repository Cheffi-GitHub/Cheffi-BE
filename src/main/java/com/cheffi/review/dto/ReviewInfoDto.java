package com.cheffi.review.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewInfoDto {

	@Schema(description = "리뷰 ID", example = "1")
	private Long id;
	@Schema(description = "리뷰 제목", example = "태초동에 생긴 맛집!!")
	private String title;
	@Schema(description = "리뷰의 적힌 본문 내용", example = "초밥 태초세트 추천해요")
	private String text;
	@Schema(description = "리뷰의 사진 URL")
	private ReviewPhotoInfoDto photo;
	@Schema(description = "잠금까지 남은 시간 (ms 단위)", example = "86399751")
	private Long timeLeftToLock;
	@Schema(description = "잠금 여부", example = "true")
	private boolean locked;
	@Schema(description = "북마크 여부", example = "true")
	private Boolean bookmarked;
	private Integer viewCount;
	private Integer number;

	@QueryProjection
	public ReviewInfoDto(Long id, String title, String text, ReviewPhotoInfoDto photo,
		LocalDateTime timeToLock, Boolean bookmarked, Integer viewCount) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.photo = photo;
		this.timeLeftToLock = Duration.between(LocalDateTime.now(), timeToLock).toMillis();
		this.locked = timeLeftToLock <= 0;
		this.bookmarked = bookmarked;
		this.viewCount = viewCount;
	}

	public void updateNumber(Integer number) {
		this.number = number;
	}
}
