package com.cheffi.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record WriteReviewRequest(
    @NotBlank
    @Schema(description = "식당 이름")
    String restaurantName,
    @NotBlank
    @Schema(description = "식당 법정주소")
    int addressCode,
    @NotBlank
    @Schema(description = "리뷰 제목")
    String title,
    @NotBlank
    @Schema(description = "리뷰 내용")
    String text,

    @NotBlank
    @Schema(description = "메뉴종류, 가격",
        example = "[ {\"food\" : \"price\"},{\"food\" : \"price\"}]")
    String foodInfoJsonArr)
    {
}
