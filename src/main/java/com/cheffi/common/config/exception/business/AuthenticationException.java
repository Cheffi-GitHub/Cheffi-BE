package com.cheffi.common.config.exception.business;

import com.cheffi.common.code.ErrorCode;

public class AuthenticationException extends BusinessException {

	private RuntimeException originalException;

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}

	public AuthenticationException(ErrorCode errorCode, RuntimeException e) {
		super(errorCode);
		this.originalException = e;
	}

	public RuntimeException getOriginalException() {
		return originalException != null ? originalException : this;
	}
}
