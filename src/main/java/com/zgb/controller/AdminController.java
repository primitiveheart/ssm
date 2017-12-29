package com.zgb.controller;


import com.zgb.entity.KaptchaUsernamePasswordToken;
import com.zgb.entity.T_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by admin on 2017/12/28.
 */
@Controller
@RequestMapping("/")
public class AdminController {
    @RequestMapping("login.html")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "loginAdmin.html", method = RequestMethod.POST)
    public String loginAdmin(T_user user, String kaptcha, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try{
            subject.login(token);
            return "admin";
        }catch (Exception e){
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("admin.html")
    public String admin(){
        return "admin";
    }

    @RequestMapping("student.html")
    public String student(){
        return "admin";
    }

    @RequestMapping("teacher.html")
    public String teacher(){
        return "admin";
    }

}

