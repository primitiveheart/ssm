package com.zgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2018/1/7.
 */
@Controller
public class ZTreeController {

    @RequestMapping("ztreedemo.html")
    public String ztreedemo(){
        return "ztreedemo";
    }
}
