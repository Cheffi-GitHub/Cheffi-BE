package com.cheffi.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.user.dto.UserInfoDto;
import com.cheffi.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/reviews")
public class UserController {

	private final UserService userService;

	@Tag(name = "User")
	@Operation(summary = "유저 정보 조회 API",
		description = "자신의 계정 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/users")
	public ApiResponse<UserInfoDto> getSignedUserInfo(HttpServletRequest request) {
		String sessionToken = request.getHeader("Authorization");
		if(sessionToken == null || sessionToken.isBlank())
			throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
		return ApiResponse.success(userService.getUserInfo());
	}

}
