<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="Model.*, java.io.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"
        type="text/javascript"></script>
 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
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
	<%
	HealthData obj = new HealthData();
	
	%>
</head>
<body>
	<form class="form-horizontal" action="healthDataUpdateForWeb" method="post">
	<div class="col-md-6">
		<div class="jumbotron">
				<div class="col-md-12">Enter a new Property :-</div>
				<br><br><br>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<label class="control-label">Enter a name :</label>
					</div>
					<div class="col-md-7">
						<input class="form-control" name="tb_new_name" type="text" placeholder="Name...">
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="col-md-12"></div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<label class="control-label">Enter a Description :</label>
					</div>
					<div class="col-md-7">
						<input class="form-control" name="tb_description" type="text" placeholder="Description...">
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="col-md-12"><br></div>
				<br><br><br>
				<div class="col-md-4"></div>
				<div class="col-md-4"><button type="submit" name="btn_new_property" class="btn btn-primary">Create New Property !</button></div>
				<div class="col-md-4"></div>
				
			</div>
			
	</div>
	<div class="col-md-6">	
		<div class="jumbotron">
			<div class="col-md-12">Enter data :-</div>
			<br><br><br>
			<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<label class="control-label">Enter a userid :</label>
					</div>
					<div class="col-md-7">
						<input class="form-control" name="tb_user_id" type="text" placeholder="User Id...">
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="col-md-12"></div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-3">
						<label class="control-label">Property :</label>
					</div>
				<div class="col-md-7">
					<select class="form-control" name="property_selector">
		            	<option value="">------Select------</option>
		                <%String []arr = obj.get_all_properties();
		                  if(arr != null){
		                	  for(int i = 1 ; i <= arr.length ; i++){%>
		                	  	<option value="<%=i%>"><%=arr[i-1]%></option>
		                	  <%} %>
		                  <%} %>
		            </select>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="col-md-12"></div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-3">
					<label class="control-label">Value :</label>
				</div>
				<div class="col-md-7">
					<input class="form-control" name="tb_prop_value" type="text" placeholder="Property Value">
				</div>
				<div class="col-md-1"></div>
			</div>
			<br>
			<div class="col-md-4"></div>
			<div class="col-md-4"><button type="submit" class="btn btn-primary">Enter Value!</button></div>
			<div class="col-md-4"></div>
		</div>
	</div>
	</form>
</body>
</html>