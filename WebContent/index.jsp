<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</head>
<body>
<!--  <form action="loginForWeb" method="post">
Email id : <input type="email" name="email"/><br/>
Password : <input type="password" name="password"/><br/>
<input type="submit" text="Login !"/>
</form>-->

<div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 container">
                <br /><br /><br /><br /><br /><br />
                <form id="main_form" action="loginForWeb" method="post" class="jumbotron">
                
                <!-- Modal not logged in  -->
                <div class="modal fade" id="modal_login_fail" tabindex="-1" role="dialog" aria-labelledby="myModal-label" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
	                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                                <h4 class="modal-title" id="myModal-label">Error !</h4>
                            	</div>
                            	<div class="modal-body">
                            		<p>Wrong login credentials !</p>
                            	</div>
                            	<div class="modal-footer">
                                	<button data-dismiss="modal" class="btn btn-primary" name="btn_register" Text="OK">OK</button>
                            	</div>
                            </div>
                        </div>
                 </div>
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal-label" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
	                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                                <h4 class="modal-title" id="myModal-label">Sign Up</h4>
                            	</div>
                            	<div class="modal-body">
                                	<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="UserId" style="padding-top:8px">User ID:</label>
                                		<div class="col-md-10"><input type="text"  class="form-control" name="tb_user_id" placeholder="Enter UserId" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Password" style="padding-top:8px">Password:</label>
                                		<div class="col-md-10"><input type="password"  class="form-control" name="tb_pwd" placeholder="Enter Password"/></div>
                                		<span class="glyphicon form-control-feedback"></span>
                             		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Confirm Password">Re-Password:</label>
                                		<div class="col-md-10"><input type="password"  class="form-control" name="tb_pwd_c" placeholder="Confirm Password" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Name">First Name:</label>
                                		<div class="col-md-10"><input type="text" class="form-control" name="tb_first_name" placeholder="First Name"/></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Name">Last Name:</label>
                                		<div class="col-md-10"><input type="text" class="form-control" name="tb_last_name" placeholder="Last Name"/></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Email" style="padding-top:8px">Email ID:</label>
                                		<div class="col-md-10"><input type="email" class="form-control" name="tb_email_signup" placeholder="Enter email" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Email">Other Email ID:</label>
                                		<div class="col-md-10"><input type="email" class="form-control" name="tb_email_signup_sec" placeholder="Enter email" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Address">Postal Address:</label>
                                		<div class="col-md-10"><input type="text" class="form-control" name="tb_address" placeholder="Enter address" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="Phone">Phone Number:</label>
                                		<div class="col-md-10"><input type="text" class="form-control" name="tb_emergency_contact" placeholder="Enter Phone" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="User Type">User Type:</label>
                                		<div class="col-md-10">
	                                		<select class="form-control" name="user_type_selector">
	                                			<option value="">------Select------</option>
	                                			<option value="EndUser">EndUser</option>
  												<option value="Moderator">Moderator</option>
  												<option value="Administrator">Administrator</option>
	                                		</select>
                                		</div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            		<div class="form-group has-feedback">
                                		<label class="control-label col-md-2" for="About">Introduce Yourself:</label>
                                		<div class="col-md-10"><input type="text" class="form-control" name="tb_about" placeholder="A line about you :)" /></div>
                                		<span class="glyphicon form-control-feedback"></span>
                            		</div>
                            		<br /><br />
                            	</div>
                            	<div class="modal-footer">
                                	<input type="submit" class="btn btn-primary" name="btn_register" Text="Register"/>
                            	</div>
                        	</div>
                    	</div>
                	</div>
                <!-- End Modal -->
                
                	<div class="container">
                        <div class="alert alert-success hidden" id="success-alert">
                            <h2>Success !</h2>
                        </div>
                    </div>

                     <div class="form-group">
                        <label class="control-label col-md-2" for="Email" style="padding-top:8px">Email ID:</label>
                        <div class="col-md-10"><input type="text"  name="email" class="form-control" id="tb_uid" placeholder="Enter Email Id"/></div>
                    </div>
                    <br /><br />
                    <div class="form-group">
                        <label class="control-label col-md-2" for="Email" style="padding-top:8px">Password:</label>
                        <div class="col-md-10"><input type="password" name="password" class="form-control" id="tb_password" placeholder="Enter Password" /></div>
                    </div>
                    <br /><br />
                    <div class="form-group">
                        <div class="col-md-2"></div>
                        <div class="checkbox col-md-4"><label><input type="checkbox"/>Remember Me</label></div>
                        <div class="col-md-7"></div>
                    </div>
                    <br /><br />
                    <div class="form-group">
                        <div class="col-md-3"></div>
                        <div class="col-md-2"><button type="submit" name="btn_signin"  class="btn btn-success">Sign In !</button></div>
                        <div class="col-md-2"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Sign Up !</button></div>
                        <div class="col-md-2"><button type="button" class="btn btn-primary" onclick="RedirectToHealthDataPage()">Health Data</button></div>
                    	<div class="col-md-3"></div>
                    	<button type="button"  style="visibility:hidden;" id="btn_problem_hidden" class="btn btn-info" data-toggle="modal" data-target="#modal_login_fail" onClick="display_failure_modal()">Sign Up !</button>
                    </div>
                    <br />
                </form>
            </div>
            <div class="col-md-3"></div>
       </div>
       <script>
        function success() {
            $('#myModal').modal('hide');
            $('#success-alert').removeClass('hidden');
        }
        
        function display_failure_modal(){
        	alert('works');
        	$('#modal_login_fail').modal('show');
        }
        
        function RedirectToHealthDataPage(){
        	var loc = window.location.toString();
        	var redirect_URL = loc.substring(0, loc.lastIndexOf('/'));
        	window.location = redirect_URL + "/InsertHealthData.jsp"
        }
    </script>
</body>
</html>