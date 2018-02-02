package com.demo.core.validate.code.Image;

import com.demo.core.validate.code.Impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by zxdong on 2018/2/1.
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode code) {
        try {
            ImageIO.write(code.getImage(), "JPEG", request.getResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
