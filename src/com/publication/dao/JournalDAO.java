package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.Journal;

public interface JournalDAO {

	
	
	public boolean saveJournal(Journal journal);
	public boolean updateJournal(Journal journal);
	public List<Journal> getAllJournals()  throws SQLException;
	public Journal getJournalByPCN();
	public boolean delete(String pcn);
	public boolean action(String deptt,String title,int volume,int issue,int pageNo,int status);
	int getMissing(int a[], int n);
	
	
}
