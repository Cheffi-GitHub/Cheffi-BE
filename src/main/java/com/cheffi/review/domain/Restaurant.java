package com.cheffi.review.domain;

import com.cheffi.common.constant.DetailedAddress;
import com.cheffi.common.domain.BaseTimeEntity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Restaurant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private DetailedAddress detailedAddress;
    //
    @Builder
    public Restaurant(String name, DetailedAddress detailedAddress) {
        this.name = name;
        this.detailedAddress = detailedAddress;
    }
}
