package com.demo.core.validate.code.Sms;

import com.demo.core.validate.code.ValidateCode;
import com.demo.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * Created by zxdong on 2018/2/1.
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generator() {
        return null;
    }
}
