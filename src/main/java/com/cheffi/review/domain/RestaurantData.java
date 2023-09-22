package com.cheffi.review.domain;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.constant.DetailedAddress;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@Embedded
	private DetailedAddress detailedAddress;

	private boolean registered;

	@Nullable
	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;

	public Restaurant toRestaurant() {
		if (registered)
			throw new BusinessException(ErrorCode.RESTAURANT_ALREADY_REGISTERED);
		this.registered = true;
		return new Restaurant(name, detailedAddress);
	}
}
