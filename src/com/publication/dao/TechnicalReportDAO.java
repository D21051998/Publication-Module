package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.TechnicalReport;


public interface TechnicalReportDAO {
	
	public boolean saveTechnicalReport(TechnicalReport technicalReport);
	public boolean updateTechnicalReport(TechnicalReport technicalReport);
	public List<TechnicalReport> getAllTechnicalReports()  throws SQLException;
	public TechnicalReport getTechnicalReportByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedTechnicalReports(String id);

}
