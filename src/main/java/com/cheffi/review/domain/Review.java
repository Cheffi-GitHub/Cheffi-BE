package com.cheffi.review.domain;

import com.cheffi.avatar.domain.Avatar;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    private int ratingCnt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Avatar author;

    @Builder
    public Review(String title, String text, Restaurant restaurant, Avatar author) {
        this.title = title;
        this.text = text;
        this.restaurant = restaurant;
        this.author = author;
        this.ratingCnt = 0;
    }
}
