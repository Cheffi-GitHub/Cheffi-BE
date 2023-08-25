package com.cheffi.avatar.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ChangeProfilePhotoRequest(
	@NotNull
	@JsonProperty(value = "default")
	@Schema(name = "default")
	Boolean defaultPhoto
) {
}
