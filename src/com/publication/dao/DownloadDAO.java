package com.publication.dao;

import javax.servlet.ServletOutputStream;

public interface DownloadDAO {
	
	
	void downloadRequest(ServletOutputStream servletOutputStream, String[] what, String[] branch, String from,String to);

	String[] downloadJournalFilesByID(String id);
	String[] downloadBookChapterFilesByID(String id);
	String[] downloadBookFilesByID(String id);
	String[] downloadConferencePresentationFilesByID(String id);
	String[] downloadConferenceProceedingFilesByID(String id);
	String[] downloadPatentFilesByID(String id);
	String[] downloadTechnicalReportFilesByID(String id);
	
}
