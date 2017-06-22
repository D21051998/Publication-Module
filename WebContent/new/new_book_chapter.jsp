<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book Chapter</title>
<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">

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
<style>
 .container{
  width:100%;
 }
.borderless {
	border-bottom: 0 none;
	border-top: none;
	border-left:0;
	border-right:0;
	background: #d9dfe0;
}
ul {
	list-style: none;
}
#sidebarStyle{
 background: #d9dfe0;
}
</style>
<body>
<!--  #sidebarStyle{
  background-color: black;
 }
  a:link {
    color: white;
}
a:VISITED {
    color: white;
}
 -->
<%
String sid  = (String) request.getSession(false).getAttribute("sid");
if(null==sid){
	response.sendRedirect("../account/access_denied.jsp");
}
System.out.println(sid);
%>
<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container">
 <div class="row">
  <div class="col-md-2" id="sidebarStyle">
  <ul class="list-group">
   <li><a href="" class="list-group-item list-group-item-action borderless">New Book Chapter</a></li>
   <li><a href="new_conf_presentation.jsp" class="list-group-item list-group-item-action borderless">New Conference Presentation</a></li>
   <li><a href="new_conference_proceeding.jsp" class="list-group-item list-group-item-action borderless">New Conference Proceeding</a></li>
   <li><a href="new_journal.jsp" class="list-group-item list-group-item-action borderless">New Journal</a></li>
   <li><a href="new_patents.jsp" class="list-group-item list-group-item-action borderless">New Patents</a></li>
   <li><a href="new_tech_rep.jsp" class="list-group-item list-group-item-action borderless">New Technical Reports</a></li>
   <li><a href="new_book.jsp" class="list-group-item list-group-item-action borderless">New Books</a></li>
   
  </ul>
  </div>
  <div class="col-md-10">
  <form action="../add/add_book_chapter.jsp" method="POST">
		<table>

			<tr>
				<td>Name of Authors in the seq. as mentioned in the Book Chapter</td>
				<td><input type="text" name="nameOauthors" placeholder="Seperate all names with commas"></td>
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
				<td>Chapter No.</td>
				<td><input type="text" name="chapterNo"></td>
			</tr>
			<tr>
				<td>Chapter Title</td>
				<td><input type="text" name="chapterTitle"></td>
			</tr>
			<tr>
				<td>Book Title</td>
				<td><input type="text" name="bookTitle"></td>
			</tr>
			<tr>
				<td>Publisher</td>
				<td><input type="text" name="publisher"></td>
			</tr>
			<tr>
				<td>International/National</td>
				<td><select name="nationality"  id="nationality" class="form-control">
						<option value="International">International</option>
						<option value="National">National</option>
				</select></td>
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
				<td><select class="form-control" name="monthPublished">
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
				<td>Page No.</td>
				<td><input type="text" name="pageNo"></td>
			</tr>
			<tr>
				<td>Book ISBN No.</td>
				<td><input type="text" name="isbn"></td>
			</tr>
			<tr>
				<td>Hyper Link</td>
				<td><input type="text" name="hyperLink"></td>
			</tr>
			<tr>
				<td>Mention if indexed in WOS/Scopus/Google Scholar/Thomson Reuter/Elsevier</td>
				<td>
				 <input type="checkbox" name="indexFlag" value="WOS">WOS<br>
				 <input type="checkbox" name="indexFlag" value="Scopus">Scopus<br>
				 <input type="checkbox" name="indexFlag" value="Google Scholar">Google Scholar<br>
				 <input type="checkbox" name="indexFlag" value="Thomson Reuter">Thomson Reuter<br>
				  <input type="checkbox" name="indexFlag" value="Elsevier">Elsevier<br>
				  <input type="checkbox" name="indexFlag" value="none">Not Indexed at all..<br>
				<td>
			</tr>
			<tr>
				<td>Link for Indexing</td>
				<td><input type="text" name="indexLink">
				  </td>
			</tr>
			
			<tr>
			<input type="hidden" name="writtenBy" value="<%=lao.getUsernameBySessionID(sid)%>"/>
				<td><button  class="btn btn-danger" type="reset">Reset</button></td>
				<td><button class="btn btn-success" type="submit">Submit</button></td>
			</tr>

		</table>
	</form>
  
  </div>
  
  
 </div>
</div>

	

</body>
</html>