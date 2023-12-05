package com.cheffi.avatar.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.CheffiCoin;
import com.cheffi.avatar.repository.CheffiCoinRepository;
import com.cheffi.review.domain.Review;
import com.cheffi.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CheffiCoinService {

	private static final int CHEFFI_COIN_FOR_REVIEW_UNLOCK = -1;
	private static final int CHEFFI_COIN_FOR_DAILY_REWARD = 5;
	private final CheffiCoinRepository cheffiCoinRepository;
	private final CheffiCoinReasonService cheffiCoinReasonService;
	private final AvatarService avatarService;
	private final ReviewService reviewService;

	@Transactional
	public void useCheffiCoinForUnlock(Long avatarId, Long reviewId) {
		Review review = reviewService.getById(reviewId);
		CheffiCoin cheffiCoin = CheffiCoin.of(avatarService.getById(avatarId), CHEFFI_COIN_FOR_REVIEW_UNLOCK,
			cheffiCoinReasonService.getReasonForReviewUnlock(review.getId()));
		cheffiCoinRepository.save(cheffiCoin);
	}

	@Transactional
	public void earnCheffiCoinForLogin(Long avatarId) {
		CheffiCoin cheffiCoin = CheffiCoin.of(avatarService.getById(avatarId)
			, CHEFFI_COIN_FOR_DAILY_REWARD, cheffiCoinReasonService.getReasonForDailyReward(LocalDateTime.now()));
		cheffiCoinRepository.save(cheffiCoin);
	}
}
