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
<title>Admin Movie List</title>
</head>
<body background="https://images.unsplash.com/photo-1635776062360-af423602aff3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2232&q=80">
	<div class="row">
		<div class="col-lg-3">
			<a href="/addMovie" class="btn btn-primary btn-sm mb-3"> AddMovie</a>
		</div>
	</div>
	<table class="table table-light">
		<thead>
			<tr>
				<th scope="col">Movie name</th>
				<th scope="col">Genre</th>
				<th scope="col">Year</th>
				<th scope="col">Director</th>
				<th scope="col">Rating</th>
				<th scope="col"></th>
				<th scope="col"></th>

			</tr>
		</thead>

		<tbody>
			<c:forEach items="${requestScope.movieList }" var="movie">
				<tr>
					<td>${movie.name}</td>
					<td>${movie.genre}</td>
					<td>${movie.year}</td>
					<td>${movie.director}</td>
					<td>${movie.rating}</td>

					<td>
						<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							<a href="/movieList/edit/${movie.id}"
								class="btn btn-outline-primary">Update</a> <a
								href="/movieList/${movie.id}" class="btn btn-outline-danger">Delete</a>
						</div>
					</td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>