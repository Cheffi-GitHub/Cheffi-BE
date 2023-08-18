package com.cheffi.test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.common.service.FileUploadService;
import com.cheffi.oauth.model.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

	private final FileUploadService fileUploadService;

	@Tag(name = "Test")
	@Operation(
		summary = "세션 토큰 테스트 API",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/auth")
	public ApiResponse<String> test(@AuthenticationPrincipal UserPrincipal principal) {
		return principal == null ? ApiResponse.success("UserPrincipal does not exist") :
			ApiResponse.success(principal.toString());
	}

	@Tag(name = "Test")
	@Operation(summary = "파일 업로드 테스트 API")
	@PostMapping(value = "/upload")
	public ApiResponse<String> test(MultipartFile file) {
		return ApiResponse.success(fileUploadService.uploadFileToS3(file));
	}

}
