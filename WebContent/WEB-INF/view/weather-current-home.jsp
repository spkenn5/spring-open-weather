<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Oddle Weather App - Search City</title>
</head>
<body>
	<h2>Oddle Weather App - Search for City's Weather Report</h2>
	<br>
	<br> Welcome, Kenji!
	<br>
	<br>
	<p>
	<form action="getCurrentWeather" method="GET">
		<input type="text" name="cityName" placeholder="City's Weather" />		
		<input type="submit" />
	</form>
	<p>
	<a href="${pageContext.request.contextPath}/weather/list">Back to List</a> 
	</p>
</body>
</html>

