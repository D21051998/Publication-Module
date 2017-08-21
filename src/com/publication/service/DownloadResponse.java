package com.publication.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.publication.dao.DownloadDAO;
import com.publication.impl.DownloadIMPL;

/**
 * Servlet implementation class DownloadResponse
 */
@WebServlet("/DownloadResponse")
public class DownloadResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dfrom =  request.getParameter("from");
		String dto = request.getParameter("to");
		String[] source = request.getParameterValues("source");
		String branch[]  = request.getParameterValues("branch");
		System.out.println(dfrom+" "+dto);
		for(String s: request.getParameterValues("source")){
			System.out.println(s);
		}
		for(String s: request.getParameterValues("branch")){
			System.out.println(s);
		}
		
		DownloadDAO downloadRequest = new DownloadIMPL();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");

		downloadRequest.downloadRequest(response.getOutputStream() ,source , branch, dfrom, dto);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu

	}

}
