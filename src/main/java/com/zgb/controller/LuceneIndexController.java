package com.zgb.controller;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Controller;

import java.nio.file.Paths;


/**
 * 博客索引类
 * Created by admin on 2017/12/28.
 */
@Controller
public class LuceneIndexController {
    private Directory dir = null;

    public IndexWriter getWriter() throws Exception{
        dir = FSDirectory.open(Paths.get("E://temp"));
        SmartChineseAnalyzer sca = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(sca);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }
}
