package com.cheffi.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.service.PurchasedItemService;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.review.domain.Review;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.response.GetReviewResponse;
import com.cheffi.review.dto.response.ReviewWriterInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewSearchService {

	private final ReviewService reviewService;
	private final PurchasedItemService purchasedItemService;
	private final ReviewAvatarService reviewAvatarService;

	@Transactional
	public GetReviewResponse getReviewInfoOfNotAuthenticated(Long reviewId) {
		Review review = reviewService.getByIdWithEntities(reviewId);
		Avatar writer = review.getWriter();
		if (review.isLocked())
			throw new AuthenticationException(ErrorCode.ANONYMOUS_USER_CANNOT_ACCESS_LOCKED_REVIEW);

		review.read();
		return GetReviewResponse.ofNotAuthenticated(review, ReviewWriterInfoDto.of(writer));
	}

	@Transactional
	public GetReviewResponse getReviewInfoOfAuthenticated(Long reviewId, Long viewerId) {
		Review review = reviewService.getByIdWithEntities(reviewId);
		Avatar writer = review.getWriter();

		if (!writer.hasSameIdWith(viewerId)) {
			if (review.isLocked() && !purchasedItemService.hasUnlocked(viewerId, reviewId)) {
				throw new BusinessException(ErrorCode.REVIEW_NOT_UNLOCKED);
			}
			review.read();
		}

		return GetReviewResponse.ofAuthenticated(review, reviewAvatarService.getInfoOfViewer(viewerId, reviewId),
			ReviewWriterInfoDto.of(writer, writer.getId().equals(viewerId)));
	}

	public List<ReviewInfoDto> searchReviewsByArea(String areaName) {

		Random random = new Random();
		List<ReviewInfoDto> mockDtos = new ArrayList<>();
		for (long i = 1L; i <= 200; i++) {

			mockDtos.add(ReviewInfoDto.builder()
				.id(i)
				.title("title(" + i + ")")
				.text("text(" + i + ")")
				.ratingCnt(random.nextInt(50) + 1)
				.bookmarked(i % 2 == 0)
				.build());
		}

		return mockDtos;
	}

}
