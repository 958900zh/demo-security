package com.demo.core.validate.code.Image;

import com.demo.core.validate.code.Impl.AbstractValidateCodeProcessor;
import com.demo.core.validate.code.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zxdong on 2018/2/1.
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor {
    @Override
    protected void send(ServletWebRequest request, ValidateCode code) {

    }
}
