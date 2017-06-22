<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 <jsp:useBean id="bookChapter" class="com.publication.model.BookChapter" scope="request"/>
 <jsp:setProperty property="*" name="bookChapter"/>
 <!--<jsp:getProperty property="nameOauthors" name="bookChapter"/>-->
 <jsp:useBean id="bookChapterImpl" class="com.publication.impl.BookChapterIMPL" scope="request"/>
 <% 
    bookChapter.setStatus(0);
    if(bookChapterImpl.saveBookChapter(bookChapter)){
    	response.sendRedirect("../faculty/faculty_home.jsp");
    }else{
    	response.sendRedirect("../new/new_book_chapter.jsp");
    }
 %>
</body>
</html>