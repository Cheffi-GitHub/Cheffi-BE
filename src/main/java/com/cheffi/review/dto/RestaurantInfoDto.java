package com.cheffi.review.dto;

import com.cheffi.common.constant.DetailedAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
public class RestaurantInfoDto {


    @Schema(description = "레스토랑 ID", example = "1L")
    private Long id;
    @Schema(description = "상가명", example = "태초밥")
    private String name;

    @Schema(description = "맛집 상세주소")
    private DetailedAddress detailedAddress;

    @Builder
    public RestaurantInfoDto(Long id, String name, DetailedAddress detailedAddress) {
        this.id = id;
        this.name = name;
        this.detailedAddress = detailedAddress;
    }
}
