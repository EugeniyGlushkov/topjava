<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<br/>

<table cellspacing="0" border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
    </tr>
    <c:set var="red" value="red"/>
    <c:set var="green" value="green"/>
    <c:forEach items="${mealsList}" var="meal">
        <tr bgcolor=${meal.exceed ? red : green}>
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?act=del&val=${meal.ID}">del</a></td>
        </tr >
    </c:forEach>
</table>

<br/>
<br/>

<form method="post">
    Date
    <input type="datetime-local" name="datetime">
    <br/>
    Description
    <input type="text" name="description">
    <br/>
    Calories
    <input type="text" name="calories">
    <br/>
    <input type="submit" value="Add">
</form>
</body>
</html>
