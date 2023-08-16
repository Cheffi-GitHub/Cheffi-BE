package com.cheffi.user.dto.request;

import com.cheffi.common.constant.Platform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OidcLoginRequest(
	@NotBlank
	String token,

	@NotNull
	Platform platform
) {
}
