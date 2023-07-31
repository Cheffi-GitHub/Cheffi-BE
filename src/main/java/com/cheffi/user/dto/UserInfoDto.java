package com.cheffi.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.cheffi.user.domain.Role;
import com.cheffi.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {
	@Schema(description = "이메일", example = "kim12345@naver.com")
	private final String email;
	@Schema(description = "마지막 비밀번호 변경 시각", example = "2023-03-01 16:31:04.019")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
	private final LocalDateTime lastPwChangedDate;
	@Schema(description = "가입 시각", example = "2023-03-01 16:31:04.019")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
	private final LocalDateTime createdDate;
	@Schema(description = "이름", example = "안유진")
	private final String name;
	@Schema(description = "유저가 가입한 플랫폼", example = "KAKAO")
	private final String provider;
	@Schema(description = "광고 수신 동의 여부", example = "true")
	private final boolean adAgreed;
	@Schema(description = "개인정보 분석 이용 동의 여부", example = "true")
	private final boolean analysisAgreed;
	@Schema(description = "역할", example = "USER")
	private final List<String> authorities;

	@Builder
	private UserInfoDto(String email, LocalDateTime lastPwChangedDate, LocalDateTime createdDate, String name, String provider, boolean adAgreed,
		boolean analysisAgreed, List<String> authorities) {
		this.email = email;
		this.lastPwChangedDate = lastPwChangedDate;
		this.createdDate = createdDate;
		this.name = name;
		this.provider = provider;
		this.adAgreed = adAgreed;
		this.analysisAgreed = analysisAgreed;
		this.authorities = authorities;
	}

	public static UserInfoDto of(User user, List<Role> roles) {
		return UserInfoDto.builder()
			.email(user.getEmail())
			// TODO 변경 필요
			// .lastPwChangedDate(user.getLastPwChangedDate())
			// .createdDate(user.getCreatedDate())
			.lastPwChangedDate(LocalDateTime.now())
			.createdDate(LocalDateTime.now())
			.name(user.getName())
			.provider(user.getProvider())
			.adAgreed(user.isAdAgreed())
			.analysisAgreed(user.isAnalysisAgreed())
			.authorities(roles.stream().map(Role::getAuthority).toList())
			.build();
	}

}
