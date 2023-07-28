package com.cheffi.common.dto;

import com.cheffi.review.domain.ReviewPhoto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ReviewPhotosValue {

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

