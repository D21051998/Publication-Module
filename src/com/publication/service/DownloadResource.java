package com.publication.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.publication.dao.DownloadDAO;
import com.publication.impl.DownloadIMPL;

/**
 * Servlet implementation class DownloadResource
 */
@WebServlet("/DownloadResource")
public class DownloadResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadResource() {
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
		PrintWriter out = response.getWriter();
		DownloadDAO dao = new DownloadIMPL();
		String deptt = request.getParameter("deptt");
		String title = request.getParameter("title");
		int volume = Integer.parseInt(request.getParameter("volume"));
		int issue = Integer.parseInt(request.getParameter("issue"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int index = Integer.parseInt(request.getParameter("index"));
		String filePath = null;
		String[] filenames = dao.downloadJournalFilesByID(deptt, title, volume, issue, pageNo);
		for (String s : filenames) {
			System.out.println(s);
		}
		File file = new File(System.getProperty("catalina.base") + "\\uploads");
		
		System.out.println(filenames.length);
		if (null != filenames[0] && null != filenames[1] && null != filenames[2]) {
			filePath = file.getAbsolutePath() + File.separator + filenames[index];
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filenames[index] + "\"");
			try {
				FileInputStream fileInputStream = new FileInputStream(filePath);
				int i;
				while ((i = fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
			}catch(FileNotFoundException f){
				out.println("Cannot Find File.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			out.print("Cannot Find File");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
