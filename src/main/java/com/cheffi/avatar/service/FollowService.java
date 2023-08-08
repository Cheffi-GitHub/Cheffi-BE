package com.cheffi.avatar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.response.AddFollowResponse;
import com.cheffi.avatar.dto.response.GetFollowResponse;
import com.cheffi.avatar.dto.response.UnfollowResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	public static final String PICTURE_URL = "https://undongin.com/data/editor/0107/1609980770_6067.jpg";

	public AddFollowResponse addFollow(Long followerId, Long followeeId) {
		return new AddFollowResponse(followerId, followeeId);
	}

	public UnfollowResponse unfollow(Long followerId, Long followeeId) {
		return new UnfollowResponse(followerId, followeeId);
	}

	public List<GetFollowResponse> getFollowee(Long userId) {
		return List.of(new GetFollowResponse(2L, "구창모", PICTURE_URL),
			new GetFollowResponse(3L, "신동갑", PICTURE_URL),
			new GetFollowResponse(4L, "이준경", PICTURE_URL),
			new GetFollowResponse(5L, "임성빈", PICTURE_URL),
			new GetFollowResponse(6L, "강민호", PICTURE_URL)
			);
	}
}
