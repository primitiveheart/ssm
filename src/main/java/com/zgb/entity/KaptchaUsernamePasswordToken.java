package com.zgb.entity;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by admin on 2017/12/29.
 */
public class KaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private String kaptcha;

    public KaptchaUsernamePasswordToken(){}

    public KaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String kaptcha){
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }

    public String getKaptcha() {return kaptcha;}

    public void setKaptcha(String kaptcha) {this.kaptcha = kaptcha;}
}
