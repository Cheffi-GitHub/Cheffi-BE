package com.cheffi.review.domain;

import com.cheffi.common.domain.BaseTimeEntity;

import com.cheffi.common.domain.ImageFile;
import com.cheffi.file.domain.MultiPhoto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewPhoto extends BaseTimeEntity implements MultiPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer givenOrder;

	@Valid
	@NotNull
	@Embedded
	private ImageFile file;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;

	private ReviewPhoto(ImageFile file, Integer givenOrder, Review review) {
		this.file = file;
		this.givenOrder = givenOrder;
		this.review = review;
	}

	public static ReviewPhoto of(ImageFile file, int order, Review review) {
		return new ReviewPhoto(file, order, review);
	}

}
