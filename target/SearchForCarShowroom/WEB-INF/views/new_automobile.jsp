<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h3>Добавление новой модели автомобиля в базу данных</h3>
<form:form modelAttribute="auto" action="/updateDB">
    <table border = "1">
        <tr>
            <td>Название модели: <form:input path="model"/></td>
        </tr>
        <tr>
            <td>Максимальная мощность: <form:input path="maxPower"/></td>
        </tr>
        <tr>
            <td>Максимальный крутящий момент: <form:input path="maxTorque"/></td>
        </tr>
        <tr>
            <td>Максимальный скорость: <form:input path="maxSpeed"/></td>
        </tr>
        <tr>
            <td>Ускорение: <form:input path="acceleration"/></td>
        </tr>
        <tr>
            <td>Расход топлива: <form:input path="fuelConsumption"/></td>
        </tr>
        <tr>
            <td>Вес автомобиля: <form:input path="weight"/></td>
        </tr>
        <tr>
            <td><td>Комплектация для автомобиля: <form:checkboxes path="carKit" items="${kitsList}"/></td></td>
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