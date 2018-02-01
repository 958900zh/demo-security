package com.demo.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zxdong on 2018/2/1.
 */
public interface ValidateCodeProcessor {

    public static final String SESSION_KEY_PRIFIX = "SECUTIRY_VALIDATE_CODE_";

    void create(ServletWebRequest request);

    void validate(ServletWebRequest request);
}
