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
<title>Movie List</title>
</head>
<body background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
	<jsp:include page="navbar.jsp" />
	<div class="container">
		
		<c:choose>
			<c:when test="${ sessionScope.user.role == 'admin'  }">
			<h1>Welcome Admin ${ sessionScope.user.username}</h1>
			<h1>List Of Movies</h1>
				<jsp:include page="adminMovieList.jsp" />
			</c:when>
			<c:when test="${ sessionScope.user.role == 'customer'  }">
			<h1>Welcome Customer ${ sessionScope.user.username}</h1>
			<h1>List Of Movies</h1>
				<jsp:include page="customerMovieList.jsp" />
			</c:when>
			<c:otherwise>
			<h1>Welcome ${ sessionScope.user.username}</h1>
			<h1>Please Register or Login to Access the rental system!</h1>
			<h1>List Of Movies</h1>
				<jsp:include page="nonLoggedInUser.jsp" />
			</c:otherwise>

		</c:choose>


	</div>
</body>
</html>