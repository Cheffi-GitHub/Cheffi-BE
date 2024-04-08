package com.cheffi.avatar.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@Getter
@Embeddable
public class Nickname implements Serializable {

	@Schema(description = "닉네임", example = "동구밭에서캔감자", required = true)
	@Size(min = 2, max = 8)
	@NotBlank
	@NotNull
	@Column(name = "nickname")
	private String value;

	@Schema(description = "마지막 닉네임 변경일", required = true)
	@NotNull
	@Column(name = "last_nickname_updated_date")
	private LocalDate lastUpdatedDate;

	private Nickname(String value, LocalDate lastUpdatedDate) {
		this.value = value;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public static Nickname getRandom() {
		return new Nickname(getRandomNickname(), LocalDate.now().minusDays(31));
	}

	public Nickname updateOf(String newNickname) {
		validateNickname(newNickname);
		return new Nickname(newNickname, LocalDate.now());
	}

	private void validateNickname(String newNickname) {
		if (!StringUtils.hasText(newNickname))
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		if (newNickname.contains("쉐피"))
			throw new BusinessException(ErrorCode.NICKNAME_CONTAINS_BANNED_WORDS);
		if (!isChangeable())
			throw new BusinessException(ErrorCode.CANNOT_CHANGE_NICKNAME_YET);
		if (newNickname.length() < 2 || newNickname.length() > 8)
			throw new BusinessException(ErrorCode.INVALID_NICKNAME_LENGTH);
	}

	private static String getRandomNickname() {
		return RandomStringUtils.randomNumeric(6) + "쉐피";
	}

	@Schema(description = "응답일자 기준 닉네임 변경가능 여부", required = true)
	public boolean isChangeable() {
		return lastUpdatedDate != null && lastUpdatedDate.isBefore(LocalDate.now().minusDays(30));
	}

}
