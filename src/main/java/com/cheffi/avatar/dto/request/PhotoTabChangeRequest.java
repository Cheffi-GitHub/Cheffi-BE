package com.cheffi.avatar.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PhotoTabChangeRequest(
	@JsonProperty(value = "default")
	@Schema(name = "default", description = "기본 사진을 사용할 지 여부 - false일 경우 사진이 반드시 포함되어야 합니다.", required = true)
	@NotNull
	Boolean defaultPhoto,

	@Schema(description = "자기소개 [50자 이하]")
	@Nullable
	@Size(max = 50)
	String introduction
) {
}
