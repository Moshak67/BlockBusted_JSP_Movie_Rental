<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Check User Page</title>
</head>
<body
	background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
	<jsp:include page="navbar.jsp" />
	<div class="container">

		<h1>Hello ${ sessionScope.user.username } Welcome!</h1>
		<c:choose>
			<c:when test="${ sessionScope.user.role == 'admin' }">
				<h1>Here is the list of users in the system:</h1>
				<table class="table table-dark">
					<thead>
						<tr>
							<th scope="col">User ID</th>
							<th scope="col">Username</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Password</th>
							<th scope="col">Email</th>
							<th scope="col">Role</th>
							<th scope="col">Customer Rentals</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ userList }" var="user">
							<tr>
								<td>${ user.id }</td>
								<td>${ user.username }</td>
								<td>${ user.fName }</td>
								<td>${ user.lName }</td>
								<td>${ user.password }</td>
								<td>${ user.email }</td>
								<td>${ user.role }</td>
								<td>${ user.rentals.size() }</td>

								<td>
									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<a href="/userList/edit/${user.id}"
											class="btn btn-outline-primary">Update</a> <a
											href="/userList/${user.id}" class="btn btn-outline-danger">Delete</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</c:when>


			<c:when test="${ sessionScope.user.role == 'customer' }">
				<h1>Here is your Details you can edit or delete your account:</h1>
				<table class="table table-dark">
					<thead>
						<tr>
							<th scope="col">User ID</th>
							<th scope="col">Username</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Password</th>
							<th scope="col">Email</th>
							<th scope="col">Role</th>
							
						</tr>
					</thead>
					<tbody>

						<tr>
							<td>${ sessionScope.user.id }</td>
							<td>${ sessionScope.user.username }</td>
							<td>${ sessionScope.user.fName }</td>
							<td>${ sessionScope.user.lName }</td>
							<td>${ sessionScope.user.password }</td>
							<td>${ sessionScope.user.email }</td>
							<td>${ sessionScope.user.role }</td>

							<td>
								<div class="d-grid gap-2 d-md-flex justify-content-md-end">
									<a href="/userList/edit/${user.id}"
										class="btn btn-outline-primary">Update</a> <a
										href="/userList/${user.id}" class="btn btn-outline-danger">Delete</a>
								</div>
							</td>
						</tr>

					</tbody>
				</table>
				
				<table class="table table-dark">
				<thead>
				<tr>
				<th scope="col">Movie On Rent</th>
				<th scope="col">Start Date</th>
				<th scope="col">Rental Expiry Date</th>
				
				</tr>
				</thead>
				<tbody>
				<tr>
				
				<c:forEach items="${ rented }" var="user">
				<tr>
				<td>${user.movie.name }</td>
				<td>${user.rentalDate }</td>
				<td>${user.expirayDate }</td>
				<tr>
				</c:forEach>
				
							
				</tr>
				</tbody>
				
				</table>

			</c:when>
			<c:otherwise>
				<p>
					Looks like you havent logged in yet. <a href="/login">log in</a>
				</p>
			</c:otherwise>

		</c:choose>

	</div>
</body>
</html>