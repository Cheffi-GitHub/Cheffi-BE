package com.cheffi.avatar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.avatar.dto.response.AddFollowResponse;
import com.cheffi.avatar.dto.response.GetFollowResponse;
import com.cheffi.avatar.dto.response.UnfollowResponse;
import com.cheffi.avatar.service.FollowService;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/avatars/follow")
public class FollowController {

	private final FollowService followService;
	@Tag(name = "Follow")
	@Operation(summary = "아바타 팔로우 등록 API",
		description = "팔로우 추가 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PostMapping
	public ApiResponse<AddFollowResponse> addFollow(HttpServletRequest request,
		Long avatarId) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(followService.addFollow(1L, avatarId));
	}

	@Tag(name = "Follow")
	@Operation(summary = "아바타 팔로우 취소 API",
		description = "팔로우 취소 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@DeleteMapping
	public ApiResponse<UnfollowResponse> unfollow(HttpServletRequest request,
		Long avatarId) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(followService.unfollow(1L, avatarId));
	}

	@Tag(name = "Follow")
	@Operation(summary = "자신의 팔로우 목록 조회 API",
		description = "팔로우 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping
	public ApiResponse<List<GetFollowResponse>> getMyFollowee(HttpServletRequest request) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(followService.getFollowee(1L));
	}
}
