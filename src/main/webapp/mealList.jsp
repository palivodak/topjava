
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Meal List</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

    <table border=1>
    <thead>
    <tr>
        <th>Id</th>
    <th>Time</th>
    <th>Description</th>
    <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>

        <c:forEach items="${meals}" var="meal">
            <c:set var="colour" value="${meal.isExceed()}"/>
            <c:if test="${colour==true}">
            <tr style="color:red">
                <td>${meal.getId()}</td>
            <td>${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </tr>
            </c:if>
            <c:if test="${colour==false}">
                <tr style="color:green">
                    <td>${meal.getId()}</td>
                    <td>${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}</td>
                    <td>${meal.getDescription()}</td>
                    <td>${meal.getCalories()}</td>
                    <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
                    <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
                </tr>
            </c:if>

        </c:forEach>
    </tbody>
        </table>
<a href="meals?action=add">Add</a>
</body>
</html>