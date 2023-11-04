package com.cheffi.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.service.AvatarService;
import com.cheffi.review.domain.Review;
import com.cheffi.review.domain.ViewHistory;
import com.cheffi.review.repository.ViewHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewHistoryService {

	private final ViewHistoryRepository viewHistoryRepository;
	private final ReviewService reviewService;
	private final AvatarService avatarService;

	@Transactional
	public void readReview(Long viewerId, Long reviewId) {
		Review review = reviewService.getById(reviewId);
		viewHistoryRepository.save(ViewHistory.of(avatarService.getById(viewerId), review));
		review.read();
	}

	@Transactional
	public void readReviewAnonymous(Long reviewId) {
		Review review = reviewService.getById(reviewId);
		viewHistoryRepository.save(ViewHistory.ofNotAuthenticated(review));
		review.read();
	}
}
