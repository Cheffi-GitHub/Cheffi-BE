package com.cheffi.review.dto;

import java.util.Objects;

import org.springframework.data.redis.core.ZSetOperations;

public class ReviewTypedTuple implements ZSetOperations.TypedTuple<Object> {

	private final Long reviewId;
	private final Double score;

	@Override
	public Object getValue() {
		return this.reviewId;
	}

	@Override
	public Double getScore() {
		return this.score;
	}

	@Override
	public int compareTo(ZSetOperations.TypedTuple<Object> objectTypedTuple) {
		if (objectTypedTuple.getScore() == null)
			return -1;
		return Double.compare(objectTypedTuple.getScore(), this.score);
	}

	public Long getReviewId() {
		return reviewId;
	}

	private ReviewTypedTuple(Long reviewId, Double score) {
		this.reviewId = reviewId;
		this.score = score;
	}

	public static ReviewTypedTuple of(Long reviewId, Double score) {
		return new ReviewTypedTuple(reviewId, score);
	}

	public static ReviewTypedTuple of(ZSetOperations.TypedTuple<Object> tuple) {
		return new ReviewTypedTuple((Long)tuple.getValue(), tuple.getScore());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ReviewTypedTuple that))
			return false;
		return reviewId.equals(that.reviewId) && score.equals(that.score);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reviewId, score);
	}
}
