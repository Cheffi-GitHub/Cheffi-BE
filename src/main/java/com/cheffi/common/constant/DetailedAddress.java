package com.cheffi.common.constant;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@Embeddable
public class DetailedAddress extends Address {

	@Schema(description = "식당 상세주소", example = "숭문길 24")
	@NotNull
	private String detail;

	private DetailedAddress(String province, String city, String detail) {
		super(province, city);
		this.detail = detail;
	}

	public static DetailedAddress of(String province, String city, String detail) {
		return new DetailedAddress(province, city, detail);
	}

	@Schema(description = "식당 전체 주소", example = "서울시 마포구 숭문길 24")
	public String getFull() {
		return getProvince() + " " + getCity() + " " + getDetail();
	}
}
