<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dao" class="com.publication.impl.BookChapterIMPL"></jsp:useBean>
<%
if(request.getParameter("level").equals("1")){
	if(dao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
		 out.print("done");
	 }else{
		 out.print("fail");
	 }
}else if(request.getParameter("level").equals("2")){
	if(dao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
		 out.print("done");
	 }else{
		 out.print("fail");
	 }	
}
 
%>
</body>
</html>