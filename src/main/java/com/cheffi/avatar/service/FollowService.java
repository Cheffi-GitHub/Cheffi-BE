package com.cheffi.avatar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.response.AddFollowResponse;
import com.cheffi.avatar.dto.response.GetFollowResponse;
import com.cheffi.avatar.dto.response.RecommendFollowResponse;
import com.cheffi.avatar.dto.response.UnfollowResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {


	public AddFollowResponse addFollow(Long followerId, Long followeeId) {
		return new AddFollowResponse(followerId, followeeId);
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
