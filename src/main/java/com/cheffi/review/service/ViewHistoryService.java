package com.cheffi.review.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.service.AvatarService;
import com.cheffi.event.event.ReviewReadEvent;
import com.cheffi.review.domain.Review;
import com.cheffi.review.domain.ViewHistory;
import com.cheffi.review.dto.ScoreDto;
import com.cheffi.review.repository.ViewHistoryJpaRepository;
import com.cheffi.review.repository.ViewHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewHistoryService {

	private final ViewHistoryRepository viewHistoryRepository;
	private final ViewHistoryJpaRepository viewHistoryJpaRepository;
	private final ReviewService reviewService;
	private final AvatarService avatarService;

	@Transactional
	public void saveViewHistory(ReviewReadEvent event) {
		Review review = reviewService.getById(event.getReviewId());
		if (event.isAuthenticated())
			viewHistoryRepository.save(ViewHistory.of(avatarService.getById(event.getViewerId()), review));
		else
			viewHistoryRepository.save(ViewHistory.ofNotAuthenticated(review));
	}

	@Transactional
	public void increaseViewCount(ReviewReadEvent event) {
		reviewService.increaseViewCount(event.getReviewId());
	}

	public List<ScoreDto> getViewScoreBetween(List<Long> ids, LocalDateTime start, LocalDateTime end) {
		return viewHistoryJpaRepository.countBetween(ids, start, end);
	}
}
