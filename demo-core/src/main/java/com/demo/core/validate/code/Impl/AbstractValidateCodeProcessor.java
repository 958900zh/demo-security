package com.demo.core.validate.code.Impl;

import com.demo.core.validate.code.ValidateCode;
import com.demo.core.validate.code.ValidateCodeException;
import com.demo.core.validate.code.ValidateCodeGenerator;
import com.demo.core.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * Created by zxdong on 2018/2/1.
 */
abstract public class AbstractValidateCodeProcessor<Code extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request, String type) {
        Code code = generator(type);
        save(request, code);
        try {
            send(request, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Code generator(String type) {
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator generator = validateCodeGeneratorMap.get(generatorName);
        if (generator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (Code) generator.generator();
    }

    private void save(ServletWebRequest request, Code code) {
        sessionStrategy.setAttribute(request, ValidateCodeProcessor.SESSION_KEY_PRIFIX + "type", code);
    }

    protected abstract void send(ServletWebRequest request, Code code) throws Exception;

    @Override
    public void validate(ServletWebRequest request) {

    }

}
