package com.demo.core.validate.code;

import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;

/**
 * Created by zxdong on 2018/2/1.
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
        HashMap<String, String> map = new HashMap<>();
    }
}
