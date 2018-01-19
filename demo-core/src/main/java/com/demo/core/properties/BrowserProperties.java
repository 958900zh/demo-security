package com.demo.core.properties;

/**
 * Created by 栋 on 2018/1/18.
 */
public class BrowserProperties {

    private String loginPage = "/demo-login.html";

    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }
}
