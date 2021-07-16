<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>Hostel Management System | home</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/welcome" class="navbar-brand">Welcome Page To This Process</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<!-- <li><a href="/login">Login</a></li> -->
					<li><a href="/register">New Registration</a></li>
					<li><a href="/show-users">All Users</a></li>
					<li><a href="/show-Allocated">All Allocated Students</a></li>
					<li><a href="search">reports and searching page</a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${mode=='MODE_HOME' }">
			<div class="container" id="homediv">
				<div class="jumbotron text-center">
					<h1>Welcome to Hostel Management System</h1>
					<h3>Hurry Up and Get Yourself Registered</h3>
				</div>
			</div>

		</c:when>

		<c:when test="${mode=='MODE_REGISTER' }">
			<div class="container text-center">
				<h3>New Registration</h3>
				<h4  style="color:red">${msg }</h4>
				<hr>
				<form class="form-horizontal" method="POST" action="save-user">
					<input type="hidden" name="sid" value="${user.sid }" />
					<div class="form-group">
						<label class="control-label col-md-3">Phone Number</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="phno" value="${user.phno }"  pattern= "(?=.*[0-9]).{10,}"
     									 title="Enter atleast 10 numbers" required/> 
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Student ID</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="stdid"
								value="${user.stdid }" required />
						</div>
						</div>
					<div class="form-group">
						<label class="control-label col-md-3">First Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="sname"
								value="${user.sname }" required  />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Last Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="lname"
								value="${user.lname }" required  />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">year</label>
						<div class="col-md-7">
									 
									<select  class="form-control" name="year">
									<option value="0"> </option>
   									 <option value="1">1</option>
    								<option value="2">2</option>
   								    <option value="3">3</option>
   								     <option value="4">4</option>
   								    
   								     
   								 </select>
   								
							
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Semester</label>
						<div class="col-md-7">
									 
									<select  class="form-control" name="sem">
									 <option value="0"> </option>
   									 <option value="1">1</option>
    								<option value="2">2</option>  								     
   								 </select>
   								</div>
								</div>
					
					<%-- <div class="form-group">
						<label class="control-label col-md-3">Registration date</label>
						<div class="col-md-7">
							<input type="hidden" class="form-control"  name="regdate"
								value="${user.regdate }" required />
								
						</div>
						</div>  --%>
					<%-- <div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-7">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div> --%>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Register" />
					</div>
				</form>
			</div>
		</c:when>
		
		
		<c:when test="${mode=='ALL_USERS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All Users</h3>
				<h4  style="color:red">${msg }</h4>
				<h4  style="color:green">${msg1 }</h4>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Registration Id</th>
								<th>Student Id</th>
								<th>Phone number</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Year</th>
								<th>Sem</th>
								<th>Registered Date</th>
								<th>Delete</th>
								<th>Edit</th>
								<th>Allocate</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users }">
								<tr>
									<td>${user.sid}</td>
									<td>${user.stdid}</td>
									<td>${user.phno}</td>
									<td>${user.sname}</td>
									<td>${user.lname}</td>
									<td>${user.year}</td>
									<td>${user.sem}</td>
									<td>${user.regdate}</td>
									<td><a href="/delete-user?sid=${user.sid }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="/edit-user?sid=${user.sid }"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
   								 
   								 <td>
   								    <div class="form-group ">
   								    <a href="/Allocate-user?sid=${user.sid }&stdid=${user.stdid}&sname=${user.sname}&lname=${user.lname}">
						<input  type="submit" class="btn btn-primary" value="Allocate" />
						</a>
					</div>
   								 </td>
   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_UPDATE' }">
			<div class="container text-center">
				<h3>Update User</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-user">
					<input type="hidden" name="sid" value="${user.sid }" />
					<div class="form-group">
						<label class="control-label col-md-3">phone Number</label>
						<div class="col-md-7">
							<input type="number" class="form-control" name="phno" pattern="[789][0-9]{9}"
								value="${user.phno }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">CollegeId</label>
						<div class="col-md-7">
							<input type="number" class="form-control" name="stdid"
								value="${user.stdid }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Student Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="sname"
								value="${user.sname }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">year</label>
						<div class="col-md-7">
							<input type="number" class="form-control" name="year"
								value="${user.year }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Semister </label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="sem"
								value="${user.sem }" />
						</div>
					</div>
					
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Update" />
					</div>
				</form>
			</div>
		</c:when>
		
				
		<c:when test="${mode=='MODE_Allocation' }">
			<div class="container text-center">
				<h3>Allocating User</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-Allocation">
				   <p>This Allocation is happening for${stdid}whose name is ${sname}-${lname }</p>
				   
					<input type="hidden" name="sid" value="${sid }" />
					<input type="hidden" name="stdid" value="${stdid }" />
					
					
					<div class="form-group">
						<label class="control-label col-md-3">Floor Number</label>
						<div class="col-md-7">
									 
									<select  class="form-control" name="floor">
									<option value="0"> </option>
   									 <option value="1">1</option>
    								<option value="2">2</option>
   								    <option value="3">3</option>
   								     <option value="4">4</option>
   								     <%-- <value = "${allocation.floorno }"> --%>
   								     
   								 </select>
   								
							
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Room Number</label>
						<div class="col-md-7">
									 
									<select name="Room" class="form-control">
									<option value="0"> </option> 
   									 <option value="1">1</option>
    								<option value="2">2</option>
   								    <option value="3">3</option>
   								     <option value="4">4</option>
   								     <option value="5">5</option>
    								<option value="6">6</option>
   								    <option value="7">7</option>
   								     <option value="8">8</option>
   								 </select>
   								 
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="control-label col-md-3">Allocation-Date</label>
						<div class="col-md-7">
							<input type="date" class="form-control" name="allocdate"
								value="${alloc.allocdate }" />
						</div>
					</div> --%>
					<div class="form-group">
						<label class="control-label col-md-3">Status </label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="status"
								value="${alloc.status }" />
						</div>
					</div>
					
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Allocate" />
					</div>
				</form>
			</div>
		</c:when>
		
		<c:when test="${mode=='ALL_ALLOCATED' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All ALLOCATED STUDENTS</h3>
				<h4  style="color:red">${msg }</h4>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>SID</th>
								<th>CollageId</th>
								<th>FLOOR</th>
								<th>ROOM</th>
								<th>ALLOCATION DATE</th>
								<th>DE-ALLOCATION DATE</th>
								<th>STATUS</th>
								<th>Deallocation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="alloc" items="${allocs }">
								<tr>
									<td>${alloc.sid}</td>
									<td>${alloc.stdid}</td>
									<td>${alloc.floor}</td>
									<td>${alloc.room}</td>
									<td>${alloc.allocdate}</td>
									<td>${alloc.deallocdate}</td>
									<td>${alloc.status}</td>
									 <td>
   								    <div class="form-group ">
   								   <%--  <a href="/Deallocating-user?sid=${alloc.sid }&stdid=${alloc.stdid}&room=${alloc.room}&floor=${alloc.floor}&aldate=${alloc.allocdate}">
						<input  type="submit" class="btn btn-primary" value="DeAllocation" /> --%>
						<form action="Deallocating-user">
                                                                                  <input type="hidden" value="${alloc.sid }" name="sid">
                                                                                  <input type="hidden" value="${alloc.stdid }" name="stdid">
                                                                                  <input type="hidden" value="${alloc.room }" name="room">
                                                                                  <input type="hidden" value="${alloc.floor}" name="floor">
                                                                                   <input type="hidden" value="${alloc.allocdate}" name="aldate">
                                                                                  <c:choose>
                                                                                         <c:when test="${alloc.status == 'DeAllocated'}">
                                                                                                <input type="button" value="DeAllocation" class="disabled">
                                                                                                <!-- <input  type="submit" value="DeAllocation" disabled class="not-active"  /> -->
                                                                                         </c:when>
                                                                                         <c:otherwise>
                                                                                                <input type="submit" value="DeAllocation"
                                                                                                       class="btn btn-primary" />
                                                                                         </c:otherwise>
                                                                                  </c:choose>
                                                                           </form>
						
						</a>
					</div>
   								 </td>   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		
		
		<c:when test="${mode=='REPORTS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>Search Options</h3>
				<h4  style="color:red">${msg }</h4>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<tbody>
						
								<tr>
								
									
   								 <td>
   								    <div class="form-group ">
   								    <a href="/search-by-room">
						<input  type="submit" class="btn btn-primary" value="search" />
						</a>
					</div>
   								 </td>
   								<!--  <td>
   								    <div class="form-group ">
   								    <a href="/search-by-floor ">
						<input  type="submit" class="btn btn-primary" value="Floor search" />
						</a>
					</div>
   								 </td> -->
   								 <td>
   								    <div class="form-group ">
   								    <a href="/download">
						<input  type="submit" class="btn btn-primary" value="Report of Allocated" />
						</a>
					</div>
   								 </td>
   								  <td>
   								    <div class="form-group ">
   								    <a href="/download1 ">
						<input  type="submit" class="btn btn-primary" value="Report of Not Allocated" />
						</a>
					</div>
   								 </td> 
   								
								</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode=='SEARCH_ROOM' }">
			<div class="container text-center">
				<h3>Search On Room Basis</h3>
				
				<hr>
				<%-- <form class="form-horizontal" method="POST" action="search-Room">
					
					<div class="form-group">
						<label class="control-label col-md-3">Floor</label>
						<div class="col-md-7">
									 
									<select  class="form-control" name="floor">
									 <option value="0"> </option>
   									 <option value="1">1</option>
    								<option value="2">2</option>
   								    <option value="3">3</option>
   								     <option value="4">4</option>
   								    
   								     
   								 </select>
   								
							
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Room</label>
						<div class="col-md-7">
									 
						      		<select  class="form-control" name="room">
						      		<option value="0">  </option>
   									 <option value="1">1</option>
    								<option value="2">2</option> 
    								<option value="3">3</option>
    								<option value="4">4</option>
   								    <option value="5">5</option>
   								     <option value="6">6</option> 
   								      <option value="7">7</option>
   								     <option value="8">8</option>								     
   								 </select>
   								 </div>
								</div> 
   								 <input type="number" name="room" value="${alloc.room }"/>
   								 <div class="form-group">
						<label class="control-label col-md-3">Registration ID </label>
						<div class="col-md-3">
							<input type="text" name="sidTemp" class="form-control"  />
						</div>
					</div>
   								 
   								
								<input type="hidden" name="status" value="Allocated"/>
					
							<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="search" />
					</div> --%>
					<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>SID</th>
								<th>CollageId</th>
								<th>FLOOR</th>
								<th>ROOM</th>
								<th>ALLOCATION DATE</th>
								<th>DE-ALLOCATION DATE</th>
								<th>STATUS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="alloc" items="${allocs }">
								<tr>
									<td>${alloc.sid}</td>
									<td>${alloc.stdid}</td>
									<td>${alloc.floor}</td>
									<td>${alloc.room}</td>
									<td>${alloc.allocdate}</td>
									<td>${alloc.deallocdate}</td>
									<td>${alloc.status}</td>   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</form>
			</div>
		</c:when>
       <c:when test="${mode=='SEARCH_FLOOR' }">
			<div class="container text-center">
				<h3>Search On Floor Basis</h3>
				
				<hr>
				<form class="form-horizontal" method="POST" action="search-floor">
				
					<div class="form-group">
						<label class="control-label col-md-3">Floor</label>
						<div class="col-md-7">
									 
									<select  class="form-control" name="floor">
   									 <option value="1">1</option>
    								<option value="2">2</option>
   								    <option value="3">3</option>
   								     <option value="4">4</option>
   								    
   								     
   								 </select>
   								
							
						</div>
					</div>
								<input type="hidden" name="status" value="Allocated"/>
							<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="search" />
					</div>
					<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>SID</th>
								<th>CollageId</th>
								<th>FLOOR</th>
								<th>ROOM</th>
								<th>ALLOCATION DATE</th>
								<th>DE-ALLOCATION DATE</th>
								<th>STATUS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="alloc" items="${allocs }">
								<tr>
									<td>${alloc.sid}</td>
									<td>${alloc.stdid}</td>
									<td>${alloc.floor}</td>
									<td>${alloc.room}</td>
									<td>${alloc.allocdate}</td>
									<td>${alloc.deallocdate}</td>
									<td>${alloc.status}</td>   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</form>
			</div>
		</c:when>
		 <c:when test="${mode=='SEARCH_SID' }">
			<div class="container text-center">
				<h3>Search On SID Basis</h3>
				
				<hr>
				<form class="form-horizontal" method="POST" action="search-sid">
					
					
					
					
					<div class="form-group">
						<label class="control-label col-md-3">Registration Id</label>
						<div class="col-md-7">
									 
								<input type="text" class="form-control" name="sid" value="${alloc.sid}"/ >
   								<input type="hidden" name="status" value="Allocated"/>
							
						</div>
					</div>
								
							<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="search" />
					</div>
					<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>SID</th>
								<th>CollageId</th>
								<th>FLOOR</th>
								<th>ROOM</th>
								<th>ALLOCATION DATE</th>
								<th>DE-ALLOCATION DATE</th>
								<th>STATUS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="alloc" items="${allocs }">
								<tr>
									<td>${alloc.sid}</td>
									<td>${alloc.stdid}</td>
									<td>${alloc.floor}</td>
									<td>${alloc.room}</td>
									<td>${alloc.allocdate}</td>
									<td>${alloc.deallocdate}</td>
									<td>${alloc.status}</td>   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</form>
			</div>
		</c:when>


		<c:when test="${mode=='MODE_LOGIN' }">
			<div class="container text-center">
				<h3>User Login</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="/login-user">
					<c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
							</div>
					</c:if>
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-7">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Login" />
					</div>
					</form>
					</div>
					</c:when>
					
		<c:when test="${mode=='MODE_DEALLOC' }">
					 
               <form  class="form-horizontal" method="POST" action="save-Deallocation">
              <div class="form-group">
						<label class="control-label col-md-3">Registration Number</label>
						<div class="col-md-7">
							<input type="number" class="form-control" name="sid"
								value="${alloc.sid }" />
						</div>
					 	<input type="hidden" name="stdid" value="${alloc.stdid }" />
                     <input type="hidden" name="floor" value="${alloc.floor }" />
                    <input type="hidden" name="room" value="${alloc.room }" />
                    <input type="hidden" name="allocdate" value="${allocdate }" />   
						<div class="form-group ">
							<input type="submit" class="btn btn-primary" value="De-Allocate" />
					</div> 
					</div>
					
