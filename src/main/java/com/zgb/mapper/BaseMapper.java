package com.zgb.mapper;

import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */
public interface BaseMapper<V,E> {
    List<V> findAllLibrary();

    int findLibraryById(E id);

    Integer findLastLibrary(V v);
}
