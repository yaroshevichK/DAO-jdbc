<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">
<html>
<head>
    <title>DAO project</title>
</head>
<body>
<h3>Список людей</h3>
<br>

<c:if test="${people.size()==0}">
    <h2>Список пуст</h2>
</c:if>

<a href="${pageContext.request.contextPath}/people?command=save" class="buttonClass">ADD PERSON</a>
<a href="${pageContext.request.contextPath}/people?command=deleteAll" class="buttonClass">DELETE ALL RECORDS</a>
<br>
<br>

<c:if test="${people.size()>0}">
    <table class="main-table">
        <tr>
            <th>№</th>
            <th>name</th>
            <th>surname</th>
            <th>gender</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach var="person" items="${people}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${person.name}</td>
                <td>${person.surname}</td>
                <td>${person.gender}</td>
                <td>
                    <form action="people" method="get">
                        <input type="hidden" name="command" value="update">
                        <input type="hidden" name="id" value="${person.id}">
                        <button type="submit" class="buttonClass">EDIT</button>
                    </form>
                </td>
                <td>
                    <form action="people" method="get">
                        <input type="hidden" name="command" value="delete">
                        <input type="hidden" name="id" value="${person.id}">
                        <button type="submit" class="buttonClass">DELETE</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</c:if>

</body>
</html>
