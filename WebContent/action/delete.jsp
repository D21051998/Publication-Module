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
<jsp:useBean id="jao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
<jsp:useBean id="bao" class="com.publication.impl.BooksIMPL"></jsp:useBean>
<jsp:useBean id="bcao" class="com.publication.impl.BookChapterIMPL"></jsp:useBean>
<jsp:useBean id="cpreao" class="com.publication.impl.ConferencePresentationIMPL"></jsp:useBean>
<jsp:useBean id="cproao" class="com.publication.impl.ConferenceProceedingIMPL"></jsp:useBean>
<jsp:useBean id="pao" class="com.publication.impl.PatentIMPL"></jsp:useBean>
<jsp:useBean id="trao" class="com.publication.impl.TechnicalReportIMPL"></jsp:useBean>

<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
<%
String sid = request.getSession(false).getAttribute("sid").toString();
String role = lao.getRoleBySessionID(sid);

if(request.getParameter("type").equals("J")){
	if(jao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}
if(request.getParameter("type").equals("B")){
	if(bao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}
if(request.getParameter("type").equals("BC")){
	if(bcao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}
if(request.getParameter("type").equals("T")){
	if(pao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}
if(request.getParameter("type").equals("C")){
	if(cpreao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}
if(request.getParameter("type").equals("P")){
	if(cproao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}

if(request.getParameter("type").equals("R")){
	if(trao.delete(request.getParameter("id"))){
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}else{
		response.sendRedirect("../"+Redirect.redirect(role, true));
	}
}

%>
</body>
</html>