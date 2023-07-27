package com.cheffi.review.constant;

public enum RatingType {

	GOOD(5),
	AVERAGE(3),
	BAD(-1);

	RatingType(int score) {
		this.score = score;
	}

	private int score;

	public int getScore() {
		return score;
	}
}
