package com.cheffi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter
public class RestaurantInfoDto {


    @Schema(description = "레스토랑 ID", example = "1L")
    private Long id;
    @Schema(description = "상가명", example = "태초밥")
    private String name;

    @Builder
    public RestaurantInfoDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
