package com.demo.core.validate.code.Impl;

import com.demo.core.validate.code.ValidateCode;
import com.demo.core.validate.code.ValidateCodeException;
import com.demo.core.validate.code.ValidateCodeGenerator;
import com.demo.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
        save(request, code, type);
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

    private void save(ServletWebRequest request, Code code, String type) {
        sessionStrategy.setAttribute(request, ValidateCodeProcessor.SESSION_KEY_PRIFIX + type.toUpperCase(), code);
    }

    protected abstract void send(ServletWebRequest request, Code code) throws Exception;

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request, String type) {
        Code code = (Code) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PRIFIX + type.toUpperCase());
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), type + "Code");
        } catch (ServletRequestBindingException e) {
            System.out.println("验证码获取失败");
            throw new ValidateCodeException("验证码获取失败");
        }

        if (code.isExpired()) {
            System.out.println("验证码已经过期");
            throw new ValidateCodeException("验证码已经过期");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            System.out.println("验证码不能为空");
            throw new ValidateCodeException("验证码不能为空");
        }

        if (!StringUtils.equals(code.getCode(), codeInRequest)) {
            System.out.println("验证码不匹配");
            throw new ValidateCodeException("验证码不匹配");
        }
    }

}
