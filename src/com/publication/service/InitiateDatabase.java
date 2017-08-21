package com.publication.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.publication.database.Initiate;

/**
 * Servlet implementation class InitiateDatabase
 */
@WebServlet("/InitiateDatabase")
public class InitiateDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitiateDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	PrintWriter out = response.getWriter();
		String myChar = "q1w2e3r4t5";
		String gotChar = request.getParameter("token");
		if(!myChar.equals(gotChar)){
			out.println(">Key Doesnt Match!");
		}else{
			out.println(">Key Match!");
			out.println(">Attempting Database Creation!");
			try{
				Initiate.createTables();
				out.println(">Database Creation Success!");
			}catch(Exception e){
				e.printStackTrace();
				out.println(">ERROR: "+e.getMessage());
				out.println(">Database Creation Failed!");
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
