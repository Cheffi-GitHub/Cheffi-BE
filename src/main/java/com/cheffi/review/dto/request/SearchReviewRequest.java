package com.cheffi.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record SearchReviewRequest(
    @Schema(description = "Review 식별자", example = "1")
    @NotBlank
    Long id
) {}
