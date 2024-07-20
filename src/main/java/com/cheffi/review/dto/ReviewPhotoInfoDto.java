package com.cheffi.review.dto;

import com.cheffi.common.domain.ImageFile;
import com.cheffi.review.domain.ReviewPhoto;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewPhotoInfoDto {

	@Schema(description = "리뷰 사진ID", example = "1", required = true)
	private Long id;
	@Schema(description = "리뷰에 보여질 사진의 순서", example = "1", required = true)
	private int order;
	@Schema(description = "리뷰 사진", example = "https.www.~", required = true)
	private ImageFile photo;

	@QueryProjection
	public ReviewPhotoInfoDto(Long id, int order, ImageFile photo) {
		this.id = id;
		this.order = order;
		this.photo = photo;
	}

	public static ReviewPhotoInfoDto of(ReviewPhoto reviewPhoto) {
		return new ReviewPhotoInfoDto(reviewPhoto.getId(), reviewPhoto.getGivenOrder(), reviewPhoto.getFile());
	}

}

