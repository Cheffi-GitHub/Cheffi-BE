package com.cheffi.review.dto.response;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.common.dto.RatingValue;
import com.cheffi.common.dto.RestaurantValue;
import com.cheffi.common.dto.ReviewPhotosValue;
import com.cheffi.review.domain.Rating;
import com.cheffi.review.domain.Restaurant;
import com.cheffi.review.domain.Review;
import com.cheffi.review.domain.ReviewPhoto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.ArrayList;
import java.util.List;

/**
 * ApiResponse의 T data에 전달할 클래스
 */
@Getter
@Setter
@ToString
public class GetReviewResponseDto {

    private Long    reviewId;
    private String  title;
    private String  text;
    private boolean bookmarked;

    private RestaurantValue restaurant;

    private List<ReviewPhotosValue>  reviewPhotos;
    private List<RatingValue>        ratings;


    /**
     * <p>
     * 단건조회 반환값 생성 메서드
     * </p>
     *
     * @param searchedReview    searched review entity
     * @param bookmarked  review bookmark state
     * @return GetReviewResponseDto
     */
    public static GetReviewResponseDto of(Review searchedReview,
                                          RestaurantValue restaurantValue,
                                          List<ReviewPhotosValue> reviewPhotoValues,
                                          List<RatingValue> ratingValues,
                                          boolean bookmarked) {

        return GetReviewResponseDto.builder()
                .reviewid(searchedReview.getId())
                .title(searchedReview.getTitle())
                .text(searchedReview.getText())
                .bookmark(bookmarked)
                .restaurant(restaurantValue)
                .reviewphotos(reviewPhotoValues)
                .ratings(ratingValues)
                .build();

    }

    @Builder
    private GetReviewResponseDto(Long reviewid,
                                String title,
                                String text,
                                boolean bookmark,
                                RestaurantValue restaurant,
                                List<ReviewPhotosValue> reviewphotos,
                                List<RatingValue> ratings) {

        this.reviewId = reviewid;
        this.text = text;
        this.title = title;
        this.bookmarked = bookmark;
        this.restaurant = restaurant;
        this.reviewPhotos = reviewphotos;
        this.ratings = ratings;
    }


    /**
     * Mock 데이터로 이루어진 Dto 반환
     * @return
     */
    //TODO 데이터 생기면 삭제
    public static GetReviewResponseDto getMockDto() {

        RestaurantValue mockRestaurant = RestaurantValue.getMockRestaurantValue();
        List<ReviewPhotosValue> mockReviewPhotos = ReviewPhotosValue.getMockReviewPhotoValues();
        List<RatingValue> mockRatings = RatingValue.getMockRatingValues();

        return GetReviewResponseDto.builder()
                .reviewid(5L)
                .title("리뷰 제목")
                .text("리뷰 내용")
                .bookmark(true)
                .restaurant(mockRestaurant)
                .reviewphotos(mockReviewPhotos)
                .ratings(mockRatings)
                .build();
    }







}
