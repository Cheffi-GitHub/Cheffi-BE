package com.cheffi.common.config.exception.business;

import com.cheffi.common.code.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}
