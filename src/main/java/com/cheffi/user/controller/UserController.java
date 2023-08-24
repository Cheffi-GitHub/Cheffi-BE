package com.cheffi.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.oauth.model.UserPrincipal;
import com.cheffi.user.dto.adapter.UserInfo;
import com.cheffi.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

	private final UserService userService;

	@Tag(name = "User")
	@Operation(summary = "유저 정보 조회 API",
		description = "자신의 계정 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping
	public ApiResponse<UserInfo> getSignedUserInfo(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.success(userService.getUserInfo(principal.getUserId()));
	}

}
