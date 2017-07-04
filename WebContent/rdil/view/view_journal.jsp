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
		String divTag = "div";
		String buttonTag = "reject";
		int index = 1;
	%>
	<jsp:include page="../../headers/new_pages_header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div class="container-fluid">

		<div class="row">

			<div class="col-md-2">
				<jsp:include page="../../sidebars/rdil_view_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<h3>View Journal</h3>
				<table class="table table-bordered">
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
						<th>Resource</th>
						<th>Plag. Report</th>
						<th>Plag. Copy</th>
						<th>Status</th>
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
							<c:url value="../../DownloadResource" var="download">
								<c:param name="deptt" value="${journal.deptt}"></c:param>
								<c:param name="title" value="${journal.title}"></c:param>
								<c:param name="volume" value="${journal.volume}"></c:param>
								<c:param name="issue" value="${journal.issue}"></c:param>
								<c:param name="pageNo" value="${journal.pageNo}"></c:param>

							</c:url>

							<td><a href="${download}&index=0">Download</a></td>
							<td><a href="${download}&index=1">Download</a></td>
							<td><a href="${download}&index=2">Download</a></td>

							<c:url value="../../action/action_journal.jsp" var="action">
								<c:param name="id" value="${journal.id}" />
								<c:param name="level" value="2"></c:param>
							</c:url>
							<c:url value="../../action/reject_journal.jsp" var="reject">
							</c:url>
							<c:choose>
								<c:when test="${journal.status==0}">
									<td>Pending</td>
								</c:when>
								<c:when test="${journal.status==1}">
									<td><a>Approved by Deptt. Coordinator</a><br> <a
										href="${action}&status=2">Approve</a><br>
										<button type="button" data-name="${journal.id}"
											class="btn btn-danger" id="<%out.print(buttonTag + index);%>"
											value="form" onclick="a(this);">Reject</button>
										<div id="<%out.print(divTag + index);
						index++;%>"></div>
									</td>
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
						</tr>

					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<script>
		function a(button) {
			var id = button.getAttribute("data-name");
			console.log(id);
			var b = '<form action=${reject}><input type=text name=reason class=form-control placeholder=Specify><input type=hidden name=id value=';
			var c = id;
			var d = '><input type=hidden name=level value=';
			var e = 2;
			var f = '><input type=hidden name=status value=';
			var g = -2;
			var h = '><button type=submit class=btn>Submit</button></form>';
			var url = b + c + d + e + f + g + h;
			var buttonName = button.id;
			var matches = buttonName.match(/\d+/g);
			console.log("Showing");
			console.log(buttonName);
			console.log(matches);
			console.log('div' + matches);
			var divName = 'div' + matches;
			document.getElementById(divName).innerHTML = url;
			button.style.display = 'none';
		}
	</script>
</body>
</html>