package com.cheffi.review.dto.response;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.review.domain.Rating;
import com.cheffi.review.domain.Restaurant;
import com.cheffi.review.domain.Review;
import com.cheffi.review.domain.ReviewPhoto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ApiResponse의 T date에 전달할 클래스
 */
@Getter @Setter
@ToString
public class GetReviewResponseDto {

    private Long    reviewid;
    private String  title;
    private String  text;
    private boolean bookmark;

    private RestaurantValue restaurant;

    private List<ReviewPhotosValue>  reviewphotos;
    private List<RatingValue>        ratings;


    /**
     * <p>
     * 단건조회 반환값 생성 메서드
     * </p>
     *
     * @param searchedReview    searched review entity
     * @param bookmark  review bookmark state
     * @return GetReviewResponseDto
     */
    public static GetReviewResponseDto of(Review searchedReview,
                                          boolean bookmark) {

        RestaurantValue mockRestaurant = new RestaurantValue(searchedReview.getRestaurant());

        List<ReviewPhotosValue> mockReviewPhotos = searchedReview.getReviewPhotos()
                .stream()
                .map(ReviewPhotosValue::new)
                .toList();

        List<RatingValue> mockRatings = searchedReview.getRatings()
                .stream()
                .map(RatingValue::new)
                .toList();

        return GetReviewResponseDto.builder()
                .reviewid(searchedReview.getId())
                .title(searchedReview.getTitle())
                .text(searchedReview.getText())
                .bookmark(bookmark)
                .restaurant(mockRestaurant)
                .reviewphotos(mockReviewPhotos)
                .ratings(mockRatings)
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

        this.reviewid = reviewid;
        this.text = text;
        this.title = title;
        this.bookmark = bookmark;
        this.restaurant = restaurant;
        this.reviewphotos = reviewphotos;
        this.ratings = ratings;
    }




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



    /**
     * RestaurantValue 보여줄 값만 정의한 InnerClass
     */
    @Getter
    public static class RestaurantValue {

        private String detail;
        private String town;
        private String city;
        private String province;
        private String name;

        public RestaurantValue(Restaurant restaurant) {

            DetailedAddress detailedAddress = restaurant.getDetailedAddress();

            this.detail = restaurant.getName();
            this.town = detailedAddress.getProvince();
            this.city = detailedAddress.getCity();
            this.province = detailedAddress.getTown();
            this.name = detailedAddress.getDetail();
        }

        //TODO 데이터 생기면 삭제
        public static RestaurantValue getMockRestaurantValue() {

            return new RestaurantValue();
        }

        //TODO 데이터 생기면 삭제
        public RestaurantValue() {
            this.detail = "맛집 상가명";
            this.town = "시/도";
            this.city = "구/시/군";
            this.province = "동/읍/면/리";
            this.name = "상세주소";
        }
    }


    /**
     * ReviewPhoto중 보여줄 값만 정의한 InnerClass
     */
    @Getter
    public static class ReviewPhotosValue {

        private Long    photoOrderInReview;
        private String  reviewUrl;

        public ReviewPhotosValue(ReviewPhoto reviewPhoto) {
            this.photoOrderInReview = reviewPhoto.getId();
            this.reviewUrl = reviewPhoto.getUrl();
        }

        //TODO 데이터 생기면 삭제
        public static List<ReviewPhotosValue> getMockReviewPhotoValues() {

            List<ReviewPhotosValue> result = new ArrayList<>();

            for (Long i = 1L; i < 3; i++) {
                result.add(new ReviewPhotosValue(i,
                        "www.photoUrl" + i));
            }

            return result;
        }

        //TODO 데이터 생기면 삭제
        public ReviewPhotosValue(Long photoOrderInReview, String reviewUrl) {
            this.photoOrderInReview = photoOrderInReview;
            this.reviewUrl = reviewUrl;
        }
    }




    /**
     * RatingValue중 보여줄 값만 정의한 InnerClass
     */
    @Getter
    public static class RatingValue {

        private String  ratingType;
        private int     score;

        public RatingValue(Rating rating) {
            this.ratingType = rating.getRatingType().name();
            this.score = rating.getRatingType().getScore();
        }

        //TODO 데이터 생기면 삭제
        public static List<RatingValue> getMockRatingValues() {

            List<RatingValue> result = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                result.add(new RatingValue("GOOD", 5));
            }

            return result;
        }

        //TODO 데이터 생기면 삭제
        public RatingValue(String ratingType, int score) {
            this.ratingType = ratingType;
            this.score = score;
        }
    }
}
