package com.cheffi.review.domain;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.review.constant.RatingType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RatingType ratingType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Builder
    public Rating(RatingType ratingType, Review review, Avatar avatar) {
        this.ratingType = ratingType;
        this.review = review;
        this.avatar = avatar;
    }
}
