package com.cheffi.test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.oauth.model.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class TestController {

	@Operation(security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/test")
	public ApiResponse<String> test(@AuthenticationPrincipal UserPrincipal principal) {
		if (principal != null)
			return ApiResponse.success(principal.toString());
		return ApiResponse.success("hello world");
	}

}
