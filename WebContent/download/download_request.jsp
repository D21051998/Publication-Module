<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="../DownloadResponse" method="get">
<table>
<tr>
<td>
From
</td>
<td>
<input type="date" required="true" name="from">
</td>
</tr>
<tr>
<td>
TO
</td>
<td>
<input type="date" required="true" name="to">
</td>
</tr>
<tr>
<td>
Source
</td>
<td>
<input type="checkbox"  name="source" value="bookChapter">Book Chapter<br>
<input type="checkbox"  name ="source" value="journal">Journal
</td>
</tr>
<tr>
<td>
Branch
</td>
<td>
<input type="checkbox"  name="branch" value="CSE">CSE<br>
<input type="checkbox"  name="branch" value="ME">ME<br>
<input type="checkbox"  name="branch" value="ECE">ECE
</td>
</tr>
<tr>
<td>
<button type="reset">Reset</button>
</td>
<td>
<button type="submit">Download</button>
</td>
</tr>
</table>
</form>
</body>
</html>