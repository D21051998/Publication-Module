package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.BookChapter;

public interface BookChapterDAO {
	
	public boolean saveBookChapter(BookChapter bookChapter);
	public boolean updateBookChapter(BookChapter bookChapter);
	public List<BookChapter> getAllBookChapters()  throws SQLException;
	public BookChapter getBookChapterByPCN();
	public boolean delete(String pcn);
	public boolean action(String deptt,String bookTitle,String chapterTitle,String chapterNo,String publisher,String isbn, int status);
	int getMissing(int a[], int n);

}
