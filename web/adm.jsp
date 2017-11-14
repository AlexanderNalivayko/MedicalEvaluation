<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сторінка адміністратора</title>
    <script type="text/javascript" src="jquery-3.2.1.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
Редагування критеріїв оцінки.
<p class="results">Повідомлення: </p>
<c:forEach var="column_i" begin="0" end="${index-1}">
    <br>
    <div id="${columns[column_i]}">
            ${column_i+1}.
            ${columns[column_i]}
        <button value="${columns[column_i]}" id="${columns[column_i]}" name="delete">видалити</button>
        <br>
        питання для користувача:
        <input type="text" id="${columnTitles[column_i]}" placeholder="${columnTitles[column_i]}" value=""/>
        <button value="${columnTitles[column_i]}" name="update">змінити</button>
    </div>
</c:forEach>
<br>
<hr>
    <div>
        Додати новий критерій
        <br>
        <br>
        Назва в базі (одним словом, англійською мовою):
        <input type="text" id="columnName"/>
        <br>
        Питання яке буде відображено користувачу:
        <input type="text" id="columnTitle"/>
        <br>
        <button name="addNew">Додати</button>
</div>
</body>
</html>