</form>
</c:when> 

<c:when test="${mode=='ALL_ALLOCATED_DETAILS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All Users</h3>
				<h4  style="color:red">${msg }</h4>
				<hr>
				<form class="form-horizontal" method="POST" action="show-Details">
				<input type="text" class="form-control" name="status" value="${allocation.status}"/ >
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="search" />
					</div>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Registration Id</th>
								<th>Student Id</th>
								<th>Room</th>
								<th>floor</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Year</th>
								<th>Sem</th>
								<th>Registered Date</th>
								<th>status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users }">
								<tr>
									<td>${user.sid}</td>
									<td>${user.stdid}</td>
									 <td>${user.allocation.room}</td>
									<td>${user.allocation.floor}</td> 
									<td>${user.sname}</td>
									<td>${user.lname}</td>
									<td>${user.year}</td>
									<td>${user.sem}</td>
									<td>${user.regdate}</td>
									
   								 
   								  <td>${user.allocation.status}</td> 
   								 
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</form>
				</div>
			
			
		</c:when>
		<c:when test="${mode=='MODE_SUCCESS' }">
			<div class="container" id="homediv">
				<div class="jumbotron text-center">
					<h1>Registration was successful for the student ${user.stdid} </h1>
					<h3>His registration number is ${user.sid} Whose name is ${user.sname}-${user.lname} </h3>
				</div>
			</div>

		</c:when>
					
					
					
					
	</c:choose>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>