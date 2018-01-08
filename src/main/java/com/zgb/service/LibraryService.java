package com.zgb.service;

import com.zgb.entity.Library;
import com.zgb.mapper.LibraryMapper;
import com.zgb.vo.LibraryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2018/1/8.
 */
@Service
public class LibraryService {
    @Autowired
    private LibraryMapper libraryMapper;

    public List<Object> findAllLibrary(){
        List<Object> listZTree = new ArrayList<Object>();
        List<Library> listLibrary = libraryMapper.findAllLibrary();
        String str = "";
        for(int i=0; i < listLibrary.size(); i++){
            Library library = listLibrary.get(i);
            str = "{id:'" + library.getId() + "',pId:'" + library.getPid() + "',name:\"" + library.getName() + "\"}";
            listZTree.add(str);
        }
        return listZTree;
    }

    public String addOrUpdateLibrary(Library library){
        int numFlag = 0;
        if(StringUtils.isBlank(library.getId())){
            return "error";
        }
        int num = libraryMapper.findLibraryById(library.getId());
        if(num > 0){
            library.setUpdate_time(new Date());
            library.setCreate_user(null);
            library.setPid(null);;
            numFlag = libraryMapper.updateByPrimatyKey(library);
        }else{
            if(library.getPid().equals("null")){
                library.setPid("0");
            }
            int orderId = libraryMapper.findLastLibrary(library);
            orderId++;
            library.setCreate_time(new Date());
            library.setUpdate_time(new Date());
            library.setIs_delete(1);
            library.setOrder_id(orderId);
            numFlag = libraryMapper.insert(library);
        }
        return "success";
    }

    public String deleteLibrary(String id){
        Library library = libraryMapper.selectByPrimaryKey(id);
        library.setIs_delete(0);
        libraryMapper.updateByPrimatyKey(library);
        int num = libraryMapper.deleteByPrimaryKey(id);
        return "success";
    }

    public String updateLibrarySort(LibraryVo libraryVo, String userName){
        Library libraryT = libraryMapper.selectByPrimaryKey(libraryVo.getTargetId());
        Library library = libraryMapper.selectByPrimaryKey(libraryVo.getId());
        library.setUpdate_user(userName);
        library.setUpdate_time(new Date());

        libraryT.setUpdate_time(new Date());
        libraryT.setUpdate_user(userName);

        Map<String,Object> libraryMap = new HashMap<String, Object>();
        //向前移动
        if(libraryVo.getMoveType().equals("prev")){
            libraryMap.put("pid", library.getPid());
            libraryMap.put("order_id", libraryT.getOrder_id());
            libraryMap.put("target_order_id", library.getOrder_id());
            List<Library> listLibraryFlag = libraryMapper.findLibraryListByOrderId(libraryMap);

            int order_id = libraryT.getOrder_id() + 1;
            library.setOrder_id(libraryT.getOrder_id());
            libraryT.setOrder_id(order_id);
            //更新所有受影响的排序字段
            for(int i = 0; i < listLibraryFlag.size(); i++){
                Library ly = listLibraryFlag.get(i);
                if(!(ly.getId().equals(library.getId())) && !(ly.getId().equals(libraryT.getId()))){
                    ly.setUpdate_time(new Date());
                    ly.setUpdate_user(userName);
                    ly.setOrder_id(ly.getOrder_id() + 1);
                    libraryMapper.updateOrderId(ly);
                }
            }
            //更新拖拽的分类信息
            libraryMapper.updateOrderId(library);
            //更新目标分类信息
            libraryMapper.updateOrderId(libraryT);
        }else if(libraryVo.getMoveType().equals("next")){//向后移动
            libraryMap.put("pid", library.getPid());
            libraryMap.put("order_id", library.getOrder_id());
            libraryMap.put("target_order_id", libraryT.getOrder_id());
            List<Library> libraries = libraryMapper.findLibraryListByOrderId(libraryMap);
            int order_id = libraryT.getOrder_id();
            library.setOrder_id(order_id);
            libraryT.setOrder_id(order_id - 1);
            for(int i = 0;i < libraries.size(); i++){
                Library ly = libraries.get(i);
                if(!(ly.getId().equals(library.getId())) && !(ly.getId().equals(libraryT.getId()))){
                    ly.setOrder_id(ly.getOrder_id() - 1);
                    ly.setUpdate_time(new Date());
                    ly.setUpdate_user(userName);
                    libraryMapper.updateOrderId(ly);
                }
            }
            libraryMapper.updateOrderId(library);
            libraryMapper.updateOrderId(libraryT);
        }
        return "success";
    }
}
