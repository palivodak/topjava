<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 13.09.2016
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Adding new meal</title>
</head>
<body>
<h2>Add or insert user</h2>

<form method="post" action='meals'  name="frmAddMeal">
    Id : <input
        type="text" readonly="readonly" name="mealId"
        value="<c:out value="${meal.getId()}"/>"/> <br />
    Time of Meal : <input
        type="datetime" name="dateTime"
        value="${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}" /> <br />
    Description : <input
        type="text" name="mealDescription"
        value="<c:out value="${meal.getDescription()}"/>"/> <br />
    Calories : <input
        type="text" name="calories"
        value="${meal.getCalories()}" /> <br />
     <input
        type="submit" value="Submit" />
</form>
<h2><a href="meals?action=mealsList">Meals List</a></h2>
</body>
</html>
