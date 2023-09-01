package com.cheffi.avatar.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.avatar.dto.adapter.SelfAvatarInfo;
import com.cheffi.avatar.dto.request.ChangeNicknameRequest;
import com.cheffi.avatar.dto.request.ChangeProfilePhotoRequest;
import com.cheffi.avatar.dto.response.AvatarInfoResponse;
import com.cheffi.avatar.service.AvatarService;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.oauth.model.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final AvatarService avatarService;

	@Tag(name = "Avatar")
	@Operation(summary = "자신의 아바타 조회 API",
		description = "자신의 아바타 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ApiResponse<SelfAvatarInfo> getSelfAvatarInfo(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.success(avatarService.getSelfAvatarInfo(principal.getAvatarId()));
	}

	@Tag(name = "Avatar")
	@Operation(summary = "자신의 닉네임 변경 API",
		description = "닉네임 변경 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PatchMapping("/nickname")
	public ApiResponse<String> changeNickname(
		@Valid @RequestBody ChangeNicknameRequest changeNicknameRequest,
		@AuthenticationPrincipal UserPrincipal principal) {
		String nickname = avatarService
			.updateNickname(principal.getAvatarId(), changeNicknameRequest.nickname())
			.nickname();
		return ApiResponse.success(nickname);
	}

	@Tag(name = "Avatar")
	@Operation(summary = "프로필 사진 변경 API",
		description = "프로필 사진 변경 - 인증 필요, swagger 에서는 오류가 발생합니다. request 부분을 application/json"
			+ "으로 설정해서 요청을 보내주세요.",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<String> changePhoto(
		@AuthenticationPrincipal UserPrincipal principal,
		@Parameter(description = "변경할 프로필 사진 파일")
		@RequestPart("file") MultipartFile file,
		@Valid @RequestPart("request") ChangeProfilePhotoRequest request) {
		return ApiResponse.success(avatarService.changePhoto(principal.getAvatarId(), file,
			request.defaultPhoto()));
	}

	@Tag(name = "Avatar")
	@Operation(summary = "타인의 아바타 조회 MOCK API")
	@GetMapping("/{id}")
	public ApiResponse<AvatarInfoResponse> getAvatarInfo(@PathVariable(name = "id") Long avatarId) {
		return ApiResponse.success(avatarService.getAvatarInfo(avatarId));
	}

}
