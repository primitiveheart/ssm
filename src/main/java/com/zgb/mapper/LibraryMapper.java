package com.zgb.mapper;

import com.zgb.entity.Library;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/1/8.
 */
public interface LibraryMapper extends BaseMapper<Library, String> {
    @Override
    List<Library> findAllLibrary();

    @Override
    int findLibraryById(String id);

    @Override
    Integer findLastLibrary(Library library);

    int insert(Library library);

    Library selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int updateByPrimatyKey(Library library);

    List<Library> findLibraryListByOrderId(Map<String, Object> library);

    int updateOrderId(Library library);
}
