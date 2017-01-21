<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Oddle Weather App - Home Page</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<h2>Oddle Weather App - Home Page</h2>

	<div id="wrapper">
		<div id="header">
			<h2>Oddle's Weather Logs</h2>
		</div>
	</div>
	<table>
		<tr>
			<td><input type="button" value="Search Current Weather"
				onClick="window.location.href='searchCurrent'; return false;"
				class="search-button" /></td>
			<td><input type="button" value="Search 5 Day Forecast"
				onClick="window.location.href='searchForecast'; return false;"
				class="search-button" /></td>
		</tr>
	</table>
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

				<c:forEach var="tempWeather" items="${weather}">
					<c:url var="deleteLink" value="/weather/delete">
						<c:param name="weatherId" value="${tempWeather.id}" />
					</c:url>

					<tr>
						<td>${tempWeather.city}</td>
						<td>${tempWeather.date}</td>
						<td>${tempWeather.temperature}</td>
						<td>${tempWeather.humidity}</td>
						<td>${tempWeather.pressure}</td>
						<td>${tempWeather.windSpeed}</td>
						<td><a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this log?'))) return false">Delete</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
	<br>
</body>
</html>

