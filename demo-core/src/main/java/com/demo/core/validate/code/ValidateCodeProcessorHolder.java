package com.demo.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zxdong on 2018/2/1.
 */
@Component("validateCodeProcessorHolder")
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessor findValidateProcessor(String type) {
        String processorName = type + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessorMap.get(processorName);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + processorName + "不存在");
        }
        return processor;
    }
}
