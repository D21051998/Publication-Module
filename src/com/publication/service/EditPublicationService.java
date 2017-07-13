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

import com.publication.dao.*;
import com.publication.impl.*;
import com.publication.model.*;
import com.publication.requestmap.*;



/**
 * Servlet implementation class EditPublicationService
 */
@WebServlet("/EditPublicationService")
public class EditPublicationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPublicationService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("publicationType");
		if (request.getParameter("publicationType").isEmpty()) {
			return;
		}
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		} else if (type.equals("J")) {
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
		}else if (type.equals("BC")) {
			BookChapterDAO dao = new BookChapterIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				BookChapter chapter = new BookChapter();
				BookChapterMap.bookChapterFormMap(chapter, fileItems);
				System.out.println(chapter);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							chapter.setId(item.getString());
							chapter.setStatus(0);
							break;
						}
					}
				}
				
				chapter.setWrittenBy(dao.getBookChapterByID(chapter.getId()).getWrittenBy());
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
								BookChapter oldCopy = dao.getBookChapterByID(chapter.getId());
								chapter.setPublicationFileName(oldCopy.getPublicationFileName());
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
								BookChapter oldCopy = dao.getBookChapterByID(chapter.getId());
								chapter.setPlagReportFileName(oldCopy.getPlagReportFileName());
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
								BookChapter oldCopy = dao.getBookChapterByID(chapter.getId());
								chapter.setPlagCopyFileName(oldCopy.getPlagCopyFileName());
							}
						}
					}
					
				}
				response.getWriter().println(chapter);
				 if (dao.updateBookChapter(chapter)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(chapter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (type.equals("B")) {
			BookDAO dao = new BooksIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				Books book = new Books();
				BookMap.bookFormMap(book, fileItems);
				System.out.println(book);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							book.setId(item.getString());
							book.setStatus(0);
							break;
						}
					}
				}
				
				book.setWrittenBy(dao.getBookByID(book.getId()).getWrittenBy());
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
								Books oldCopy = dao.getBookByID(book.getId());
								book.setPublicationFileName(oldCopy.getPublicationFileName());
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
								Books oldCopy = dao.getBookByID(book.getId());
								book.setPlagReportFileName(oldCopy.getPlagReportFileName());
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
								Books oldCopy = dao.getBookByID(book.getId());
								book.setPlagCopyFileName(oldCopy.getPlagCopyFileName());
							}
						}
					}
					
				}
				response.getWriter().println(book);
				 if (dao.updateBook(book)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (type.equals("C")) {
			ConferencePresentationDAO dao = new ConferencePresentationIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				ConferencePresentation cp = new ConferencePresentation();
				ConferencePresentationMap.conferencePresentationFormMap(cp, fileItems);
				System.out.println(cp);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							cp.setId(item.getString());
							cp.setStatus(0);
							break;
						}
					}
				}
				
				cp.setWrittenBy(dao.getConferencePresentationByID(cp.getId()).getWrittenBy());
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
								ConferencePresentation oldCopy = dao.getConferencePresentationByID(cp.getId());
								cp.setPublicationFileName(oldCopy.getPublicationFileName());
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
								ConferencePresentation oldCopy = dao.getConferencePresentationByID(cp.getId());
								cp.setPlagReportFileName(oldCopy.getPlagReportFileName());
							}
						}
					}
					
				}
				response.getWriter().println(cp);
				 if (dao.updateConferencePresentation(cp)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(cp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (type.equals("P")) {
			ConferenceProceedingDAO dao = new ConferenceProceedingIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				ConferenceProceedings cpo = new ConferenceProceedings();
				ConferenceProceedingMap.conferenceProceedingFormMap(cpo, fileItems);
				System.out.println(cpo);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							cpo.setId(item.getString());
							cpo.setStatus(0);
							break;
						}
					}
				}
				
				cpo.setWrittenBy(dao.getConferenceProceedingsByID(cpo.getId()).getWrittenBy());
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
								ConferenceProceedings oldCopy = dao.getConferenceProceedingsByID(cpo.getId());
								cpo.setPublicationFileName(oldCopy.getPublicationFileName());
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
								ConferenceProceedings oldCopy = dao.getConferenceProceedingsByID(cpo.getId());;
								cpo.setPlagReportFileName(oldCopy.getPlagReportFileName());
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
								ConferenceProceedings oldCopy = dao.getConferenceProceedingsByID(cpo.getId());
								cpo.setPlagCopyFileName(oldCopy.getPlagCopyFileName());
							}
						}
					}
					
				}
				response.getWriter().println(cpo);
				 if (dao.updateConferenceProceedings(cpo)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(cpo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (type.equals("R")) {
			TechnicalReportDAO dao = new TechnicalReportIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				TechnicalReport report = new TechnicalReport();
				TechnicalReportMap.technicalReportFormMap(report, fileItems);
				System.out.println(report);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							report.setId(item.getString());
							report.setStatus(0);
							break;
						}
					}
				}
				
				report.setWrittenBy(dao.getTechnicalReportByID(report.getId()).getWrittenBy());
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
								TechnicalReport oldCopy = dao.getTechnicalReportByID(report.getId());
								report.setPublicationFileName(oldCopy.getPublicationFileName());
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
								TechnicalReport oldCopy = dao.getTechnicalReportByID(report.getId());
								report.setPlagReportFileName(oldCopy.getPlagReportFileName());
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
								TechnicalReport oldCopy = dao.getTechnicalReportByID(report.getId());
								report.setPlagCopyFileName(oldCopy.getPlagCopyFileName());
							}
						}
					}
					
				}
				response.getWriter().println(report);
				 if (dao.updateTechnicalReport(report)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(report);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("T")) {
			PatentDAO dao = new PatentIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				Patent patent = new Patent();
				PatentMap.patentFormMap(patent, fileItems);
				System.out.println(patent);
				
				for(FileItem item:fileItems){
					if(item.isFormField()){
						if("id".equals(item.getFieldName())){
							patent.setId(item.getString());
							patent.setStatus(0);
							break;
						}
					}
				}
				
				patent.setWrittenBy(dao.getPatentByID(patent.getId()).getWrittenBy());
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
								Patent oldCopy = dao.getPatentByID(patent.getId());
								patent.setPublicationFileName(oldCopy.getPublicationFileName());
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
								Patent oldCopy = dao.getPatentByID(patent.getId());
								patent.setPlagReportFileName(oldCopy.getPlagReportFileName());
							}
						}
					}
					
				}
				response.getWriter().println(patent);
				 if (dao.updatePatent(patent)) {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 } else {
				 response.sendRedirect("faculty/view/view_journal.jsp");
				 }
				System.out.println(patent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}




	}

}
