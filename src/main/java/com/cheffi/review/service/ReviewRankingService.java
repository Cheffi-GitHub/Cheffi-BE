package com.cheffi.review.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.constant.Address;
import com.cheffi.review.domain.Review;
import com.cheffi.review.domain.ReviewRanking;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewRankingService {

	private final ReviewService reviewService;
	private final RatingService ratingService;
	private final ViewHistoryService viewHistoryService;

	@Transactional(readOnly = true)
	public Set<ZSetOperations.TypedTuple<Object>> calculateRanking(Address address, LocalDateTime from, LocalDateTime to) {
		List<Review> queriedReviews = reviewService.getByAddress(address);
		if(queriedReviews.isEmpty())
			throw new BusinessException(ErrorCode.REVIEW_NOT_EXIST_IN_AREA);
		ReviewRanking reviewRanking = new ReviewRanking(queriedReviews);

		List<Long> ids = reviewRanking.getIds();
		reviewRanking.includeScore(ratingService.getRatingScoreBetween(ids, from, to));
		reviewRanking.includeScore(viewHistoryService.getViewScoreBetween(ids, from, to));

		return reviewRanking.toTuples();
	}
}
