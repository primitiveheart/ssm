package com.zgb.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by admin on 2018/1/8.
 */
public class ResponseUtil {
    public static void renderJson(HttpServletResponse response, String jsonStr){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    public static void rendJson(HttpServletResponse response, JSONObject jsonObject){
        renderJson(response, jsonObject.toJSONString());
    }
}
