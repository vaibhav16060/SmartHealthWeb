<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="Model.*, java.io.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
	        <%if(!cont.get_user_type().equals("EndUser")){%>
		        <li class="active"><a href="ForumAdmin.jsp">Forum Admin Page</a></li>
       		 <%}%>
	        <li><a href="Logout.jsp">Logout</a></li>
	      </ul><br>
	    </div>
	
	    <div class="col-md-9">
      
      <h2><font color="Blue">Forum Administration Page</font></h2>
      <hr>
      <br>
      <p>Create Forum</p>
      <br><br>
      <form class="form-horizontal" action="forumAdminForWeb" method="post">
        <div class="form-group">
	        <label class="col-md-3 control-label">Enter new Forum Name : </label>
	        <div class="col-md-7">
	            <input class="form-control" name="tb_forum_name" type="text" placeholder="Forum Name">
	        </div>
	        <div class="col-md-2"></div>
	        <div class="col-md-12"></div>
	        <label class="col-md-3 control-label">Enter a short description : </label>
	        <div class="col-md-7">
	            <input class="form-control" name="tb_forum_desc" type="text" placeholder="Forum Description">
	        </div>
	        <div class="col-md-2"></div>
        </div>
      	<br><br>
      	<div class="col-md-4"></div>
      	<div class="col-md-2">
			<button type="submit" name="btn_create_forum" class="btn btn-primary">Create Forum</button>
      	</div>
      	<div class="col-md-6"></div>
      	
      	<div class="col-md-12"><br><br><br><br></div>
      	
      	<div class="jumbotron col-md-12">
      		<div class="col-md-12"><p>All Forums</p></div>
      		<div class="row">
      			<div class="col-md-6"><u>Topic</u></div>
      			<div class="col-md-6"></div>
      			<div class="col-md-12"><br></div>
      		</div>
      		<%String[][] all_forums = cont.get_all_forum_list();
      		  if(all_forums != null){
      			  for(int ol = 0 ; ol < all_forums.length ; ol++){%>
      				 <!-- id, name, desc -->
      				 <div class="row">
						<div class="col-md-6"><%=all_forums[ol][1]%></div>
						<div class="col-md-6"><button type="submit" name=<%=all_forums[ol][0]%> class="btn btn-danger">Delete !</button></div>
					 	<div class="col-md-12"></div>
					 </div>
				  <%}%>
			<%}%>
      	</div>
      	
      </form>
      <div class="col-md-6"></div>
    </div>
	  </div>
	</div>
</body>
</html>


