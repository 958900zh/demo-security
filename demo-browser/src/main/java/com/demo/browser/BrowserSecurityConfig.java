package com.demo.browser;

import com.demo.core.properties.SecurityProperties;
import com.demo.core.validate.SmsAuthenticationConfig;
import com.demo.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by 栋 on 2018/1/18.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer mySpringSocialConfigurer;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        http
                .apply(smsAuthenticationConfig)
                .and()
                .apply(mySpringSocialConfigurer)
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()   //使用表单验证 UsernamePasswordAuthenticationFilter()
                .loginPage("/authentication/require")  //指定验证的页面，如果不指定，默认使用spring-security的login.html
                .loginProcessingUrl("/authentication/form") //指定处理登陆的url，如果不指定，默认是 /login post请求
                .successHandler(myAuthenticationSuccessHandler) //验证成功处理
                .failureHandler(myAuthenticationFailureHandler) //验证失败处理
                .and()
                .authorizeRequests() //所有请求都需要被验证
                .antMatchers(
                        "/authentication/require",
                        properties.getBrowser().getLoginPage(),
                        "/code/*",
                        properties.getBrowser().getSignupUrl(),
                        "/register").permitAll() //匹配到的请求不需要被验证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable(); //关闭csrf
    }
}
