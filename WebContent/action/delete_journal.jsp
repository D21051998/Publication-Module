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
String sid = request.getSession(false).getAttribute("sid").toString();
String role = lao.getRoleBySessionID(sid);
if(dao.delete(request.getParameter("id"))){
	response.sendRedirect("../"+Redirect.redirect(role, true));
}else{
	response.sendRedirect("../"+Redirect.redirect(role, true));
}


%>

</body>
</html>