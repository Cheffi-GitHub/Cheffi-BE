package com.cheffi.review.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.review.ReviewService;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.request.SearchReviewRequest;
import com.cheffi.review.dto.response.SearchReviewResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "리뷰 단건 조회")
    @Operation(summary = "리뷰 단건 조회 API")

    @GetMapping
    public ApiResponse<SearchReviewResponse> searchReview(HttpServletRequest request,
                                                          SearchReviewRequest requestDto) {

        return ApiResponse.success(reviewService.searchReview(requestDto.getId()));
    }

    @Tag(name = "지역별 맛집 조회")
    @Operation(summary = "지역별 맛집 조회 API")
    @GetMapping("/areas")
    public ApiResponse<List<ReviewInfoDto>> searchRegionalReviews(HttpServletRequest request) {

        return ApiResponse.success(reviewService.searchRegionalReviews());
    }
}
