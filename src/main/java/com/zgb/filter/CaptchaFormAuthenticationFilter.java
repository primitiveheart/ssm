package com.zgb.filter;

import com.google.code.kaptcha.Constants;
import com.zgb.entity.KaptchaUsernamePasswordToken;
import com.zgb.exception.IncorrectCaptchaException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2017/12/29.
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    private String captchaParam = "kaptcha";

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request){
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new KaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    protected void doCaptchaValidate(HttpServletRequest request, KaptchaUsernamePasswordToken token){
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(StringUtils.isEmpty(token.getKaptcha()) || !token.getKaptcha().equalsIgnoreCase(captcha)){
                throw new IncorrectCaptchaException("验证码错误！");
        }
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        KaptchaUsernamePasswordToken token = (KaptchaUsernamePasswordToken) createToken(request, response);
        try {
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);

            return onLoginSuccess(token, subject, request, response);
        }catch (AuthenticationException e){
            return onLoginFailure(token, e, request, response);
        }

    }
}
