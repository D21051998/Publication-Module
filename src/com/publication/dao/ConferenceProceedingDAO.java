package com.publication.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.publication.model.ConferenceProceedings;

public interface ConferenceProceedingDAO {

	
	public boolean saveConferenceProceedings(ConferenceProceedings ConferenceProceedings);
	public boolean updateConferenceProceedings(ConferenceProceedings ConferenceProceedings);
	public List<ConferenceProceedings> getAllConferenceProceedingss()  throws SQLException;
	public ConferenceProceedings getConferenceProceedingsByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedConferenceProceedingss(String id);
	Map<String, ConferenceProceedings> getAllRejConferenceProceedingss() throws SQLException;
	boolean checkIfRejected(String id);
}
