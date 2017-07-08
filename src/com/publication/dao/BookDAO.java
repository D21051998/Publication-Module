package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.Books;



public interface BookDAO {
	
	public boolean saveBook(Books book);
	public boolean updateBook(Books book);
	public List<Books> getAllBooks()  throws SQLException;
	public Books getBookByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedBooks(String id);

}
