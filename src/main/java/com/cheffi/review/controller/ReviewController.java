package com.cheffi.review.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.common.response.ApiResponse;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.request.SearchReviewRequest;
import com.cheffi.review.dto.request.WriteReviewRequest;
import com.cheffi.review.dto.response.SearchReviewResponse;
import com.cheffi.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "리뷰 단건 조회")
    @Operation(summary = "리뷰 단건 조회 API")
    @GetMapping
    public ApiResponse<SearchReviewResponse> searchReview(SearchReviewRequest requestDto) {

        return ApiResponse.success(reviewService.searchReview(requestDto.id()));
    }

    @Tag(name = "지역별 맛집 조회")
    @Operation(summary = "지역별 맛집 조회 API")
    @GetMapping("/areas")
    public ApiResponse<List<ReviewInfoDto>> searchRegionalReviews() {

        return ApiResponse.success(reviewService.searchRegionalReviews());
    }

    @Tag(name = "리뷰 등록")
    @Operation(summary = "리뷰 등록 API",
        description = "인증 필요, 현재 swagger로 테스트 불가능, content-type : multipart/form-data 형태로 아래의 형식에 맞춰서 보내면 정상적으로 작동합니다.",
        security = {@SecurityRequirement(name = "session-token")})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Void> writeReview(HttpServletRequest request,
        WriteReviewRequest requestDto,
        List<MultipartFile> files) {

        String sessionToken = request.getHeader("Authorization");
        if(sessionToken == null || sessionToken.isBlank())
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);

        reviewService.writeReview(requestDto, 1L);

        return ApiResponse.success(null);
    }
}
