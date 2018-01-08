package com.zgb.controller;

import com.zgb.entity.T_user;
import com.zgb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("export.html")
    public String export(){
        return "exportCSV";
    }

    @RequestMapping("exportCSV.html")
    public void exportCSV(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<T_user> users = fileService.findAllUser();

        // csv文件名字，为了方便默认给个名字，当然名字可以自定义，看实际需求了
        String fileName = "我是csv文件.csv";
        // 解决不同浏览器出现的乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);

        FileCopyUtils.copy(fileService.exportUsersTocsv(users), response.getOutputStream());
    }
}
