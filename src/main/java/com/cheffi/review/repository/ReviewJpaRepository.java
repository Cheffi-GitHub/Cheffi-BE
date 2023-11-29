package com.cheffi.review.repository;

import static com.cheffi.avatar.domain.QPurchasedItem.*;
import static com.cheffi.review.domain.QBookmark.*;
import static com.cheffi.review.domain.QRestaurant.*;
import static com.cheffi.review.domain.QReview.*;
import static com.cheffi.review.domain.QReviewPhoto.*;
import static com.cheffi.review.domain.QReviewTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cheffi.common.constant.Address;
import com.cheffi.review.domain.Review;
import com.cheffi.review.dto.QReviewInfoDto;
import com.cheffi.review.dto.QReviewPhotoInfoDto;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.ReviewSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class ReviewJpaRepository {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;

	public ReviewJpaRepository(EntityManager em) {
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}

	public List<ReviewInfoDto> findAllById(List<Long> ids) {
		if (ids.isEmpty())
			return List.of();

		JPAQuery<ReviewInfoDto> query = queryFactory
			.select(new QReviewInfoDto(
				review.id,
				review.title,
				review.text,
				new QReviewPhotoInfoDto(reviewPhoto.id, reviewPhoto.givenOrder, reviewPhoto.url),
				review.timeToLock,
				review.viewCnt,
				review.status,
				Expressions.FALSE,
				Expressions.FALSE,
				Expressions.FALSE
			))
			.from(review)
			.leftJoin(review.photos, reviewPhoto)
			.on(photoOrderEq(0))
			.where(reviewIdIn(ids));

		return query.fetch();
	}

	public List<ReviewInfoDto> findAllByIdWithBookmark(List<Long> ids, Long viewerId) {
		if (ids.isEmpty())
			return List.of();

		JPAQuery<ReviewInfoDto> query = queryFactory
			.select(new QReviewInfoDto(
				review.id,
				review.title,
				review.text,
				new QReviewPhotoInfoDto(reviewPhoto.id, reviewPhoto.givenOrder, reviewPhoto.url),
				review.timeToLock,
				review.viewCnt,
				review.status,
				bookmark.isNotNull(),
				review.writer.id.eq(viewerId),
				purchasedItem.isNotNull()
			))
			.from(review)
			.leftJoin(review.photos, reviewPhoto)
			.on(photoOrderEq(0))
			.leftJoin(bookmark)
			.on(bookmark.review.id.eq(review.id),
				bookmarkWriterEq(viewerId))
			.leftJoin(purchasedItem)
			.on(purchasedItem.review.eq(review),
				purchasedItem.avatar.id.eq(viewerId))
			.where(reviewIdIn(ids));

		return query.fetch();

	}

	private BooleanExpression bookmarkWriterEq(Long viewerId) {
		return bookmark.avatar.id.eq(viewerId);
	}

	private BooleanExpression reviewIdIn(List<Long> reviewIds) {
		return review.id.in(reviewIds);
	}

	private BooleanExpression photoOrderEq(Integer givenOrder) {
		return reviewPhoto.givenOrder.eq(givenOrder);
	}

	public List<Review> findByCondition(ReviewSearchCondition condition) {
		Address address = condition.getAddress();

		JPAQuery<Review> query = queryFactory
			.select(review)
			.from(restaurant)
			.where(
				restaurant.detailedAddress.province.eq(address.getProvince()),
				restaurant.detailedAddress.city.eq(address.getCity()))
			.leftJoin(review)
			.on(restaurant.eq(review.restaurant));

		Long tagId = condition.getTagId();
		if (tagId != null)
			query = query.rightJoin(reviewTag)
				.on(reviewTag.review.eq(review),
					reviewTag.tag.id.eq(tagId));

		return query.fetch();
	}
}
