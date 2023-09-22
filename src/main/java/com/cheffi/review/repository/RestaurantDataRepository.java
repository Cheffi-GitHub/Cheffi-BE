package com.cheffi.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheffi.review.domain.RestaurantData;

public interface RestaurantDataRepository extends JpaRepository<RestaurantData, Long> {
}
