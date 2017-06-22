<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 <jsp:useBean id="journal" class="com.publication.model.Journal" scope="request"/>
 <jsp:setProperty property="*" name="journal"/>
 <jsp:useBean id="journalIMPL" class="com.publication.impl.JournalIMPL" scope="request"/>
 <% 
    journal.setStatus(0);
    if(journalIMPL.saveJournal(journal)){
    	response.sendRedirect("../faculty/view/view_journal.jsp");
    }else{
    	response.sendRedirect("../faculty/view/view_journal.jsp");
    }
 %>
</body>
</html>