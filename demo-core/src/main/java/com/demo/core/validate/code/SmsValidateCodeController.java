package com.demo.core.validate.code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsValidateCodeController {

    private SmsValidateCodeProvider smsValidateCodeProvider;

    @GetMapping("/code/sms")
    public void send(String phone) {
        smsValidateCodeProvider.send(phone);
        System.out.println(phone);
    }
}
