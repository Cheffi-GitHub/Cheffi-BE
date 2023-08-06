package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.response.AddFollowResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	public AddFollowResponse addFollow(Long followerId, Long followeeId) {
		return new AddFollowResponse(followerId, followeeId);
	}
}
