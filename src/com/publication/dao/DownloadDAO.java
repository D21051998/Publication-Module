package com.publication.dao;

import java.sql.Date;

public interface DownloadDAO {
	
	
	void downloadRequest(String[] what, String[] branch, Date from, Date to);

}
