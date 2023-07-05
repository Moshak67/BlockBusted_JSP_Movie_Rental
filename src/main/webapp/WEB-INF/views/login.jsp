<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Login Page</title>

</head>
<body background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
<jsp:include page="navbar.jsp" />
	<div class="container">
	<c:choose>
		<c:when test="${not empty sessionScope.user }">
		
		<h1>You are already Logged in</h1>
		</c:when>
		<c:otherwise>
		<h1>User Login Form:</h1>
		<div class="card">
			<div class="card-body">
				<form action="proccessLogin" method="POST">

					<div class="form-group row">
						<label for="username" class="col-sm-2 col-form-label">Username</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="username"
								placeholder="Enter Username">
						</div>
					</div>
					<div class="form-group row">
						<label for="password" class="col-sm-2 col-form-label">Password</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" name="password"
								placeholder="Enter password">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>

				</form>
			</div>
		</div>
	</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty requestScope.invalidLogin }">
			<h1>${ invalidLogin }</h1>
		</c:when>
		
	</c:choose>
	</div>
</body>
</html>