<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Добавление нового автосалона в базу данных</h3>
<form action="/updateDBWithNewCarShowroom" method="POST">
    <table>
        <tr>
            <td>Название автосалона: <input name="nameOfCarShowroom" type="text"/></td>
        </tr>
        <tr>
            <td>Формирование адреса для автосалона, страна: <input name="countryOfCarShowroom" type="text"/></td>
        </tr>
        <tr>
            <td>Формирование адреса для автосалона, город: <input name="cityOfCarShowroom" type="text"/></td>
        </tr>
        <tr>
            <td>Формирование адреса для автосалона, улица: <input name="streetOfCarShowroom" type="text"/></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Добавить в БД"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>