package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.BuyItem;
import com.cheffi.avatar.repository.BuyItemRepository;
import com.cheffi.review.domain.Review;
import com.cheffi.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BuyItemService {

	private final BuyItemRepository buyItemRepository;
	private final AvatarService avatarService;
	private final ReviewService reviewService;
	private final CheffiCoinService cheffiCoinService;

	@Transactional(readOnly = true)
	public BuyItem unlock(Long avatarId, Long reviewId) {
		Avatar avatar = avatarService.getById(avatarId);
		Review review = reviewService.getById(reviewId);
		cheffiCoinService.useCheffiCoinForUnlock(avatarId, reviewId);
		return buyItemRepository.save(new BuyItem(avatar, review));
	}

	@Transactional(readOnly = true)
	public boolean hasUnlocked(Long avatarId, Long reviewId) {
		return buyItemRepository.findByAvatarAndReview(avatarId, reviewId).isPresent();
	}


}
