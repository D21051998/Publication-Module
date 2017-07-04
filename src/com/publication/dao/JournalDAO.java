package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.Journal;

public interface JournalDAO {

	
	
	public boolean saveJournal(Journal journal);
	public boolean updateJournal(Journal journal);
	public List<Journal> getAllJournals()  throws SQLException;
	public Journal getJournalByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
<<<<<<< HEAD
	public boolean reject(String id,int status, String message);
=======
	public boolean reject(String id,int status);
>>>>>>> 188eba97c9db97f3dd4ce0f6df30d316b1989bbf
	int getMissing(int a[], int n);
	public int notificationRejectedJournal(String id);
	
}
