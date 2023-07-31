package com.cheffi.review;

import com.cheffi.review.dto.RatingInfoDto;
import com.cheffi.review.dto.RestaurantInfoDto;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.ReviewPhotoInfoDto;
import com.cheffi.review.dto.response.SearchReviewResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ReviewService {

    public SearchReviewResponse searchReview(Long reviewId) {

        List<ReviewPhotoInfoDto> mockReviewPhotoes = new ArrayList<>();
        for (Long i = 1L; i < 3; i++) {
            mockReviewPhotoes.add(new ReviewPhotoInfoDto(i,
                    "www.photoUrl" + i));
        }
        List<RatingInfoDto> mockRatingInfoes = new ArrayList<>();
        mockRatingInfoes.add(new RatingInfoDto("GOOD", 30));
        mockRatingInfoes.add(new RatingInfoDto("Bad", -7));


        return SearchReviewResponse.builder()
                .reviewInfo(ReviewInfoDto.builder()
                        .id(5L)
                        .title("리뷰 제목")
                        .text("리뷰 내용")
                        .bookmarked(true)
                        .build())
                .restaurant(RestaurantInfoDto.builder()
                        .id(1L)
                        .name("태초밥")
                        .town("서울시")
                        .city("강북구")
                        .province("수유리")
                        .detail("평화로 45길 12-11 502호")
                        .build())
                .reviewphotos(mockReviewPhotoes)
                .ratings(mockRatingInfoes)
                .build();
    }


    public List<ReviewInfoDto> getRegionalReviews() {

        List<ReviewInfoDto> mockDtos = new ArrayList<>();
        for (Long i = 1L; i < 11; i++) {

            mockDtos.add(ReviewInfoDto.builder()
                    .id(i)
                    .title("title" + i)
                    .bookmarked(i % 2 == 0)
                    .build());
        }

        return mockDtos;
    }

}
