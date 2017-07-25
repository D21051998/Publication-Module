<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
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
			response.sendRedirect("../account/access_denied.jsp");
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
	<div class="container-fluid content2">
		<div class="row">
			<div class="col-md-12 transbox">
			<section>
			<h3>View Rejected Publication</h3>
			<ul>
			<li>
			
				<button type="button" class="btn" style="width: 400px;" data-toggle="collapse"
					data-target="#table-bc">
					<h3>View Rejected Book Chapter&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-down"></span></h3>
				</button>
				<table class="table table-bordered collapse" id="table-bc">
					<thead>
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
						<th>Plag. Report</th>
						<th>Plag. Copy</th>
					</thead>
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
				<button type="button" class="btn" style="width: 400px;" data-toggle="collapse"
					data-target="#table-book">
					<h3>View Rejected Books&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-down"></span></h3>
				</button>
				<table class="table table-bordered collapse" id="table-book">
				<thead>
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
						<th>Plag. Report</th>
						<th>Plag. Copy</th>
					</thead>
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
			</ul>
			</section>
			</div>
		</div>
	</div>



</body>
</html>