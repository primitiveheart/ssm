package com.zgb.controller;

import com.zgb.entity.Library;
import com.zgb.service.LibraryService;
import com.zgb.utils.UUIDUtil;
import com.zgb.vo.LibraryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */
@Controller
@RequestMapping("library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping("toListLibrary")
    public String toListLibrary(){
        return "library/listLibrary";
    }

    @RequestMapping(value = "findAllLibrary", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> findAllLibrary(){
        List<Object> list = libraryService.findAllLibrary();
        return list;
    }

    @RequestMapping( value = "saveLibrary", method = RequestMethod.POST)
    @ResponseBody
    public String saveLibrary(HttpServletResponse response, HttpServletRequest request){
        String libraryId = UUIDUtil.randomUUID();
        return libraryId;
    }

    @RequestMapping("updateLibraryName")
    @ResponseBody
    public String updateLibraryName(HttpServletRequest request, HttpServletResponse response, Library library){
        String createname = "xiaoming";
        library.setCreate_user(createname);
        library.setUpdate_user(createname);
        return libraryService.addOrUpdateLibrary(library);
    }

    @RequestMapping("deleteLibrary")
    @ResponseBody
    public String deleteLibrary(@RequestParam("id") String id){
        return libraryService.deleteLibrary(id);
    }

    @RequestMapping("updateLibrarySort")
    @ResponseBody
    public String updateLibrarySort(HttpServletRequest request, LibraryVo libraryVo){
        String createname = "xiaoming";
        return libraryService.updateLibrarySort(libraryVo, createname);
    }
}
