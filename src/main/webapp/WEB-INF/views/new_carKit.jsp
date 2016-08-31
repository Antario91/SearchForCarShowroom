<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Добавление новой комплектации автомобиля в базу данных</h3>
<form:form modelAttribute="carKit" action="/updateDBWithNewCarKit" method="POST">
    <table>
        <tr>
            <td>Тонировка стекл: <form:checkbox path="windowTinting"/></td>
        </tr>
        <tr>
            <td>Легкосплавне диски: <form:checkbox path="alloyWheels"/></td>
        </tr>
        <tr>
            <td>Иммобилайзер: <form:checkbox path="immobiliser"/></td>
        </tr>
        <tr>
            <td>Радиоподготовка: <form:checkbox path="radioEquipment"/></td>
        </tr>
        <tr>
            <td>Круиз-контроль: <form:checkbox path="cruiseControl"/></td>
        </tr>
        <tr>
            <td>Цена: <form:input path="cost"/></td>
        </tr>
        <tr>
            <td>Модель автомобиля: <form:select path="auto">
                                      <form:options items="${autosList}" itemValue="model" itemLabel="model"/></td>
                                    </form:select>
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