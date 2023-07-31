package com.cheffi.review.controller;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.review.ReviewService;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.request.SearchReviewRequest;
import com.cheffi.review.dto.response.SearchReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "리뷰 단건 조회")
    @Operation(summary = "리뷰 단건 조회 API",
            description = "자신의 계정 조회 - 인증 필요",
            security = {@SecurityRequirement(name = "session-token")})
    @GetMapping
    public ApiResponse<SearchReviewResponse> searchReview(HttpServletRequest request,
                                                          SearchReviewRequest requestDto) {

        String sessionToken = request.getHeader("Authorization");
        if(sessionToken == null || sessionToken.isBlank())
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);

        return ApiResponse.success(reviewService.searchReview(requestDto.getId()));
    }



    @Tag(name = "지역별 맛집 조회")
    @Operation(summary = "지역별 맛집 조회 API",
            description = "자신의 계정 조회 - 인증 필요",
            security = {@SecurityRequirement(name = "session-token")})
    @GetMapping("/areas")
    public ApiResponse<List<ReviewInfoDto>> searchRegionalReviews(HttpServletRequest request) {

        String sessionToken = request.getHeader("Authorization");
        if(sessionToken == null || sessionToken.isBlank())
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);

        return ApiResponse.success(reviewService.getRegionalReviews());
    }
}
