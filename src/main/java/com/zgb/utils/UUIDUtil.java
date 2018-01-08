package com.zgb.utils;

import java.util.UUID;

/**
 * Created by admin on 2018/1/8.
 */
public class UUIDUtil {
    public static String randomUUID(){
        String uuidStr = UUID.randomUUID().toString().replaceAll("-","");
        return uuidStr;
    }
}
