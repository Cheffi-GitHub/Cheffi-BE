package com.cheffi.review.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.common.response.ApiResponse;
import com.cheffi.review.dto.ReviewInfoDto;
import com.cheffi.review.dto.request.SearchReviewRequest;
import com.cheffi.review.dto.request.WriteReviewRequest;
import com.cheffi.review.dto.response.SearchReviewResponse;
import com.cheffi.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@Tag(name = "Review")
	@Operation(summary = "리뷰 단건 조회 API")
	@GetMapping
	public ApiResponse<SearchReviewResponse> searchReviews(SearchReviewRequest requestDto) {
		return ApiResponse.success(reviewService.searchReview(requestDto.id()));
	}

	@Tag(name = "Review")
	@Operation(summary = "지역별 맛집 조회 API")
	@GetMapping("/areas/{area}")
	public ApiResponse<List<ReviewInfoDto>> searchReviewsByArea(@PathVariable("area") String areaName) {
		return ApiResponse.success(reviewService.searchReviewsByArea(areaName));
	}

	@Tag(name = "Review")
	@Operation(summary = "리뷰 등록 API",
		description = "인증 필요, 현재 swagger로 테스트 불가능, "
			+ "content-type : multipart/form-data 형태로 아래의 형식에 맞춰서 보내면 정상적으로 작동합니다.",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Long> writeReview(
		@RequestPart("request") WriteReviewRequest requestDto,
		@Parameter(description = "작성할 리뷰의 사진 파일")
		@RequestPart("files") List<MultipartFile> files) {
		return ApiResponse.success(reviewService.writeReview(requestDto, 1L));
	}

}
