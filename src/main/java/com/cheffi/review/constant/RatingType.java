package com.cheffi.review.constant;

import lombok.Getter;

@Getter
public enum RatingType {

	GOOD(5),
	AVERAGE(3),
	BAD(-1);

	RatingType(int score) {
		this.score = score;
	}

	private int score;
}
