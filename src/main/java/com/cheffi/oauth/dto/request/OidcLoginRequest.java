package com.cheffi.oauth.dto.request;

import com.cheffi.common.constant.Platform;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OidcLoginRequest(
	@Schema(description = "소셜 로그인 OIDC를 통해 받을 수 있는 JWT", example = "header.payload.signature")
	@NotBlank
	String token,

	@Schema(description = "소셜 로그인을 진행한 플랫폼 (반드시 대문자 입력)\n\n예시: [AOS, IOS, WEB]", example = "IOS")
	@NotNull
	Platform platform
) {
}
