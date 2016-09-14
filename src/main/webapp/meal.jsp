<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 13.09.2016
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Adding new meal</title>
</head>
<body>
<form method="post" action='meals'  name="frmAddMeal">
    Id : <input
        type="text" readonly="readonly" name="mealId"
        value="<c:out value="${meal.getId()}" />"/> <br />
    Time of Meal : <input
        type="datetime" name="dateTime"
        value="${meal.setDateTime()}" /> <br />
    Description : <input
        type="text" name="mealDescription"
        value="<c:out value="${meal.getId()}"/>" /> <br />
    Calories : <input
        type="text" name="calories"
        value="${meal.setCalories()}" /> <br />
     <input
        type="submit" value="Submit" />
</form>
</body>
</html>
