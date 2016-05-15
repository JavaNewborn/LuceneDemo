package demo;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class CreateIndex {
	public static void main(String[] args) {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		Directory directory = null;
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File("/home/hp/project/demo1_index"));
			if (indexWriter.isLocked(directory)) {
				indexWriter.unlock(directory);
			}
			indexWriter = new IndexWriter(directory, indexWriterConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc1 = new Document();
		doc1.add(new StringField("id", "112050901", Store.YES));
		doc1.add(new TextField("name", "Aaron", Store.YES));
		doc1.add(new IntField("num", 1, Store.YES));
		try {
			indexWriter.addDocument(doc1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc2 = new Document();
		doc2.add(new StringField("id", "11205090102", Store.YES));
		doc2.add(new TextField("name", "Hugh", Store.YES));
		doc2.add(new IntField("num", 2, Store.YES));
		try {
			indexWriter.addDocument(doc2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			indexWriter.commit();
			
			indexWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
