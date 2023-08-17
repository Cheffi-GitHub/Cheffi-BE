package com.cheffi.common.config.exception.business;

import com.cheffi.common.code.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(String message) {
		super(message);
		this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}
}
