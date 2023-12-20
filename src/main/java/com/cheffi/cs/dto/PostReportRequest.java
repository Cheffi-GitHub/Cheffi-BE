package com.cheffi.cs.dto;

import com.cheffi.cs.constant.ReportReason;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PostReportRequest(
	@NotNull @Positive
	@Schema(description = "신고 대상의 ID")
	Long id,
	@NotNull
	@Schema(description = "신고 사유")
	ReportReason reason
) {
}
