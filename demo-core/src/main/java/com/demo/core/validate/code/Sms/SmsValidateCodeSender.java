package com.demo.core.validate.code.Sms;

/**
 * Created by zxdong on 2018/2/2.
 */
public interface SmsValidateCodeSender {
    void send(String phoneNumber, String code);
}
