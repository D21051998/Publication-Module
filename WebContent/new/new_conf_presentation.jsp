<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">
<title>Add Conf. Presentations</title>
<style>
 .container{
  width:100%;
 }

li.borderless {
  border-bottom: 0 none;
  border-top: none; 
}

ul{
 list-style:none;
}
 
</style>

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container">
 <div class="row">
  <div class="col-md-1"></div>
  <div class="col-md-2" id="sidebarStyle">
  <ul class="list-group">
   <li><a href="new_book_chapter.jsp" class="list-group-item">New Book Chapter</a></li>
   <li><a href="new_conf_presentation.jsp" class="list-group-item list-group-item-action active">New Conference Presentation</a></li>
   <li><a href="new_conference_proceeding.jsp" class="list-group-item list-group-item-action">New Conference Proceeding</a></li>
   <li><a href="new_journal.jsp" class="list-group-item list-group-item-action">New Journal</a></li>
   <li><a href="new_patents.jsp" class="list-group-item list-group-item-action">New Patents</a></li>
   <li><a href="new_tech_rep.jsp" class="list-group-item list-group-item-action">New Technical Reports</a></li>
   <li><a href="new_book.jsp" class="list-group-item list-group-item-action">New Books</a></li>
   
  </ul>
  </div>
  <div class="col-md-7">

	<form>
		<table>
			<tr>
				<td>Faculty</td>
				<td><input type="text" name="nameOauthors"></td>
			</tr>
			<tr>
				<td>Deptt.</td>
				<td><select class="form-control" name="deptt">
						<option value="cse">CSE</option>
						<option value="ece">ECE</option>
						<option value="me">ME</option>
						<option value="cvu">CVU</option>
				</select></td>
			</tr>
			<tr>
				<td>Title of Paper</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>Conference Presentation</td>
				<td><input type="text" name="presentation"></td>
			</tr>
			<tr>
				<td>International/National</td>
				<td><select name="nationality" id="nationality"
					class="form-control">
						<option value="International">International</option>
						<option value="National">National</option>
				</select></td>
			</tr>
			<tr>
				<td>Organised by</td>
				<td><input type="text" name="organised_by"></td>
			</tr>
			<tr>
				<td>Venue Details for Conference</td>
				<td><input type="text" name="venue_details"></td>
			</tr>
			<tr>
				<td>Year</td>
				<td><select class="form-control" name="year">
						<%
							for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1980; i--) {
						%>
						<option value="<%=i%>"><%=i%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Month in which published</td>
				<td><select class="form-control" name="month">
						<%
							String[] months = new String[] { "January", "Feburary", "March", "April", "May", "June", "July", "August",
									"September", "October", "November", "December" };
							for (int i = 0; i < months.length; i++) {
						%>
						<option value="<%=months[i]%>"><%=months[i]%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Hyper Link</td>
				<td><input type="text" name="link"></td>
			</tr>
			<tr>
				<td><button type="reset">Reset</button></td>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>

	</form>
 </div>
  <div class="col-md-1"></div>
  
 </div>
</div>

</body>
</html>