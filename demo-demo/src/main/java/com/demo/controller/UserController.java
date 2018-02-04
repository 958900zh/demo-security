package com.demo.controller;

import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zxdong on 2018/2/4.
 */
@RestController
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {
        String userId = user.getUsername();

        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }
}
