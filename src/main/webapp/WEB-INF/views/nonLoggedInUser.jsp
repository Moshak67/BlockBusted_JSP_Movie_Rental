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
<body>

<table class="table table-dark">
		<thead>
			<tr>
				<th scope="col">Movie name</th>
				<th scope="col">Genre</th>
				<th scope="col">Year</th>
				<th scope="col">Director</th>
				<th scope="col">Rating</th>

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


				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>