package com.cheffi.avatar.dto.request;

import jakarta.validation.constraints.Size;

public record ChangeNicknameRequest(
	@Size(min = 2, max = 8)
	String nickname
) {
}
