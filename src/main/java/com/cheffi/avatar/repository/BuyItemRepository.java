package com.cheffi.avatar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheffi.avatar.domain.BuyItem;

public interface BuyItemRepository extends JpaRepository<BuyItem, Long> {

	@Query("select b from BuyItem b "
		+ "where b.avatar.id = :avatar "
		+ "and b.review.id = :review ")
	Optional<BuyItem> findByAvatarAndReview(@Param("avatar") Long avatarId, @Param("review") Long reviewId);
}
