package com.cheffi.review.domain;

import com.cheffi.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewPhoto extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String url;
	@NotNull
	private String s3Key;
	private Long size;
	private Integer width;
	private Integer height;
	@NotNull
	private Integer givenOrder;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;

	@Builder
	public ReviewPhoto(String url, String s3Key, Long size, Integer width, Integer height, Integer givenOrder,
		Review review) {
		this.url = url;
		this.s3Key = s3Key;
		this.size = size;
		this.width = width;
		this.height = height;
		this.givenOrder = givenOrder;
		this.review = review;
	}
}
