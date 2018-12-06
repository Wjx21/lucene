package com.baizhi.test;

import com.baizhi.util.LuceneUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;

import java.io.IOException;



public class Test {
    @org.junit.Test
    public void testAdd() throws IOException {
        IndexWriter indexWriter = LuceneUtils.getIndexWriter();
//        indexWriter.deleteDocuments();
        Document document = new Document();
        document.add(new IntField("id",1, Field.Store.YES));
        document.add(new StringField("title","背影", Field.Store.YES));
        document.add(new StringField("author","朱自清", Field.Store.YES));
        document.add(new TextField("content","百知教育你站在那儿别动，我去买几个橘子", Field.Store.YES));

        indexWriter.addDocument(document);


        indexWriter.commit();
        indexWriter.close();
    }

    @org.junit.Test
    public void test2() throws IOException, ParseException {
        IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
//        Query query = new TermQuery(new Term("title","背影"));
        String[] fields = {"title","content","author"};
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getVersion(),fields,LuceneUtils.getAnalyzer());
        Query query = queryParser.parse("百知教育");
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("author"));
            System.out.println(document.get("content"));

        }

    }


}
