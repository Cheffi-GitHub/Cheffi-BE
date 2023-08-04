package com.cheffi.avatar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.avatar.dto.request.TagsChangeRequest;
import com.cheffi.avatar.dto.response.AvatarInfoResponse;
import com.cheffi.avatar.dto.response.SelfAvatarInfoResponse;
import com.cheffi.avatar.dto.response.TagsChangeResponse;
import com.cheffi.avatar.service.AvatarService;
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
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final AvatarService avatarService;

	@Tag(name = "Avatar")
	@Operation(summary = "아바타 취향 변경 API",
		description = "자신의 취향 변경 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PatchMapping("/tags")
	public ApiResponse<TagsChangeResponse> changeTags(HttpServletRequest request,
		@RequestBody TagsChangeRequest tagsChangeRequest) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(avatarService.changeTags(1L, tagsChangeRequest));
	}

	@Tag(name = "Avatar")
	@Operation(summary = "자신의 아바타 조회 API",
		description = "자신의 아바타 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping
	public ApiResponse<SelfAvatarInfoResponse> getSelfAvatarInfo(HttpServletRequest request) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(avatarService.getSelfAvatarInfo(1L));
	}


	@Tag(name = "Avatar")
	@Operation(summary = "타인의 아바타 조회 API")
	@GetMapping("/{id}")
	public ApiResponse<AvatarInfoResponse> getAvatarInfo(HttpServletRequest request,
	@PathVariable(name = "id") Long avatarId) {
		return ApiResponse.success(avatarService.getAvatarInfo(avatarId));
	}

}
