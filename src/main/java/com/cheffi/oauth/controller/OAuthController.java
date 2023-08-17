package com.cheffi.oauth.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.oauth.service.OAuthService;
import com.cheffi.user.dto.request.OidcLoginRequest;
import com.cheffi.user.dto.response.OidcLoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}")
public class OAuthController {

	private final OAuthService oauthService;

	@Tag(name = "Authentication")
	@Operation(summary = "소셜 로그인 API", description = "소셜 로그인 API")
	@PostMapping("/oauth/login/{provider}")
	public ApiResponse<OidcLoginResponse> getSignedUserInfo(@PathVariable String provider,
		@RequestBody OidcLoginRequest oidcLoginRequest, HttpServletRequest request,
		HttpServletResponse response) {
		return ApiResponse.success(oauthService.oidcLogin(oidcLoginRequest, provider, request, response));
	}

}
