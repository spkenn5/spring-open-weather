<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Oddle Weather App - Search City</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<h2>Oddle Weather App - Search for City's Weather Report</h2>
	<br>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>City</th>
					<th>Date</th>
					<th>Temperature</th>
					<th>Humidity</th>
					<th>Pressure</th>
					<th>Wind Speed</th>
					<th>Action</th>
				</tr>

				<c:forEach var="weather" items="${weatherList}">
					<c:url var="addLink" value="/weather/add">
						<c:param name="weatherObj"
							value="${weather.city},${weather.date},${weather.temperature},${weather.humidity},${weather.pressure},${weather.windSpeed}" />
					</c:url>
					<tr>
						<td>${weather.city}</td>
						<td>${weather.date}</td>
						<td>${weather.temperature}°</td>
						<td>${weather.humidity}%</td>
						<td>${weather.pressure}hpa</td>
						<td>${weather.windSpeed}m/s</td>
						<td><a href="${addLink}"
						onclick="if(!(confirm('Are you sure you want to log this data?'))) return false">Add</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
	<p>
		<a href="${pageContext.request.contextPath}/weather/list">Back to
			List</a>
	</p>
</body>
</html>

