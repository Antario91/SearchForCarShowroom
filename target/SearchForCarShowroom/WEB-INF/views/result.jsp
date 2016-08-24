<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
searchObject.description: ${searchObject.getDescription ()}
<br>

<c:forEach var="kit" items="${searchingResults}">
    <table border = "1">
        <tr>
            <td>Модель:</td> <td>${kit.getAutomobile().model}</td>
        </tr>
        <tr>
            <td>Цена:</td> <td>${kit.cost}</td>
        </tr>
        <tr>
            <td colspan = "2">Характеристики комплектации:</td>
        </tr>
        <tr>
            <td>Тонировка стекла:</td> <td>${kit.windowTinting}</td>
        </tr>
        <tr>
            <td>Легкосплавные диски:</td> <td>${kit.alloyWheels}</td>
        </tr>
        <tr>
            <td>Immobiliser:</td> <td>${kit.immobiliser}</td>
        </tr>
        <tr>
            <td>Радиоподготовка:</td> <td>${kit.radioEquipment}</td>
        </tr>
        <tr>
            <td>Круиз-контроль:</td> <td>${kit.cruiseControl}</td>
        </tr>
    </table>
    <br>
</c:forEach>
</body>
</html>
