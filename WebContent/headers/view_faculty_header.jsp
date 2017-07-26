
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">



    <!-- Bootstrap core CSS -->
    <link href="../resources/styles_header/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../resources/styles_header/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../resources/styles_header/custom.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../resources/styles_header/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    
    .container{
    width: 100%;}

        .navbar-default .navbar-brand{
            color:#9e433d;
        }
        .navbar-default{
            background-color:#dddcdb ;
        }
        .navbar-fixed-top {
    min-height: 80px;
}

.navbar-nav > li > a {
    padding-top: 0px;
    padding-bottom: 0px;
    line-height: 80px;
}

@media (max-width: 767px) {
    .navbar-nav > li > a {
    line-height: 20px;
    padding-top: 10px;
    padding-bottom: 10px;}
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
}

h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

h4 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

p {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 18px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

table {
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: break-word;
}

a {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 18px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}
        
.content:before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	display: block;
	background-image: url('DSCN7348.jpg');
	-webkit-filter: brightness(0.8);
	filter: brightness(0.8);
	background-size: cover;
	width: 100%;
	height: 100%;
	-webkit-filter: blur(5px);
	-moz-filter: blur(5px);
	-o-filter: blur(5px);
	-ms-filter: blur(5px);
	filter: blur(5px);
}

.content {
	overflow: visible;
	position: relative;
}
    </style>
  </head>

  <body>
  	<nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid clearfix">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a style="background-color: transparent !important;
            color: #9e433d !important;" class="navbar-brand"><strong>
 <img src="../resources/images/ncu logo.png" width="150px" height="50px" id="logo" />
The NorthCap University</strong></a>
        </div>	
        <div id="navbar" class="navbar-collapse collapse">
        
        <ul class="nav navbar-nav navbar-left">
             <li class="dropdown">
              <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="
            color: #9e433d !important;">Navigate To<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="../rdil_home.jsp">Home</a></li>
		<li><a href="view_book_chapter.jsp">View Book Chapter</a></li>
		<li><a href="view_conf_presentation.jsp">View Conference
				Presentation</a></li>
		<li><a href="view_conference_proceeding.jsp">View
				Conference Proceeding</a></li>
		<li><a href="view_journal.jsp">View Journal</a></li>
		<li><a href="view_patents.jsp">View Patents</a></li>
		<li><a href="view_tech_rep.jsp">View Technical Reports</a></li>
		<li><a href="view_book.jsp">View Books</a></li>
              </ul>
            </li>
            </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown pull-left">
              <a href="#"  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span>&nbsp;Profile<span class="caret"></span></a>
              <ul class="dropdown-menu" id="profile-menu">
                <li><a href="">Edit Profile</a></li>
                <li><a href="../../account/logout.jsp">Logout</a></li>
              </ul>
            </li>	
          </ul>
        </div>
      </div>
    </nav>
      
     <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>
