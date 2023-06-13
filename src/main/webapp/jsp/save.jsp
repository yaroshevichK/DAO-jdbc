<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">
<html>
<head>
    <title>DAO project</title>
</head>
<body>
<h3>Добавить данные человека</h3>

<form action="people" method="post">
    <p>
        <label for="name">Введите имя:</label>
        <input name="name" id="name" placeholder="name">
    </p>
    <p>
        <label for="surname">Введите фамилию:</label>
        <input name="surname" id="surname" placeholder="surname">
    </p>

    <p>
        Пол:
        <input type="radio" name="gender" value="MALE">male (муж.)
        <input type="radio" name="gender" value="FEMALE">female (жен.)
    </p>

    <p>
        <button type="submit" name="command" value="save" class="buttonClass" formmethod="post">SAVE</button>
        <button type="submit" name="command" value="getAll" class="buttonClass">CANCEL</button>
    </p>
</form>

</body>
</html>
