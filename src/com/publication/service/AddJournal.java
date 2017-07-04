package com.publication.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.publication.dao.JournalDAO;
import com.publication.impl.JournalIMPL;
import com.publication.model.Journal;
import com.publication.requestmap.JournalMap;

/**
 * Servlet implementation class AddJournal
 */
@WebServlet("/AddJournal")
public class AddJournal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddJournal() {
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
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			return;
		}else{
			JournalDAO dao = new JournalIMPL();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			File file = new File(System.getProperty("catalina.base")+"\\uploads");
			try{
				List<FileItem> fileItems = upload.parseRequest(request);	
				Journal journal = new Journal();
				JournalMap.journalFormMap(journal, fileItems);
				for(FileItem item : fileItems){
					if(!item.isFormField()){
						String fileName = new File(item.getName()).getName();
						if(!file.exists()){
							file.mkdir();
						}
						String filePath = file.getAbsolutePath() + File.separator + fileName;
						System.out.println("FILEPATH"+filePath);
						 File storeFile = new File(filePath);
						 item.write(storeFile);
					}
				}
				System.out.println("Final"+journal);
				if (dao.saveJournal(journal)) {
					response.sendRedirect("faculty/view/view_journal.jsp");
				} else {
					response.sendRedirect("faculty/view/view_journal.jsp");
				}
				System.out.println(journal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
