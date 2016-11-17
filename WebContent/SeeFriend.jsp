<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="Model.*, java.io.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart Health</title>
  <nav class="navbar navbar-default" role="navigation" style="background-color:#87CEFA">
	    <div class="container-fluid">
	        <!-- Brand and toggle get grouped for better mobile display -->
	        <div class="navbar-header">
	            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a style="font-weight:bolder; font-size:x-large" class="navbar-brand" href="Home.jsp">Smart Health</a>
	        </div>
	        <!-- Collect the nav links, forms, and other content for toggling -->
	        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <ul class="nav navbar-nav">
	               <%-- <li class="active"><a href="#">About</a></li>--%><%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
	            </ul>
	            <a class="navbar-brand" href="#">
	            <div class="col-md-offset-4 navbar-header col-md-4">
	            </a>
	                    </div>
	        <!-- /.navbar-collapse -->
	    </div>
	    <!-- /.container-fluid -->
	</nav>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 750px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;}
    }
  </style>
  
  <%LoginDetails cont = (LoginDetails)session.getAttribute("LoggerObj"); 
	  if(cont == null){
		  String url = request.getRequestURL().toString();
		  url = url.substring(0, url.lastIndexOf('/')) + "/index.jsp";
		  response.setStatus(response.SC_FORBIDDEN);
		  response.sendRedirect(url);
		  return;
	  }
	%>
</head>


<body>
	<div class="container-fluid">
	  <div class="row content">
	    <div class="col-md-3 sidenav">
	      <h4>Smart Health</h4>
	      <ul class="nav nav-pills nav-stacked">
	        <li><a href="Home.jsp">Home</a></li>
	        <li><a href="Deactivate.jsp">Deactivate Account</a></li>
	        <li><a href="Update.jsp">Update</a></li>
	         <%
        	if(cont.get_user_type().equals("EndUser")){%>
		        <li><a href="SendFriendRequest.jsp">Send Request</a></li>
		        <li><a href="SeeFriendRequest.jsp">See Request</a></li>
		        <li class="active"><a href="SeeFriend.jsp">See Friends</a></li>
		        <li><a href="AllForums.jsp">All Forums</a></li>
		        <li><a href="HealthData.jsp">Health Data</a></li>
		    <%}%>
	        <li><a href="Logout.jsp">Logout</a></li>
	      </ul><br>
	    </div>
	
	    <div class="col-md-9">
	      
	      <h2><font color="Blue">See All Friends</font></h2>
	      <hr>
	      <br>
	      <form id="friend_req_form" action="seeFriendWeb" method="post" class="jumbotron">
	      	<div class="row">
	      		<div class="col-md-4"><p><u>Username</u></p></div>
	      		<div class="col-md-4"></div>
	      	</div>
	      	<%
	      		if(!cont.get_user_type().equals("EndUser")){
	      			
	      		}
	      		else{
	      			FriendRequest fr = new FriendRequest();
	      			session.setAttribute("RequestAttrib", fr);
	      			String []friends = fr.get_all_friends_list(cont.get_user_name());
	      			for(int j = 0 ; friends != null && j < friends.length ; j++){
	      			%>
	      				<div class="row">
		      				<div class="col-md-6">
		      					<p><%=friends[j]%></p>
		      				</div>
		      				<div class="col-md-6">
		      					<button type="submit" name="<%=j%>"  class="btn btn-danger">Un-friend</button>
		      				</div>
	      				</div>
	      		<%
	      			}
	      		}
	      		%>
	      			
	      </form>
	      <br><br>
	    </div>
	  </div>
	</div>
</body>
</html>