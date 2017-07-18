<%@page import="com.publication.model.Patent"%>
<%@page import="com.publication.model.ConferencePresentation"%>
<%@page import="com.publication.model.ConferenceProceedings"%>
<%@page import="com.publication.model.TechnicalReport"%>
<%@page import="com.publication.model.BookChapter"%>
<%@page import="com.publication.model.Books"%>
<%@page import="com.publication.constants.Redirect"%>
<%@page import="com.publication.impl.EmailService"%>
<%@page import="com.publication.model.Journal"%>
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
System.out.println(request.getParameter("id")+request.getParameter("type")+request.getParameter("level")+request.getParameter("status"));
if(request.getParameter("type").equals("J")){
	if(request.getParameter("level").equals("1")){
		if(jao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Journal j = jao.getJournalByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your journal Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(jao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Journal j = jao.getJournalByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your journal Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}
}else if(request.getParameter("type").equals("B")){
	if(request.getParameter("level").equals("1")){
		if(jao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Books j = bao.getBookByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your book Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(jao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Journal j = jao.getJournalByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your book Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}
}else if(request.getParameter("type").equals("BC")){
	if(request.getParameter("level").equals("1")){
		if(bcao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			BookChapter j = bcao.getBookChapterByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your book chapter Titled \""+j.getChapterTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(bcao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			BookChapter j = bcao.getBookChapterByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your book chapter Titled \""+j.getChapterTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}
}else if(request.getParameter("type").equals("T")){
	
	if(request.getParameter("level").equals("1")){
		if(pao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Patent j = pao.getPatentByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your patent Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(pao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			Patent j = pao.getPatentByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your patent Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}
}
else if(request.getParameter("type").equals("R")){
	if(request.getParameter("level").equals("1")){
		if(trao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			TechnicalReport j = trao.getTechnicalReportByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your technical report Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(trao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			TechnicalReport j = trao.getTechnicalReportByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your technical report Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}	
}

else if(request.getParameter("type").equals("P")){
		if(request.getParameter("level").equals("1")){
			if(cproao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
				ConferenceProceedings j = cproao.getConferenceProceedingsByID(request.getParameter("id"));
				String email = lao.getEmailByUsername(j.getWrittenBy());
				EmailService.sendEmail("NCU: Publication Approved", "Your conference Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
				response.sendRedirect("../"+Redirect.redirect(role, true));
			}else{
				 response.sendRedirect("../"+Redirect.redirect(role, true));
			 }
		}else if(request.getParameter("level").equals("2")){
			if(cproao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
				ConferenceProceedings j = cproao.getConferenceProceedingsByID(request.getParameter("id"));
				String email = lao.getEmailByUsername(j.getWrittenBy());
				EmailService.sendEmail("NCU: Publication Approved", "Your conference Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
				response.sendRedirect("../"+Redirect.redirect(role, true));
			 }else{
				 response.sendRedirect("../"+Redirect.redirect(role, true));
			 }	
		}
}

else if(request.getParameter("type").equals("C")){
	if(request.getParameter("level").equals("1")){
		if(cpreao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			 ConferencePresentation j= cpreao.getConferencePresentationByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your presentation Titled \""+j.getTitle()+"\" , has been approved by Department Coordinator.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		}else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }
	}else if(request.getParameter("level").equals("2")){
		if(cpreao.action(request.getParameter("id"), Integer.parseInt(request.getParameter("status")))){
			ConferencePresentation j = cpreao.getConferencePresentationByID(request.getParameter("id"));
			String email = lao.getEmailByUsername(j.getWrittenBy());
			EmailService.sendEmail("NCU: Publication Approved", "Your presentation Titled \""+j.getTitle()+"\" , has been approved by RDIL.\n To login click here: http://localhost:8080/Publication_Portal" , email);
			response.sendRedirect("../"+Redirect.redirect(role, true));
		 }else{
			 response.sendRedirect("../"+Redirect.redirect(role, true));
		 }	
	}
}





%>

</body>
</html>