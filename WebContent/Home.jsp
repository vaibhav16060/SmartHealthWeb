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
	  String alter = "No_image_available.png";
	  String []images = cont.get_image_urls();
	  if(images[0].equals(cont.get_user_name() + "\\")){
		  images[0] = alter;
	  }
	  if(images[1].equals((cont.get_user_name() + "\\"))){
		  images[1] = alter;
	  }
	  if(images[2].equals((cont.get_user_name() + "\\"))){
		  images[2] = alter;
	  }
	  //String UPLOAD_DIRECTORY = "C:\\Users\\Vaibhav\\workspace\\SmartHealthWeb\\WebContent\\uploads\\";
	%>
</head>
<body>
	
	<div class="container-fluid">
	  <div class="row content">
	    <div class="col-md-3 sidenav">
	      <h4>Smart Health</h4>
	      <ul class="nav nav-pills nav-stacked">
	        <li class="active"><a href="Home.jsp">Home</a></li>
	        <li><a href="Deactivate.jsp">Deactivate Account</a></li>
	        <li><a href="Update.jsp">Update</a></li>
	        <%
        	if(cont.get_user_type().equals("EndUser")){%>
		        <li><a href="SendFriendRequest.jsp">Send Request</a></li>
		        <li><a href="SeeFriendRequest.jsp">See Request</a></li>
		        <li><a href="SeeFriend.jsp">See Friends</a></li>
		        <li><a href="AllForums.jsp">All Forums</a></li>
		        <li><a href="HealthData.jsp">Health Data</a></li>
       		 <%}%>
       		 <%
        	if(!cont.get_user_type().equals("EndUser")){%>
		        <li><a href="ForumAdmin.jsp">Forum Admin Page</a></li>
       		 <%}%>
	        <li><a href="Logout.jsp">Logout</a></li>
	      </ul><br>
	    </div>
	
	    <div class="col-md-9">
	      
	      
	      <div class="col-md-12"><h2><font color="Blue">Welcome to Smart Health</font></h2></div>
	      <hr>
      	  <br>
	      <div class="col-md-12">
	   	      <p>
	   	      	Hello <%= cont.get_name() %> !
	   	      </p>   	
	      </div>
	      <form class="form-horizontal" action="imageUploadForWeb" method="post" enctype="multipart/form-data">
		      <div class="col-md-12">
		      
		      	<div class="col-md-6">
		      		<img src="<%=images[0]%>" alt="No_image_available.png" style="width:200px;height:200px;">
		      	</div>
		      	<div class="col-md-6">
		      		<input class="form-control" type="file" name="image1"  placeholder="Image 1..." accept="image/*">
  					<div class="col-md-12"><br></div>
  					<button  type="submit" class="btn btn-info" name="btn_image_1">Upload !</button>
		      	</div>	
		      </div>
		   </form>
		      <div class="col-md-12"><br></div>
		    <form class="form-horizontal" action="imageUploadForWeb2" method="post" enctype="multipart/form-data">   
		      <div class="col-md-12">
		      	<div class="col-md-6">
		      		<img src="<%=images[1]%>" alt="No_image_available.png" style="width:200px;height:200px;">
		      	</div>
		      	<div class="col-md-6">
		      		<input class="form-control" type="file" name="image2"  placeholder="Image 2..." accept="image/*">
  					<div class="col-md-12"><br></div>
  					<button type="submit" class="btn btn-info" name="btn_image_2">Upload !</button>
		      	</div>	
		      </div>
		    </form>
		      <div class="col-md-12"><br></div>
		     <form class="form-horizontal" action="imageUploadForWeb3" method="post" enctype="multipart/form-data">  
		      <div class="col-md-12">
		      	<div class="col-md-6">
		      		<img src="<%=images[2]%>" alt="No_image_available.png" style="width:200px;height:200px;">
		      	</div>
		      	<div class="col-md-6">
		      		<input class="form-control" type="file" name="image3"  placeholder="Image 3..." accept="image/*">
  					<div class="col-md-12"><br></div>
  					<button type="submit" class="btn btn-info" name="btn_image_3">Upload !</button>
		      	</div>	
		      </div>
	         </form>
	    </div>
	  </div>
	</div>
</body>
</html>


