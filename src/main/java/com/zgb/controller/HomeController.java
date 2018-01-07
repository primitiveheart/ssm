package com.zgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2017/12/28.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "test";
    }

    @RequestMapping("modalLogin.html")
    public String modaleLogin(){
        return "modal";
    }

    @RequestMapping("popupDialog.html")
    public String popupDialog(){
        return "popupdialog";
    }
}
