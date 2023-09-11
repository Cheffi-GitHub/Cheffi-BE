package com.cheffi.avatar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.Follow;
import com.cheffi.avatar.dto.response.AddFollowResponse;
import com.cheffi.avatar.dto.response.GetFollowResponse;
import com.cheffi.avatar.dto.response.RecommendFollowResponse;
import com.cheffi.avatar.dto.response.UnfollowResponse;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.avatar.repository.FollowRepository;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.config.exception.business.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	private final AvatarRepository avatarRepository;
	private final FollowRepository followRepository;


	public AddFollowResponse addFollow(Long followerId, Long followeeId) {

		Avatar follower = avatarRepository
			.findById(followerId)
			.orElseThrow( () -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));
		Avatar followee = avatarRepository
			.findById(followeeId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));

		boolean isAlreadyFollowed = followRepository
			.findBySubjectAndTarget(followee, follower)
			.isPresent();
		if (isAlreadyFollowed) {
			throw new BusinessException(ErrorCode.ALREADY_FOLLOWED);
		}

		Follow toCreateFollow = Follow.createFollowRelationship(follower, followee);
		Follow createdFollow = followRepository.save(toCreateFollow);

		return new AddFollowResponse(
			createdFollow.getSubject().getId(),
			createdFollow.getTarget().getId()
		);
	}

	public UnfollowResponse unfollow(Long followerId, Long followeeId) {
		return new UnfollowResponse(followerId, followeeId);
	}

	public List<GetFollowResponse> getFollowee(Long userId) {
		return GetFollowResponse.mock();
	}

	public List<RecommendFollowResponse> recommendFollowee(Long userId) {
		return RecommendFollowResponse.mock();
	}
}
