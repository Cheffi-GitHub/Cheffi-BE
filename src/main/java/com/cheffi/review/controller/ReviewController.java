package com.cheffi.review.controller;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.review.dto.request.GetReviewRequestDto;
import com.cheffi.review.dto.response.GetReviewResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Tag(name = "리뷰 단건 조회")
    @GetMapping
    public ApiResponse<GetReviewResponseDto> getReview(GetReviewRequestDto requestDto) {

        return ApiResponse.success(GetReviewResponseDto.getMockDto());
    }

}
