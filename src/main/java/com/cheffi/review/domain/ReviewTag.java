package com.cheffi.review.domain;

import com.cheffi.tag.domain.Tag;

import jakarta.persistence.Entity;
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
public class ReviewTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public ReviewTag(Review review, Tag tag) {
        this.review = review;
        this.tag = tag;
    }
}
