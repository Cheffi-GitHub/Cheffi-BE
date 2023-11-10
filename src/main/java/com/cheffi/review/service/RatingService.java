package com.cheffi.review.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.review.dto.RatingInfoDto;
import com.cheffi.review.dto.dao.ScoreDto;
import com.cheffi.review.repository.RatingJpaRepository;
import com.cheffi.review.repository.RatingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RatingService {

	private final RatingRepository ratingRepository;
	private final RatingJpaRepository ratingJpaRepository;

	public RatingInfoDto getRatingInfoOf(Long avatarId, Long reviewId) {
		return ratingRepository.findByAvatarAndReview(avatarId, reviewId)
			.map(RatingInfoDto::of)
			.orElseGet(RatingInfoDto::notRated);
	}

	public List<ScoreDto> getRatingScoreBetween(List<Long> ids, LocalDateTime start, LocalDateTime end) {
		return ratingJpaRepository.countBetween(ids, start, end);
	}
}
