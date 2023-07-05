<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
<jsp:include page="navbar.jsp" />
<div class="container">
	<c:choose>
		<c:when test="${not empty sessionScope.user }">
		
		<h1>Update User Form:</h1>
		<div class="card">
			<div class="card-body">
				<sf:form action="/userList/pp/${requestScope.user.id }" method="POST" modelAttribute="user">
				
					<div class="form-group row">
						<sf:label path="username" for="username" class="col-sm-2 col-form-label">Username</sf:label>
						
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="username"
								placeholder="Enter Username" />
								<sf:errors path="username" cssClass="error" />
								
						</div>
					</div>

					<div class="form-group row">
						<sf:label path="fName" class="col-sm-2 col-form-label">First
							Name</sf:label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="fName"
								placeholder="Enter First Name"/>
								<sf:errors path="fName" cssClass="error" />
						</div>
					</div>

					<div class="form-group row">
						<sf:label path="lName" class="col-sm-2 col-form-label">Last
							Name</sf:label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="lName"
								placeholder="Enter Last Name"/>
								<sf:errors path="lName" cssClass="error" />
						</div>
					</div>

					<div class="form-group row">
						<sf:label path="password" class="col-sm-2 col-form-label">Password</sf:label>
						<div class="col-sm-7">
							<sf:input type="password" class="form-control" path="password"
								placeholder="Enter password"/>
								<sf:errors path="password" cssClass="error" />
						</div>
					</div>

					<div class="form-group row">
						<sf:label path="email" class="col-sm-2 col-form-label">Email</sf:label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="email"
								placeholder="Enter email"/>
								<sf:errors path="email" cssClass="error" />
						</div>
					</div>
					
						<button type="submit" class="btn btn-primary">Update</button>
						
				</sf:form>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<h1>You need to be logged in to update a user!</h1>
		</c:otherwise>
	</c:choose>
		
	</div>

	
</body>
</html>