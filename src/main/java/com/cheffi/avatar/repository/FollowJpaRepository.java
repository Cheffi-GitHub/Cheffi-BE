package com.cheffi.avatar.repository;

import static com.cheffi.avatar.domain.QAvatarTag.*;
import static com.cheffi.avatar.domain.QFollow.*;
import static com.cheffi.avatar.domain.QProfilePhoto.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cheffi.avatar.domain.QAvatar;
import com.cheffi.avatar.dto.response.QRecommendFollowResponse;
import com.cheffi.avatar.dto.response.RecommendFollowResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class FollowJpaRepository {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;
	private final QAvatar recommended = new QAvatar("recommended");

	public FollowJpaRepository(EntityManager em) {
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}

	public List<RecommendFollowResponse> recommendByTag(Long tagId, Long avatarId) {
		JPAQuery<RecommendFollowResponse> query = queryFactory
			.from(recommended)
			.select(new QRecommendFollowResponse(
				recommended.id,
				recommended.nickname,
				profilePhoto.url,
				recommended.introduction,
				recommended.followerCnt,
				follow.isNotNull()
			))
			.leftJoin(recommended.photo, profilePhoto)
			.leftJoin(follow)
			.on(follow.target.id.eq(recommended.id),
				follow.subject.id.eq(avatarId))
			.leftJoin(recommended.avatarTags, avatarTag)
			.on(avatarTag.tag.id.eq(tagId))
			.where(follow.isNull(),
				avatarTag.isNotNull(),
				recommended.id.ne(avatarId))
			.orderBy(recommended.followerCnt.desc())
			.limit(12);

		return query.fetch();
	}
}
