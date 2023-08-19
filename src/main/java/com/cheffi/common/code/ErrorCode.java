package com.cheffi.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception test"),

	//인증
	//401 에러
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
	NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈값입니다."),
	NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
	REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료됐습니다."),
	NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),

	//403 에러
	FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", "관리자 Role이 아닙니다."),

	//400 에러
	NOT_SUPPORTED_OAUTH_PROVIDER(HttpStatus.BAD_REQUEST, "A-009", "지원하지 않는 OAuth 프로바이더 입니다."),

	// 500 에러
	EMAIL_LOGIN_NOT_SUPPORTED(HttpStatus.INTERNAL_SERVER_ERROR, "A-009", "UserType 이 LOCAL입니다. (일반 로그인은 지원하지 않습니다.)"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "I-001", "내부 에러가 발생했습니다."),

	AVATAR_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "I-002", "아바타가 이미 존재합니다."),

	// 회원
	INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "M-001", "잘못된 회원 타입입니다. (memberType : KAKAO)"),
	ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입된 회원 입니다."),
	USER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M-003", "해당 회원은 존재하지 않습니다.");

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
