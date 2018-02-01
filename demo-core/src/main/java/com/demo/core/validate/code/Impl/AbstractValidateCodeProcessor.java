package com.demo.core.validate.code.Impl;

import com.demo.core.validate.code.ValidateCode;
import com.demo.core.validate.code.ValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zxdong on 2018/2/1.
 */
abstract public class AbstractValidateCodeProcessor<Code extends ValidateCode> implements ValidateCodeProcessor {

    @Override
    public void create(ServletWebRequest request) {
        generator();
        save();
    }

    @Override
    public void validate(ServletWebRequest request) {

    }

    protected void generator() {
    }

    protected void save() {
    }
}
