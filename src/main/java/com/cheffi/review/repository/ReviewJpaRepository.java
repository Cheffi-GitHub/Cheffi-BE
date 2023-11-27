package com.cheffi.review.repository;

import static com.cheffi.avatar.domain.QPurchasedItem.*;
import static com.cheffi.review.domain.QBookmark.*;
import static com.cheffi.review.domain.QReview.*;
import static com.cheffi.review.domain.QReviewPhoto.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cheffi.review.dto.QReviewInfoDto;
import com.cheffi.review.dto.QReviewPhotoInfoDto;
import com.cheffi.review.dto.ReviewInfoDto;
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

}
