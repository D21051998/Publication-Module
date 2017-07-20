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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.publication.dao.BookChapterDAO;
import com.publication.dao.BookDAO;
import com.publication.dao.ConferencePresentationDAO;
import com.publication.dao.ConferenceProceedingDAO;
import com.publication.dao.JournalDAO;
import com.publication.dao.PatentDAO;
import com.publication.dao.TechnicalReportDAO;
import com.publication.impl.BookChapterIMPL;
import com.publication.impl.BooksIMPL;
import com.publication.impl.ConferencePresentationIMPL;
import com.publication.impl.ConferenceProceedingIMPL;
import com.publication.impl.JournalIMPL;
import com.publication.impl.PatentIMPL;
import com.publication.impl.TechnicalReportIMPL;
import com.publication.model.BookChapter;
import com.publication.model.Books;
import com.publication.model.ConferencePresentation;
import com.publication.model.ConferenceProceedings;
import com.publication.model.Journal;
import com.publication.model.Patent;
import com.publication.model.TechnicalReport;
import com.publication.requestmap.BookChapterMap;
import com.publication.requestmap.BookMap;
import com.publication.requestmap.ConferencePresentationMap;
import com.publication.requestmap.ConferenceProceedingMap;
import com.publication.requestmap.JournalMap;
import com.publication.requestmap.PatentMap;
import com.publication.requestmap.TechnicalReportMap;

/**
 * Servlet implementation class AddPublicationService
 */
@WebServlet("/AddPublicationService")
public class AddPublicationService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPublicationService() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base") + "\\uploads");
			List<FileItem> fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (FileItem caseOf : fileItems) {
				
				if(caseOf.getFieldName().equals("publicationType")){
					
				
				if (caseOf.getString().equals("J")) {
					JournalDAO dao = new JournalIMPL();

					try {

						Journal journal = new Journal();
						JournalMap.journalFormMap(journal, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + journal);
						if (dao.saveJournal(journal)) {
							response.sendRedirect("faculty/view/view_journal.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_journal.jsp?add=failed");
						}
						System.out.println(journal);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("BC")) {
					System.out.println("INSIDE");
					BookChapterDAO dao = new BookChapterIMPL();
					try {
						BookChapter chapter = new BookChapter();
						BookChapterMap.bookChapterFormMap(chapter, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + chapter);
						if (dao.saveBookChapter(chapter)) {
							response.sendRedirect("faculty/view/view_book_chapter.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_book_chapter.jsp?add=failed");
						}
						System.out.println(chapter);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("B")) {
					BookDAO dao = new BooksIMPL();
					try {
						Books book = new Books();
						BookMap.bookFormMap(book, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + book);
						if (dao.saveBook(book)) {
							response.sendRedirect("faculty/view/view_book.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_book.jsp?add=failed");
						}
						System.out.println(book);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("C")) {
					ConferencePresentationDAO dao = new ConferencePresentationIMPL();
					try {
						ConferencePresentation conferencePresentation = new ConferencePresentation();
						ConferencePresentationMap.conferencePresentationFormMap(conferencePresentation, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + conferencePresentation);
						if (dao.saveConferencePresentation(conferencePresentation)) {
							response.sendRedirect("faculty/view/view_conference_presentation.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_conference_presentation.jsp?add=failed");
						}
						System.out.println(conferencePresentation);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("P")) {
					ConferenceProceedingDAO dao = new ConferenceProceedingIMPL();
					try {
						ConferenceProceedings conferenceProceedings = new ConferenceProceedings();
						ConferenceProceedingMap.conferenceProceedingFormMap(conferenceProceedings, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + conferenceProceedings);
						if (dao.saveConferenceProceedings(conferenceProceedings)) {
							response.sendRedirect("faculty/view/view_conference_proceeding.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_conference_proceeding.jsp?add=failed");
						}
						System.out.println(conferenceProceedings);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("R")) {
					TechnicalReportDAO dao = new TechnicalReportIMPL();
					try {
						TechnicalReport report = new TechnicalReport();
						TechnicalReportMap.technicalReportFormMap(report, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + report);
						if (dao.saveTechnicalReport(report)) {
							response.sendRedirect("faculty/view/view_tech_rep.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_tech_rep.jsp?add=failed");
						}
						System.out.println(report);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (caseOf.getString().equals("T")) {
					PatentDAO dao = new PatentIMPL();
					try {
						Patent patent = new Patent();
						PatentMap.patentFormMap(patent, fileItems);
						for (FileItem item : fileItems) {
							if (!item.isFormField()) {
								String fileName = new File(item.getName()).getName();
								if (!file.exists()) {
									file.mkdir();
								}
								String filePath = file.getAbsolutePath() + File.separator + fileName;
								System.out.println("FILEPATH" + filePath);
								File storeFile = new File(filePath);
								item.write(storeFile);
							}
						}
						System.out.println("Final" + patent);
						if (dao.savePatent(patent)) {
							response.sendRedirect("faculty/view/view_patent.jsp?add=success");
						} else {
							response.sendRedirect("faculty/view/view_patent.jsp?add=failed");
						}
						System.out.println(patent);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			}
		}

	}

}
