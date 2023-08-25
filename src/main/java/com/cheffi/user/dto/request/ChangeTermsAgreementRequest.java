package com.cheffi.user.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChangeTermsAgreementRequest(
	@NotNull
	@Schema(description = "광고 수신 동의 여부", example = "true")
	Boolean adAgreed,
	@NotNull
	@Schema(description = "개인정보 분석 활용 동의", example = "true")
	Boolean analysisAgreed
) {

}
