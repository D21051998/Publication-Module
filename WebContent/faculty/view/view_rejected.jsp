<%@page import="com.publication.model.Login"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Rejections</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="../../resources/styles_header/navbar_addition.css"
	rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	background-color: #fcfcfc;
}

.blank_row {
	height: 10px !important; /* overwrites any other rules */
	background-color: rgba(255, 255, 255, 0.6);
}

td {
	text-align: center;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
	padding: 10px;
}

th {
	text-align: center;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 17px;
	font-style: italic;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 20px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

table {
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: break-word;
}

a {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 17px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

.container {
	width: 100%;
}

li.borderless {
	border-bottom: 0 none;
	border-top: none;
}

ul {
	list-style: none;
}

.content2:before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	display: block;
	background-image: url('../../resources/images/DSCN7348.jpg');
	-webkit-filter: brightness(0.8);
	filter: brightness(0.8);
	background-size: cover;
	width: 100%;
	height: 100%;
	-webkit-filter: blur(10px);
	-moz-filter: blur(10px);
	-o-filter: blur(10px);
	-ms-filter: blur(10px);
	filter: blur(10px);
}

.content2 {
	overflow: visible;
	position: relative;
}

div.transbox {
	margin: 30px;
	background-color: rgba(255, 255, 255, 0.6);
	border: 0px solid;
	width: auto;
	/* For IE8 and earlier */
}

.content2 p {
	margin: 15px;
	background: rgba(255, 255, 255, 0.3);
	padding: 5px;
	box-shadow: 0 0 5px gray;
}

.ui-content {
	width: auto;
}
</style>
</head>
<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
	onload = function() {
		var e = document.getElementById("refreshed");
		if (e.value == "no")
			e.value = "yes";
		else {
			e.value = "no";
			location.reload();
		}
	}
</script>

<body>
	<jsp:useBean id="bookChapterDao"
		class="com.publication.impl.BookChapterIMPL" scope="page"></jsp:useBean>
	<jsp:useBean id="bookDao" class="com.publication.impl.BooksIMPL"></jsp:useBean>
	<jsp:useBean id="confProcDao"
		class="com.publication.impl.ConferenceProceedingIMPL"></jsp:useBean>
	<jsp:useBean id="confPresDao"
		class="com.publication.impl.ConferencePresentationIMPL"></jsp:useBean>
	<jsp:useBean id="journalDao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
	<jsp:useBean id="techReportDao"
		class="com.publication.impl.TechnicalReportIMPL"></jsp:useBean>
	<jsp:useBean id="patentDao" class="com.publication.impl.PatentIMPL"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
	String sid = (String) request.getSession(false).getAttribute("sid");
	if (null == sid) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
	Login login = lao.getLogin(lao.getUsernameBySessionID(sid));
	if (null == login) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
	if (!lao.getRoleBySessionID(sid).equals("ROLE_FACULTY")) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
		pageContext.setAttribute("principal", lao.getUsernameBySessionID(sid));

		pageContext.setAttribute("accBookChapter", bookChapterDao.getAllBookChapters());
		pageContext.setAttribute("accBook", bookDao.getAllBooks());
		pageContext.setAttribute("accConfProc", confProcDao.getAllConferenceProceedingss());
		pageContext.setAttribute("accConfPres", confPresDao.getAllConferencePresentations());
		pageContext.setAttribute("accJournal", journalDao.getAllJournals());
		pageContext.setAttribute("accTechRep", techReportDao.getAllTechnicalReports());
		pageContext.setAttribute("accPatent", patentDao.getAllPatents());

		pageContext.setAttribute("rejBookChapter", bookChapterDao.getAllRejectedBookChapters());
		pageContext.setAttribute("rejBook", bookDao.getAllRejBooks());
		pageContext.setAttribute("rejConfProc", confProcDao.getAllRejConferenceProceedingss());
		pageContext.setAttribute("rejConfPres", confPresDao.getAllRejConferencePresentations());
		pageContext.setAttribute("rejJournal", journalDao.getAllRejJournals());
		pageContext.setAttribute("rejTechRep", techReportDao.getAllRejTechnicalReports());
		pageContext.setAttribute("rejPatent", patentDao.getAllRejPatents());
	%>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid clearfix">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a
				style="background-color: transparent !important; color: #9e433d !important;"
				class="navbar-brand"><strong> <img
					src="../../resources/images/ncu logo.png" width="150px"
					height="50px" id="logo" /> The NorthCap University
			</strong></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">

			<ul class="nav navbar-nav navbar-left">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" style="color: #9e433d !important;">Navigate
						To<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="../faculty_home.jsp">Home</a></li>
						<li><a href="view_book_chapter.jsp">View Book Chapter</a></li>
						<li><a href="view_conference_presentation.jsp">View
								Conference Presentation</a></li>
						<li><a href="view_conference_proceeding.jsp">View
								Conference Proceeding</a></li>
						<li><a href="view_journal.jsp">View Journal</a></li>
						<li><a href="view_patent.jsp">View Patents</a></li>
						<li><a href="view_tech_rep.jsp">View Technical Reports</a></li>
						<li><a href="view_book.jsp">View Books</a></li>
						<li><a href="view_rejected.jsp">View Rejected</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown pull-left"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><span
						class="glyphicon glyphicon-user"></span>&nbsp;Profile<span
						class="caret"></span></a>
					<ul class="dropdown-menu" id="profile-menu">
						<li><a href="../../account/change_password.jsp">Change
								Password</a></li>
						<li><a href="../../account/logout.jsp">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid content2">
		<div class="row">
			<div class="col-md-12 transbox">
				<section>
				<h3>View Rejected Publications</h3>
				<ul>
					<li>

						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-bc">
							<h3>
								View Rejected Book Chapter&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>

						<table class="table table-borderless collapse" id="table-bc">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN & Date Assigned</th>
								<th>Name Of Authors</th>
								<th>Department</th>
								<th>Chapter No</th>
								<th>Chapter Title</th>
								<th>Book Title</th>
								<th>Publisher</th>
								<th>Nationality</th>
								<th>Year</th>
								<th>Month Published</th>
								<th>Page No</th>
								<th>ISBN</th>
								<th>HyperLink</th>
								<th>Index Flag</th>
								<th>Index Link</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
								<th>Plagiarism Copy</th>
							</tr>
							<c:forEach items="${accBookChapter}" var="bc">
								<c:if test="${bookChapterDao.checkIfRejected(bc.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty bc.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty bookChapter.value.pcn}">
												<c:out value="${bc.pcn}" />
												<br>
												<br>
												<c:out value="${bc.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${bc.nameOauthors}" /></td>
										<td><c:out value="${bc.deptt}" /></td>
										<td><c:out value="${bc.chapterNo}" /></td>
										<td><c:out value="${bc.chapterTitle}" /></td>
										<td><c:out value="${bc.bookTitle}" /></td>
										<td><c:out value="${bc.publisher}" /></td>
										<td><c:out value="${bc.nationality}" /></td>
										<td><c:out value="${bc.year}" /></td>
										<td><c:out value="${bc.monthPublished}" /></td>
										<td><c:out value="${bc.pageNo}" /></td>
										<td><c:out value="${bc.isbn}" /></td>
										<td><c:out value="${bc.hyperLink}" /></td>
										<td><c:out value="${bc.indexFlag}" /></td>
										<td><c:out value="${bc.indexLink}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${bc.id}"></c:param>
											<c:param name="type" value="BC"></c:param>
										</c:url>
										<td><a href="${download}&index=0" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=1" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=2" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
									</tr>
								</c:if>
								<c:forEach items="${rejBookChapter}" var="bookChapter">
									<c:if test="${bc.id eq bookChapter.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty bookChapter.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty bookChapter.value.pcn}">
													<c:out value="${bookChapter.value.pcn}" />
													<br>
													<br>
													<c:out value="${bookChapter.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${bookChapter.value.nameOauthors}" /></td>
											<td><c:out value="${bookChapter.value.deptt}" /></td>
											<td><c:out value="${bookChapter.value.chapterNo}" /></td>
											<td><c:out value="${bookChapter.value.chapterTitle}" /></td>
											<td><c:out value="${bookChapter.value.bookTitle}" /></td>
											<td><c:out value="${bookChapter.value.publisher}" /></td>
											<td><c:out value="${bookChapter.value.nationality}" /></td>
											<td><c:out value="${bookChapter.value.year}" /></td>
											<td><c:out value="${bookChapter.value.monthPublished}" /></td>
											<td><c:out value="${bookChapter.value.pageNo}" /></td>
											<td><c:out value="${bookChapter.value.isbn}" /></td>
											<td><c:out value="${bookChapter.value.hyperLink}" /></td>
											<td><c:out value="${bookChapter.value.indexFlag}" /></td>
											<td><c:out value="${bookChapter.value.indexLink}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${bookChapter.value.id}"></c:param>
												<c:param name="type" value="BC"></c:param>
											</c:url>
											<td><a href="${download}&index=0" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=1" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=2" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table>
					</li>
					<li>
						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-book">
							<h3>
								View Rejected Books&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>
						<table class="table table-borderless collapse" id="table-book">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN & Date Assigned</th>
								<th>Name Of Authors</th>
								<th>Department</th>
								<th>Book Title</th>
								<th>Publisher</th>
								<th>Nationality</th>
								<th>Year</th>
								<th>Month Published</th>
								<th>Page No</th>
								<th>ISBN</th>
								<th>HyperLink</th>
								<th>Indices</th>
								<th>Index Link</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
								<th>Plagiarism Copy</th>
							</tr>
							<c:forEach items="${accBook}" var="b">
								<c:if test="${bookDao.checkIfRejected(b.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty b.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty b.pcn}">
												<c:out value="${b.pcn}" />
												<br>
												<br>
												<c:out value="${b.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${b.nameOauthors}" /></td>
										<td><c:out value="${b.deptt}" /></td>
										<td><c:out value="${b.title}" /></td>
										<td><c:out value="${b.publisher}" /></td>
										<td><c:out value="${b.nationality}" /></td>
										<td><c:out value="${b.year}" /></td>
										<td><c:out value="${b.monthPublished}" /></td>
										<td><c:out value="${b.pageNo}" /></td>
										<td><c:out value="${b.isbn}" /></td>
										<td><c:out value="${b.hyperlink}" /></td>
										<td><c:out value="${b.index}" /></td>
										<td><c:out value="${b.link}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${b.id}"></c:param>
											<c:param name="type" value="B"></c:param>
										</c:url>
										<td><a href="${download}&index=0" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=1" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=2" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
									</tr>
								</c:if>
								<c:forEach items="${rejBook}" var="book">
									<c:if test="${b.id eq book.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty book.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty book.value.pcn}">
													<c:out value="${book.value.pcn}" />
													<br>
													<br>
													<c:out value="${book.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${book.value.nameOauthors}" /></td>
											<td><c:out value="${book.value.deptt}" /></td>
											<td><c:out value="${book.value.title}" /></td>
											<td><c:out value="${book.value.publisher}" /></td>
											<td><c:out value="${book.value.nationality}" /></td>
											<td><c:out value="${book.value.year}" /></td>
											<td><c:out value="${book.value.monthPublished}" /></td>
											<td><c:out value="${book.value.pageNo}" /></td>
											<td><c:out value="${book.value.isbn}" /></td>
											<td><c:out value="${book.value.hyperlink}" /></td>
											<td><c:out value="${book.value.index}" /></td>
											<td><c:out value="${book.value.link}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${book.value.id}"></c:param>
												<c:param name="type" value="B"></c:param>
											</c:url>
											<td><a href="${download}&index=0" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=1" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=2" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table>
					</li>
					<li>
						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-journal">
							<h3>
								View Rejected Journals&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>
						<table class="table table-borderless collapse" id="table-journal">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN</th>
								<th>Name Of Authors</th>
								<th>Department</th>
								<th>Title Of Paper</th>
								<th>Journal</th>
								<th>Nationality</th>
								<th>Year</th>
								<th>Month Published</th>
								<th>Volume</th>
								<th>Number/Issue</th>
								<th>Page No</th>
								<th>DOI No.</th>
								<th>Impact Factor</th>
								<th>What Impact Factor</th>
								<th>Link for Impact factor</th>
								<th>Paid/Unpaid</th>
								<th>Payment Done or not</th>
								<th>PW : Publication reported in Web of Science)</th>
								<th>PS: Publication reported in Scopus</th>
								<th>PG: Publication reported in Google Scholar</th>
								<th>PI: Publication reported in Indian Citation Index</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
								<th>Plagiarism Copy</th>
							</tr>
							<c:forEach items="${accJournal}" var="j">
								<c:if test="${journalDao.checkIfRejected(j.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty j.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty j.pcn}">
												<c:out value="${j.pcn}" />
												<br>
												<br>
												<c:out value="${j.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${j.nameOauthors}" /></td>
										<td><c:out value="${j.deptt}" /></td>
										<td><c:out value="${j.title}" /></td>
										<td><c:out value="${j.journal}" /></td>
										<td><c:out value="${j.nationality}" /></td>
										<td><c:out value="${j.year}" /></td>
										<td><c:out value="${j.monthPublished}" /></td>
										<td><c:out value="${j.volume}" /></td>
										<td><c:out value="${j.issue}" /></td>
										<td><c:out value="${j.pageNo}" /></td>
										<td><c:out value="${j.doiNo}" /></td>
										<td><c:out value="${j.impactFactor}" /></td>
										<td><c:out value="${j.whatImpactFactor}" /></td>
										<td><c:out value="${j.linkImpFactor}" /></td>
										<td><c:out value="${j.paidOrUnpaid}" /></td>
										<td><c:out value="${j.paymentFlag}" /></td>
										<td><c:out value="${j.pwFlag}" /></td>
										<td><c:out value="${j.psFlag}" /></td>
										<td><c:out value="${j.pgFlag}" /></td>
										<td><c:out value="${j.piFlag}" /></td>

										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${j.id}"></c:param>
											<c:param name="type" value="J"></c:param>

										</c:url>

										<td><a href="${download}&index=0"><span
												class="glyphicon glyphicon-download"></span></a></td>
										<td><a href="${download}&index=1"><span
												class="glyphicon glyphicon-download"></span></a></td>
										<td><a href="${download}&index=2"><span
												class="glyphicon glyphicon-download"></span></a></td>

									</tr>
								</c:if>
								<c:forEach items="${rejJournal}" var="journal">
									<c:if test="${j.id eq journal.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty journal.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty journal.value.pcn}">
													<c:out value="${journal.value.pcn}" />
													<br>
													<br>
													<c:out value="${journal.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${journal.value.nameOauthors}" /></td>
											<td><c:out value="${journal.value.deptt}" /></td>
											<td><c:out value="${journal.value.title}" /></td>
											<td><c:out value="${journal.value.journal}" /></td>
											<td><c:out value="${journal.value.nationality}" /></td>
											<td><c:out value="${journal.value.year}" /></td>
											<td><c:out value="${journal.value.monthPublished}" /></td>
											<td><c:out value="${journal.value.volume}" /></td>
											<td><c:out value="${journal.value.issue}" /></td>
											<td><c:out value="${journal.value.pageNo}" /></td>
											<td><c:out value="${journal.value.doiNo}" /></td>
											<td><c:out value="${journal.value.impactFactor}" /></td>
											<td><c:out value="${journal.value.whatImpactFactor}" /></td>
											<td><c:out value="${journal.value.linkImpFactor}" /></td>
											<td><c:out value="${journal.value.paidOrUnpaid}" /></td>
											<td><c:out value="${journal.value.paymentFlag}" /></td>
											<td><c:out value="${journal.value.pwFlag}" /></td>
											<td><c:out value="${journal.value.psFlag}" /></td>
											<td><c:out value="${journal.value.pgFlag}" /></td>
											<td><c:out value="${journal.value.piFlag}" /></td>

											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${journal.value.id}"></c:param>
												<c:param name="type" value="J"></c:param>

											</c:url>

											<td><a href="${download}&index=0"><span
													class="glyphicon glyphicon-download"></span></a></td>
											<td><a href="${download}&index=1"><span
													class="glyphicon glyphicon-download"></span></a></td>
											<td><a href="${download}&index=2"><span
													class="glyphicon glyphicon-download"></span></a></td>
										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="25"></td>
								</tr>
							</c:forEach>
						</table>
					</li>
					<li>
						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-proc">
							<h3>
								View Rejected Proceedings&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>

						<table class="table table-borderless collapse" id="table-proc">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN</th>
								<th>Name Of Authors</th>
								<th>Department</th>
								<th>Title Of Paper</th>
								<th>Proceedings Of</th>
								<th>Nationality</th>
								<th>Venue</th>
								<th>Month Published</th>
								<th>Year</th>
								<th>Publisher</th>
								<th>Page No</th>
								<th>Hyperlink</th>
								<th>Indices</th>
								<th>Link</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
								<th>Plagiarism Copy</th>
							</tr>


							<c:forEach items="${accConfProc}" var="p">
								<c:if test="${confProcDao.checkIfRejected(p.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty p.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty p.pcn}">
												<c:out value="${p.pcn}" />
												<br>
												<br>
												<c:out value="${p.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${p.nameOauthors}" /></td>
										<td><c:out value="${p.deptt}" /></td>
										<td><c:out value="${p.title}" /></td>
										<td><c:out value="${p.proceedingsOf}" /></td>
										<td><c:out value="${p.nationality}" /></td>
										<td><c:out value="${p.venue}" /></td>
										<td><c:out value="${p.monthPublished}" /></td>
										<td><c:out value="${p.year}" /></td>
										<td><c:out value="${p.publisher}" /></td>
										<td><c:out value="${p.pageNo}" /></td>
										<td><c:out value="${p.hyperlink}" /></td>
										<td><c:out value="${p.index}" /></td>
										<td><c:out value="${p.link}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${p.id}"></c:param>
											<c:param name="type" value="P"></c:param>
										</c:url>
										<td><a href="${download}&index=0"><span
												class="glyphicon glyphicon-download"></span></a></td>
										<td><a href="${download}&index=1"><span
												class="glyphicon glyphicon-download"></span></a></td>
										<td><a href="${download}&index=2"><span
												class="glyphicon glyphicon-download"></span></a></td>
									</tr>
								</c:if>
								<c:forEach items="${rejConfProc}" var="cp">
									<c:if test="${p.id eq cp.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty cp.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty cp.value.pcn}">
													<c:out value="${cp.value.pcn}" />
													<br>
													<br>
													<c:out value="${cp.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${cp.value.nameOauthors}" /></td>
											<td><c:out value="${cp.value.deptt}" /></td>
											<td><c:out value="${cp.value.title}" /></td>
											<td><c:out value="${cp.value.proceedingsOf}" /></td>
											<td><c:out value="${cp.value.nationality}" /></td>
											<td><c:out value="${cp.value.venue}" /></td>
											<td><c:out value="${cp.value.monthPublished}" /></td>
											<td><c:out value="${cp.value.year}" /></td>
											<td><c:out value="${cp.value.publisher}" /></td>
											<td><c:out value="${cp.value.pageNo}" /></td>
											<td><c:out value="${cp.value.hyperlink}" /></td>
											<td><c:out value="${cp.value.index}" /></td>
											<td><c:out value="${cp.value.link}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${cp.value.id}"></c:param>
												<c:param name="type" value="P"></c:param>
											</c:url>
											<td><a href="${download}&index=0"><span
													class="glyphicon glyphicon-download"></span></a></td>
											<td><a href="${download}&index=1"><span
													class="glyphicon glyphicon-download"></span></a></td>
											<td><a href="${download}&index=2"><span
													class="glyphicon glyphicon-download"></span></a></td>
										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table>
					</li>
					<li>
						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-pres">
							<h3>
								View Rejected Presentations&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>

						<table class="table table-borderless collapse" id="table-pres">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN & Date Assigned</th>
								<th>faculty</th>
								<th>Department</th>
								<th>Title</th>
								<th>Conference Presentation</th>
								<th>Nationality</th>
								<th>Organized By</th>
								<th>Venue</th>
								<th>Year</th>
								<th>Dates</th>
								<th>Hyperlink</th>
								<th>Month Published</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
							</tr>


							<c:forEach items="${accConfPres}" var="c">
								<c:if test="${confPresDao.checkIfRejected(c.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty c.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty cpo.pcn}">
												<c:out value="${c.pcn}" />
												<br>
												<br>
												<c:out value="${c.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${c.faculty}" /></td>
										<td><c:out value="${c.deptt}" /></td>
										<td><c:out value="${c.title}" /></td>
										<td><c:out value="${c.conferencePresentation}" /></td>
										<td><c:out value="${c.nationality}" /></td>
										<td><c:out value="${c.organisedBy}" /></td>
										<td><c:out value="${c.venue}" /></td>
										<td><c:out value="${c.year}" /></td>
										<td><c:out value="${c.dates}" /></td>
										<td><c:out value="${c.hyperlink}" /></td>

										<td><c:out value="${c.monthPublished}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${cpo.id}"></c:param>
											<c:param name="type" value="C"></c:param>
										</c:url>

										<td><a href="${download}&index=0" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=1" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
									</tr>
								</c:if>
								<c:forEach items="${rejConfPres}" var="cpo">
									<c:if test="${c.id eq cpo.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty cpo.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty cpo.value.pcn}">
													<c:out value="${cpo.value.pcn}" />
													<br>
													<br>
													<c:out value="${cpo.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${cpo.value.faculty}" /></td>
											<td><c:out value="${cpo.value.deptt}" /></td>
											<td><c:out value="${cpo.value.title}" /></td>
											<td><c:out value="${cpo.value.conferencePresentation}" /></td>
											<td><c:out value="${cpo.value.nationality}" /></td>
											<td><c:out value="${cpo.value.organisedBy}" /></td>
											<td><c:out value="${cpo.value.venue}" /></td>
											<td><c:out value="${cpo.value.year}" /></td>
											<td><c:out value="${cpo.value.dates}" /></td>
											<td><c:out value="${cpo.value.hyperlink}" /></td>
											<td><c:out value="${cpo.value.monthPublished}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${cpo.value.id}"></c:param>
												<c:param name="type" value="C"></c:param>
											</c:url>

											<td><a href="${download}&index=0" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=1" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>

										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table>
					</li>
					<li><button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-patent">
							<h3>
								View Rejected Patents&nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>

						<table class="table table-borderless collapse" id="table-patent">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN & Date Assigned</th>
								<th>Faculty</th>
								<th>Department</th>
								<th>Title</th>
								<th>Nationality</th>
								<th>Country</th>
								<th>Application No</th>
								<th>Application Year</th>
								<th>Application Date</th>
								<th>Patent Year</th>
								<th>Award Date</th>
								<th>Patent No</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
							</tr>


							<c:forEach items="${accPatent}" var="p">
								<c:if test="${patentDao.checkIfRejected(p.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty p.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty p.pcn}">
												<c:out value="${p.pcn}" />
												<br>
												<br>
												<c:out value="${p.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${p.faculty}" /></td>
										<td><c:out value="${p.deptt}" /></td>
										<td><c:out value="${p.title}" /></td>
										<td><c:out value="${p.nationality}" /></td>
										<td><c:out value="${p.country}" /></td>
										<td><c:out value="${p.applicationNo}" /></td>
										<td><c:out value="${p.applicationYear}" /></td>
										<td><c:out value="${p.applicationDate}" /></td>
										<td><c:out value="${p.patentYear}" /></td>
										<td><c:out value="${p.awardDate}" /></td>
										<td><c:out value="${p.patentNo}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${p.id}"></c:param>
											<c:param name="type" value="T"></c:param>
										</c:url>

										<td><a href="${download}&index=0" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=1" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>

									</tr>
								</c:if>
								<c:forEach items="${rejPatent}" var="patent">
									<c:if test="${p.id eq patent.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty patent.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty patent.value.pcn}">
													<c:out value="${patent.value.pcn}" />
													<br>
													<br>
													<c:out value="${patent.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${patent.value.faculty}" /></td>
											<td><c:out value="${patent.value.deptt}" /></td>
											<td><c:out value="${patent.value.title}" /></td>
											<td><c:out value="${patent.value.nationality}" /></td>
											<td><c:out value="${patent.value.country}" /></td>
											<td><c:out value="${patent.value.applicationNo}" /></td>
											<td><c:out value="${patent.value.applicationYear}" /></td>
											<td><c:out value="${patent.value.applicationDate}" /></td>
											<td><c:out value="${patent.value.patentYear}" /></td>
											<td><c:out value="${patent.value.awardDate}" /></td>
											<td><c:out value="${patent.value.patentNo}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${patent.value.id}"></c:param>
												<c:param name="type" value="T"></c:param>
											</c:url>

											<td><a href="${download}&index=0" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=1" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>

										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table></li>
					<li>
						<button type="button" class="btn" style="width: 400px;"
							data-toggle="collapse" data-target="#table-report">
							<h3>
								View Rejected Reports &nbsp;&nbsp;<span
									class="glyphicon glyphicon-chevron-down"></span>
							</h3>
						</button>
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>tr>tr>td, .table-borderless>tr>tr>th {
	border: none !important;
}
</style>

						<table class="table table-borderless collapse" id="table-report">
							<tr>
								<th style="background-color: black;">&nbsp;</th>
								<th>PCN & Date Assigned</th>
								<th>faculty</th>
								<th>Department</th>
								<th>Title</th>
								<th>Year</th>
								<th>Date</th>
								<th>Remarks</th>
								<th>Month Published</th>
								<th>Resource</th>
								<th>Plagiarism Report</th>
								<th>Plagiarism Copy</th>
							</tr>


							<c:forEach items="${accTechRep}" var="r">
								<c:if test="${techReportDao.checkIfRejected(r.id)}">
									<tr>
										<td style="background-color: green;"></td>
										<td><c:if test="${empty r.pcn}">
												<c:out value="Not Generated" />
											</c:if> <c:if test="${not empty r.pcn}">
												<c:out value="${r.pcn}" />
												<br>
												<br>
												<c:out value="${r.monthAssigned}" />
											</c:if></td>
										<td><c:out value="${r.faculty}" /></td>
										<td><c:out value="${r.deptt}" /></td>
										<td><c:out value="${r.title}" /></td>
										<td><c:out value="${r.year}" /></td>
										<td><c:out value="${r.date}" /></td>
										<td><c:out value="${r.remarks}" /></td>
										<td><c:out value="${r.monthPublished}" /></td>
										<c:url value="../../DownloadResource" var="download">
											<c:param name="id" value="${r.id}"></c:param>
											<c:param name="type" value="R"></c:param>
										</c:url>
										<td><a href="${download}&index=0" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=1" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>
										<td><a href="${download}&index=2" class="btn btn-info">
												<span class="glyphicon glyphicon-download"></span>
										</a></td>

									</tr>
								</c:if>
								<c:forEach items="${rejTechRep}" var="report">
									<c:if test="${r.id eq report.value.id}">
										<tr>
											<td style="background-color: red;"></td>
											<td><c:if test="${empty report.value.pcn}">
													<c:out value="Not Generated" />
												</c:if> <c:if test="${not empty report.value.pcn}">
													<c:out value="${report.value.pcn}" />
													<br>
													<br>
													<c:out value="${report.value.monthAssigned}" />
												</c:if></td>
											<td><c:out value="${report.value.faculty}" /></td>
											<td><c:out value="${report.value.deptt}" /></td>
											<td><c:out value="${report.value.title}" /></td>
											<td><c:out value="${report.value.year}" /></td>
											<td><c:out value="${report.value.date}" /></td>
											<td><c:out value="${report.value.remarks}" /></td>
											<td><c:out value="${report.value.monthPublished}" /></td>
											<c:url value="../../DownloadResource" var="download">
												<c:param name="id" value="${report.value.id}"></c:param>
												<c:param name="type" value="R"></c:param>
											</c:url>
											<td><a href="${download}&index=0" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=1" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>
											<td><a href="${download}&index=2" class="btn btn-info">
													<span class="glyphicon glyphicon-download"></span>
											</a></td>

										</tr>
									</c:if>
								</c:forEach>
								<tr class="blank_row">
									<td colspan="19"></td>
								</tr>
							</c:forEach>
						</table>
					</li>

				</ul>
				</section>
			</div>
		</div>
	</div>

<script type="text/javascript">
		var $rows = $('#table tr');
		$('#search').keyup(function() {
			var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

			$rows.show().filter(function() {
				var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
				return !~text.indexOf(val);
			}).hide();
		});
	</script>

</body>
</html>