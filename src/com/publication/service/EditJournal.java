package com.publication.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.Out;

import com.publication.dao.JournalDAO;
import com.publication.impl.JournalIMPL;
import com.publication.model.Journal;
import com.publication.requestmap.JournalMap;

/**
 * Servlet implementation class EditJournal
 */
@WebServlet("/EditJournal")
public class EditJournal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditJournal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		} else {
			JournalDAO dao = new JournalIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				Journal journal = new Journal();
				
				JournalMap.journalFormMap(journal, fileItems);
				System.out.println(journal);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							journal.setId(item.getString());
							journal.setStatus(0);
							break;
						}
					}
				}
				
				journal.setWrittenBy(dao.getJournalByID(journal.getId()).getWrittenBy());
				for (FileItem item : fileItems) {
					if (!item.isFormField()) {
						
						if("publication".equals(item.getFieldName())){
							String fileName = new File(item.getName()).getName();
							if (item.getName() != null && !item.getName().isEmpty() && !fileName.isEmpty()) {
								System.out.println("FILENAME" + fileName);
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}else{
								Journal oldCopy = dao.getJournalByID(journal.getId());
								journal.setPublicationFileName(oldCopy.getPublicationFileName());
							}
						}
						if("plagReport".equals(item.getFieldName())){
							String fileName = new File(item.getName()).getName();
							if (item.getName() != null && !item.getName().isEmpty() && !fileName.isEmpty()) {
								System.out.println("FILENAME" + fileName);
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}else{
								Journal oldCopy = dao.getJournalByID(journal.getId());
								journal.setPlagReportFileName(oldCopy.getPlagReportFileName());
							}
						}
						if("plagCopy".equals(item.getFieldName())){
							String fileName = new File(item.getName()).getName();
							if (item.getName() != null && !item.getName().isEmpty() && !fileName.isEmpty()) {
								System.out.println("FILENAME" + fileName);
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}else{
								Journal oldCopy = dao.getJournalByID(journal.getId());
								journal.setPlagCopyFileName(oldCopy.getPlagCopyFileName());
							}
						}
					}
					
				}
				response.getWriter().println(journal);
				 if (dao.updateJournal(journal)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(journal);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
