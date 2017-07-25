package com.publication.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.publication.model.BookChapter;

public interface BookChapterDAO {
	
	public boolean saveBookChapter(BookChapter BookChapter);
	public boolean updateBookChapter(BookChapter BookChapter);
	public List<BookChapter> getAllBookChapters()  throws SQLException;
	public BookChapter getBookChapterByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedBookChapters(String id);
	public Map<String, BookChapter> getAllRejectedBookChapters();
	boolean checkIfRejected(String id);
}
