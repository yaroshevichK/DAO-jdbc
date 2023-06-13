<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">
<html>
<head>
    <title>DAO project</title>
</head>
<body>
<h3>Добавить данные человека</h3>

<form action="people">
    <p>
        <input type="hidden" name="id" value="${requestScope.id}">
        <label for="name">Введите имя:</label>
        <input name="name" id="name" placeholder="name" value="${requestScope.name}">
    </p>
    <p>
        <label for="surname">Введите фамилию:</label>
        <input name="surname" id="surname" placeholder="surname" value="${requestScope.surname}">
    </p>

    <p>
        Пол:
        <c:choose>
            <c:when test="${requestScope.gender == 'MALE'}">
                <input type="radio" name="gender" value="MALE" checked>male (муж.)
                <input type="radio" name="gender" value="FEMALE">female (жен.)
            </c:when>
            <c:when test="${requestScope.gender == 'FEMALE'}">
                <input type="radio" name="gender" value="MALE">male (муж.)
                <input type="radio" name="gender" value="FEMALE" checked>female (жен.)
            </c:when>
            <c:otherwise>
                <input type="radio" name="gender" value="MALE">male (муж.)
                <input type="radio" name="gender" value="FEMALE">female (жен.)
            </c:otherwise>
        </c:choose>
    </p>

    <p>
        <button type="submit" name="command" value="update" class="buttonClass" formmethod="post">UPDATE</button>
        <button type="submit" name="command" value="getAll" class="buttonClass" formmethod="get">CANCEL</button>
    </p>
</form>

</body>
</html>
