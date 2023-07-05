<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Add Movie</title>
</head>
<body background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
<jsp:include page="navbar.jsp" />
<div class="container">
<h1>Add a Movie</h1>
	<div class="card">
	<div class="card-body">
	<sf:form action="add" method="POST" modelAttribute="movie">

	<div class="form-group row">
	<sf:label path="name" for="name" class="col-sm-2 col-form-label">Movie Name</sf:label>
	<div class="col-sm-7">
	<sf:input type="text" class="form-control" path="name" placeholder="Enter Movie Name" />
	<sf:errors path="name" cssClass="error" />
	</div>
	</div>
	<div class="form-group row">
	<sf:label path="genre" for="genre" class="col-sm-2 col-form-label">Movie Genre</sf:label>
	<div class="col-sm-7">
	<sf:input type="text" class="form-control" path="genre" placeholder="Enter Movie Genre" />
	<sf:errors path="genre" cssClass="error" />
	</div>
	</div>
	<div class="form-group row">
	<sf:label path="year" for="year" class="col-sm-2 col-form-label">Year</sf:label>
	<div class="col-sm-7">
	<sf:input type="text" class="form-control" path="year" placeholder="Enter Year" />
	<sf:errors path="year" cssClass="error" />
	</div>
	</div>
	
	<div class="form-group row">
	<sf:label path="director" for="director" class="col-sm-2 col-form-label">Director</sf:label>
	<div class="col-sm-7">
	<sf:input type="director" class="form-control" path="director" placeholder="Enter Year" />
	<sf:errors path="director" cssClass="error" />
	</div>
	</div>
	
	<div class="form-group row">
	<sf:label path="rating" for="rating" class="form-label">Rating</sf:label>
	<div class="col-sm-7">
	<sf:input type="range" path="rating" for="rating" class="form-range" min="0" max="10" id="rating"/>
	</div>
	</div>
	<button type="submit" class="btn btn-primary">Add</button>
	</sf:form>
	
	</div>
	</div>
	
</div>
</body>
</html>