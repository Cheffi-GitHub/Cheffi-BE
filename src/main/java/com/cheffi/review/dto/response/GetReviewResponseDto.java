package com.cheffi.review.dto.response;

import com.cheffi.common.dto.DetailAddressInfoDto;
import com.cheffi.common.dto.RatingInfoDto;
import com.cheffi.common.dto.RestaurantInfoDto;
import com.cheffi.common.dto.ReviewInfoDto;
import com.cheffi.common.dto.ReviewPhotoInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.List;

@NoArgsConstructor
@Getter @Setter
@ToString
public class GetReviewResponseDto {

    @Schema(description = "리뷰 정보", example = "너무 맛있어요")
    private ReviewInfoDto reviewInfo;
    @Schema(description = "리뷰의 맛집 정보")
    private RestaurantInfoDto restaurant;
    @Schema(description = "리뷰의 맛집 주소")
    private DetailAddressInfoDto addressInfo;

    @Schema(description = "리뷰의 맛집 정보")
    private List<ReviewPhotoInfoDto> reviewPhotos;
    @Schema(description = "리뷰의 타입별 총합점수")
    private List<RatingInfoDto> ratings;


    @Builder
    private GetReviewResponseDto(ReviewInfoDto reviewInfo,
                                RestaurantInfoDto restaurant,
                                DetailAddressInfoDto addressInfo,
                                List<ReviewPhotoInfoDto> reviewphotos,
                                List<RatingInfoDto> ratings) {

        this.reviewInfo = reviewInfo;
        this.restaurant = restaurant;
        this.addressInfo = addressInfo;
        this.reviewPhotos = reviewphotos;
        this.ratings = ratings;
    }


}
