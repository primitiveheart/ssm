package com.zgb.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by admin on 2017/12/29.
 */
@Controller
public class KaptchaImageCreateController {
    private Producer kaptchaProducer = null;

    @Autowired
    public void setKaptchaProducer(Producer kaptchaProducer){
        this.kaptchaProducer = kaptchaProducer;
    }

    @RequestMapping("kaptcha.jpg")
    @ResponseBody
    public void kaptcha(HttpServletResponse response, HttpServletRequest request){
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");

        response.setContentType("image/jpeg");

        String kapText = kaptchaProducer.createText();

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, kapText);

        BufferedImage bi = kaptchaProducer.createImage(kapText);


       try{
           ServletOutputStream out = response.getOutputStream();

           ImageIO.write(bi, "jpg", out);

           out.flush();
           out.close();
       }catch (Exception e){
            e.printStackTrace();
       }
    }
}
