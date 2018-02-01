package com.demo.core.validate.code.Sms;

import com.demo.core.validate.code.Impl.AbstractValidateCodeProcessor;
import com.demo.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zxdong on 2018/2/1.
 */
@Component
public class SmsValidateCodProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsValidateCodeSender smsValidateCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode code) throws Exception {
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "");
        smsValidateCodeSender.send(phoneNumber, code.getCode());
    }
}
