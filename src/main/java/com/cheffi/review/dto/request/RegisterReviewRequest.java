package com.cheffi.review.dto.request;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record RegisterReviewRequest (

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

    @Schema(description = "사진")
    List<MultipartFile> files,
    @NotBlank
    @Schema(description = "메뉴종류, 가격")
    Map<String, String> foodInfo

){}

