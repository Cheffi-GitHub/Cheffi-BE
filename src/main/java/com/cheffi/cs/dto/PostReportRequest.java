package com.cheffi.cs.dto;

import com.cheffi.cs.constant.ReportReason;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record PostReportRequest(
	@NotNull
	@Schema(description = "신고 대상의 ID")
	Long id,
	@NotNull
	@Schema(description = "신고 사유")
	ReportReason reason
) {
}
