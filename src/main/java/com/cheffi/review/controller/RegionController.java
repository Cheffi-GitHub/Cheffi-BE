package com.cheffi.review.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheffi.common.config.data.RegionProperties;
import com.cheffi.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/region")
@RequiredArgsConstructor
public class RegionController {

	private final RegionProperties properties;

	@Tag(name = "regionInfo")
	@Operation(summary = "시, 군/구 정보 조회 API, 세종시는 군/구대신 읍/면 까지 조회합니다.")
	@GetMapping
	public ApiResponse<List<RegionProperties.Region>> getAllRegionInfo() {

		return ApiResponse.success(properties.getRegions());
	}


}
