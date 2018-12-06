package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneUtils {
    // 索引库
    private static Directory directory = null;
    // 分词器
    private static Analyzer analyzer;
    // 索引库写入对象的配置对象
    private static IndexWriterConfig config = null;

    private static final Version version = Version.LUCENE_44;

    static{
        try {
            directory = FSDirectory.open(new File("f:/index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        analyzer = new IKAnalyzer();
        config = new IndexWriterConfig(version,analyzer);
    }

    public static IndexWriter getIndexWriter(){
        try {
            IndexWriter indexWriter = new IndexWriter(directory,config);
            return indexWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IndexSearcher getIndexSearcher(){
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            return indexSearcher;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Version getVersion() {
        return version;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }
}
