package com.publication.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.publication.model.ConferencePresentation;

public interface ConferencePresentationDAO {

	public boolean saveConferencePresentation(ConferencePresentation ConferencePresentation);
	public boolean updateConferencePresentation(ConferencePresentation ConferencePresentation);
	public List<ConferencePresentation> getAllConferencePresentations()  throws SQLException;
	public ConferencePresentation getConferencePresentationByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedConferencePresentations(String id);
	Map<String, ConferencePresentation> getAllRejConferencePresentations();
	boolean checkIfRejected(String id);
	
}
