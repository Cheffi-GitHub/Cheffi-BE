package com.cheffi.review.dto.response;

import com.cheffi.common.dto.ReviewInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetRegionalReviewsResponseDto {

    @Schema(description = "리뷰 ID", example = "5")
    private ReviewInfoDto reviewInfo;

    public GetRegionalReviewsResponseDto(ReviewInfoDto reviewInfo) {
        this.reviewInfo = reviewInfo;
    }
}
