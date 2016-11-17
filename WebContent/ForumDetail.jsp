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
	  if((String)session.getAttribute("Forum") == null){
		  String url = request.getRequestURL().toString();
		  url = url.substring(0, url.lastIndexOf('/')) + "/AllForums.jsp";
		  response.setStatus(response.SC_FORBIDDEN);
		  response.sendRedirect(url);
		  return;
	  }
	  int f_id = Integer.parseInt((String)session.getAttribute("Forum"));
	  String[] login_data = cont.get_forum_data_for_display(f_id);
	  String[][] all_post = cont.fetch_all_posts(f_id);
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
		        <li><a href="SeeFriend.jsp">See Friends</a></li>
		        <li class="active"><a href="AllForums.jsp">All Forums</a></li>
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
	      
	      
	      <div class="col-md-12"><h2><font color="Blue">Forum <u><%=login_data[0]%></u></font></h2></div>
	      <hr>
      	  <br>
      	  <div class="col-md-12"><%=login_data[1]%></div>
      	  <br>
	      <form class="form-horizontal" action="forumDetailWeb" method="post">
		      <div class="jumbotron col-md-12">
			  <!-- insert a post here -->
			  <div class="row">
				  <div class="col-md-10">
				  	<div>
				  		<textarea style='resize: none;' rows="5" class="form-control" name="tb_new_post" type="text" placeholder="Post..."></textarea>
				  	</div>
				  </div>
				  <div class="col-md-2">
				  	<button type="submit" class="btn btn-success" name="btn_new_post">Post</button>
				  </div>
				  <div class="col-md-12"><br><br></div>
			  </div>
			      <%if(all_post != null){ 
			    	  String [][]comments;
			      	 int it, j;
			    	  for(it = 0 ; it < all_post.length ; it++){%>
			      	 	<!-- Id\tUsername\tTime Created\tText Entry -->
					 	<div class="panel container">
					 		<div class="col-md-12"><u><%=all_post[it][0]%></u> <%=all_post[it][1]%></div>
					 		<%
					 		if(cont.if_rating_by_user_exists_on(all_post[it][0], all_post[it][1])){
					 			/*if it is then show the average rating*/
					 			double rating = cont.get_rating_for_post(all_post[it][0], all_post[it][1]);
					 			%>
					 			<div class="col-md-12">Rating : <%=rating%></div>
					 		<%}
					 		else{
					 			/*get this post rated by the user. Make a button here. Implement 
					 			this in the servlet also*/
					 			%>
					 			<div class="col-md-12">
					 				<div class="col-md-2">
						 				<select class="form-control" name="rating_selector<%=it%>">
						 					<option value="0">0</option>
	                                		<option value="1">1</option>
  											<option value="2">2</option>
  											<option value="3">3</option>
  											<option value="4">4</option>
  											<option value="5">5</option>
						 				</select>
					 				</div>
					 				<div class="col-md-2"><button type="submit" name="btn_rating_save<%=it%>"  class="btn btn-info">Rate</button></div>
					 				<div class="col-md-8"></div>
					 			</div>
					 		<%}
					 		%>
					 		<div class="col-md-12">
					 			<span>
					 				<h4><%=all_post[it][3] %></h4>
					 			</span>
					 		</div>
					 	</div>
					 	<%comments = cont.get_comments_for_post(it);
					 	  if(comments != null){%>
					 	  	  <div class="row">
					 	  	  	  <div class="col-md-2"></div>
					 	  	  	  <div class="col-md-10">
							 	  	  <div class="panel container">
								 		  	<%for(j = 0 ; j < comments.length ; j++){ %>
									 	  		<div>
									 	  			<div class="col-md-12">
									 	  				<u><%=comments[j][0] %></u> <%=comments[j][1]%>
									 	  			</div>
									 	  			<div class="col-md-12">
									 	  				<%=comments[j][2] %>
									 	  			</div>
									 	  			<div class="col-md-12"><br></div>
									 	  		</div>
								 	  		<%}%>
								 	  		<div class="col-md-10">
								 	  			<input class="form-control" name="tb_new_comment<%=it%>" type="text" placeholder="Comment...">
								 	  		</div>
								 	  		<div class="col-md-2">
								 	  			<button type="submit" name="btn_new_comment<%=it%>" class="btn btn-info">Comment</button>
								 	  		</div>
								 	  		<div class="col-md-12"><br></div>
							 	  	  </div>
						 	  	  </div>
					 	  	  </div>
					 	  <%}else{%>
					 	  <div class="row">
						 	  <div class="col-md-2"></div>
						 	  <div class="col-md-10">
							   	  <div class="panel container">
							   	  <div class="col-md-12"><br></div>
							   	  	<div class="col-md-10">
							 	  		<input class="form-control" name="tb_new_comment<%=it%>" type="text" placeholder="Comment...">
							 	  	</div>
							 	  	<div class="col-md-2">
							 	  		<button type="submit" name="btn_new_comment<%=it%>" class="btn btn-info">Comment</button>
							 	  	</div>
							 	  	<div class="col-md-12"><br></div>
							  	  </div>
							 </div>
						 </div>
					 	  <%}%>
					 <%}%>
			      <%}%>
	      	  </div>
      	</form>
	    </div>
	  </div>
	</div>
</body>
</html>


