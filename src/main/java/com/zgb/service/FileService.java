package com.zgb.service;

import com.zgb.entity.T_user;
import com.zgb.mapper.T_userMapper;
import com.zgb.utils.CVSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */
@Service
public class FileService {

    @Autowired
    private T_userMapper t_userMapper;

    public List<T_user> findAllUser(){
        return t_userMapper.findAllUsers();
    }
    //导出用户到csv文件中
    public byte[] exportUsersTocsv(List<T_user> users){
        List<LinkedHashMap<String, Object>> exportData = new ArrayList<>(users.size());
        //行数据
        for(T_user user : users){
            LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
            rowData.put("1", user.getId());
            rowData.put("2", user.getPassword());
            rowData.put("3", user.getUserName());
            rowData.put("4", user.getRoleId());
            exportData.add(rowData);
        }
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("1", "用户的id");
        header.put("2", "用户密码");
        header.put("3", "用户名");
        header.put("4", "角色id");
        return CVSUtil.exportCSV(header, exportData);
    }
}
