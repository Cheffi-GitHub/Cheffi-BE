package com.cheffi.review.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.cheffi.common.constant.Address;

@Service
public class ReviewRedisKeyGenerator {

	private final DateTimeFormatter formatter;

	public ReviewRedisKeyGenerator() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
	}

	public String getTrendingSearchKey(Address address, LocalDateTime now) {
		return address.getCombined() + now.format(formatter);
	}
}
