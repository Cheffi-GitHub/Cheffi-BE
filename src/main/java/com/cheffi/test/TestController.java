package com.cheffi.test;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.common.constant.S3RootPath;
import com.cheffi.common.dto.ImageFileInfo;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.common.service.FileUploadService;
import com.cheffi.common.service.SecurityContextService;
import com.cheffi.oauth.model.AuthenticationToken;
import com.cheffi.oauth.model.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Profile({"local", "dev"})
public class TestController {

	private final FileUploadService fileUploadService;
	private final SecurityContextService securityContextService;

	@Tag(name = "Test")
	@Operation(
		summary = "세션 토큰 테스트 API",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/auth")
	public ApiResponse<String> auth(@AuthenticationPrincipal UserPrincipal principal) {
		return principal == null ? ApiResponse.success("UserPrincipal does not exist") :
			ApiResponse.success(principal.toString());
	}

	@Tag(name = "Test")
	@Operation(summary = "테스트용 세션 발급 API")
	@GetMapping("/session/issue")
	public ApiResponse<String> provideTestSession() {
		securityContextService.saveToSecurityContext(
			AuthenticationToken.mock(List.of(new SimpleGrantedAuthority("ROLE_USER"))));
		return ApiResponse.success("테스트용 세션 발급 성공");
	}

	@Tag(name = "Test")
	@Operation(summary = "이미지 업로드 테스트 API")
	@PostMapping(value = "/upload")
	public ApiResponse<ImageFileInfo> upload(MultipartFile file) {
		return ApiResponse.success(fileUploadService.uploadImageToS3(file, S3RootPath.TEST));
	}

}
