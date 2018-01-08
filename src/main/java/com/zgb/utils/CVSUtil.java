package com.zgb.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/1/8.
 */
public class CVSUtil {

    //导出cvs文件
    //headers类型是LinkedHashMap，保证遍历输出顺序和添加顺序一致。
     //     而HashMap的话不保证添加数据的顺序和遍历出来的数据顺序一致，这样就出现
    //                数据的标题不搭的情况的

    public static byte[] exportCSV(LinkedHashMap<String, String> headers, List<LinkedHashMap<String,Object>> exportData){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedWriter writer = null;
        try{
            //处理编码2312，处理excel打开cvs的时候的标题中文乱码
            writer = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));
            //写入文件头
            Map.Entry entry = null;
            for(Iterator<Map.Entry<String, String>> entryIterator = headers.entrySet().iterator(); entryIterator.hasNext(); ){
                entry = entryIterator.next();
                writer.write("\"" + entry.getValue().toString() + "\"");
                if(entryIterator.hasNext()){
                    writer.write(",");
                }
            }
            writer.newLine();
            //写入文件内容
            LinkedHashMap row = null;
            for(Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext();){
                row = iterator.next();
                for(Iterator<Map.Entry> pIterator = row.entrySet().iterator(); pIterator.hasNext();){
                    entry = pIterator.next();
                    writer.write("\"" + entry.getValue().toString() + "\"");
                    if(pIterator.hasNext()){
                        writer.write(",");
                    }
                }
                if(iterator.hasNext()){
                    writer.newLine();
                }
            }
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

}
