package com.cheffi.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.review.dto.RestaurantInfoDto;
import com.cheffi.review.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;

	public Page<RestaurantInfoDto> searchRestaurantByName(String name, Pageable pageable) {
		String keyword = name.trim().replace(" ", "");

		return restaurantRepository.findByNameContaining(keyword, pageable).map(RestaurantInfoDto::of);
	}
}
