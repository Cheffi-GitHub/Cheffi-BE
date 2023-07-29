package com.cheffi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DetailAddressInfoDto {

    @Schema(description = "시/도", example = "서울시")
    private String town;
    @Schema(description = "구/시/군", example = "강북구")
    private String city;
    @Schema(description = "동/읍/면/리", example = "수유리")
    private String province;
    @Schema(description = "상세 주소", example = "평화로 45길 12-11 502호")
    private String detail;

    @Builder
    public DetailAddressInfoDto(String town, String city, String province, String detail) {
        this.town = town;
        this.city = city;
        this.province = province;
        this.detail = detail;
    }
}
