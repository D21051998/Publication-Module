<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.publication.model.Journal"%>
<%@page import="com.publication.impl.EmailService"%>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.publication.constants.Redirect"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>

<%

String sid = request.getSession(false).getAttribute("sid").toString();
String role = lao.getRoleBySessionID(sid);

if(request.getParameter("level").equals("1")){
	if(dao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")), request.getParameter("reason"))){
		Journal j = dao.getJournalByID(request.getParameter("id"));
		String email = lao.getEmailByUsername(j.getWrittenBy());
		EmailService.sendEmail("NCU: Publication Rejected", "Your journal Titled \""+j.getTitle()+"\" , has been rejected by Department Coordinator because of reason \""+request.getParameter("reason")+"\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal" , email);
		response.sendRedirect("../department_coord/view/view_journal.jsp");
	 }else{
		 response.sendRedirect("../department_coord/view/view_journal.jsp");
	 }
}else if(request.getParameter("level").equals("2")){
	if(dao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")), request.getParameter("reason"))){
		Journal j = dao.getJournalByID(request.getParameter("id"));
		String email = lao.getEmailByUsername(j.getWrittenBy());
		EmailService.sendEmail("NCU: Publication Rejected", "Your journal Titled \""+j.getTitle()+"\" , has been rejected by RDIL because of reason \""+request.getParameter("reason")+"\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal" , email);
		response.sendRedirect("../"+Redirect.redirect(role, true));
	 }else{
		 response.sendRedirect("../"+Redirect.redirect(role, true));
	 }	
}
 
%>


</body>
</html>

</body>
</html>