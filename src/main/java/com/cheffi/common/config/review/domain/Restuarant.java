package com.cheffi.common.config.review.domain;

import com.cheffi.common.constant.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Restuarant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    @Builder
    public Restuarant(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
