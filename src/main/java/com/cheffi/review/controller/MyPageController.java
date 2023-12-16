package com.cheffi.review.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.avatar.dto.MyPageInfo;
import com.cheffi.avatar.service.AvatarService;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.oauth.model.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class MyPageController {

	private final AvatarService avatarService;

	@Tag(name = "My Page")
	@Operation(summary = "마이페이지 정보 조회 API",
		description = "마이페이지 정보 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/my-page")
	public ApiResponse<MyPageInfo> getMyPageInfo(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.success(avatarService.getMyPageInfo(principal.getAvatarId()));
	}

}
