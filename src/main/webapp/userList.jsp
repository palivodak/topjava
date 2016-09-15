<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<a href="/meal.jsp?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a>
<h2>User list</h2>
</body>
</html>
