package com.cheffi.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewPhotoInfoDto {

    @Schema(description = "리뷰 사진ID", example = "1")
    private Long id;
    @Schema(description = "리뷰에 보여질 사진의 순서", example = "1")
    private Long photoOrderInReview;
    @Schema(description = "리뷰 사진의 URL", example = "https.www.~")
    private String reviewUrl;


    @Builder
    public ReviewPhotoInfoDto(Long id, Long photoOrderInReview, String reviewUrl) {
        this.id = id;
        this.photoOrderInReview = photoOrderInReview;
        this.reviewUrl = reviewUrl;
    }
}

