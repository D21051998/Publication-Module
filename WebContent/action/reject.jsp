<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.publication.model.*"%>
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
	<jsp:useBean id="jao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
	<jsp:useBean id="bao" class="com.publication.impl.BooksIMPL"></jsp:useBean>
	<jsp:useBean id="bcao" class="com.publication.impl.BookChapterIMPL"></jsp:useBean>
	<jsp:useBean id="cpreao"
		class="com.publication.impl.ConferencePresentationIMPL"></jsp:useBean>
	<jsp:useBean id="cproao"
		class="com.publication.impl.ConferenceProceedingIMPL"></jsp:useBean>
	<jsp:useBean id="pao" class="com.publication.impl.PatentIMPL"></jsp:useBean>
	<jsp:useBean id="trao" class="com.publication.impl.TechnicalReportIMPL"></jsp:useBean>

	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<%
		String sid = request.getSession(false).getAttribute("sid").toString();
		String role = lao.getRoleBySessionID(sid);
		if (request.getParameter("type").equals("J")) {
			if (request.getParameter("level").equals("1")) {
				if (jao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Journal j = jao.getJournalByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your journal Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_journal.jsp?reject=success");

				} else {
					response.sendRedirect("../department_coord/view/view_journal.jsp?reject=failed");

				}
			} else if (request.getParameter("level").equals("2")) {
				if (jao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Journal j = jao.getJournalByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected", "Your journal Titled \"" + j.getTitle()
							+ "\" , has been rejected by RDIL because of reason \"" + request.getParameter("reason")
							+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_journal.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_journal.jsp?reject=failed");

				}
			}
		} else if (request.getParameter("type").equals("B")) {
			if (request.getParameter("level").equals("1")) {
				if (bao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Books j = bao.getBookByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your book Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_book.jsp?reject=success");

				} else {
					response.sendRedirect("../department_coord/view/view_book.jsp?reject=failed");

				}
			} else if (request.getParameter("level").equals("2")) {
				if (bao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Books j = bao.getBookByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected", "Your book Titled \"" + j.getTitle()
							+ "\" , has been rejected by RDIL because of reason \"" + request.getParameter("reason")
							+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_book.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_book.jsp?reject=failed");

				}
			}
		}

		else if (request.getParameter("type").equals("BC")) {
			if (request.getParameter("level").equals("1")) {
				if (bcao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					BookChapter j = bcao.getBookChapterByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your book chapter Titled \"" + j.getChapterTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_book_chapter.jsp?reject=success");

				} else {
					response.sendRedirect("../department_coord/view/view_book_chapter.jsp?reject=failed");

				}
			} else if (request.getParameter("level").equals("2")) {
				if (bcao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					BookChapter j = bcao.getBookChapterByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your book chapter Titled \"" + j.getChapterTitle()
									+ "\" , has been rejected by RDIL because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_book_chapter.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_book_chapter.jsp?reject=failed");

				}
			}
		}

		else if (request.getParameter("type").equals("T")) {
			if (request.getParameter("level").equals("1")) {
				if (pao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Patent j = pao.getPatentByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your patent Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_patent.jsp?reject=success");
				} else {
					response.sendRedirect("../department_coord/view/view_patent.jsp?reject=failed");
				}
			} else if (request.getParameter("level").equals("2")) {
				if (pao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					Patent j = pao.getPatentByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected", "Your patent Titled \"" + j.getTitle()
							+ "\" , has been rejected by RDIL because of reason \"" + request.getParameter("reason")
							+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_patent.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_patent.jsp?reject=failed");

				}
			}
		}

		else if (request.getParameter("type").equals("C")) {
			if (request.getParameter("level").equals("1")) {
				if (cpreao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					ConferencePresentation j = cpreao.getConferencePresentationByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your conference Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_conference_presentation.jsp?reject=success");
				} else {
					response.sendRedirect("../department_coord/view/view_conference_presentation.jsp?reject=failed");
				}
			} else if (request.getParameter("level").equals("2")) {
				if (cpreao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					ConferencePresentation j = cpreao.getConferencePresentationByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected", "Your conference Titled \"" + j.getTitle()
							+ "\" , has been rejected by RDIL because of reason \"" + request.getParameter("reason")
							+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_conference_presentation.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_conference_presentation.jsp?reject=failed");

				}
			}
		}

		else if (request.getParameter("type").equals("P")) {
			if (request.getParameter("level").equals("1")) {
				if (cproao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					ConferenceProceedings j = cproao.getConferenceProceedingsByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your presentation Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_conference_proceeding.jsp?reject=success");
				} else {
					response.sendRedirect("../department_coord/view/view_conference_proceeding.jsp?reject=failed");
				}
			} else if (request.getParameter("level").equals("2")) {
				if (cproao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					ConferenceProceedings j = cproao.getConferenceProceedingsByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected", "Your presentation Titled \"" + j.getTitle()
							+ "\" , has been rejected by RDIL because of reason \"" + request.getParameter("reason")
							+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_conference_proceeding.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_conference_proceeding.jsp?reject=failed");

				}
			}
		}

		else if (request.getParameter("type").equals("R")) {
			if (request.getParameter("level").equals("1")) {
				if (trao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					TechnicalReport j = trao.getTechnicalReportByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your technical report Titled \"" + j.getTitle()
									+ "\" , has been rejected by Department Coordinator because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../department_coord/view/view_tech_rep.jsp?reject=success");
				} else {
					response.sendRedirect("../department_coord/view/view_tech_rep.jsp?reject=failed");
				}
			} else if (request.getParameter("level").equals("2")) {
				if (trao.reject(request.getParameter("id"), Integer.parseInt(request.getParameter("status")),
						request.getParameter("reason"))) {
					TechnicalReport j = trao.getTechnicalReportByID(request.getParameter("id"));
					String email = lao.getEmailByUsername(j.getWrittenBy());
					EmailService.sendEmail("NCU: Publication Rejected",
							"Your technical report Titled \"" + j.getTitle()
									+ "\" , has been rejected by RDIL because of reason \""
									+ request.getParameter("reason")
									+ "\". To make changes to previous copy, please login here: http://localhost:8080/Publication_Portal",
							email);
					response.sendRedirect("../rdil/view/view_tech_rep.jsp?reject=success");

				} else {
					response.sendRedirect("../rdil/view/view_tech_rep.jsp?reject=failed");

				}
			}
		}
	%>

	%>
</body>
</html>
