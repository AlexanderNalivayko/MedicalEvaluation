<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Головна сторінка сервісу</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type="text/javascript" src="jquery-3.2.1.js"></script>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
    <h1>Веб-додаток для оцінки якості сервісу в медичних установах</h1>
    <h2>Будь ласка оберіть медичний заклад який ви відвідували</h2>
    <p>Пошук за областю</p>
    <form action="${pageContext.request.contextPath}/start" method="get" id="form">
        Область:
        <select name="region">
            <option value="${selected}" selected>${selected}</option>
            <c:forEach items="${locations}" var="place">
                <c:if test="${place != selected}">
                    <option value="${place}">${place}</option>
                </c:if>
            </c:forEach>
        </select>
        <input type="submit" value="пошук">
    </form>

    <form action="ev" method="get" id="form1">
        <select name="institutions" size="15" onChange="this.form.submit()" >
            <c:forEach items="${institutions}" var="institution">
                <option value="${institution}" name="${institution}">${institution}</option>
            </c:forEach>
        </select>
    </form>
    <div id="map"></div>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAqcEWR32IhtOz8-lQyf75lliJpYfen118&callback=initMap"
            async defer></script>
</body>
</html>
