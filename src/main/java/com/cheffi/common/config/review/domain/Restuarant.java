package com.cheffi.common.config.review.domain;

import com.cheffi.common.constant.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Restuarant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;
}
