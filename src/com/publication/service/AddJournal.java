package com.publication.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	Journal journal = new JournalMap(request).getJournal();
		System.out.println(journal);
		JournalDAO dao = new JournalIMPL();
		if(dao.saveJournal(journal)){
			response.sendRedirect("faculty/view/view_journal.jsp");
		} else {
			response.sendRedirect("faculty/view/view_journal.jsp");
		}
	}

}
