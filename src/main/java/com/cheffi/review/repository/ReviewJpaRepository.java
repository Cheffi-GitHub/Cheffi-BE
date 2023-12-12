package com.cheffi.review.repository;

import static com.cheffi.avatar.domain.QPurchasedItem.*;
import static com.cheffi.review.domain.QBookmark.*;
import static com.cheffi.review.domain.QMenu.*;
import static com.cheffi.review.domain.QRestaurant.*;
import static com.cheffi.review.domain.QReview.*;
import static com.cheffi.review.domain.QReviewPhoto.*;
import static com.cheffi.review.domain.QReviewTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cheffi.common.constant.Address;
import com.cheffi.review.constant.ReviewStatus;
import com.cheffi.review.domain.Review;
import com.cheffi.review.dto.AddressSearchRequest;
import com.cheffi.review.dto.MenuSearchRequest;
import com.cheffi.review.dto.QReviewInfoDto;
import com.cheffi.review.dto.QReviewPhotoInfoDto;
import com.cheffi.review.dto.ReviewCursor;
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

	public List<ReviewInfoDto> findAllById(List<Long> ids, Long viewerId) {
		if (ids.isEmpty())
			return List.of();

		JPAQuery<?> common = queryFactory
			.from(review)
			.leftJoin(review.photos, reviewPhoto)
			.on(photoOrderEq(0))
			.where(reviewIdIn(ids));

		if (viewerId == null)
			return selectSimple(common).fetch();

		return selectWithViewer(common, viewerId).fetch();
	}

	public List<Review> findByCondition(ReviewSearchCondition condition) {
		JPAQuery<Review> query = queryFactory
			.select(review)
			.from(restaurant)
			.where(
				restaurantAddressEq(condition.getAddress())
			)
			.leftJoin(review)
			.on(restaurant.eq(review.restaurant));

		Long tagId = condition.getTagId();
		if (tagId != null)
			query = query.rightJoin(reviewTag)
				.on(reviewTag.review.eq(review),
					reviewTag.tag.id.eq(tagId));

		return query.fetch();
	}

	public List<ReviewInfoDto> findByMenu(MenuSearchRequest request, Long viewerId) {
		ReviewCursor cursor = request.getCursor();

		JPAQuery<?> common = queryFactory
			.from(review)
			.distinct()
			.innerJoin(review.menus, menu)
			.on(menu.name.startsWith(request.getMenu()))
			.leftJoin(review.photos, reviewPhoto)
			.on(photoOrderEq(0))
			.where(review.status.eq(ReviewStatus.ACTIVE)
				.and(review.viewCnt.lt(cursor.getCount())
					.or(review.viewCnt.eq(cursor.getCount()).and(review.id.loe(cursor.getId())))))
			.orderBy(review.viewCnt.desc(), review.id.desc())
			.limit(request.getSize() + 1L);

		if (viewerId == null) {
			return selectSimple(common).fetch();
		}
		return selectWithViewer(common, viewerId).fetch();
	}

	public List<ReviewInfoDto> findByAddress(AddressSearchRequest request, Long viewerId) {
		ReviewCursor cursor = request.getCursor();

		JPAQuery<?> common = queryFactory
			.from(review)
			.leftJoin(review.restaurant, restaurant)
			.leftJoin(review.photos, reviewPhoto)
			.on(photoOrderEq(0))
			.where(review.status.eq(ReviewStatus.ACTIVE)
					.and(review.viewCnt.lt(cursor.getCount())
						.or(review.viewCnt.eq(cursor.getCount()).and(review.id.loe(cursor.getId()))))
				, restaurantAddressEq(request.getAddress())
			)
			.orderBy(review.viewCnt.desc(), review.id.desc())
			.limit(request.getSize() + 1);

		if (viewerId == null) {
			return selectSimple(common).fetch();
		}
		return selectWithViewer(common, viewerId).fetch();
	}

	private static JPAQuery<ReviewInfoDto> selectSimple(JPAQuery<?> query) {
		return query.select(new QReviewInfoDto(
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
		));
	}

	private static JPAQuery<ReviewInfoDto> selectWithViewer(JPAQuery<?> query, Long viewerId) {
		return query.select(new QReviewInfoDto(
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
			.leftJoin(bookmark)
			.on(bookmark.review.id.eq(review.id),
				bookmarkWriterEq(viewerId))
			.leftJoin(purchasedItem)
			.on(purchasedItem.review.eq(review),
				purchasedItem.avatar.id.eq(viewerId));
	}

	private static BooleanExpression restaurantAddressEq(Address address) {
		return restaurant.detailedAddress.province.eq(address.getProvince())
			.and(restaurant.detailedAddress.city.eq(address.getCity()));
	}

	private static BooleanExpression bookmarkWriterEq(Long viewerId) {
		return bookmark.avatar.id.eq(viewerId);
	}

	private static BooleanExpression reviewIdIn(List<Long> reviewIds) {
		return review.id.in(reviewIds);
	}

	private static BooleanExpression photoOrderEq(Integer givenOrder) {
		return reviewPhoto.givenOrder.eq(givenOrder);
	}
}
