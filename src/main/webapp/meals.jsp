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
            <td><form name="del" method="post">
                <input type="text" name="action" value="del" hidden>
                <input type="text" name="ID" value="${meal.ID}" hidden>
                <input type="submit" value="Delete">
                </form>
            </td>
            <td><form name="edit" method="post">
                <input type="text" name="action" value="edit" hidden>
                <input type="text" name="ID" value="${meal.ID}" hidden>
                <input type="submit" value="Edit">
                </form>
            </td>
        </tr >
    </c:forEach>
</table>

<br/>

<h3>${action}</h3>

<form method="post">
    <input type="text" name="ID" value="${currentid}" hidden>
    <input type="text" name="action" value="add" hidden>
    Date
    <input type="datetime-local" name="datetime">
    <br/>
    Description
    <input type="text" name="description">
    <br/>
    Calories
    <input type="text" name="calories">
    <br/>
    <input type="submit" value="${action}">
</form>
</body>
</html>
