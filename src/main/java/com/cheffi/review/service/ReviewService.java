package com.cheffi.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.review.dto.RatingInfoDto;
import com.cheffi.review.dto.RestaurantInfoDto;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.ReviewPhotoInfoDto;
import com.cheffi.review.dto.request.WriteReviewRequest;
import com.cheffi.review.dto.response.SearchReviewResponse;

@Transactional
@Service
public class ReviewService {

	public SearchReviewResponse searchReview(Long reviewId) {

		List<ReviewPhotoInfoDto> mockReviewPhotoes = new ArrayList<>();
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(1L, 5, "https://cdn.mindgil.com/news/photo/202109/72274_10092_4553.jpg"));
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(2L, 2, "https://dimg.donga.com/wps/NEWS/IMAGE/2011/08/18/39608221.2.jpg"));
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(3L, 4, "http://www.lampcook.com/wi_files/food_top100/top5/5_8.jpg"));
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(4L, 1, "http://www.lampcook.com/wi_files/food_top100/top5/5_14.jpg"));
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(5L, 3, "http://www.lampcook.com/wi_files/food_top100/top5/5_11.jpg"));
		mockReviewPhotoes.add(new ReviewPhotoInfoDto(6L, 6, "http://www.lampcook.com/wi_files/food_top100/top5/5_17.jpg"));

		List<RatingInfoDto> mockRatingInfoes = new ArrayList<>();
		mockRatingInfoes.add(new RatingInfoDto("GOOD", 30));
		mockRatingInfoes.add(new RatingInfoDto("Bad", -7));

		return SearchReviewResponse.builder()
			.reviewInfo(ReviewInfoDto.builder()
				.id(5L)
				.title("리뷰 제목")
				.text("리뷰 내용")
				.ratingCnt(27)
				.bookmarked(true)
				.build())
			.restaurant(RestaurantInfoDto.builder()
				.id(1L)
				.name("태초밥")
				.detailedAddress(
					DetailedAddress.of("수유리",
						"강북구",
						"서울시",
						"평화로 45길 12-11 502호")
				)
				.build())
			.reviewPhotos(mockReviewPhotoes)
			.ratings(mockRatingInfoes)
			.build();
	}

	public List<ReviewInfoDto> searchReviewsByArea(String areaName) {

		Random random = new Random();
		List<ReviewInfoDto> mockDtos = new ArrayList<>();
		for (Long i = 1L; i <= 200; i++) {

			mockDtos.add(ReviewInfoDto.builder()
				.id(i)
				.title("title(" + i +")")
				.text("text(" + i +")")
				.ratingCnt(random.nextInt(50) +  1)
				.bookmarked(i % 2 == 0)
				.build());
		}

		return mockDtos;
	}

	public Long writeReview(WriteReviewRequest requestDto, Long writerId) {
		return 1L;
	}
}
