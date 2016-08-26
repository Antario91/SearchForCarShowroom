<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Добавление новой модели автомобиля в базу данных</h3>
<form:form modelAttribute="auto" action="/updateDB" method="GET">
    <table border = "1">
        <tr>
            <td>Название модели: <form:input path="model"/></td>
        </tr>
        <tr>
            <td>Максимальная мощность (л.с): <form:input path="maxPower"/></td>
        </tr>
        <tr>
            <td>Максимальный крутящий момент (Н*м): <form:input path="maxTorque"/></td>
        </tr>
        <tr>
            <td>Максимальный скорость (км/ч): <form:input path="maxSpeed"/></td>
        </tr>
        <tr>
            <td>Ускорение 0-100 км/ч(с): <form:input path="acceleration"/></td>
        </tr>
        <tr>
            <td>Расход топлива (л/100 км): <form:input path="fuelConsumption"/></td>
        </tr>
        <tr>
            <td>Вес автомобиля (кг): <form:input path="weight"/></td>
        </tr>
        <tr>
            <td>Комплектация для автомобиля: <c:forEach var="kit" items="${kitsList}">
                                                     <p><form:checkbox path="carKit" value="${kit.id}" label="${kit.toString()}"/></p>
                                                 </c:forEach>
            </td>
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