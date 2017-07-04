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
	public boolean reject(String id,int status);
	int getMissing(int a[], int n);
	
	
}
