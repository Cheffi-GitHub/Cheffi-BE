package com.cheffi.avatar.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PhotoTabChangeRequest(
	@NotNull
	@JsonProperty(value = "default")
	@Schema(name = "default")
	Boolean defaultPhoto,

	@Nullable
	@Size(max = 50)
	String introduction
) {
}
