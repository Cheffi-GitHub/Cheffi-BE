package com.cheffi.common.config.exception.business;

import com.cheffi.common.code.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode){
        super(errorCode);
    }
}
