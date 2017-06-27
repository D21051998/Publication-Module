<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Patent</title>

<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">
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

<script>
 function getNation(){
	 var form = document.getElementById("nationality");
	 var val = form.options[form.selectedIndex].value;
		if (val == "National") {
			document.getElementById("country").value = "India";
		}
 }
</script>
</head>
<body>
<jsp:include page="../headers/new_pages_header.jsp"></jsp:include>
<div class="container-fluid">
 <div class="row">
  <div class="col-md-2">
  <jsp:include page="../sidebars/new_pages_sidebar.jsp"></jsp:include>
    </div>
  <div class="col-md-10">
  <h3>Add New Patents</h3>
	<form method="get">
		<table class="form-group">
			<tr>
				<td>Faculty</td>
				<td><input type="text" class="form-control" required="on"
					name="faculty"></td>
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
				<td>Title of Patent</td>
				<td><input type="text" class="form-control" required="on"
					name="title"></td>
			</tr>

			<tr>
				<td>International/National</td>
				<td><select name="nationality" onclick="getNation()" id="nationality" class="form-control">
						<option value="International">International</option>
						<option value="National">National</option>
				</select></td>
			</tr>

			<tr>
				<td>Country</td>
				<td><input type="text" class="form-control" id="country" name="country"></td>
			</tr>
			
			<tr>
			 <td>
			  Patent Application Number
			 </td>
			 <td>
			  <input type="text" name="patent_application" class="form-control">
			 </td>
			</tr>
			<tr>
				<td>Patent Application Year</td>
				<td><select class="form-control" name="application_year">
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
				<td>Patent Application Date</td>
				<td><select class="form-control" name="appilcation_month">
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
				<td>Patent Award Year</td>
				<td><select class="form-control" name="award_year">
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
				<td>Patent Award Date</td>
				<td><select class="form-control" name="awrad_month">
						<%
							months = new String[] { "January", "Feburary", "March", "April", "May", "June", "July", "August",
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
				<td>Patent No.</td>
				<td><input type='text' name="patent_no"></td>
			</tr>
			<tr>
				<td><button type="reset" class="form-control">Reset</button></td>
				<td><button type="submit" class="form-control">Submit</button></td>
			</tr>
		</table>
	</form> 
	</div>
 </div>
</div>
	

</body>
</html>