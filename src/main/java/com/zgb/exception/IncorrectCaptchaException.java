package com.zgb.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by admin on 2017/12/29.
 */
public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException(){
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause){
        super(message, cause);
    }

    public IncorrectCaptchaException(String message){
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause){
        super(cause);
    }
}
