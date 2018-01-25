package com.demo.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by zxdong on 2018/1/26.
 */
public class ValidationCodeException extends AuthenticationException {

    public ValidationCodeException(String msg) {
        super(msg);
    }
}
