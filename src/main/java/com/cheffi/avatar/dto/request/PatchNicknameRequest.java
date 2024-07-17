package com.cheffi.avatar.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PatchNicknameRequest(
	@Schema(description = "변경할 닉네임 [길이 2 이상, 8 이하]", example = "동구밭과수원길", required = true)
	@Size(min = 2, max = 8)
	String nickname
) {
}
