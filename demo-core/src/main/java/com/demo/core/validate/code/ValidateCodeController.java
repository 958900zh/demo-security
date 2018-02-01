package com.demo.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zxdong on 2018/1/21.
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder processorHolder;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws IOException {
        processorHolder.findValidateProcessor(type).create(new ServletWebRequest(request, response));
    }

}
