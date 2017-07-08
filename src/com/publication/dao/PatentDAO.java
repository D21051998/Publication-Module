package com.publication.dao;

import java.sql.SQLException;
import java.util.List;

import com.publication.model.Patent;

public interface PatentDAO {
	
	
	public boolean savePatent(Patent Patent);
	public boolean updatePatent(Patent Patent);
	public List<Patent> getAllPatents()  throws SQLException;
	public Patent getPatentByID(String id);
	public boolean delete(String id);
	public boolean action(String id,int status);
	public boolean reject(String id,int status, String message);
	int getMissing(int a[], int n);
	public int notificationRejectedPatents(String id);

}
