<<<<<<< HEAD
<%@page import="com.publication.impl.EmailService"%>
<%@page import="com.publication.model.Journal"%>
=======
>>>>>>> 188eba97c9db97f3dd4ce0f6df30d316b1989bbf
<%@page import="com.publication.constants.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>

<%
<<<<<<< HEAD
String sid = request.getSession(false).getAttribute("sid").toString();
String role = lao.getRoleBySessionID(sid);
if(request.getParameter("level").equals("1")){
	if(dao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
		Journal j = dao.getJournalByID(request.getParameter("id"));
		String email = lao.getEmailByUsername(j.getWrittenBy());
		EmailService.sendEmail("NCU: Publication Approved", "Your journal Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
=======

String sid = request.getSession(false).getAttribute("sid").toString();
String role = lao.getRoleBySessionID(sid);

if(request.getParameter("level").equals("1")){
	if(dao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
>>>>>>> 188eba97c9db97f3dd4ce0f6df30d316b1989bbf
		 response.sendRedirect("../"+Redirect.redirect(role, true));
	 }else{
		 response.sendRedirect("../"+Redirect.redirect(role, true));
	 }
}else if(request.getParameter("level").equals("2")){
	if(dao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
<<<<<<< HEAD
		Journal j = dao.getJournalByID(request.getParameter("id"));
		String email = lao.getEmailByUsername(j.getWrittenBy());
		EmailService.sendEmail("NCU: Publication Approved", "Your journal Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
=======
>>>>>>> 188eba97c9db97f3dd4ce0f6df30d316b1989bbf
		response.sendRedirect("../"+Redirect.redirect(role, true));
	 }else{
		 response.sendRedirect("../"+Redirect.redirect(role, true));
	 }	
}
%>
</body>
</html>