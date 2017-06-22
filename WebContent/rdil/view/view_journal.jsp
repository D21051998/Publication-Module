<%@page import="com.publication.constants.FetchDepptCode"%>
<%@page import="com.publication.model.Journal"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Journal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
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
	<jsp:useBean id="dao" class="com.publication.impl.JournalIMPL"
		scope="page"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
		List<Journal> list = dao.getAllJournals();
		for (Journal j : list) {
			System.out.println(j);
		}
		String sid = (String) request.getSession(false).getAttribute("sid");
		System.out.println(sid);
		if (null == sid) {
			response.sendRedirect("../");
			return;
		}
		if (!lao.getRoleBySessionID(sid).contains("RDIL")) {
			response.sendRedirect("../../account/access_denied.jsp");
			return;
		}
		request.setAttribute("eList", list);
	%>
	<jsp:include page="../../common/header.jsp"></jsp:include>


	<div class="container">
		<div class="row">

			<div class="col-md-2" id="sidebarStyle">
				<ul class="list-group">
					<li><a href="#" class="list-group-item active">View Book
							Chapter</a></li>
				</ul>
			</div>
			<div class="col-md-10">

				<table class="table table-condensed">
					<thead>
						<th>PCN</th>
						<th>Name Of Authors</th>
						<th>Department</th>
						<th>Title Of Paper</th>
						<th>Journal</th>
						<th>Nationality</th>
						<th>Year</th>
						<th>Month Published</th>
						<th>Month PCN Generated</th>
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
						<th>Status</th>
						<th>Delete</th>
					</thead>
					<c:forEach items="${eList}" var="journal">

						<tr>
							<td><c:out value="${journal.pcn}" /></td>
							<td><c:out value="${journal.nameOauthors}" /></td>
							<td><c:out value="${journal.deptt}" /></td>
							<td><c:out value="${journal.title}" /></td>
							<td><c:out value="${journal.journal}" /></td>
							<td><c:out value="${journal.nationality}" /></td>
							<td><c:out value="${journal.year}" /></td>
							<td><c:out value="${journal.monthPublished}" /></td>
							<td><c:out value="${journal.monthAssigned}" /></td>
							<td><c:out value="${journal.volume}" /></td>
							<td><c:out value="${journal.issue}" /></td>
							<td><c:out value="${journal.pageNo}" /></td>
							<td><c:out value="${journal.doiNo}" /></td>
							<td><c:out value="${journal.impactFactor}" /></td>
							<td><c:out value="${journal.whatImpactFactor}" /></td>
							<td><c:out value="${journal.linkImpFactor}" /></td>
							<td><c:out value="${journal.paidOrUnpaid}" /></td>
							<td><c:out value="${journal.paymentFlag}" /></td>
							<td><c:out value="${journal.pwFlag}" /></td>
							<td><c:out value="${journal.psFlag}" /></td>
							<td><c:out value="${journal.pgFlag}" /></td>
							<td><c:out value="${journal.piFlag}" /></td>
							<c:url value="../../action/action_journal.jsp" var="approve">

								<c:param name="deptt" value="${journal.deptt}"></c:param>
								<c:param name="title" value="${journal.title}"></c:param>
								<c:param name="volume" value="${journal.volume}"></c:param>
								<c:param name="issue" value="${journal.issue}"></c:param>
								<c:param name="pageNo" value="${journal.pageNo}"></c:param>
								<c:param name="level" value="2"></c:param>

							</c:url>
							<c:choose>
								<c:when test="${journal.status==0}">
									<td>Pending</td>
								</c:when>
								<c:when test="${journal.status==1}">
									<td><a>Approved by Deptt. Coordinator</a><br> <a
										href="${approve}&status=2">Approve</a><br> <a
										href="${approve}&status=-2">Reject</a></td>
								</c:when>
								<c:when test="${journal.status==-1}">
									<td><a>Rejected</a></td>
								</c:when>
								<c:when test="${journal.status==2}">
									<td><a>Approved By RDIL</a></td>
								</c:when>
								<c:when test="${journal.status==-2}">
									<td><a>Rejected By RDIL</a></td>
								</c:when>
								<c:otherwise>
									<td>Invalid</td>
								</c:otherwise>
							</c:choose>

							<c:url value="../delete/delete.jsp" var="delete">

							</c:url>
							<td><a href='<c:out value="${delete}" />'>Delete</a></td>
						</tr>

					</c:forEach>
				</table>
			</div>

		</div>
	</div>

</body>
</html>