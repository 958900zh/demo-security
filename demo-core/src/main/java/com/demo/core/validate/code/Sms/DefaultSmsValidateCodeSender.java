package com.demo.core.validate.code.Sms;

import org.springframework.stereotype.Component;

/**
 * Created by zxdong on 2018/2/2.
 */
@Component
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {
    @Override
    public void send(String phoneNumber, String code) {
        System.out.println("向手机号:" + phoneNumber + "发送的验证码为:" + code);
    }
}
