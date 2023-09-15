package com.cheffi.review.controller;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.response.ApiPageResponse;
import com.cheffi.review.dto.RestaurantInfoDto;
import com.cheffi.review.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/restaurants")
public class RestaurantController {
	private final RestaurantService restaurantService;

	@Tag(name = "Restaurant")
	@Operation(summary = "식당 검색 API",
		description = "식당 검색 페이징 API 입니다. WHERE [식당이름] LIKE '%검색어%' 문으로 검색됩니다.")
	@Parameter(name = "name", description = "검색할 식당의 이름(쿼리 파라미터)", required = true)
	@PageableAsQueryParam
	@GetMapping
	public ApiPageResponse<RestaurantInfoDto> searchRestaurantsByName(
		@RequestParam @Valid @NotBlank String name,
		@Parameter(hidden = true) Pageable pageable) {
		return ApiPageResponse.success(restaurantService.searchRestaurantByName(name, pageable));
	}

}
