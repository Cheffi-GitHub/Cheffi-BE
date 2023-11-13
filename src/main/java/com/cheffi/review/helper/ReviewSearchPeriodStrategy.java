package com.cheffi.review.helper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cheffi.util.constant.SearchConstant;
import com.cheffi.util.model.ExactPeriod;

@Service
public class ReviewSearchPeriodStrategy {

	public ExactPeriod getTrendingSearchPeriod(LocalDateTime standard) {
		return new ExactPeriod(standard, SearchConstant.TRENDING_UPDATE_CYCLE);
	}

	public ExactPeriod getTagSearchPeriod(LocalDateTime standard) {
		return new ExactPeriod(standard, SearchConstant.TAG_UPDATE_CYCLE);
	}

}
