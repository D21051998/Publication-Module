package com.publication.dao;

import javax.servlet.ServletOutputStream;

public interface DownloadDAO {
	
	
	void downloadRequest(ServletOutputStream servletOutputStream, String[] what, String[] branch, String from,String to);

	
}
