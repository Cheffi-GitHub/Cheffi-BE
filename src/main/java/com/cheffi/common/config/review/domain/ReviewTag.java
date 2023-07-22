package com.cheffi.common.config.review.domain;

import jakarta.persistence.*;

@Entity
public class ReviewTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
