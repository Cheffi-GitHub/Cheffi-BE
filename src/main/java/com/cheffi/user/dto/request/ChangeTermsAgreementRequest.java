package com.cheffi.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ChangeTermsAgreementRequest(
	@NotNull
	@Schema(description = "광고 수신 동의 여부", example = "true")
	Boolean adAgreed,
	@NotNull
	@Schema(description = "개인정보 분석 활용 동의", example = "true")
	Boolean analysisAgreed
) {

}
