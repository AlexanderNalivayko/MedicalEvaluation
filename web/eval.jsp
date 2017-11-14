<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.11.2017
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оцінка</title>
</head>
<body>
    <h1>Веб-додаток для оцінки якості сервісу в медичних установах</h1>
    <h2>Будь ласка оцініть: ${institution}</h2>

    <form action="ev" method="get" id="form2">
        <table>
            <tr>
                <td>
                    Загальне враження
                </td>
                <td>
                    <input name="i1" type="radio">Добре
                    <input name="i1" type="radio">Погано
                </td>
            </tr>

            <tr>
                <td>
        Дотримання правил асептики і антисептики
                </td>
                <td>
        <input name="i2" type="radio">Добре
        <input name="i2" type="radio">Погано
                </td>
            </tr>

            <tr>
                <td>
        Наявність необхідних вам лікарів
                </td>
            <td>
        <input name="i3" type="radio">Добре
        <input name="i3" type="radio">Погано
            </td>
            </tr>

            <tr>
                <td>
        Наявність матеріалів та засобів
                </td>
            <td>
        <input name="i4" type="radio">Добре
        <input name="i4" type="radio">Погано
            </td>
            </tr>

            <tr>
                <td>
        Наявність обладнання
                </td>
            <td>
        <input name="i5" type="radio">Добре
        <input name="i5" type="radio">Погано
            </td>
            </tr>

            <tr>
                <td>
        Час очікування
                </td>
            <td>
        <input name="i6" type="radio">Добре
        <input name="i6" type="radio">Погано
            </td>
            </tr>

            <tr>
                <td>
        Вічливість персоналу
                </td>
            <td>
        <input name="i7" type="radio">Добре
        <input name="i7" type="radio">Погано
            </td>
            </tr>
            <tr>
            <td>
        <textarea name="comment" rows="4"></textarea>
        <input name="sub" type="submit" size="">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
